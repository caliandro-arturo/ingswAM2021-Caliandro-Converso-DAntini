package it.polimi.ingsw.server;

import it.polimi.ingsw.commonFiles.utility.JSONReader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {
    private final int port;
    private final List<VirtualView> virtualViews = new ArrayList<>();
    private VirtualView currentView;
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public ServerMain(int port) {
        this.port = port;
    }

    public List<VirtualView> getVirtualViews() {
        return virtualViews;
    }

    /**
     * Creates the server socket, accepts new sockets and submits the client connection
     * to a free thread.
     */
    public void startServer() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Server ready");
        /*main loop: accepting new sockets and verifying that there's space in the game for other players;
         * otherwise, creating new games. */
        Socket socket;
        do {
            System.out.println("Accepting...");
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            try {
                executor.submit(new ClientHandler(this, socket, findFirstFreeVirtualView(socket)));
            } catch (IOException e) {
                try {
                    socket.close();
                } catch (IOException ignore) {
                }
            }
            System.out.println("Accepted "
                    + socket.getInetAddress().getCanonicalHostName()
                    + " at port "
                    + socket.getPort() + ".");
        } while (!serverSocket.isClosed());
        executor.shutdown();
    }

    /**
     * Ensures that there's a free virtual view in which to add new clients. If a new virtual view is created, assigns
     * the socket provided as parameter to the firstPlayer field.
     *
     * @param socket the client socket that eventually is used to set the first player of the game
     */
    public synchronized VirtualView findFirstFreeVirtualView(Socket socket) {
        return virtualViews.stream()
                .filter(v -> !v.isFull())
                .findFirst()
                .orElseGet(() -> {
                    currentView = new VirtualView(this, socket);
                    virtualViews.add(currentView);
                    return currentView;
                });
    }

    public synchronized void reinsertToTheFirstFreeVirtualView(ClientHandler clientHandler) {
        findFirstFreeVirtualView(clientHandler.getSocket()).addToWaitingList(clientHandler);
    }

    public static void main(String[] args) {
        ServerMain server = null;
        Map<String, String> file = null;
        try {
            server = new ServerMain(Integer.parseInt(args[0]));
        } catch (ArrayIndexOutOfBoundsException e) {
            try {
                file = JSONReader.readMap("serverConfig.json");
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                System.exit(1);
            } catch (NullPointerException ex) {
                System.err.println("Cannot read file serverConfig.json.");
                System.exit(1);
            }
            server = new ServerMain(Integer.parseInt(file.get("serverPort")));
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        server.startServer();
    }
}