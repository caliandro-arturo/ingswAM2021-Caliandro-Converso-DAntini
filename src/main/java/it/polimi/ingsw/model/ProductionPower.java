package it.polimi.ingsw.model;

/**
 * Basic type of production power
 */
public class ProductionPower implements Production{
    private final UtilityProductionAndCost[] cost;
    private final UtilityProductionAndCost[] production;


    public ProductionPower(UtilityProductionAndCost[] cost, UtilityProductionAndCost[] production){
        this.cost = cost;
        this.production = production;
    }


    @Override
    public UtilityProductionAndCost[] startProduction(PersonalBoard board) {
        manageCost(cost,board);
        return production;
    }

    public void manageCost(UtilityProductionAndCost[] cost ,PersonalBoard board) {
        for(int i=0;i< cost.length;i++) {
            if (askPlayerWhatDeposit(board)) {
                board.getPersonalBox().getResourceMap().remove(cost[i].getResource(), 1);
            } else {
                //board.getStore()
            }
        }
    }

    private boolean askPlayerWhatDeposit(PersonalBoard board){
        return true;
    }

    private Resource askPlayerResourceType(){
        return null;
    }

    public UtilityProductionAndCost[] getCost(){
        return cost;
    }
    public UtilityProductionAndCost[] getProduction(){
        return production;
    }

}
