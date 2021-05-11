package it.polimi.ingsw.client.model;

import java.util.ArrayList;


/**
 * SpecialWarehouse class for client
 */
public class SpecialWarehouse implements LeaderPower{
    private ArrayList<Resource> resources;
    private Resource resourceType;

    public SpecialWarehouse( Resource resourceType) {
        this.resources = new ArrayList<>();
        this.resourceType = resourceType;
    }

    public void setResources(Resource resource) {
        resources.add(resource);
    }

    @Override
    public String toString() {
        if (resources.size()==0) {
            return "│" + Utility.center(resourceType + ": × ×",17) + "│\n";
        } else if (resources.size()==1){
            return "│" + Utility.center(resourceType + ": " + resourceType + " ×",17) +"│\n";
        } else{
            return "│" + Utility.center(resourceType + ": " + resourceType + " " + resourceType,17) + "│\n";
        }
    }
}
