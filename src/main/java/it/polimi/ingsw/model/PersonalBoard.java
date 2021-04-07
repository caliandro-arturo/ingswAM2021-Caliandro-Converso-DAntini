package it.polimi.ingsw.model;

/**
 * this class represents the personal board
 * each personal board is unique for a player and it's connected to it with a Composition link
 *
 */

import java.util.*;

public class PersonalBoard {

    private ArrayList<WarehouseStore> store = new ArrayList<>();
    private FaithTrack personalPath = new FaithTrack();
    private Strongbox personalBox = new Strongbox();
    private DevelopmentPlace[] personalDevelopmentSpace = new DevelopmentPlace[3];
    private LeaderCard[] leaderCards = new LeaderCard[2];
    private ArrayList<Production> ProductionList = new ArrayList<>();

    public DevelopmentPlace[] getPersonalDevelopmentSpace() {
        return personalDevelopmentSpace;
    }
    public ArrayList<Production> getProductionList() {
        return ProductionList;
    }
    public void setStore(ArrayList<WarehouseStore> store) { this.store = store;    }
    public Strongbox getPersonalBox() { return personalBox;    }
    public ArrayList<WarehouseStore> getStore() { return store;    }
    public FaithTrack getPersonalPath() {
        return personalPath;
    }
    public void setPersonalPath(FaithTrack personalPath) { this.personalPath = personalPath; }

    /**
     * this is a specific constructor that initialise the board and its elements
     */
    public PersonalBoard() {
        this.store.add(0,new WarehouseStore());
        this.store.add(1, new WarehouseStore());
        this.store.add(2, new WarehouseStore());
        this.store.get(0).setSize(1);
        this.store.get(1).setSize(2);
        this.store.get(2).setSize(3);
        this.personalPath.setPosition(0);
        this.personalPath.setScoreCard(0);
    }

    /**
     * this method is used from the Market to add a resource in the Warehouse store, checking if it's possible
     * another method will allow to move the resources in the Warehouse store
     * @param resource
     */

    public void addResource(Resource resource){
        boolean added = false;

        for(int i=0; i<3;i++){

            if((store.get(i).getResource()== resource || store.get(i).getResource()==null) && !added){

                if (store.get(i).getQuantity() < store.get(i).getSize() || store.get(i).getQuantity() == 0){
                    store.get(i).setResource(resource);
                    store.get(i).setQuantity(store.get(i).getQuantity() + 1);
                    added=true;
                }
                else
                    discardResource(resource);
            }
            else
                discardResource(resource);
        }
    }

    //TODO fare eccezione nel caso in cui la risorsa non c'è
    public void removeResource(Resource resource) {
        boolean removed= false;
        for (int i = 0; i < 3; i++) {

            if ((store.get(i).getResource() == resource)&& !removed) {
                store.get(i).setQuantity(store.get(i).getQuantity() - 1);
                removed= true;
            }

        }
    }
    /**
     * this method is called from addResource when it's not possible to add more resources to the Warehouse store
     * @param resource
     */
    public void discardResource(Resource resource){}

}
