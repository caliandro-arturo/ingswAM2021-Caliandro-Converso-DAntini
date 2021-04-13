package it.polimi.ingsw.model;

/**
 * this class represents the personal board
 * each personal board is unique for a player and it's connected to it with a Composition link
 *
 */

import java.util.*;

public class PersonalBoard {

    private ArrayList<WarehouseStore> store;
    private FaithTrack personalPath;
    private Strongbox personalBox ;
    private DevelopmentPlace[] personalDevelopmentSpace;
    private LeaderCard[] leaderCards ;
    private ArrayList<Production> ProductionList ;
    private Game game;

    public DevelopmentPlace[] getPersonalDevelopmentSpace() {
        return personalDevelopmentSpace;
    }
    public ArrayList<Production> getProductionList() {
        return ProductionList;
    }
    public Strongbox getPersonalBox() {
        return personalBox;
    }
    public ArrayList<WarehouseStore> getStore() {
        return store;
    }
    public FaithTrack getPersonalPath() {
        return personalPath;
    }
    public LeaderCard[] getLeaderCards() {
        return leaderCards;
    }
    public Game getGame() {
        return game;
    }

    public void setStore(ArrayList<WarehouseStore> store) {
        this.store = store;
    }
    public void setPersonalPath(FaithTrack personalPath) {
        this.personalPath = personalPath;
    }
    public void setLeaderCards(LeaderCard[] leaderCards) {
        this.leaderCards = leaderCards;
    }
    public void setPersonalBox(Strongbox personalBox) {
        this.personalBox = personalBox;
    }
    public void setPersonalDevelopmentSpace(DevelopmentPlace[] personalDevelopmentSpace) {
        this.personalDevelopmentSpace = personalDevelopmentSpace;
    }
    public void setProductionList(ArrayList<Production> productionList) {
        ProductionList = productionList;
    }
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * this is a specific constructor that initialise the board and its elements
     */
    public PersonalBoard() {
        this.store = new ArrayList<>();
        this.personalPath = new FaithTrack(1,0);
        this.personalBox = new Strongbox();
        this.personalDevelopmentSpace = new DevelopmentPlace[3];
        this.leaderCards = new LeaderCard[2];
        this.ProductionList = new ArrayList<>();
        this.personalDevelopmentSpace[0]= new DevelopmentPlace();
        this.personalDevelopmentSpace[1]= new DevelopmentPlace();
        this.personalDevelopmentSpace[2]= new DevelopmentPlace();
        this.store.add(0,new WarehouseStore());
        this.store.add(1, new WarehouseStore());
        this.store.add(2, new WarehouseStore());
        this.store.get(0).setSize(1);
        this.store.get(1).setSize(2);
        this.store.get(2).setSize(3);
    }

    /**
     * this method is used from the Market to add a resource in the Warehouse store, checking if it's possible
     * another method will allow to move the resources in the Warehouse store
     * @param resource
     */
    public void addResource(Resource resource){
        boolean added = false;
        boolean discarded = false;
        for(int i=0; i<3;i++) {

            if ((store.get(i).getResource() == resource) && !added && !discarded) {
                if (store.get(i).getQuantity() < store.get(i).getSize()) {
                    store.get(i).setResource(resource);
                    store.get(i).setQuantity(store.get(i).getQuantity() + 1);
                    added = true;
                }
                else
                    discardResource();
                    discarded = true;
            }
        }
        for(int j=0; j<3;j++) {
            if ((store.get(j).getResource() == null) && !added && !discarded) {
                if (store.get(j).getQuantity() < store.get(j).getSize()) {
                    store.get(j).setResource(resource);
                    store.get(j).setQuantity(store.get(j).getQuantity() + 1);
                    added = true;

                }
            }
            if(j==2 && !added && !discarded){
                discardResource();
                discarded = true;
            }
        }
    }

    //TODO fare eccezione nel caso in cui la risorsa non c'è
    public void removeResource(Resource resource) {
        boolean removed= false;
        for (int i = 0; i < 3; i++) {

            if ((store.get(i).getResource() == resource) && !removed) {
                store.get(i).setQuantity(store.get(i).getQuantity() - 1);
                if(store.get(i).getQuantity()==0)
                    store.get(i).setResource(null);

                removed= true;
            }

        }
    }

    /**
     * this method is called from addResource when it's not possible to add more resources to the Warehouse store
     */
    public void discardResource(){
        for(Player player: game.getPlayers()){
            if(!player.equals(game.getCurrentPlayer()))
                player.getBoard().getPersonalPath().setPosition(player.getBoard().getPersonalPath().getPosition()+1);
        }
        for(Player player: game.getPlayers()){
            if(!player.equals(game.getCurrentPlayer()))
                player.getBoard().getPersonalPath().checkPosition();
        }
    }

    /**
     * this method is used to check if there is a free space for a new devCard
     * @param devCard
     */
    public boolean checkSpaceForNewCard(DevelopmentCard devCard) {
        return !freePlaces(devCard).isEmpty();
    }

    public void addCard(DevelopmentCard devCard, int pos){
        pos--;
        if(personalDevelopmentSpace[pos].hasRoomForCard(devCard.getLevel())) {
            personalDevelopmentSpace[pos].getDevelopmentCards().push(devCard);
        }
    }

    /**
     * this method shows to the view which places are free to put a card in
     * @param devCard
     * @return
     */
    public ArrayList<DevelopmentPlace> freePlaces(DevelopmentCard devCard){
        int level = devCard.getLevel();
        ArrayList<DevelopmentPlace> developmentPlaces = new ArrayList<>();
        for(DevelopmentPlace dev : personalDevelopmentSpace){
            if(dev.hasRoomForCard(level))
                developmentPlaces.add(dev);
        }
        return developmentPlaces;
    }
}
