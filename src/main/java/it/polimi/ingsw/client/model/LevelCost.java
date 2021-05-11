package it.polimi.ingsw.client.model;


/**
 * LevelCost class for client
 */
public class LevelCost extends ColorCost implements Requirements {
    private int level;

    public LevelCost(Color[] colors, int[] quantity, int level) {
        super(colors, quantity);
        this.level = level;
    }

    @Override
    public String toString() {
        return "│"+ Utility.center(colors[0] +":"+ quantity[0] + "  level:"+level,17) +"│\n";
    }
}
