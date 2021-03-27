package it.polimi.ingsw.model;

/**
 * this class represents the personal board
 * each personal board is unique for a player and it's connected to it with a Composition link
 *
 */

import java.util.HashMap;

public class PersonalBoard {

    private WarehouseStore[] store = new WarehouseStore[3];
    private final FaithTrack personalPath = new FaithTrack();
    private final Strongbox personalBox = new Strongbox();
    private final DevelopmentPlace[] personalDevelopmentSpace = new DevelopmentPlace[3];
    private LeaderCard[] leaderCards = new LeaderCard[2];
    //boardProduction


    public void setStore(WarehouseStore[] store) {
        this.store = store;

    }

    public WarehouseStore[] getStore() {

        return store;
    }

    public FaithTrack getPersonalPath() {
        return personalPath;
    }

    /**
     * this method is used in the beginning of the game to reset and build the Warehouse store
     */
    public void resetPersonalBoard(){
        store[0]= new WarehouseStore();
        store[1]= new WarehouseStore();
        store[2]= new WarehouseStore();
        store[0].setSize(1);
        store[1].setSize(2);
        store[2].setSize(3);
        personalPath.setPosition(0);
        personalPath.setScoreCard(0);
        personalBox.getResourceMap().put(Resource.SERF,0);
        personalBox.getResourceMap().put(Resource.COIN,0);
        personalBox.getResourceMap().put(Resource.SHIELD,0);
        personalBox.getResourceMap().put(Resource.STONE,0);

    }

    /**
     * this method is used from the Market to add a resource in the Warehouse store, checking if it's possible
     * another method will allow to move the resources in the Warehouse store
     * @param resource
     */

    public void addResource(Resource resource){
        int i;

        for(i=0; i<3;i++){

            if(store[i].getResource()== resource || store[i].getResource()==null){

                if (store[i].getQuantity() < store[i].getSize() || store[i].getQuantity() == 0){
                    store[i].setResource(resource);
                    store[i].setQuantity(store[i].getQuantity() + 1);
                }
                else
                    discardResource(resource);
            }
            else
                discardResource(resource);
        }
    }

    /**
     * this method is called from addResource when it's not possible to add more resources to the Warehouse store
     * @param resource
     */
    public void discardResource(Resource resource){}
}