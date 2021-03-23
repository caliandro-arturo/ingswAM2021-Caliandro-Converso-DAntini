package it.polimi.ingsw.model;

/**
 * Basic type of production power
 */
public class ProductionPower {
    private UtilityProductionAndCost[] cost;
    private UtilityProductionAndCost[] production;


    public ProductionPower(UtilityProductionAndCost[] cost, UtilityProductionAndCost[] production){
        this.cost = cost;
        this.production = production;
    }

    /**
     * standard operation for the prodution in the game
     */
    public void startProduction(){

    }

}
