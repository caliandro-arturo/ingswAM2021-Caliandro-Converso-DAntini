package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.utility.StringUtility;

import java.util.ArrayList;

/**
 * Representation of level cost for leader cards. It is expressed as quantity of possessed development cards with a
 * certain level.
 */
public class LevelCost extends ColorCost implements Requirements {
    private final int level;

    public LevelCost(ArrayList<Color> colors, ArrayList<Integer> quantity, int level) {
        super(colors, quantity);
        this.level = level;
    }

    @Override
    public String toString() {
        Color color = getColors().get(0);
        Integer quantity = getQuantity().get(0);
        return "│" + StringUtility.center(color + ":" + quantity + "  level:" + level, 17) + "│\n";
    }
}
