package it.polimi.ingsw.model;

/**
 * class that represents the three different shelves of the Warehouse store
 * it is connected with a Composition link to the PersonalBoard
 */

public class WarehouseStore {
    private int size ;
    private int quantity;
    private Resource resource;


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setSize(int size) { this.size = size; }
    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public int getQuantity() {
        return quantity;
    }
    public int getSize() {
        return size;
    }
    public Resource getResource() {
        return resource;
    }


}
