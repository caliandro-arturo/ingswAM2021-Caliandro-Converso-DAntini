package it.polimi.ingsw.client.model;

import java.util.ArrayList;

public class LevelCost extends ColorCost implements Requirements{
    private final int level;

    public LevelCost(ArrayList<Color> colors, ArrayList<Integer> quantity, int level) {
        super(colors, quantity);
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "│"+ Utility.center(getColors() +":"+ getQuantity() + "  level:"+level,17) +"│\n";
    }
}
