package it.polimi.ingsw.commonFiles.messages.toClient.updates;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ToClientMessageVisitor;

/**
 * Message sent each time a new turn phase starts.
 */
public class TurnPhaseAnnouncement extends Message implements GameUpdate {
    private final String turnPhaseName;
    private final String turnPhaseToDo;

    public TurnPhaseAnnouncement(String turnPhaseName, String turnPhaseToDo) {
        this.turnPhaseName = turnPhaseName;
        this.turnPhaseToDo = turnPhaseToDo;
    }

    public String getTurnPhaseName() {
        return turnPhaseName;
    }

    public String getTurnPhaseToDo() {
        return turnPhaseToDo;
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
