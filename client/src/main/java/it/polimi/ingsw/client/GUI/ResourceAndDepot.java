package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.commonFiles.model.Resource;

/**
 * Represents the pair resource - depot. It's used for payment and productions.
 */
public class ResourceAndDepot {
    private final Resource resource;
    private final int depot;

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
