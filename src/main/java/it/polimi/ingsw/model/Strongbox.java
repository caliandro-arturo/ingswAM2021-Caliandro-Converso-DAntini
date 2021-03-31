package it.polimi.ingsw.model;

/**
 * class that represent the strongbox storing the resources produced, it has unlimited space stored in
 * HashMap<Resource, Integer>
 * it is connected with a Composition link to the PersonalBoard
 */

import java.util.HashMap;
public class Strongbox {

    private HashMap<Resource, Integer> resourceMap = new HashMap<>();

    /**
     * this method adds resource to the strongbox in the Hashmap structured explained before
     * @param resource
     */
    public void addProdResource(Resource resource) {
        Integer temp=this.resourceMap.get(resource) ;
        if (this.resourceMap.containsKey(resource))
            this.resourceMap.replace(resource, temp, temp+1);
    }

    public void removeResource(Resource resource){
        resourceMap.remove(resource, 1);
    }


    public HashMap<Resource, Integer> getResourceMap() {
        return resourceMap;
    }


}
