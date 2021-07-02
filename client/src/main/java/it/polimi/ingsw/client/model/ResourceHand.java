package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.Resource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Representation of the resource hand.
 */
public class ResourceHand {
    private final ObservableList<Resource> resources = FXCollections.observableArrayList();

    public ObservableList<Resource> getResources() {
        return resources;
    }

    public boolean isEmpty() {
        return resources.isEmpty();
    }

    /**
     * Adds resources into the hand.
     * @param resources the resources to add
     */
    public void addResources(List<Resource> resources) {
        this.resources.addAll(resources);
    }

    /**
     * Adds a resource into the hand
     * @param resource the resource to add
     */
    public void addResource(Resource resource) {
        this.resources.add(resource);
    }

    /**
     * Removes the resources from the hand.
     * @param resources the resources to remove
     */
    public void removeResources(List<Resource> resources) {
        this.resources.removeAll(resources);
    }

    /**
     * Removes a resource from the hand.
     * @param resource the resource to remove from the hand
     */
    public void removeResource(Resource resource) {
        this.resources.remove(resource);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        resources.forEach(resource -> res.append(resource).append(" "));
        return res.toString();
    }
}
