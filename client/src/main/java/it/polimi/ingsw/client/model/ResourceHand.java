package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.Resource;

import java.util.ArrayList;
import java.util.List;

public class ResourceHand {
    private final List<Resource> resources = new ArrayList<>();


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
