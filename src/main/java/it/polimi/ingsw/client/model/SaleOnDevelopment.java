package it.polimi.ingsw.client.model;

public class SaleOnDevelopment implements LeaderPower{
    private Resource resource;

    public SaleOnDevelopment(Resource resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "│      -1 " + resource + "       │\n";
    }
}
