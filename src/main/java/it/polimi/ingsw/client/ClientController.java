package it.polimi.ingsw.client;

import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.messages.toClient.ClientMessageVisitor;
import it.polimi.ingsw.messages.toClient.ToClientMessage;
import it.polimi.ingsw.messages.toClient.updates.GameUpdate;

import java.io.IOException;

/**
 * Controller element of the MVC architectural pattern applied to the client.
 */
public class ClientController {
    private final View view;
    private ClientModel model;
    private final SocketManager socketManager;
    private final ClientMessageVisitor clientMessageVisitor = new ClientMessageVisitor(this);
    private final UpdateHandler updateHandler;

    public ClientController(View view, SocketManager socketManager) {
        this.view = view;
        view.setController(this);
        this.socketManager = socketManager;
        socketManager.setController(this);
        this.model = new ClientModel();
        updateHandler = new UpdateHandler(this, model);
    }

    /**
     * Reads
     *
     * @param msg
     */
    public synchronized void readMessage(ToClientMessage msg) {
        msg.accept(clientMessageVisitor);
    }

    public synchronized void sendMessage(Message message) {
        try {
            socketManager.sendMessage(message);
        } catch (IOException e) {
            showError("Connection error.");
        }
    }

    public void show(String element) {
        view.show(element);
    }

    public void showError(String error) {
        view.showError(error);
    }

    public void showUpdate(String update) {
        view.showUpdate(update);
    }

    public void manageUpdate(GameUpdate msg) {
        msg.accept(updateHandler);
    }
}
