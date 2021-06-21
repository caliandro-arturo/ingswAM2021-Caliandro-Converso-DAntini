package it.polimi.ingsw.commonFiles.messages.toServer;


import it.polimi.ingsw.commonFiles.messages.Message;

/**
 * Message sent when the player chooses the turn action to perform.
 */
public class ChooseTurnPhase extends Message implements ToServerMessage {
    private final String turnPhaseName;

    public ChooseTurnPhase(String turnPhaseName) {
        this.turnPhaseName = turnPhaseName;
    }

    public String getTurnPhaseName() {
        return turnPhaseName;
    }

    @Override
    public void accept(ToServerMessageHandler v) {
        v.visit(this);
    }
}
