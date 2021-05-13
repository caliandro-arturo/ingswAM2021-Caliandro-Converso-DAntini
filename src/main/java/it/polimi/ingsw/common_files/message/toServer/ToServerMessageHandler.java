package it.polimi.ingsw.common_files.message.toServer;

import it.polimi.ingsw.common_files.message.toServer.actions.StartProduction;
import it.polimi.ingsw.common_files.message.toServer.actions.UseMarket;
import it.polimi.ingsw.common_files.message.toServer.actions.BuyCard;

public interface ToServerMessageHandler {
    void visit(SetGame setGame);
    void visit(SetNickname setNickname);
    void visit(UseMarket useMarket);
    void visit(StartProduction startProduction);
    void visit(BuyCard buyCard);
}
