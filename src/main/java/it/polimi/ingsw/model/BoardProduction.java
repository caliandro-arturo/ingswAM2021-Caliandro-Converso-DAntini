package it.polimi.ingsw.model;

public class BoardProduction implements Production{

    @Override
    public UtilityProductionAndCost[] startProduction(PersonalBoard board) {
        UtilityProductionAndCost[] costs = new UtilityProductionAndCost[2];
        costs[0] = new UtilityProductionAndCost(1,askPlayerResourceType());
        manageCost(costs[0],board);
        costs[1] = new UtilityProductionAndCost(1,askPlayerResourceType());
        UtilityProductionAndCost[] production = new UtilityProductionAndCost[1];
        manageCost(costs[1],board);
        production[0] = new UtilityProductionAndCost(1,askPlayerResourceType());
        return production;
    }

    public void manageCost(UtilityProductionAndCost cost ,PersonalBoard board) {
        if (askPlayerWhatDeposit(board)) {
            board.getPersonalBox().getResourceMap().remove(cost.getResource(), 1);
        } else {
            //board.getStore()
        }
    }

    private boolean askPlayerWhatDeposit(PersonalBoard board){
        return true;
    }

    private Resource askPlayerResourceType(){
        return null;
    }
}
