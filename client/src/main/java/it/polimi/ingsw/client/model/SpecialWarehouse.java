package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.utility.StringUtility;

import java.util.ArrayList;

public class SpecialWarehouse implements LeaderPower{
    ArrayList<Resource> resources = new ArrayList<>();
    Resource resourceType;

    public SpecialWarehouse(Resource resource) {
        resourceType = resource;
    }

    @Override
    public void activatePower() {

    }

    @Override
    public String toString() {
        if (resources.size()==0) {
            return "│" + StringUtility.center(resourceType + ": × ×",17) + "│\n";
        } else if (resources.size()==1){
            return "│" + StringUtility.center(resourceType + ": " + resourceType + " ×",17) +"│\n";
        } else{
            return "│" + StringUtility.center(resourceType + ": " + resourceType + " " + resourceType,17) + "│\n";
        }
    }
}
