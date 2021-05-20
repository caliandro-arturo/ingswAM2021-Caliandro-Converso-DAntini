package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.utility.StringUtility;

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
        Color color = getColors().get(0);
        Integer quantity = getQuantity().get(0);
        return "│"+ StringUtility.center(color +":"+ quantity + "  level:"+level,17) +"│\n";
    }
}
