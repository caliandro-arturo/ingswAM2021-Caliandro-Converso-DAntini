package it.polimi.ingsw.model;

import java.util.ArrayList;

/**
 * this class implements all the methods used by a player
 */
public class Player {
    private String username;
    private PersonalBoard board;
    private final ArrayList<LeaderCard> leaderCards;
    private final ArrayList<Resource> whiteAlt;
    private final ArrayList<Resource> sale;
    private Game game;
    private final int[] victoryPoints;
    private int whiteMarbleChoices = 0;
    private int initialResources = 0;

    /**
     * customised constructor for player that initialises all attributes a player needs
     * @param username chosen by the player
     */
    public Player(String username){
        this.username = username;
        this.board = new PersonalBoard();
        this.leaderCards = new ArrayList<>();
        this.whiteAlt = new ArrayList<>();
        this.sale = new ArrayList<>();
        victoryPoints = new int[5];
    }

    public String getUsername() {
        return username;
    }
    public PersonalBoard getBoard() {
        return board;
    }
    public ArrayList<LeaderCard> getLeaderCards() {
        return leaderCards;
    }
    public ArrayList<Resource> getWhiteAlt() {
        return whiteAlt;
    }
    public ArrayList<Resource> getSale() {
        return sale;
    }
    public Game getGame() {
        return game;
    }
    /**
     * in the last turn this method sum all the victory points the player is entitled to
     * <p> index 0 faith path VP
     * <p> index 1 dev card VP
     * <p> index 2 lead card VP
     * <p> index 3 Pope's favor VP
     * <p> index 4 resource VP
     * @return array of int containing VictoryPoints already summed in different indexes
     */
    public int[] getVictoryPoints(){
        // index 0 faith path VP
        int pos = getBoard().getPersonalPath().getPosition();
        if(pos>=3 && pos<6) {
            victoryPoints[0]=1;
        }else if(pos>=6 && pos<9) {
            victoryPoints[0]=2;
        }else if(pos>=9 && pos<12) {
            victoryPoints[0]=4;
        }else if(pos>=12 && pos<15){
            victoryPoints[0]=6;
        }else if(pos>=15 && pos<18){
            victoryPoints[0]=9;
        }else if(pos>=18 && pos<21){
            victoryPoints[0]=12;
        }else if(pos>=21 && pos<24) {
            victoryPoints[0]=16;
        }else if(pos==24) {
            victoryPoints[0] = 20;
        }
        // index 1 dev card VP
        for(DevelopmentPlace devPlace: board.getPersonalDevelopmentSpace()){
            for(DevelopmentCard devCard : devPlace.getDevelopmentCards()){
                victoryPoints[1]+=devCard.getVictoryPoints();
            }
        }
        // index 2 lead card VP
        for(LeaderCard lead: getBoard().getActiveLeaders()){
            victoryPoints[2] += lead.getVictoryPoints();
        }
        // index 3 Pope's favor VP
        victoryPoints[3]= board.getPersonalPath().getScoreCard();
        // index 4 resource VP
        int boxRes = 0;
        int storeRes = 0;
        if(!board.getPersonalBox().isEmptyBox()) {
            boxRes += board.getPersonalBox().getResourceMap().get(Resource.SERF);
            boxRes += board.getPersonalBox().getResourceMap().get(Resource.COIN);
            boxRes += board.getPersonalBox().getResourceMap().get(Resource.SHIELD);
            boxRes += board.getPersonalBox().getResourceMap().get(Resource.STONE);
        }
        for( WarehouseStore stores: board.getStore()){
            storeRes =+ stores.getQuantity();
        }
        victoryPoints[4]= (boxRes+storeRes)/5;
        return victoryPoints;
    }
    public int getWhiteMarbleChoices() {
        return whiteMarbleChoices;
    }
    public int getInitialResources() {
        return initialResources;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setBoard(PersonalBoard board) {
        this.board = board;
    }
    public void addLeaderCards(LeaderCard leader) {
        this.leaderCards.add(leader);
    }

    public void setGame(Game game) {
        this.game = game;
        board.setGame(game);
        board.getPersonalPath().setGame(game);
    }
    public void setWhiteMarbleChoices(int whiteMarbleChoices) {
        this.whiteMarbleChoices = whiteMarbleChoices;
    }
    public void setInitialResources(int initialResources) {
        this.initialResources = initialResources;
    }

    public void addWhiteAlt(Resource resource) {


        whiteAlt.add(resource);
    }
    public void changeWhiteMarbleChoicesNumber(int variation){
        whiteMarbleChoices += variation;
    }
    public void addSale(Resource resource) {
        sale.add(resource);
    }

    /**
     * this method is used in the beginning of the game to discard 2 leaderCards of your choice
     * or during a player turn
     * @param leader the leader the player decides to discard
     *
     */
    public void discardLeaderCard(LeaderCard leader){
        if(!game.isStarted() && leaderCards.size()>2)
            leaderCards.remove(leader);
        else if(leaderCards.size()>0) {
            leaderCards.remove(leader);
            game.getCurrentPlayer().getBoard().getPersonalPath().increasePosition();
        }
    }


    /**
     * buying method of Development Cards
     * @param card the card to buy
     * @param box box from where take the resource
     * @param index the position of development place where the player wants to place the card
     */
    public void buyDevelopmentCard (DevelopmentCard card,int[] box,int index){
        int j = 0;
        int k =0;
        if (box.length < card.getCost().length){
            throw new IllegalArgumentException("invalid cmd");
        }
        for (int i=0;i<card.getCost().length;i++) {
            Resource resource = card.getCost()[i].getResource();
            j += card.getCost()[i].getQuantity();
            for (; k < j; k++) {
                try {
                    if (box.length == card.getCost().length && card.getCost()[i].getQuantity() > 1){
                        k = k -1;
                        throw new IllegalArgumentException("invalid cmd");
                    }
                    if (box[k] == 0) {
                        if (board.getPersonalBox().getResourceMap().get(resource) != 0) {
                            board.getPersonalBox().removeResource(resource);
                        } else {
                            throw new IllegalArgumentException("not enough resource");
                        }
                    } else if (box[k] > 3 && box[k] <= 5) {
                        if (box[k] > board.getStore().size()) {
                            k = k -1;
                            throw new IllegalArgumentException("wrong store");
                        }
                        if (board.getStore().get(box[k] - 1).getTypeOfResource() == resource &&
                                !board.getStore().get(box[k] - 1).getResources().isEmpty()) {
                            board.getStore().get(box[k] - 1).takeOutResource();
                        } else {
                            throw new IllegalArgumentException("not enough resource");
                        }
                    }
                }catch (IllegalArgumentException e){
                    game.getViewAdapter().sendErrorMessage(this,e.getMessage());
                    refundCost(k, box, card.getCost());
                    return;
                }
            }
            k++;
        }
        board.addCard( game.getDevelopmentGrid().buyCard(card.getColor(), card.getLevel()),index);
    }


    /**
     * this method is used to activate a leader power
     * @param leader the leader you want to activate power from
     */
    public void useLeader(LeaderCard leader){
        if(leaderCards.contains(leader)) {
            if (leader.getRequirements().checkRequirements(game.getCurrentPlayer())) {
                leader.getLeaderPower().activatePower(game.getCurrentPlayer());
                board.addActiveLeader(leader);
            }else
                throw new IllegalArgumentException("you don't have these requirements");
        }else
            throw new IllegalArgumentException("you don't own this Leader Card");

    }

    /**
     * Board Production
     * @param box store from where you take resource
     * @param cost cost chosen by the player
     * @param production production chose by the player
     */
    public void startBoardProduction(int[] box,String[] cost,Resource production){
        if (!UtilityMap.isStorable(production)) {
            throw new IllegalArgumentException("Not valid production input");
        } else if (!board.getProductionList().get(0).getProductionCanBeActivate()){
            throw new IllegalArgumentException("You have already used this production");
        }
        for (int i = 0; i < 2; i++) {
            Resource resource = UtilityMap.mapResource.get(cost[i]);
            if (!UtilityMap.isStorable(resource)) {
                throw new IllegalArgumentException("Not valid input");
            }
            if (box[i] == 0) {
                int j = board.getPersonalBox().getResourceMap().get(resource);
                if (j == 0) {
                    throw new IllegalArgumentException("Not enough resources");
                }
            } else if (box[i] > 3) {
                if (board.getStore().size() < box[i]) {
                    throw new IllegalArgumentException("Invalid store");
                } else {
                    if (board.getStore().get(box[i] - 1).getResources().isEmpty()) {
                        throw new IllegalArgumentException("Not enough resources");
                    } else if (board.getStore().get(box[i] - 1).getTypeOfResource() != resource) {
                        throw new IllegalArgumentException("wrong resources");
                    }
                }
            } else {
                WarehouseStore warehouseStore = board.getStore().get(box[i] - 1);
                if (warehouseStore.getResources().isEmpty() || warehouseStore.getTypeOfResource() != resource) {
                    throw new IllegalArgumentException("Not enough resources or invalid store");
                }
            }
        }
        for (int i = 0; i < cost.length; i++) {
            if (box[i] == 0) {
                Resource resource = UtilityMap.mapResource.get(cost[i]);
                board.getPersonalBox().removeResource(resource);
            } else {
                board.getStore().get(box[i] - 1).takeOutResource();
            }
        }
        board.getPersonalBox().addToProdBox(production);
    }

    /**
     * Leader Production
     * @param cost store from where you take the resource
     * @param production resource chose by the player
     * @param index
     */
    public void startLeaderProduction(int cost,Resource production,int index){
        Production productionPower = this.getBoard().getProductionList().get(index);
        if (!UtilityMap.isStorable(production)) {
            throw new IllegalArgumentException("Not valid production input");
        }else if (this.getBoard().getProductionList().get(index) == null) {
            throw new IllegalArgumentException("You don't have leader card on your board");
        }else if (!productionPower.getProductionCanBeActivate()){
            throw new IllegalArgumentException("You have already used this production");
        }
        Resource costResource = productionPower.getCost()[0].getResource();
        if (cost == 0) {
            if (board.getPersonalBox().getResourceMap().get(costResource) != 0) {
                board.getPersonalBox().removeResource(costResource);
            } else {
                throw new IllegalArgumentException("Not enough resource");
            }
        } else if (cost > 3) {
            if (board.getStore().size() < cost) {
                throw new IllegalArgumentException("Invalid store");
            } else {
                if (board.getStore().get(cost - 1).getResources().isEmpty()) {
                    throw new IllegalArgumentException("Not enough resources");
                } else if (board.getStore().get(cost - 1).getTypeOfResource() != costResource) {
                    throw new IllegalArgumentException("Wrong resource");
                } else {
                    board.getStore().get(cost - 1).takeOutResource();
                }
            }
        } else {
            WarehouseStore warehouseStore = board.getStore().get(cost - 1);
            if (warehouseStore.getResources().isEmpty() || warehouseStore.getTypeOfResource() != costResource) {
                throw new IllegalArgumentException("Not enough resources or invalid store");
            } else
                board.getStore().get(cost - 1).takeOutResource();
        }
        board.getPersonalBox().addToProdBox(production);
        board.getPersonalPath().increasePosition();
    }

    /**
     * Development production
     * @param index index of the production selected
     * @param box store from where the player take the resources
     */
    public void startDevProduction(int index,int[] box){
        ArrayList<DevelopmentCard> devCards = this.getBoard().showActiveDevCards();
        int j = 0;
        int k = 0;
        if (devCards.isEmpty() || devCards.get(index-1) == null) {
            throw new IllegalArgumentException("The Development place selected is empty");
        }
        UtilityProductionAndCost[] cost = devCards.get(index-1).getProduction().getCost();
        Production production = devCards.get(index-1).getProduction();
        if (!production.getProductionCanBeActivate()){
            throw new IllegalArgumentException("You have already used this production");
        }
        if (box.length < cost.length) {
            throw new IllegalArgumentException("Invalid cmd");
        }
        for (UtilityProductionAndCost utilityProductionAndCost : cost) {
            Resource resource = utilityProductionAndCost.getResource();
            j += utilityProductionAndCost.getQuantity();
            for (; k < j; k++) {
                try {
                    if (box.length == cost.length && utilityProductionAndCost.getQuantity()>1) {
                        k=k-1;
                        throw  new IllegalArgumentException("Invalid cmd");
                    }
                    if (box[k] == 0) {
                        if (board.getPersonalBox().getResourceMap().get(resource) != 0) {
                            board.getPersonalBox().removeResource(resource);
                        } else {
                            throw new IllegalArgumentException("not enough resource");
                        }
                    } else if (box[k] > 3 && box[k] <= 5) {
                        if (box[k] > board.getStore().size()) {
                            k = k - 1;
                            throw new IllegalArgumentException("wrong store");
                        }
                        if (board.getStore().get(box[k] - 1).getTypeOfResource() == resource &&
                                !board.getStore().get(box[k] - 1).getResources().isEmpty()) {
                            board.getStore().get(box[k] - 1).takeOutResource();
                        } else {
                            throw new IllegalArgumentException("not enough resource");
                        }
                    }
                } catch (IllegalArgumentException e) {
                    game.getViewAdapter().sendErrorMessage(this, e.getMessage());
                    refundCost(k, box, cost);
                    return;
                }
            }
            k++;
        }
        UtilityProductionAndCost[] productionResource = production.getProd();
        for (UtilityProductionAndCost utilityProductionAndCost : productionResource) {
            Resource resource = utilityProductionAndCost.getResource();
            for (k = 0; k < utilityProductionAndCost.getQuantity(); k++) {
                if (resource == Resource.FAITH) {
                    board.getPersonalPath().increasePosition();
                } else {
                    board.getPersonalBox().addToProdBox(resource);
                }
            }
        }
    }


    /**
     * method called by the production in case of error
     * @param size number of resource processed before the error
     * @param box command by the player
     * @param cost cost of the production that need to be refund
     */
    public void refundCost(int size,int[] box, UtilityProductionAndCost[] cost) {
        if (size < 0) return;
        int j = 0;
        int k = 0;
        for (UtilityProductionAndCost utilityProductionAndCost : cost) {
            Resource resource = utilityProductionAndCost.getResource();
            j += utilityProductionAndCost.getQuantity();
            for (; k < j; k++) {
                if (k == size) return;
                if (box[k] == 0) {
                    board.getPersonalBox().addProdResource(resource);
                } else
                    board.getStore().get(box[k] - 1).addResource(resource);
            }
        }
    }

    /**
     *
     * @return number of dev cards owned by the player
     */
    public int getNumOfCards(){
        int numOfDevCards = 0;
        for (DevelopmentPlace devPlace : board.getPersonalDevelopmentSpace()){
            numOfDevCards += devPlace.getDevelopmentCards().size();
        }
        return numOfDevCards;
    }
}