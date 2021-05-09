package it.polimi.ingsw.client;

import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.messages.toClient.Ok;
import it.polimi.ingsw.messages.toClient.ToClientMessage;
import it.polimi.ingsw.network.SocketManager;

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
    public void readMessage(Message message) {
        if (message instanceof Ok)
            controller.confirmMove(((Ok) message).getId());
        else controller.readMessage((ToClientMessage) message);
    }

    public void setController(ClientController controller) {
        this.controller = controller;
    }
}
