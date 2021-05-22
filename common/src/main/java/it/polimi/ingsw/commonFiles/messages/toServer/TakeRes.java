package it.polimi.ingsw.commonFiles.messages.toServer;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toServer.ToServerMessage;
import it.polimi.ingsw.commonFiles.messages.toServer.ToServerMessageHandler;

/**
 * input command to take resources out of the specified Warehouse store
 */
public class TakeRes extends Message implements ToServerMessage {
    private final int depot;

    public int getDepot() {
        return depot;
    }

    public TakeRes(int depot) {
        this.depot = depot;
    }
    @Override
    public void accept(ToServerMessageHandler v){v.visit(this);};
}