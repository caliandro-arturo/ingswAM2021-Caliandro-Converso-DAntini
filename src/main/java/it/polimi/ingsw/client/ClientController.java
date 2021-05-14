package it.polimi.ingsw.client;

import it.polimi.ingsw.common_files.message.Message;
import it.polimi.ingsw.common_files.message.toClient.ClientMessageVisitor;
import it.polimi.ingsw.common_files.message.toClient.ToClientMessage;
import it.polimi.ingsw.common_files.message.toClient.updates.GameUpdate;
import it.polimi.ingsw.common_files.message.toServer.ToServerMessage;

import java.io.IOException;
import java.util.HashMap;

/**
 * Controller element of the MVC architectural pattern applied to the client.
 */
public class ClientController {
    private final View view;
    private final ClientModel model = new ClientModel();
    private final ClientSocketManager socketManager;
    private final ClientMessageVisitor clientMessageVisitor = new ClientMessageVisitor(this);
    private final UpdateHandler updateHandler;
    private final HashMap<Integer, ToServerMessage> messageBuffer = new HashMap<>();

    public ClientController(View view, ClientSocketManager socketManager) {
        this.view = view;
        view.setController(this);
        this.socketManager = socketManager;
        socketManager.setController(this);
        updateHandler = new UpdateHandler(this, model);
    }

    public ClientModel getModel() {
        return model;
    }

    /**
     * Calls {@link ToClientMessage#accept(ClientMessageVisitor)}
     *
     * @param msg the message to read
     */
    public synchronized void readMessage(ToClientMessage msg) {
        msg.accept(clientMessageVisitor);
    }

    public synchronized void sendMessage(ToServerMessage message) {
        addToConfirm(message);
        try {
            socketManager.sendMessage((Message) message);
        } catch (IOException e) {
            showError("Connection error.");
            messageBuffer.remove(message.hashCode());
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

    public void confirmMove(int id) {
        getFromBuffer(id).accept(updateHandler);
    }

    public void addToConfirm(ToServerMessage msg) {
        messageBuffer.put(msg.hashCode(), msg);
    }

    public ToServerMessage getFromBuffer(int id) {
        return messageBuffer.remove(id);
    }
}
