package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.model.Production;
import it.polimi.ingsw.commonFiles.model.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * this class represents the personal board
 * each board is unique for a player and it's connected to it with a Composition link
 *
 */
public class Board {

    private final Player player;
    private ArrayList<WarehouseStore> store;
    private FaithTrack faithTrack;
    private final Strongbox strongbox;
    private final DevelopmentPlace[] developmentSpace;
    private final ArrayList<Production> productionList;
    private final ArrayList<LeaderCard> activeLeaders;
    private Game game;
    private ArrayList<Resource> resHand;

    public ArrayList<WarehouseStore> getStore() {
        return store;
    }
    public FaithTrack getFaithTrack() {
        return faithTrack;
    }
    public Strongbox getStrongbox() {
        return strongbox;
    }
    public DevelopmentPlace[] getDevelopmentSpace() {
        return developmentSpace;
    }
    public ArrayList<Production> getProductionList() {
        return productionList;
    }
    public ArrayList<LeaderCard> getActiveLeaders() {
        return activeLeaders;
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
    public void setFaithTrack(FaithTrack faithTrack) {
        this.faithTrack = faithTrack;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    public void setResHand(ArrayList<Resource> resHand) {
        this.resHand = resHand;
    }

    /**
     * this is a specific constructor that initialise the board and its elements
     */
    public Board(Player player) {
        this.player = player;
        this.resHand = new ArrayList<>();
        this.store = new ArrayList<WarehouseStore>() {{
            addAll(Arrays.asList(
                    new WarehouseStore(1),
                    new WarehouseStore(2),
                    new WarehouseStore(3)
            ));
        }};
        this.faithTrack = new FaithTrack(player);
        this.strongbox = new Strongbox();
        this.developmentSpace = new DevelopmentPlace[3];
        this.productionList = new ArrayList<Production>(){{
            add(new BoardProduction());
        }};
        this.activeLeaders = new ArrayList<>();
        this.developmentSpace[0] = new DevelopmentPlace();
        this.developmentSpace[1] = new DevelopmentPlace();
        this.developmentSpace[2] = new DevelopmentPlace();
    }

    /** used to deploy a resource the player is holding in the hand, the hand must empty
     *
     * @param resource the resource you want to store in warehouse
     * @param pos the number of the shelf you want to store in
     */
    public void deployResource(Resource resource, int pos) {
        pos--;
        if (!resHand.contains(resource))
            throw new IllegalArgumentException("You don't have this resource.");
        WarehouseStore depot = store.get(pos);
        if (!(depot.hasRoomForResource(resource) && allowedResourceTypes(pos).contains(resource)))
            throw new IllegalArgumentException("You can't add this resource in this depot.");
        else {
            store.get(pos).addResource(resource);
            resHand.remove(resource);
        }
    }

    private List<Resource> allowedResourceTypes(int pos) {
        if (store.get(pos).getTypeOfResource() != null)
            return Collections.singletonList(store.get(pos).getTypeOfResource());
        else {
            List<Resource> resources = new ArrayList<>(Arrays.asList(Resource.values()));
            for (WarehouseStore w :
                    store.subList(0, 3)) {
                if (w.getTypeOfResource() != null)
                    resources.remove(w.getTypeOfResource());
            }
            return resources;
        }
    }

    /**
     * used to take out a resource when the player want to rearrange the position of the resources in the warehouse
     *
     * @param pos the number of the shelf you want to remove a resource from
     */
    public void takeOutResource(int pos){
        pos--;
        resHand.add(store.get(pos).takeOutResource());
    }

    /**
     * discard a resource that can't be stored or arranged. gives +1 faith resource to the other players
     * @param resource the resource you want to discard
     * @throws GameException.IllegalMove it's thrown if you ask to discard a resource you don't have in hand
     */
    public void discardResource(Resource resource) throws GameException.IllegalMove {
        if(resHand.contains(resource)) {
            resHand.remove(resource);
            for (Player player : game.getPlayers()) {
                if (!player.equals(game.getCurrentPlayer()))
                    player.getBoard().getFaithTrack().increasePosition();
            }
            for (Player player : game.getPlayers()) {
                if (!player.equals(game.getCurrentPlayer()))
                    player.getBoard().getFaithTrack().checkPosition();
            }
        }
        else
            throw new GameException.IllegalMove();
    }

    /**
     * this method is used to check if there is a free space for a new devCard
     * @param devCard the card that the player wants to buy
     * @return true if the player can place the card
     */
    public boolean checkSpaceForNewCard(DevelopmentCard devCard) {
        return !freePlaces(devCard).isEmpty();
    }

    /** used by a player to add a devcard in a certain place of the 3
     *
     * @param devCard the card the player needs to add
     * @param pos chosen position where to stack
     */
    public void addCard(DevelopmentCard devCard, int pos){
        pos--;
        if(developmentSpace[pos].hasRoomForCard(devCard.getLevel())) {
            developmentSpace[pos].getDevelopmentCards().push(devCard);
        }
    }

    /**
     * this method shows to the view which places are free to put a card in
     * @param devCard tha card that the player want to buy
     * @return a new list of the free places
     */
    public ArrayList<DevelopmentPlace> freePlaces(DevelopmentCard devCard){
        int level = devCard.getLevel();
        ArrayList<DevelopmentPlace> developmentPlaces = new ArrayList<>();
        for(DevelopmentPlace dev : developmentSpace){
            if(dev.hasRoomForCard(level))
                developmentPlaces.add(dev);
        }
        return developmentPlaces;
    }

    /**
     * used by the market to add a resource to the hand of the player. this hand must empty
     * this can also be used to arrange the resources in the warehouse
     * @param resource the resource to add in the hand
     */
    public void addResource(Resource resource){
        resHand.add(resource);
    }

    /**
     * show the top of the leader cards stack
     * @return a list with the 0/1/2/3 dev cards are on top in this moment
     */
    public ArrayList<DevelopmentCard> showActiveDevCards(){
        ArrayList<DevelopmentCard> activeDevCards = new ArrayList<>();
        for(DevelopmentPlace dev : developmentSpace){
            if(!dev.getDevelopmentCards().isEmpty())
                activeDevCards.add(dev.getDevelopmentCards().peek());

        }
        return activeDevCards;
    }

    /**
     * used to add a new leader card in the personal board
     * @param leader the leader you want to add in the personal board
     */
    public void addActiveLeader(LeaderCard leader){
        if(activeLeaders.size()<2){
            activeLeaders.add(leader);
        }
        else
            throw new IllegalArgumentException("you don't have more space for another leader");
    }

    @Override
    public String toString() {
        StringBuilder boardJson = new StringBuilder();
        boardJson.append("""
                {
                    "faithTrack": %s,
                    "leaderCards": [
                """.formatted(faithTrack));
        for (LeaderCard l :
                activeLeaders) {
            boardJson.append(l).append(",\n");
        }
        if (!activeLeaders.isEmpty())
            boardJson.deleteCharAt(boardJson.lastIndexOf(","));
        boardJson.append("""
                    ],
                    "warehouseStores": [
                """);
        for (WarehouseStore w : store) {
            boardJson.append(w).append(",\n");
        }
        boardJson.deleteCharAt(boardJson.lastIndexOf(","));
        boardJson.append("""
                    ],
                    "strongBox": %s,
                    "developmentPlaces": [
                """.formatted(strongbox));
        for (DevelopmentPlace devPlace : developmentSpace) {
            boardJson.append(devPlace).append(",\n");
        }
        boardJson.deleteCharAt(boardJson.lastIndexOf(","));
        boardJson.append("""
                    ],
                    "resourceHand": [
                """);
        for (Resource r : resHand) {
            boardJson.append("\"").append(r.name()).append("\",\n");
        }
        if (!resHand.isEmpty())
            boardJson.deleteCharAt(boardJson.lastIndexOf(","));
        boardJson.append("""
                    ]
                }""");
        return boardJson.toString();
    }
}
