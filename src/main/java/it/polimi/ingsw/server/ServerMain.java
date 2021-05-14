package it.polimi.ingsw.server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {
    private final int port;
    private final List<VirtualView> virtualViews = new ArrayList<>();
    private VirtualView currentView;
    private final List<Socket> connectedClients = new ArrayList<>();
    private final ExecutorService executor = Executors.newCachedThreadPool();
    public static final int timeout = 20000;

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
        while (true) {
            System.out.println("Accepting...");
            try {
                Socket socket = serverSocket.accept();
                socket.setSoTimeout(timeout);
                connectedClients.add(socket);
                System.out.println("Accepted "
                        + socket.getInetAddress().getHostName()
                        + " at port "
                        + socket.getPort() + ".");
                try {
                    executor.submit(new ClientHandler(this, socket, findFirstFreeVirtualView(socket)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        try {
            server = new ServerMain(Integer.parseInt(args[0]));
        } catch (ArrayIndexOutOfBoundsException e) {
            try (Reader reader = new InputStreamReader(
                    Objects.requireNonNull(ServerMain.class
                            .getClassLoader()
                            .getResourceAsStream("serverConfig.json")
                    )
            )) {
                Gson gson = new Gson();
                Type serverPort = new TypeToken<Map<String, Integer>>() {
                }.getType();
                server = new ServerMain((int) ((Map) gson.fromJson(reader, serverPort)).get("serverPort"));
            } catch (IOException ex) {
                e.printStackTrace();
                System.exit(1);
            } catch (NullPointerException ex) {
                System.err.println("Cannot read file serverConfig.json.");
                System.exit(1);
            }
        }
        server.startServer();
    }
}