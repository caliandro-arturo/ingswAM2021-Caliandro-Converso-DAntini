package it.polimi.ingsw.commonFiles.messages.toClient;

import it.polimi.ingsw.commonFiles.messages.Message;

public class Ok extends Message implements ToClientMessage {
    private final int id;

    public Ok(Message message) {
        this.id = message.getId();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void accept(ToClientMessageVisitor v) {
        v.visit(this);
    }
}
