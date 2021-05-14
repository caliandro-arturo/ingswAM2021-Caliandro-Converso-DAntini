package it.polimi.ingsw.common_files.message.toServer;

import it.polimi.ingsw.common_files.message.toServer.actions.*;

public interface ToServerMessageHandler {
    void visit(SetGame setGame);
    void visit(SetNickname setNickname);
    void visit(UseMarket useMarket);
    void visit(StartProduction startProduction);
    void visit(BuyCard buyCard);
    void visit(DeployRes deployRes);
    void visit(UseLeader useLeader);
    void visit(DiscardLeader discardLeader);
    void visit(TakeRes takeRes);
}
