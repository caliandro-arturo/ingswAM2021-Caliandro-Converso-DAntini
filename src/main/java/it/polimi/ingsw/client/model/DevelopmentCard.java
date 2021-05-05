package it.polimi.ingsw.client.model;

import java.util.Arrays;

public class DevelopmentCard {
    private int victoryPoints;
    private Color color;
    private int level;
    private UtilityProductionAndCost[] costs;
    private UtilityProductionAndCost[] productionCost;
    private UtilityProductionAndCost[] production;

    public DevelopmentCard(int victoryPoints, Color color, int level, UtilityProductionAndCost[] costs,
                           UtilityProductionAndCost[] productionCost, UtilityProductionAndCost[] production) {
        this.victoryPoints = victoryPoints;
        this.color = color;
        this.level = level;
        this.costs = costs;
        this.productionCost = productionCost;
        this.production = production;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public Color getColor() {
        return color;
    }

    public int getLevel() {
        return level;
    }

    public UtilityProductionAndCost[] getCosts() {
        return costs;
    }

    public UtilityProductionAndCost[] getProductionCost() {
        return productionCost;
    }

    public UtilityProductionAndCost[] getProduction() {
        return production;
    }

    @Override
    public String toString() {
        String cost = assertLength(costs);
        String productionsCost = assertLength(productionCost);
        String productionValue = assertLength(production);
        return  "┌─────────────────┐\n" +
                "│     " + color + "  " + level + "  " + color + "     │\n" +
                 cost +
                "│                 │\n" +
                "│                 │\n" +
                productionsCost +
                productionValue +
                "│                 │\n" +
                "│     " + color + " " + String.format("%2d", victoryPoints) + "  " + color + "     │\n" +
                "└─────────────────┘\n";
    }

    private String assertLength(UtilityProductionAndCost[] array){
        int size = array.length;
        return "│" + Utility.center(Arrays.toString(array), 17)+ "│\n";
    }
}
