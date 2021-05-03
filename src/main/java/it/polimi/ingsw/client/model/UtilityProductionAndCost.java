package it.polimi.ingsw.client.model;

public class UtilityProductionAndCost {
    private Resource resource;
    private int quantity;

    public UtilityProductionAndCost(Resource resource,int quantity){
        this.resource = resource;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "" +resource.toString()+ "" + quantity;
    }
}
