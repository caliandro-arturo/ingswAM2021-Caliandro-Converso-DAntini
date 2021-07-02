package it.polimi.ingsw.client.model;

/**
 * Leader card representation for client.
 */
public class LeaderCard {
    private final int ID;
    private final int victoryPoints;
    private final Requirements requirements;
    private final LeaderPower power;

    public LeaderCard(int ID, int victoryPoints, Requirements requirements, LeaderPower power) {
        this.ID = ID;
        this.victoryPoints = victoryPoints;
        this.requirements = requirements;
        this.power = power;
    }

    public int getID() {
        return ID;
    }

    public LeaderPower getPower() {
        return power;
    }

    @Override
    public String toString() {
        return  "┌─────────────────┐\n" +
                "│                 │\n"+
                requirements+
                "│                 │\n"+
                "│                 │\n"+
                "│                 │\n"+
                power+
                "│                 │\n"+
                "│       " + String.format("%2d",victoryPoints) + "        │\n" +
                "└─────────────────┘\n";
    }
}
