package it.polimi.ingsw.common_files.model;

import java.util.Arrays;

/**
 * this class represent a special type of card (Development type)
 *
 * level -> level of the card
 * color -> color of the card
 * cost -> cost in resource of the card
 * productionPower -> the power of production of the card
 *
 */
public class DevelopmentCard {
    private final int victoryPoints;
    private final int level;
    private final Color color;
    private final UtilityProductionAndCost[] cost;
    private final Production productionPower;

    public DevelopmentCard(int victoryPoints, int level, Color color, UtilityProductionAndCost[] cost, ProductionPower productionPower){

        this.victoryPoints = victoryPoints;
        this.level = level;
        this.color = color;
        this.cost = cost;
        this.productionPower = productionPower;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public Color getColor(){
        return color;
    }
    public int getLevel(){
        return level;
    }
    public UtilityProductionAndCost[] getCost(){
        return cost;
    }
    public Production getProduction(){
        return productionPower;
    }

    @Override
    public String toString() {

        return  "┌─────────────────┐\n" +
                "│     " + color + "  " + level + "  " + color + "     │\n" +
                "│" + Utility.center(Arrays.toString(cost), 17)+ "│\n" +
                "│                 │\n" +
                "│                 │\n" +
                productionPower+
                "│                 │\n" +
                "│     " + color + " " + String.format("%2d", victoryPoints) + "  " + color + "     │\n" +
                "└─────────────────┘\n";
    }
}
