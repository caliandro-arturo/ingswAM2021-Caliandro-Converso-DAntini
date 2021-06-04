package it.polimi.ingsw.commonFiles.messages.toClient;

import it.polimi.ingsw.commonFiles.messages.Message;

public class ErrorMessage extends Message implements ToClientMessage {
    private String error;

    public ErrorMessage(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    @Override
    public void accept(ToClientMessageVisitor v) {
        v.visit(this);
    }

}
