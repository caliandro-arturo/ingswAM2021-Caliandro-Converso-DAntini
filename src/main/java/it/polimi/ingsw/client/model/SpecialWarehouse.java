package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.Resource;

import java.util.ArrayList;

public class SpecialWarehouse implements LeaderPower{
    ArrayList<Resource> resources = new ArrayList<>();
    Resource resourceType;

    public SpecialWarehouse(ArrayList<Resource> warehouse, Resource resource) {
        this.resources = warehouse;
        resourceType = resource;
    }

    @Override
    public void activatePower() {

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
