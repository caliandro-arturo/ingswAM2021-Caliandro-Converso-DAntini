package it.polimi.ingsw.commonFiles.messages.toClient;

import it.polimi.ingsw.commonFiles.messages.Message;

/**
 * Message sent each time a new turn phase starts.
 */
public class TurnPhaseAnnouncement extends Message implements ToClientMessage {
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
}
