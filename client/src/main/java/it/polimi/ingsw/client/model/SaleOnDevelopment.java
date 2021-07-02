package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.utility.StringUtility;

/**
 * Representation of the sale on development cards of the leader.
 */
public class SaleOnDevelopment implements LeaderPower{
    private final Resource resource;

    public SaleOnDevelopment(Resource resource){
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }

    @Override
    public void activatePower(Board board) {
        board.setPowerSale(resource);
    }

    @Override
    public String toString() {
        return "│" + StringUtility.center("-1 " + resource,17) + "│\n";
    }
}
