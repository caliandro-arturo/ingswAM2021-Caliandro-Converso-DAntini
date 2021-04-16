package it.polimi.ingsw.model;
import java.util.HashMap;

/**
 * class that represent the strongbox storing the resources produced, it has unlimited space stored in
 * HashMap<Resource, Integer>
 * it is connected with a Composition link to the PersonalBoard
 */


public class Strongbox {

    private final HashMap<Resource, Integer> resourceMap = new HashMap<Resource, Integer>(){{
        put(Resource.SERF,0);
        put(Resource.COIN,0);
        put(Resource.SHIELD,0);
        put(Resource.STONE,0);
    }} ;

    public HashMap<Resource, Integer> getResourceMap() { return resourceMap;    }

    /**
     * is used to check if the strongbox is empty
     * @return true if the strongbox is empty
     */
    public boolean isEmptyBox(){
        return resourceMap.get(Resource.SERF) == 0 && resourceMap.get(Resource.COIN) == 0
                && resourceMap.get(Resource.SHIELD) == 0 && resourceMap.get(Resource.STONE) == 0;
    }

    /**
     * adds resource to the strongbox in the Hashmap structured explained before
     * @param resource the resource the production produces
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
        else
            throw new IllegalArgumentException("you don't own this resource");
    }
}