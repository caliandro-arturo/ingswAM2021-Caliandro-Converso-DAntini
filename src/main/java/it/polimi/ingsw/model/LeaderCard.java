package it.polimi.ingsw.model;

/**
 *  a special type of card (Leader type)
 *
 *  requirements
 *  leaderPower
 */
public class LeaderCard extends Card{
    private Requirements requirements;
    private LeaderPower leaderPower;

    public LeaderCard(int victoryPoints, Requirements requirements,LeaderPower leaderPower){
        super(victoryPoints);
        this.requirements = requirements;
        this.leaderPower = leaderPower;
    }
}
