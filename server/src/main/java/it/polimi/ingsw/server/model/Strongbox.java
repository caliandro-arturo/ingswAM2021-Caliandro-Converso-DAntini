package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.model.Resource;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents the strongbox storing the produced resources, it has unlimited space.
 * HashMap<Resource, Integer>
 * it is connected with a Composition link to the Board
 */


public class Strongbox {

    private final HashMap<Resource, Integer> resourceMap = new HashMap<Resource, Integer>() {{
        put(Resource.SERF, 0);
        put(Resource.COIN, 0);
        put(Resource.SHIELD, 0);
        put(Resource.STONE, 0);
    }};
    private final ArrayList<Resource> productionBox = new ArrayList<>();


    public HashMap<Resource, Integer> getResourceMap() {
        return resourceMap;
    }

    public ArrayList<Resource> getProductionBox() {
        return productionBox;
    }

    public void addToProdBox(Resource resource) {
        productionBox.add(resource);
    }

    /**
     * Check if the strongbox is empty.
     *
     * @return true if the strongbox is empty
     */
    public boolean isEmptyBox() {
        return resourceMap.get(Resource.SERF) == 0 && resourceMap.get(Resource.COIN) == 0
                && resourceMap.get(Resource.SHIELD) == 0 && resourceMap.get(Resource.STONE) == 0;
    }

    /**
     * Removes one of the specified resource, if it's >0.
     *
     * @param resource resource chose by the player to be removed
     */
    public void removeResource(Resource resource) {
        Integer temp = this.resourceMap.get(resource);
        if (temp > 0)
            this.resourceMap.replace(resource, temp, temp - 1);
        else
            throw new IllegalArgumentException("you don't own this resource");
    }

    /**
     * Adds resource to the strongbox in the Hashmap structured explained before.
     *
     * @param resource the resource the production produces
     */
    public void addProdResource(Resource resource) {
        Integer temp = this.resourceMap.get(resource);
        if (this.resourceMap.containsKey(resource))
            this.resourceMap.replace(resource, temp, temp + 1);
    }

    /**
     * Empty the temporary prodBox and fills the resourceMap of the strongbox.
     */
    public void emptyProdBox() {
        for (Resource res : productionBox) {
            Integer temp = this.resourceMap.get(res);
            this.resourceMap.replace(res, temp, temp + 1);
        }
    }

    @Override
    public String toString() {
        StringBuilder strongboxJson = new StringBuilder();
        strongboxJson.append("{");
        for (Resource r : resourceMap.keySet()) {
            strongboxJson.append("""
                    "%s": %d,
                    """.formatted(
                    r.name(),
                    resourceMap.get(r)
            ));
        }
        strongboxJson.deleteCharAt(strongboxJson.lastIndexOf(","));
        strongboxJson.append("}");
        return strongboxJson.toString();
    }
}