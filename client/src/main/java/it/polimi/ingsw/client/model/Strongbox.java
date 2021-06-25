package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.utility.CLIColor;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;

/**
 * light version of Strongbox class for representation purposes
 */
public class Strongbox {

    private int[] resources = new int[]{0,0,0,0};


    public int[] getResources() {
        return resources;
    }

    public int getSpecificResourcesQuantity(Resource resource){
        return resources[Utility.mapStrongbox.get(resource)];
    }

    /**
     * add a num of resource in the strongbox
     * @param num : number of the resources to add
     * @param resource : resource to add
     */
    public void addResources(int num, Resource resource) {
        this.resources[Utility.mapStrongbox.get(resource)] += num;
    }

    /**
     * remove a resource from the strongbox
     * @param resource : resource to add
     */
    public void removeResources(Resource resource) {
        this.resources[Utility.mapStrongbox.get(resource)]--;
    }

    /**
     * representation method for Strongbox (CLI)
     * @return String with the representation
     */
    @Override
    public String toString(){
        String boxArt = "" +
                "│          " + "\u001b[43m" + "StrongBox" + CLIColor.ANSI_RESET + "       \n" +
                "│  ┌──────────────────────┐\n" +
                "│  │   " + Resource.COIN + " :" + String.format("%3d", resources[0]) +
                "     " + Resource.SERF + " :" + String.format("%3d", resources[1]) +
                "  │\n" +
                "│  │                      │\n" +
                "│  │   " + Resource.STONE + " :" + String.format("%3d", resources[2]) +
                "     " + Resource.SHIELD + " :" + String.format("%3d", resources[3]) +
                "  │\n" +
                "│  └──────────────────────┘";
        return boxArt;
    }

    //for testing purpose


    public void setResources(int quantity, int depot) {
        this.resources[depot] = quantity;
    }
}
