package it.polimi.ingsw.commonFiles.messages.toClient;

import it.polimi.ingsw.commonFiles.messages.Message;

public class WaitGameStart extends Message implements ToClientMessage {
    @Override
    public void accept(ToClientMessageVisitor v) {
        v.visit(this);
    }
}
