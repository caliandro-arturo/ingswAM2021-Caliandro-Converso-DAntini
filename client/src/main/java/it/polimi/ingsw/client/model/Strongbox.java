package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.utility.CLIColor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;

/**
 * Representation of the strongbox for client
 */
public class Strongbox {
    private final ObservableList<Integer> strongboxList;

    public Strongbox() {
        this.strongboxList = FXCollections.observableArrayList(Arrays.asList(0, 0, 0, 0));
    }

    public int getResourcesQuantity(Resource resource) {
        return strongboxList.get(Utility.mapStrongbox.get(resource));
    }

    public ObservableList<Integer> getStrongboxList() {
        return strongboxList;
    }

    /**
     * Adds a num of resource in the strongbox.
     *
     * @param num      number of the resources to add
     * @param resource resource to add
     */
    public void addResources(int num, Resource resource) {
        strongboxList.set(Utility.mapStrongbox.get(resource), strongboxList.get(Utility.mapStrongbox.get(resource)) + num);
    }

    /**
     * Removes a resource from the strongbox.
     *
     * @param resource resource to add
     */
    public void removeResources(Resource resource) {
        strongboxList.set(Utility.mapStrongbox.get(resource), strongboxList.get(Utility.mapStrongbox.get(resource)) - 1);
    }

    /**
     * representation method for Strongbox (CLI)
     *
     * @return String with the representation
     */
    @Override
    public String toString() {
        return "" +
                "│          " + "\u001b[43m" + "StrongBox" + CLIColor.ANSI_RESET + "       \n" +
                "│  ┌──────────────────────┐\n" +
                "│  │   " + Resource.COIN + " :" + String.format("%3d", strongboxList.get(0)) +
                "     " + Resource.SERF + " :" + String.format("%3d", strongboxList.get(1)) +
                "  │\n" +
                "│  │                      │\n" +
                "│  │   " + Resource.STONE + " :" + String.format("%3d", strongboxList.get(2)) +
                "     " + Resource.SHIELD + " :" + String.format("%3d", strongboxList.get(3)) +
                "  │\n" +
                "│  └──────────────────────┘";
    }

    //for testing purpose

    public Integer[] getResources() {
        return strongboxList.toArray(new Integer[0]);
    }

    public void setResources(int quantity, int depot) {
        strongboxList.set(depot, quantity);
    }
}
