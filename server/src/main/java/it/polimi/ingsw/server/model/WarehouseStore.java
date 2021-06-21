package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.model.Resource;

import java.util.ArrayList;

/**
 * class that represents the three different shelves of the Warehouse store
 * it is connected with a Composition link to the Board
 */

public class WarehouseStore {
    private Resource typeOfResource = null;
    private ArrayList<Resource> resources = new ArrayList<>();
    private final int size;

    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }

    public void setTypeOfResource(Resource typeOfResource) {
        this.typeOfResource = typeOfResource;
    }

    public WarehouseStore(int size) {
        this.size = size;
    }

    public int getQuantity() {
        return resources.size();
    }

    public int getSize() {
        return size;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public Resource getTypeOfResource() {
        return typeOfResource;
    }

    /**
     * is used to check if there is space for a new resource in this depot
     * @param resource the player wants to check for space
     * @return true if there is space to store a new resource of the specified type, false otherwise
     */
    public boolean hasRoomForResource(Resource resource){
        if(resources.isEmpty())
            return true;
        else return typeOfResource==resource && resources.size() < size;
    }

    /**
     * add a resource compatibility to the rules of adding
     * @param resource the resource to add in the depot
     * @throws IllegalArgumentException if the player can't add this resource in this depot because the depot is full
     * or not matching with the current resource
     */
    public void addResource(Resource resource){
        if(hasRoomForResource(resource)){
            resources.add(resource);
            if(typeOfResource==null) {
                typeOfResource=resource;
            }
        }
        else
            throw new IllegalArgumentException ("you can't add this resource in this depot");
    }

    /**
     * take out a resource from this depot
     * @return the resource taken out from the depot
     * @throws IllegalArgumentException if the shelf is already empty
     */
    public Resource takeOutResource(){
        if(!resources.isEmpty()){
            Resource resToTake = resources.get(0);
            resources.remove(0);
            if(resources.isEmpty())
                typeOfResource=null;
            return resToTake;
        }
        else
            throw new IllegalArgumentException("This shelf is empty.");
    }

    @Override
    public String toString() {
        return """
                {
                    "resourceType": %s,
                    "quantity": %d
                }""".formatted(
                (typeOfResource != null ? "\"" + typeOfResource.name() + "\"" : null),
                getQuantity());
    }
}
