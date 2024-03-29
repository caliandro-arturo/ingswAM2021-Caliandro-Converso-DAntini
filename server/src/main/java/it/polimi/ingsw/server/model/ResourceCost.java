package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;

/**
 * Resource requirements.
 */
public class ResourceCost implements Requirements {
    private final UtilityProductionAndCost cost;

    public ResourceCost(UtilityProductionAndCost cost){
        this.cost = cost;
    }

    @Override
    public boolean checkRequirements (Player player) {
        int i = cost.getQuantity();
        for (WarehouseStore store: player.getBoard().getStore()){
            if (cost.getResource() == store.getTypeOfResource()){
                i = i - store.getQuantity();
            }
        }
        return player.getBoard().getStrongbox().getResourceMap().get(cost.getResource()) >= i;
    }

    @Override
    public String[] identifier() {
        return new String[]{"resourceCost", cost.getResource().toString(), 
                Integer.toString(cost.getQuantity())} ;
    }
}
