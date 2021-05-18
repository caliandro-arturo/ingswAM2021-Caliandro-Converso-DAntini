package it.polimi.ingsw.commonFiles.messages.toClient.updates;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ToClientMessageVisitor;

import java.util.ArrayList;

public class InitLeaderHand extends Message implements GameUpdate {
    private ArrayList<Integer> victoryPoints = new ArrayList<>();
    private ArrayList<String[]> requirements = new ArrayList<>();
    private ArrayList<String[]> leaderPower = new ArrayList<>();

    public InitLeaderHand(ArrayList<Integer> victoryPoints, ArrayList<String[]> requirements, ArrayList<String[]> leaderPower) {
        this.victoryPoints = victoryPoints;
        this.requirements = requirements;
        this.leaderPower = leaderPower;
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
