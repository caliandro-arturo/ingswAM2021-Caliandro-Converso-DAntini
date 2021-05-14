package it.polimi.ingsw.common_files.message.toServer.actions;

import it.polimi.ingsw.common_files.message.Message;
import it.polimi.ingsw.common_files.message.toServer.ToServerMessage;
import it.polimi.ingsw.common_files.message.toServer.ToServerMessageHandler;

/**
 * input command to take resources out of the specified Warehouse store
 */
public class TakeRes extends Message implements ToServerMessage {
    private int depot;

    public void setDepot(int depot) {
        this.depot = depot;
    }

    public int getDepot() {
        return depot;
    }

    public TakeRes(int depot) {
        this.depot = depot;
    }
    @Override
    public void accept(ToServerMessageHandler v){v.visit(this);};
}
