package it.polimi.ingsw.commonFiles.messages.toClient.updates;

public interface UpdateHandler {
    void visit(GameSet msg);
    void visit(GameStarted msg);
    void visit(LastTurn msg);
    void visit(NewPlayer msg);
    void visit(PlayerLeft msg);
    void visit(ResourceUpdate msg);
    void visit(InitLeaderHand msg);
    void visit(InitMarket msg);
    void visit(InitDevGrid msg);
    void visit(InitBoards msg);
    void visit(TablePosition msg);
}
