package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.commonFiles.model.Resource;

public class ResourceAndDepot {
    private Resource resource;
    private int depot;

    public ResourceAndDepot(Resource resource, int depot) {
        this.resource = resource;
        this.depot = depot;
    }

    public Resource getResource() {
        return resource;
    }

    public int getDepot() {
        return depot;
    }
}
