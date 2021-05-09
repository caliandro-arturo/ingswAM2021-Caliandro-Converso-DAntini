package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.messages.Message;

public class ErrorMessage extends Message implements ToClientMessage {
    private int id;
    private String error;

    public ErrorMessage(Message message, String error) {
        this.id = message.hashCode();
        this.error = error;
    }

    public int getId() {
        return id;
    }

    public String getError() {
        return error;
    }

    @Override
    public void accept(ClientMessageVisitor v) {
        v.visit(this);
    }

}
