package it.polimi.ingsw.client;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ToClientMessage;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.GameUpdate;
import it.polimi.ingsw.commonFiles.messages.toServer.ToServerMessage;

import java.io.IOException;

/**
 * Controller element of the MVC architectural pattern applied to the client.
 */
public class ClientController {
    private final View view;
    private final ClientModel model = new ClientModel();
    private final ClientSocketManager socketManager;
    private final ClientMessageVisitor clientMessageVisitor = new ClientMessageVisitor(this);
    private final ClientUpdateHandler updateHandler;

    public ClientController(View view, ClientSocketManager socketManager) {
        this.view = view;
        view.setController(this);
        this.socketManager = socketManager;
        socketManager.setController(this);
        updateHandler = new ClientUpdateHandler(this, model);
    }


    public View getView() {
        return view;
    }

    public ClientModel getModel() {
        return model;
    }

    /**
     * Reads a message.
     *
     * @param msg the message to read
     */
    public synchronized void readMessage(Message msg) {
        if (msg instanceof ToClientMessage)
            ((ToClientMessage) msg).accept(clientMessageVisitor);
        else if (msg instanceof ToServerMessage)
            manageUpdate((ToServerMessage) msg);
    }

    /**
     * Sends a message to the server.
     * @param message the message to send
     */
    public synchronized void sendMessage(ToServerMessage message) {
        message.setPlayer(model.getPlayerUsername());
        try {
            socketManager.sendMessage((Message) message);
        } catch (IOException e) {
            showError("Connection error.");
        }
    }

    /**
     * Shows an element.
     * @param element the element to show
     */
    public void show(String element) {
        view.show(element);
    }

    /**
     * Shows an error.
     * @param error the error to show
     */
    public void showError(String error) {
        view.showError(error);
    }

    /**
     * Shows an update.
     * @param update the update to show
     */
    public void showUpdate(String... update) {
        view.showUpdate(update);
    }

    /**
     * Manages an update.
     * @param msg the update message
     */
    public void manageUpdate(ToServerMessage msg) {
        msg.accept(updateHandler);
    }

    /**
     * Manages an update.
     * @param msg the update message
     */
    public void manageUpdate(GameUpdate msg) {
        msg.accept(updateHandler);
    }
}
