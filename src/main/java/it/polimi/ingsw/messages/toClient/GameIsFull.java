package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.messages.Message;

public class GameIsFull extends Message implements ToClientMessage {
    @Override
    public void accept(ClientMessageVisitor v) {
        v.visit(this);
    }
}
