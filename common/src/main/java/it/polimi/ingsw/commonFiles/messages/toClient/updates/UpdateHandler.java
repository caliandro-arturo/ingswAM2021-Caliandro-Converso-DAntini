package it.polimi.ingsw.commonFiles.messages.toClient.updates;

import it.polimi.ingsw.commonFiles.messages.toClient.GameRejoin;

public interface UpdateHandler {
    void visit(GameStarted msg);
    void visit(LastTurn msg);
    void visit(NewPlayer msg);
    void visit(PlayerLeft msg);
    void visit(ResourceUpdate msg);
    void visit(InitLeaderHand msg);
    void visit(InitMarket msg);
    void visit(InitDevGrid msg);
    void visit(IncrementFaithTrackPosition msg);
    void visit(InitBoards msg);
    void visit(TablePosition msg);
    void visit(NewTurn msg);
    void visit(TurnPhaseAnnouncement msg);
    void visit(InitialResourcesAmount msg);
    void visit(UpdateLeaderCards msg);
    void visit(VaticanReport msg);
    void visit(LorenzoPick msg);
    void visit(GameRejoin gameRejoin);
    void visit(LorenzoPosition msg);
}
