package it.polimi.ingsw.common_files.message.toClient.updates;

import it.polimi.ingsw.client.UpdateHandler;
import it.polimi.ingsw.common_files.message.Message;
import it.polimi.ingsw.common_files.message.toClient.ClientMessageVisitor;

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
