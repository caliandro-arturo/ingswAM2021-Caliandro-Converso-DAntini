package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;
import it.polimi.ingsw.commonFiles.utility.StringUtility;

public class ResourceCost implements Requirements{
    private final UtilityProductionAndCost cost;

    public ResourceCost(UtilityProductionAndCost cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        Resource resource = cost.getResource();
        int quantity = cost.getQuantity();
        return "│"+ StringUtility.center(resource + ":"+ quantity,17)+"│\n";
    }
}
