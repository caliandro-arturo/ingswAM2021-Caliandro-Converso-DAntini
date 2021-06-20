package it.polimi.ingsw.client;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.network.SocketManager;

import java.io.IOException;
import java.net.Socket;

/**
 * Manages sending and receiving of messages with the server.
 */
public class ClientSocketManager extends SocketManager {
    private ClientController controller;

    public ClientSocketManager(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    public synchronized void readMessage(Message message) {
        controller.readMessage(message);
    }

    public void setController(ClientController controller) {
        this.controller = controller;
    }
}
