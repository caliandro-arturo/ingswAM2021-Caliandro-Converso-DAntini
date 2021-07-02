package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.model.Resource;

/**
 * Leader power that makes a discount on the development card cost.
 */
public class SaleOnDevelopment implements LeaderPower {
    private final Resource resource;

    public Resource getResource() {
        return resource;
    }

    public SaleOnDevelopment(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void activatePower(Player player) {
        player.addSale(getResource());
    }

    @Override
    public String[] identifier() {
        return new String[]{"saleOnDevelopment", resource.toString()};
    }
}
