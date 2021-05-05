package it.polimi.ingsw.client.model;

public class ResourceCost implements Requirements{
    private Resource resource;
    private int quantity;

    public ResourceCost(Resource resource, int quantity) {
        this.resource = resource;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "│"+ Utility.center(resource + ":"+ quantity,17)+"│\n";
    }
}
