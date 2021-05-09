package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.client.ClientController;
import it.polimi.ingsw.messages.toClient.updates.GameUpdate;

/**
 * Reads messages from server and updates the client version of the model, or notifies the player.
 * It implements the visitor element of the visitor pattern.
 */
public class ClientMessageVisitor {
    private final ClientController controller;

    public ClientMessageVisitor(ClientController controller) {
        this.controller = controller;
    }

    public void visit(AskNickname msg) {
        controller.show("asknickname");
    }

    public void visit(CreateGame msg) {
        controller.show("creategame");
    }

    public void visit(ErrorMessage msg) {
        controller.getFromBuffer(msg.getId());
        controller.showError(msg.getError());
    }

    public void visit(WaitGameCreation msg) {
        controller.show("waitgamecreation");
    }

    public void visit(GameUpdate msg) {
        controller.manageUpdate(msg);
    }

    public void visit(Ok msg) {
        controller.confirmMove(msg.getId());
    }

    public void visit(GameIsFull gameIsFull) {
        controller.showError("The game is full. Reconnecting to another game...");
    }

    public void visit(TablePosition tablePosition) {

    }
}