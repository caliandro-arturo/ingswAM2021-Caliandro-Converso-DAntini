package it.polimi.ingsw.client.model;

public class AdditionalProductionPower implements LeaderPower{
    private Resource resource;

    public AdditionalProductionPower(Resource resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "│" + Utility.center(resource + " -> ?, " + Resource.FAITH ,17) +"│\n";
    }
}
