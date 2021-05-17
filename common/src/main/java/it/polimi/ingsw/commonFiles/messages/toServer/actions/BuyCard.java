package it.polimi.ingsw.commonFiles.messages.toServer.actions;

import it.polimi.ingsw.commonFiles.messages.toServer.ToServerMessage;
import it.polimi.ingsw.commonFiles.messages.toServer.ToServerMessageHandler;
import it.polimi.ingsw.commonFiles.messages.Message;

import java.util.ArrayList;
import java.util.Locale;

public class BuyCard extends Message implements ToServerMessage {
    private int level;
    private String color;
    private int space;
    private int[] stores;

    public BuyCard(int level, String color, int space, ArrayList<Integer> stores) {
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

    @Override
    public void accept(ToServerMessageHandler v) {
        v.visit(this);
    }
}
