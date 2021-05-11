package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.messages.toServer.actions.StartProduction;
import it.polimi.ingsw.messages.toServer.actions.UseMarket;

public interface ToServerMessageHandler {
    void visit(SetGame setGame);
    void visit(SetNickname setNickname);
    void visit(UseMarket useMarket);
    void visit(StartProduction startProduction);
}
