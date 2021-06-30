package it.polimi.ingsw.client;

import it.polimi.ingsw.client.GUI.App;
import it.polimi.ingsw.commonFiles.messages.toClient.*;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.GameUpdate;

/**
 * Reads messages from server and updates the client version of the model, or notifies the player. It implements the
 * visitor element of the visitor pattern.
 */
public class ClientMessageVisitor implements ToClientMessageVisitor {
    private final ClientController controller;
    private final View guiView = App.getGui().getView();

    public ClientMessageVisitor(ClientController controller) {
        this.controller = controller;
    }

    private void setToDo(String id, String toDo) {
        controller.getView().setToDo(id, toDo);
    }

    private void deleteToDo(String id) {
        controller.getView().deleteToDo(id);
    }

    @Override
    public void visit(CreateGame msg) {
        controller.showUpdate("creategame");
    }

    @Override
    public void visit(ErrorMessage msg) {
        controller.showError(msg.getError());
    }

    @Override
    public void visit(WaitGameStart waitGameStart) {
        controller.showUpdate("waitgamestart");
    }

    @Override
    public void visit(GameUpdate msg) {
        controller.manageUpdate(msg);
    }

    @Override
    public void visit(GameIsFull gameIsFull) {
        controller.showError("The game is full. You are being reconnected to another game.");
    }

    @Override
    public void visit(EndingScores msg) {
        controller.getView().displayEndingScore(msg.getPoints(), msg.getRanking());
    }
}