package it.polimi.ingsw.commonFiles.messages.toClient.updates;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ToClientMessageVisitor;

public class UpdateLeaderCards extends Message implements GameUpdate {
    private int victoryPoints;
    private String[] requirements;
    private String[] leaderPower;

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public String[] getRequirements() {
        return requirements;
    }

    public String[] getLeaderPower() {
        return leaderPower;
    }

    public UpdateLeaderCards(int victoryPoints, String[] requirements, String[] leaderPower) {
        this.victoryPoints = victoryPoints;
        this.requirements = requirements;
        this.leaderPower = leaderPower;
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
