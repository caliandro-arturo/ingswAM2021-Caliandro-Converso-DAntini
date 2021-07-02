package it.polimi.ingsw.client;

import it.polimi.ingsw.commonFiles.network.SocketManager;
import it.polimi.ingsw.commonFiles.utility.JSONReader;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * Interface that represents the user interface. It also contains network handling methods.
 */
public abstract class UI {
    private View view;
    private ClientSocketManager socketManager;
    private Thread messageReader;
    private boolean isConnected = false;

    public View getView() {
        return view;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setView(View view) {
        this.view = view;
    }

    public abstract void run();

    /**
     * Configures the connection with the server.
     * @param hostNameAndPort host name and the port of the server
     */
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
        show("Connecting to " + hostName + " at port " + portNumber + "...");
        try {
            connectToServer(hostName, portNumber);
        } catch (UnknownHostException e) {
            showError("Don't know about host " + hostName);
            return;
        } catch (IOException e) {
            showError("Couldn't get I/O for the connection to " + hostName);
            return;
        }
        isConnected = true;
    }

    /**
     * Starts a connection to the server.
     * @param hostName the host name of the server
     * @param port the port
     * @throws IOException if the connection fails
     */
    public void connectToServer(String hostName, int port) throws IOException {
        socketManager = new ClientSocketManager(new Socket(hostName, port));
        show("Connected successfully.");
    }

    /**
     * Initializes the controller and the message reception.
     */
    public void initializeClient() {
        new ClientController(view, socketManager);
        messageReader = new Thread(() -> {
            try {
                socketManager.receiveMessages();
            } catch (SocketTimeoutException e) {
                isConnected = false;
            } catch (SocketException e) {
                showUpdate("connectionlost");
                isConnected = false;
            }
        });
        messageReader.start();
    }

    /**
     * Shows a connection error.
     * @param error the error to show
     */
    public void showError(String error) {
        view.showError(error);
    }

    /**
     * Shows an element.
     * @param toShow element to show
     */
    public void show(String toShow) {
        view.show(toShow);
    }

    /**
     * Shows an update.
     * @param update update to show
     */
    public void showUpdate(String... update) {
        view.showUpdate(update);
    }
}