package it.polimi.ingsw.commonFiles.model;

import it.polimi.ingsw.commonFiles.model.Resource;

/**
 *  an utility class to help in the construction of various classes
 */
public class UtilityProductionAndCost {
    private final Resource resource;
    private final int quantity;

    public UtilityProductionAndCost(int quantity, Resource resource){
        this.quantity = quantity;
        this.resource = resource;
    }

    public Resource getResource(){
        return resource;
    }

    public int getQuantity(){
        return quantity;
    }

    @Override
    public String toString() {
        return "" +resource+ "" + quantity;
    }
}
