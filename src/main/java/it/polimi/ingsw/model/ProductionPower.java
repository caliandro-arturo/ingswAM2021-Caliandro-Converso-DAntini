package it.polimi.ingsw.model;

/**
 * Basic type of production power
 */
public class ProductionPower implements Production{
    private UtilityProductionAndCost[] cost;
    private UtilityProductionAndCost[] production;


    public ProductionPower(UtilityProductionAndCost[] cost, UtilityProductionAndCost[] production){
        this.cost = cost;
        this.production = production;
    }


    @Override
    public UtilityProductionAndCost[] startProduction( Player player) {
                /*
        Ask the player where they want to take the cost
         */
        for (int i=0; i<cost.length; i++) {
            player.getBoard().getPersonalBox().getResourceMap().remove(cost[i].getResource(),cost[i].getQuantity());
        }
        return production;
    }

    public UtilityProductionAndCost[] getCost(){
        return cost;
    }
    public UtilityProductionAndCost[] getProduction(){
        return production;
    }

}
