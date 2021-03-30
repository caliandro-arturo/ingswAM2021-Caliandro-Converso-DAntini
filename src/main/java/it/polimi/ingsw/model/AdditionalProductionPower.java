package it.polimi.ingsw.model;

public class AdditionalProductionPower  implements LeaderPower,Production{

    private Resource cost;
    private Resource production1 = Resource.FAITH;

    @Override
    public UtilityProductionAndCost[] startProduction(PersonalBoard Board) {
        UtilityProductionAndCost[] production = new UtilityProductionAndCost[1];
        production[0] = new UtilityProductionAndCost(1,production1);
        production[1] = new UtilityProductionAndCost(1,askPlayerResourceType());

        return production;
    }

    private Resource askPlayerResourceType(){
        return null;
    }
}
