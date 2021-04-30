package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.messages.Message;

public class Disconnect extends Message implements ToServerMessage {
    @Override
    public void accept(ServerMessageVisitor v) {
        v.visit(this);
    }
}
