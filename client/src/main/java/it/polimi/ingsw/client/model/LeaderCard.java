package it.polimi.ingsw.client.model;

public class LeaderCard {
    private int victoryPoints;
    private Requirements requirements;
    private LeaderPower power;

    public LeaderCard(int victoryPoints, Requirements requirements, LeaderPower power) {
        this.victoryPoints = victoryPoints;
        this.requirements = requirements;
        this.power = power;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public Requirements getRequirements() {
        return requirements;
    }

    public void setRequirements(Requirements requirements) {
        this.requirements = requirements;
    }

    public LeaderPower getPower() {
        return power;
    }

    public void setPower(LeaderPower power) {
        this.power = power;
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
