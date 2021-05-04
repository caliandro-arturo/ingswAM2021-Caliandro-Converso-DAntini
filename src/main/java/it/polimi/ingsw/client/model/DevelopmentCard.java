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

    @Override
    public String toString() {
        String cost = assertLength(costs);
        String productionsCost = assertLength(productionCost);
        String productionValue = assertLength(production);
        return  "┌─────────────────┐\n" +
                "│     " + color + "  " + level + "  " + color + "     │\n" +
                 cost +
                "│\t  \t  \t \t  │\n" +
                "│\t  \t  \t \t  │\n" +
                productionsCost +
                productionValue +
                "│\t  \t  \t \t  │\n" +
                "│     " + color + " " + String.format("%2d", victoryPoints) + "  " + color + "     │\n" +
                "└─────────────────┘\n";
    }

    private String assertLength(UtilityProductionAndCost[] array){
        int size = array.length;
        if (size == 1 ){
            return "│      " + Arrays.toString(array) + "       │\n";
        } else if(size == 2){
            return "│    " + Arrays.toString(array) + "     │\n";
        } else{
            return "│   " + Arrays.toString(array) + "  │\n";
        }
    }
}
