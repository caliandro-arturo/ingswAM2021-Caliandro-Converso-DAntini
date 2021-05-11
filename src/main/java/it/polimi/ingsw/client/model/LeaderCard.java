package it.polimi.ingsw.client.model;

/**
 * LeaderCard class for client
 */
public class LeaderCard {
    Requirements requirements;
    LeaderPower leaderPower;
    int victoryPoints;

    public LeaderCard(Requirements requirements, LeaderPower leaderPower, int victoryPoints) {
        this.requirements = requirements;
        this.leaderPower = leaderPower;
        this.victoryPoints = victoryPoints;
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
