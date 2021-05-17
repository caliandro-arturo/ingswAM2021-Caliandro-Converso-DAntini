package it.polimi.ingsw.commonFiles.messages.toClient;

import it.polimi.ingsw.commonFiles.messages.toClient.updates.GameUpdate;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.ResourceUpdate;

public interface ToClientMessageVisitor {
    void visit(CreateGame msg);
    void visit(ErrorMessage msg);
    void visit(WaitGameCreation msg);
    void visit(GameUpdate msg);
    void visit(Ok msg);
    void visit(GameIsFull msg);
    void visit(TablePosition msg);
    void visit(AskPositionWarehouse msg);
    void visit(ResourceUpdate msg);
    void visit(TurnPhaseAnnouncement msg);
}
