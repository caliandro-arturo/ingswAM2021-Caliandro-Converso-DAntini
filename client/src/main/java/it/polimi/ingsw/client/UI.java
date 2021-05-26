package it.polimi.ingsw.client;

import it.polimi.ingsw.commonFiles.utility.JSONReader;
import it.polimi.ingsw.commonFiles.network.SocketManager;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * Interface that represents user interface.
 */
public abstract class UI {
    private View view;
    private ClientSocketManager socketManager;
    private Thread messageReader;
    private boolean isConnected;

    public View getView() {
        return view;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setSocketManager(ClientSocketManager socketManager) {
        this.socketManager = socketManager;
    }

    public abstract void run();

    public void configureConnection(String hostNameAndPort) {
        String hostName;
        int portNumber;
        if (hostNameAndPort.equals("")) {
            Map<String, String> socketId;
            try {
                socketId = JSONReader.readMap("clientConfig.json");
            } catch (IOException e) {
                showError("Config file not found.");
                return;
            }
            hostName = socketId.get("serverName");
            portNumber = Integer.parseInt(socketId.get("serverPort"));
        } else {
            String[] parser = hostNameAndPort.trim().split("\\s*:\\s*", 2);
            try {
                hostName = parser[0];
                portNumber = Integer.parseInt(parser[1]);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                showError("Wrong parameter: you must insert hostname:port.");
                return;
            }
        }
        System.out.println("Connecting to " + hostName + " at port " + portNumber + "...");
        try {
            connectToServer(hostName, portNumber);
            isConnected = true;
        } catch (UnknownHostException e) {
            showError("Don't know about host " + hostName);
        } catch (IOException e) {
            showError("Couldn't get I/O for the connection to " + hostName);
        }
    }

    public void connectToServer(String hostName, int port) throws IOException {
        socketManager = new ClientSocketManager(new Socket(hostName, port));
        show("Connected successfully.");
    }

    public void initializeClient() {
        new ClientController(view, socketManager);
        messageReader = new Thread(() -> {
            try {
                socketManager.receiveMessages();
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
            } catch (SocketException e) {
                showError("Server closed :(");
                System.exit(1);
            }
        });
        messageReader.start();
    }

    public void shutdown() {
        messageReader.interrupt();
        socketManager.shutdown();
    }

    public void showError(String error) {
        view.showError(error);
    }
    public void show(String toShow) {
        view.show(toShow);
    }

    public SocketManager getSocketManager() {
        return socketManager;
    }
}
