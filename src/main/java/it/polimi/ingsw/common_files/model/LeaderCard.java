package it.polimi.ingsw.common_files.model;

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

    @Override
    public String toString() {
        return  "┌─────────────────┐\n" +
                "│                 │\n"+
                requirements+
                "│                 │\n"+
                "│                 │\n"+
                "│                 │\n"+
                leaderPower+
                "│                 │\n"+
                "│       " + String.format("%2d",victoryPoints) + "        │\n" +
                "└─────────────────┘\n";
    }
}
