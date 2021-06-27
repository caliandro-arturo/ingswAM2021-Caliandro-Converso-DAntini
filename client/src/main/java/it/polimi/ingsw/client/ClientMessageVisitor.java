package it.polimi.ingsw.client;

import it.polimi.ingsw.client.GUI.App;
import it.polimi.ingsw.commonFiles.messages.toClient.*;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.AskWhiteMarble;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.GameUpdate;

import java.util.Timer;
import java.util.TimerTask;

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
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                guiView.show("");
            }
        };
        Timer timer = new Timer();

        controller.showError(msg.getError());
        guiView.showError("You can't do this");
        timer.schedule(task, 4000);
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
    public void visit(DisplayEndingScores msg) {
        controller.getView().displayEndingScore(msg.getNames(), msg.getPoints(), msg.getRanking());
    }
}