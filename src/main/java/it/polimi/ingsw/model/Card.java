package it.polimi.ingsw.model;

/**
 *  this abstract class is the base for all the cards that are present in the project
 */
public abstract class Card {
    private final int victoryPoints;

    public Card(int victoryPoints){
        this.victoryPoints = victoryPoints;
    }

    public int getVictoryPoints(){
        return victoryPoints;
    }
}
