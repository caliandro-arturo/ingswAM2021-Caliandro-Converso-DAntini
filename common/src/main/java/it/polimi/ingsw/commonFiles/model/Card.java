package it.polimi.ingsw.commonFiles.model;

import java.io.Serializable;

public class Card implements Serializable {
    private final int ID;
    private final UtilityProductionAndCost[] newCardCost;
    private final int newCardVictoryPoints;
    private final Production productions;
    private int level;

    public UtilityProductionAndCost[] getNewCardCost() {
        return newCardCost;
    }

    public int getNewCardVictoryPoints() {
        return newCardVictoryPoints;
    }

    public Production getProductions() {
        return productions;
    }

    public int getID() {
        return ID;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Card(int ID, UtilityProductionAndCost[] newCardCost, int newCardVictoryPoints, Production productions) {
        this.ID = ID;
        this.newCardCost = newCardCost;
        this.newCardVictoryPoints = newCardVictoryPoints;
        this.productions = productions;
    }
}
