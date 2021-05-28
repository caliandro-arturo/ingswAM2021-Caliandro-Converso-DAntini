package it.polimi.ingsw.commonFiles.messages.toServer;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.model.Production;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;

import java.util.ArrayList;
import java.util.Locale;

public class BuyCard extends Message implements ToServerMessage {
    private final int level;
    private int ID;
    private final String color;
    private final int space;
    private final int[] stores;
    private UtilityProductionAndCost[] newCardCost;
    private int newCardVictoryPoints;
    private Production productions;

    public BuyCard( int level, String color, int space, ArrayList<Integer> stores) {
        this.level = level;
        this.color = color.toUpperCase(Locale.ROOT);
        this.space = space;
        this.stores = stores.stream().mapToInt(i->i).toArray();
    }

    public int getLevel() {
        return level;
    }

    public String getColor() {
        return color;
    }

    public int getSpace() {
        return space;
    }

    public int[] getStores() {
        return stores;
    }

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

    public void setNewCard(int ID, UtilityProductionAndCost[] cost, int victoryPoints, Production productions) {
        this.ID = ID;
        newCardCost = cost;
        newCardVictoryPoints = victoryPoints;
        this.productions = productions;
    }

    @Override
    public void accept(ToServerMessageHandler v) {
        v.visit(this);
    }
}
