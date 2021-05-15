package it.polimi.ingsw.commonFiles.messages.toClient;

import it.polimi.ingsw.commonFiles.messages.Message;

public class GameIsFull extends Message implements ToClientMessage {
    @Override
    public void accept(ClientMessageVisitor v) {
        v.visit(this);
    }
}
