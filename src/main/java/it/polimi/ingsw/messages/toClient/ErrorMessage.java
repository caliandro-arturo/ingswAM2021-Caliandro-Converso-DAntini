package it.polimi.ingsw.messages.toClient;

import it.polimi.ingsw.messages.Message;

public class ErrorMessage extends Message implements ToClientMessage {

    @Override
    public void accept(ClientMessageVisitor v) {
        v.visit(this);
    }

    private String error;

    public ErrorMessage(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
