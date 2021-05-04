package it.polimi.ingsw.client.model;

import java.util.ArrayList;

public class SpecialWarehouse implements LeaderPower{
    private ArrayList<Resource> resources;
    private Resource resourceType;

    public SpecialWarehouse( Resource resourceType) {
        this.resources = new ArrayList<>();
        this.resourceType = resourceType;
    }

    @Override
    public String toString() {
        if (resources.size()==0) {
            return "│     " + resourceType + ": × ×      │\n";
        } else if (resources.size()==1){
            return "│     " + resourceType + ": v ×      │\n";
        } else{
            return "│     " + resourceType + ": v v      │\n";
        }
    }
}
