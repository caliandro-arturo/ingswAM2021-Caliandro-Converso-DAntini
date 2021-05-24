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

    private void setToDo(String id, String toDo) {
        controller.getView().setToDo(id, toDo);
    }

    private void deleteToDo(String id) {
        controller.getView().deleteToDo(id);
    }

    @Override
    public void visit(CreateGame msg) {
        controller.show("creategame");
    }

    @Override
    public void visit(ErrorMessage msg) {
        controller.showError(msg.getError());
    }

    @Override
    public void visit(WaitGameCreation msg) {
        controller.show("waitgamecreation");
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
    public void visit(AskWhiteMarble msg) {
        setToDo("chooseleader", "You have to choose which leader to use to gain the resource from the " +
                msg.getWhiteMarblesToChoose() + " white marble" +
                (msg.getWhiteMarblesToChoose() > 1 ? "s" : "") +
                " of the market. Type CHOOSEWHITE: <leader number> to choose the leader.");
    }

    @Override
    public void visit(DisplayEndingScores msg) {
        controller.getView().displayEndingScore(msg.getNames(), msg.getPoints(), msg.getRanking());
    }
}