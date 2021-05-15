package it.polimi.ingsw.server.model;

/**
 *  a special type of card (Leader type)
 *
 *  requirements
 *  leaderPower
 */
public class LeaderCard{
    private final int victoryPoints;
    private final Requirements requirements;
    private final LeaderPower leaderPower;

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public Requirements getRequirements() {
        return requirements;
    }

    public LeaderPower getLeaderPower() {
        return leaderPower;
    }

    public LeaderCard(int victoryPoints, Requirements requirements, LeaderPower leaderPower){
        this.victoryPoints = victoryPoints;
        this.requirements = requirements;
        this.leaderPower = leaderPower;
    }
}
