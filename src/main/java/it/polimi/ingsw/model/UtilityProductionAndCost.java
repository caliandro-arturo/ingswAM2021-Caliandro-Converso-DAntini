package it.polimi.ingsw.model;

/**
 *  an utily class to help in the costruction of various classes
 */
public class UtilityProductionAndCost {
    private Resource resource;
    private int quantity;

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
}
