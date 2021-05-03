package it.polimi.ingsw.client.model;

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
        return "LeaderCard{" +
                "requirements=" + requirements +
                ", leaderPower=" + leaderPower +
                ", victoryPoints=" + victoryPoints +
                '}';
    }
}
