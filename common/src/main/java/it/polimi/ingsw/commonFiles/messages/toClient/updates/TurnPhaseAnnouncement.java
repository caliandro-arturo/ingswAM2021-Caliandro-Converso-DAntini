package it.polimi.ingsw.commonFiles.messages.toClient.updates;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ToClientMessage;
import it.polimi.ingsw.commonFiles.messages.toClient.ToClientMessageVisitor;

/**
 * Message sent each time a new turn phase starts.
 */
public class TurnPhaseAnnouncement extends Message implements GameUpdate {
    private final String turnPhaseName;

    public TurnPhaseAnnouncement(String turnPhaseName) {
        this.turnPhaseName = turnPhaseName;
    }

    public String getTurnPhaseName() {
        return turnPhaseName;
    }

    @Override
    public void accept(ToClientMessageVisitor v) {
        v.visit(this);
    }

    @Override
    public void accept(UpdateHandler v) {
        v.visit(this);
    }
}
