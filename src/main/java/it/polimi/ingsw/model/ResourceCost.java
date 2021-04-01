package it.polimi.ingsw.model;

public class ResourceCost implements Requirements{
    private final UtilityProductionAndCost cost;

    public ResourceCost(UtilityProductionAndCost cost){
        this.cost = cost;
    }

    public UtilityProductionAndCost getCost(){
        return cost;
    }

    @Override
    public void checkRequirements (Player player) throws NotMetRequirementsException {

    }
}
