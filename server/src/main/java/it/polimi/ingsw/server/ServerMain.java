package it.polimi.ingsw.server;

import it.polimi.ingsw.commonFiles.utility.JSONReader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {
    private final int port;
    private final List<VirtualView> virtualViews = new ArrayList<>();
    private ConcurrentHashMap<String, Boolean> connectedClients = new ConcurrentHashMap<>();
    private HashMap<String, VirtualView> clientToLobbyMap = new HashMap<>();
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public ServerMain(int port) {
        this.port = port;
    }

    public ConcurrentHashMap<String, Boolean> getConnectedClients() {
        return connectedClients;
    }

    public HashMap<String, VirtualView> getClientToLobbyMap() {
        return clientToLobbyMap;
    }

    public Optional<VirtualView> getVirtualView(String name) {
        return virtualViews.stream().filter(v -> v.getName().equals(name)).findAny();
    }

    public List<VirtualView> getFreeVirtualViews() {
        return virtualViews.stream().filter(v -> !v.isFull()).toList();
    }

    public void addVirtualView(VirtualView view) {
        virtualViews.add(view);
    }

    public void removeVirtualView(VirtualView view) {
        clientToLobbyMap.forEach((c, v) -> {
            if (v.equals(view)) clientToLobbyMap.replace(c, null);
        });
        virtualViews.remove(view);
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
            System.out.println("Cannot start the server: " + e.getMessage());
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
                ClientHandler client = new ClientHandler(this, socket);
                executor.submit(client);
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