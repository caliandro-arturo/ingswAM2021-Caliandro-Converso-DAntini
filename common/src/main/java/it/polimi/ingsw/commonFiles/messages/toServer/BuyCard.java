package it.polimi.ingsw.commonFiles.messages.toServer;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.model.Card;
import it.polimi.ingsw.commonFiles.model.Production;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;

import java.util.ArrayList;
import java.util.Locale;

public class BuyCard extends Message implements ToServerMessage {
    private final int level;
    private final String color;
    private final int space;
    private final int[] stores;
    private Card newCard;

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
        return newCard.getNewCardCost();
    }

    public int getNewCardVictoryPoints() {
        return newCard.getNewCardVictoryPoints();
    }

    public Production getProductions() {
        return newCard.getProductions();
    }

    public int getID() {
        return newCard.getID();
    }

    public void setNewCard(Card card) {
        this.newCard = card;
    }

    @Override
    public void accept(ToServerMessageHandler v) {
        v.visit(this);
    }
}
