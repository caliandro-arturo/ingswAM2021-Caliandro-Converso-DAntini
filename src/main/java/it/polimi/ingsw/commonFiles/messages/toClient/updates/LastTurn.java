package it.polimi.ingsw.commonFiles.messages.toClient.updates;

import it.polimi.ingsw.client.UpdateHandler;
import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ClientMessageVisitor;

/**
 * Update sent when a player reaches a victory condition.
 */
public class LastTurn extends Message implements GameUpdate{
    private final String reason;

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
