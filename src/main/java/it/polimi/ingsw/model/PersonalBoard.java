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
    private ArrayList<Resource> resHand;



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
    public ArrayList<Resource> getResHand() {
        return resHand;
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
        this.resHand = new ArrayList<>();
        this.store = new ArrayList<WarehouseStore>() {{
            addAll(Arrays.asList(
                    new WarehouseStore(1),
                    new WarehouseStore(2),
                    new WarehouseStore(3)
            ));
        }};
        this.personalPath = new FaithTrack();
        this.personalBox = new Strongbox();
        this.personalDevelopmentSpace = new DevelopmentPlace[3];
        this.leaderCards = new LeaderCard[2];
        this.ProductionList = new ArrayList<>();
        this.personalDevelopmentSpace[0] = new DevelopmentPlace();
        this.personalDevelopmentSpace[1] = new DevelopmentPlace();
        this.personalDevelopmentSpace[2] = new DevelopmentPlace();
    }

    /**new version of addResource
     *
     * @param resource
     */
    public void deployResource(Resource resource, int pos) {
        if(!resHand.contains(resource))
            throw new IllegalArgumentException("you don't have this resource.");
        else if (!store.get(pos).hasRoomForResource(resource))
            throw new IllegalArgumentException("you can't add this resource in this depot");
        else
            store.get(pos).addResource(resource);
            resHand.remove(resource);
    }

    public void takeOutResource(int pos){
        resHand.add(store.get(pos).takeOutResource());
    }

    /**
     * this method is called from addResource when it's not possible to add more resources to the Warehouse store
     */
    public void discardResource(Resource resource){
        if(!resHand.contains(resource)) {
            resHand.remove(resource);
            for (Player player : game.getPlayers()) {
                if (!player.equals(game.getCurrentPlayer()))
                    player.getBoard().getPersonalPath().setPosition(player.getBoard().getPersonalPath().getPosition() + 1);
            }
            for (Player player : game.getPlayers()) {
                if (!player.equals(game.getCurrentPlayer()))
                    player.getBoard().getPersonalPath().checkPosition();
            }
        }
        else
            throw new IllegalArgumentException("this resource is not in your hand");
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

    public void addResource(Resource resource){
        resHand.add(resource);
    }

}
