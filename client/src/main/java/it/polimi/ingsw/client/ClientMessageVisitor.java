package it.polimi.ingsw.client;

import it.polimi.ingsw.commonFiles.messages.toClient.*;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.GameUpdate;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.ResourceUpdate;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.TablePosition;
import it.polimi.ingsw.commonFiles.messages.toServer.ToServerMessage;

/**
 * Reads messages from server and updates the client version of the model, or notifies the player.
 * It implements the visitor element of the visitor pattern.
 */
public class ClientMessageVisitor implements ToClientMessageVisitor {
    private final ClientController controller;

    public ClientMessageVisitor(ClientController controller) {
        this.controller = controller;
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

    public void visit(ToServerMessage msg) {
        controller.manageUpdate(msg);
    }

    public void visit(Ok msg) {
        controller.confirmMove(msg.getId());
    }

    public void visit(GameIsFull gameIsFull) {
        controller.showError("The game is full. Reconnecting to another game...");
    }

    public void visit(TurnPhaseAnnouncement msg) {
        controller.getModel().setCurrentTurnPhase(msg.getTurnPhaseName());
        controller.show("turnphase");
    }

    @Override
    public void visit(InitialResourcesAmount msg) {
        controller.showUpdate("You have to get " + msg.getResourcesAmount() + " initial resources. Type GETRES: <resource name> to get a resource.");
    }
}