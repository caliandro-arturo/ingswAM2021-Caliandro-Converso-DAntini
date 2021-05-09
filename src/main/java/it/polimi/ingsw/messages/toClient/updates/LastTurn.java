package it.polimi.ingsw.messages.toClient.updates;

import it.polimi.ingsw.client.UpdateHandler;
import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.messages.toClient.ClientMessageVisitor;

public class LastTurn extends Message implements GameUpdate{
    private String reason;

    public LastTurn(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public void accept(ClientMessageVisitor v) {
        v.visit(this);
    }

    @Override
    public void accept(UpdateHandler v) {
        v.visit(this);
    }
}
