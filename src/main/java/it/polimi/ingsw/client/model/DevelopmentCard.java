package it.polimi.ingsw.client.model;

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
        return  "|---------------------------------|\n" +
                "|\t " + color.toString() + "  " + level + "   " + color.toString() + " \t|\n" +
                "|\t " + costs.toString() + " \t|\n" +
                "|\t  \t  \t|\n" +
                "|\t  \t  \t|\n" +
                "|\t " + productionCost.toString() + " \t|\n" +
                "|\t " + production.toString() + " \t|\n" +
                "|\t  \t  \t|\n" +
                "|\t  " + victoryPoints + "  \t|\n" +
                "|---------------------------------|\n";
    }
}
