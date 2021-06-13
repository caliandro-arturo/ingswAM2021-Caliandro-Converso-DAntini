package it.polimi.ingsw.server.model;

/**
 *  a special type of card (Leader type)
 *
 *  victoryPoints points of the card
 *  requirements to deploy the card
 *  leaderPower special power of the card
 *  ID identifier of the card
 */
public class LeaderCard{
    private final int ID;
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

    public int getID(){return ID;}

    public LeaderCard(int ID,int victoryPoints, Requirements requirements, LeaderPower leaderPower){
        this.ID = ID;
        this.victoryPoints = victoryPoints;
        this.requirements = requirements;
        this.leaderPower = leaderPower;
    }

    @Override
    public String toString() {
        StringBuilder leadCardJson = new StringBuilder();
        leadCardJson.append("""
                {
                    "ID": %d,
                    "requirements": [
                """.formatted(ID));
        for (String r : requirements.identifier()) {
            leadCardJson.append(r).append(",\n");
        }
        leadCardJson.deleteCharAt(leadCardJson.lastIndexOf(","));
        leadCardJson.append("""
                ],
                    "leaderPower": [
                """);
        for (String r : leaderPower.identifier()) {
            leadCardJson.append(r).append(",\n");
        }
        leadCardJson.deleteCharAt(leadCardJson.lastIndexOf(","));
        leadCardJson.append("""
                ],
                    "victoryPoints": %d
                }""".formatted(victoryPoints));
        return leadCardJson.toString();
    }
}
