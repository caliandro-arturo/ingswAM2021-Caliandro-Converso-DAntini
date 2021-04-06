package it.polimi.ingsw.model;

/**
 * this class rapresent a special type of card (Development type)
 *
 * level -> level of the card
 * color -> color of the card
 * cost -> cost in resource of the card
 * productionPower -> the power of production of the card
 *
 */
public class DevelopmentCard {
    private int victoryPoints;
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
}
