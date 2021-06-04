package it.polimi.ingsw.commonFiles.messages.toServer;

public interface ToServerMessageHandler {
    void visit(SetGame setGame);
    void visit(SetNickname setNickname);
    void visit(UseMarket useMarket);
    void visit(StartProduction startProduction);
    void visit(BuyCard buyCard);
    void visit(DeployRes deployRes);
    void visit(UseLeader useLeader);
    void visit(DiscardLeader discardLeader);
    void visit(Next next);
    void visit(ChooseTurnPhase chooseTurnPhase);
    void visit(TakeRes takeRes);
    void visit(GetResource getResource);
    void visit(ChooseWhiteMarble chooseWhiteMarble);
    void visit(Back back);
    void visit(DiscardRes discardRes);
    void visit(GamesList gamesList);
    void visit(JoinGame joinGame);
}
