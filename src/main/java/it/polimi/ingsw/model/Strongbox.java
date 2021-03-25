package it.polimi.ingsw.model;

/**
 * class that represent the strongbox storing the resources produced, it has unlimited space stored in
 * HashMap<Resource, Integer>
 * it is connected with a Composition link to the PersonalBoard
 */

import java.util.HashMap;
public class Strongbox {
    private HashMap<Resource, Integer> resourceMap = new HashMap<>();

    public void addProdResource(Resource resource) {

    }

    public HashMap<Resource, Integer> getResourceMap() {
        return resourceMap;
    }
}
