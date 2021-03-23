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
public class DevelopmentCard extends Card{
    private int level;
    private Color color;
    private UtilityProductionAndCost[] cost;
    private ProductionPower productionPower;

    public DevelopmentCard(int victoryPoints, int level, Color color, UtilityProductionAndCost[] cost, ProductionPower productionPower){
        super(victoryPoints);
        this.level = level;
        this.color = color;
        this.cost = cost;
        this.productionPower = productionPower;
    }
}
