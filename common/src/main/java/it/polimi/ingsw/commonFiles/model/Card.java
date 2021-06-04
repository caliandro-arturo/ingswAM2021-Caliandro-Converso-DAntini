package it.polimi.ingsw.commonFiles.model;

import java.io.Serializable;

public class Card implements Serializable {
    private int ID;
    private UtilityProductionAndCost[] newCardCost;
    private int newCardVictoryPoints;
    private Production productions;

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

    public Card(int ID, UtilityProductionAndCost[] newCardCost, int newCardVictoryPoints, Production productions) {
        this.ID = ID;
        this.newCardCost = newCardCost;
        this.newCardVictoryPoints = newCardVictoryPoints;
        this.productions = productions;
    }
}
