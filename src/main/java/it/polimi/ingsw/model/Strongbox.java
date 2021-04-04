package it.polimi.ingsw.model;

/**
 * class that represent the strongbox storing the resources produced, it has unlimited space stored in
 * HashMap<Resource, Integer>
 * it is connected with a Composition link to the PersonalBoard
 */

import java.util.HashMap;
public class Strongbox {

    private HashMap<Resource, Integer> resourceMap = new HashMap<>() ;

    public HashMap<Resource, Integer> getResourceMap() {
        return resourceMap;
    }

    public void setResourceMap(HashMap<Resource, Integer> resourceMap) { this.resourceMap = resourceMap; }

    /**
     * this is a specific constructor that initialises the hashmap once
     */
    public Strongbox() {
        this.resourceMap.put(Resource.SERF,0);
        this.resourceMap.put(Resource.COIN,0);
        this.resourceMap.put(Resource.SHIELD,0);
        this.resourceMap.put(Resource.STONE,0);

    }
    /**
     * this method adds resource to the strongbox in the Hashmap structured explained before
     * @param resource
     */
    public void addProdResource(Resource resource) {
        Integer temp=this.resourceMap.get(resource) ;
        if (this.resourceMap.containsKey(resource))
            this.resourceMap.replace(resource, temp, temp+1);
    }

    /**
     * this method remove one quantity of the specified resource, if it's >0
     * @param resource
     */
    public void removeResource(Resource resource) {
        Integer temp=this.resourceMap.get(resource);
        if (temp>0)
            this.resourceMap.replace(resource, temp, temp-1);


    }





}
