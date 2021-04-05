package it.polimi.ingsw.model;

import java.util.ArrayList;

public class ResourceCost implements Requirements{
    private final UtilityProductionAndCost cost;

    public ResourceCost(UtilityProductionAndCost cost){
        this.cost = cost;
    }

    public UtilityProductionAndCost getCost(){
        return cost;
    }

    @Override
    public boolean checkRequirements (Player player) {
        int i = cost.getQuantity();
        for (WarehouseStore store: player.getBoard().getStore()){
            if (cost.getResource() == store.getResource()){
                i = i - store.getQuantity();
            }
        }
        if (player.getBoard().getPersonalBox().getResourceMap().get(cost.getResource()) >= i) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void getRequirements() {
        System.out.println("to deploy this card you must have: " +cost.getQuantity()+""+cost.getResource());
    }
}
