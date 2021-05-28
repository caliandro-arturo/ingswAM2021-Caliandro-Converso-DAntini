package it.polimi.ingsw.commonFiles.messages.toClient.updates;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ToClientMessageVisitor;

import java.util.ArrayList;

public class InitLeaderHand extends Message implements GameUpdate {
    private final ArrayList<Integer> victoryPoints;
    private final ArrayList<String[]> requirements;
    private final ArrayList<String[]> leaderPower;
    private final ArrayList<Integer> IDs;

    public InitLeaderHand(ArrayList<Integer> IDs,ArrayList<Integer> victoryPoints, ArrayList<String[]> requirements, ArrayList<String[]> leaderPower) {
        this.IDs = IDs;
        this.victoryPoints = victoryPoints;
        this.requirements = requirements;
        this.leaderPower = leaderPower;
    }

    public ArrayList<Integer> getIDs() {
        return IDs;
    }

    public ArrayList<Integer> getVictoryPoints() {
        return victoryPoints;
    }

    public ArrayList<String[]> getRequirements() {
        return requirements;
    }

    public ArrayList<String[]> getLeaderPower() {
        return leaderPower;
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
