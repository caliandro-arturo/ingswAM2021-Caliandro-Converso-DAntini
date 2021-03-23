package it.polimi.ingsw.model;

/**
 *  this abstract class is the base for all the card that are present in the project
 */
public abstract class Card {
    private int victoryPoints;

    public Card(int victoryPoints){
        this.victoryPoints = victoryPoints;
    }

    /**
     *
     * @return the points of the card
     */
    public int returnScore(){
        return victoryPoints;
    }
}
