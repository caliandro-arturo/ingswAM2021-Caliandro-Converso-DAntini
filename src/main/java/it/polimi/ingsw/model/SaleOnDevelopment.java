package it.polimi.ingsw.model;

public class SaleOnDevelopment implements LeaderPower{
    private Resource resource;

    public Resource getResource() {
        return resource;
    }

    public SaleOnDevelopment(Resource resource){
        this.resource = resource;
    }
}
