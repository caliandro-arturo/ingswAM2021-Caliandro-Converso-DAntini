package it.polimi.ingsw.client;

import it.polimi.ingsw.commonFiles.messages.toClient.*;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.GameUpdate;

/**
 * Reads messages from server and updates the client version of the model, or notifies the player. It implements the
 * visitor element of the visitor pattern.
 */
public class ClientMessageVisitor implements ToClientMessageVisitor {
    private final ClientController controller;

    public ClientMessageVisitor(ClientController controller) {
        this.controller = controller;
    }

    /**
     * Visits CreateGame message.
     */
    @Override
    public void visit(CreateGame msg) {
        controller.showUpdate("creategame");
    }

    /**
     * Visits ErrorMessage message.
     */
    @Override
    public void visit(ErrorMessage msg) {
        controller.showError(msg.getError());
    }

    /**
     * Visits WaitGameStart message.
     */
    @Override
    public void visit(WaitGameStart waitGameStart) {
        controller.showUpdate("waitgamestart");
    }

    /**
     * Visits GameUpdate message.
     */
    @Override
    public void visit(GameUpdate msg) {
        controller.manageUpdate(msg);
    }

    /**
     * Visits EndingScores message.
     */
    @Override
    public void visit(EndingScores msg) {
        controller.getView().displayEndingScore(msg.getPoints(), msg.getRanking());
    }
}