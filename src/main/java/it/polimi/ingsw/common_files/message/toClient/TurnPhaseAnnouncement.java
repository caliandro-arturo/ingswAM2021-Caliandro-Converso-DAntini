package it.polimi.ingsw.common_files.message.toClient;

import it.polimi.ingsw.common_files.message.Message;

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
    public void accept(ClientMessageVisitor v) {
        v.visit(this);
    }
}
