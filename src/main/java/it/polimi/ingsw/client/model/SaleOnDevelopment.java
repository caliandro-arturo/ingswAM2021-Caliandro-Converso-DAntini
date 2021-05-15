package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.Resource;

public class SaleOnDevelopment implements LeaderPower{
    private final Resource resource;

    public SaleOnDevelopment(Resource resource){
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }

    @Override
    public void activatePower() {

    }

    @Override
    public String toString() {
        return "│" + Utility.center("-1 " + resource,17) + "│\n";
    }
}
