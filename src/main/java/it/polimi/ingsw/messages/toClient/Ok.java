package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.messages.Message;

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
    public void accept(ClientMessageVisitor v) {
        v.visit(this);
    }
}
