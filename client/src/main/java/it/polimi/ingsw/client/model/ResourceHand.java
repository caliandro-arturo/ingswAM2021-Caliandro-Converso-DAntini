package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.Resource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ResourceHand {
    private final ObservableList<Resource> resources = FXCollections.observableArrayList();

    public ObservableList<Resource> getResources() {
        return resources;
    }

    public void addResources(List<Resource> resources) {
        this.resources.addAll(resources);
    }

    public void addResource(Resource resource) {
        this.resources.add(resource);
    }

    public void removeResources(List<Resource> resources) {
        this.resources.removeAll(resources);
    }

    public void removeResource(Resource resource) {
        this.resources.remove(resource);
    }

    public boolean isEmpty() {
        return resources.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        resources.forEach(resource -> res.append(resource).append(" "));
        return res.toString();
    }
}
