package it.polimi.ingsw.model;

public class BoardProduction implements Production{

    @Override
    public UtilityProductionAndCost[] startProduction(PersonalBoard board) {
        UtilityProductionAndCost[] costs = new UtilityProductionAndCost[1];
        costs[0] = new UtilityProductionAndCost(1,askPlayerResourceType());
        costs[1] = new UtilityProductionAndCost(1,askPlayerResourceType());
        UtilityProductionAndCost[] production = new UtilityProductionAndCost[0];
        production[0] = new UtilityProductionAndCost(1,askPlayerResourceType());
        return production;
    }

    private Resource askPlayerResourceType(){
        return null;
    }
}
