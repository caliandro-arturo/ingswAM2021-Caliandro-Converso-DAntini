package it.polimi.ingsw.client;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ToClientMessage;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.GameUpdate;
import it.polimi.ingsw.commonFiles.messages.toServer.ToServerMessage;

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
    private final ClientUpdateHandler updateHandler;
    private final HashMap<Integer, ToServerMessage> messageBuffer = new HashMap<>();

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

    public synchronized void sendMessage(ToServerMessage message) {
        message.setPlayer(model.getPlayerUsername());
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

    public void manageUpdate(ToServerMessage msg) {
        msg.accept(updateHandler);
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
