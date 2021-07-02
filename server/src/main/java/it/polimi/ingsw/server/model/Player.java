package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.messages.toServer.DeployRes;
import it.polimi.ingsw.commonFiles.messages.toServer.DiscardRes;
import it.polimi.ingsw.commonFiles.messages.toServer.GetResource;
import it.polimi.ingsw.commonFiles.model.Production;
import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;

import java.util.ArrayList;
import java.util.Random;

/**
 * Implements all the methods used by a player.
 */
public class Player {
    private String username;
    private Board board;
    private final ArrayList<LeaderCard> leaderCards;
    private final ArrayList<Resource> whiteAlt;
    private final ArrayList<Resource> sale;
    private Game game;
    private final int[] victoryPoints;
    private int whiteMarbleChoices = 0;
    private int initialResources = 0;
    private int processedResources;
    private boolean isConnected = true;

    /**
     * Customised constructor for player that initialises all attributes a player needs.
     *
     * @param username chosen by the player
     */
    public Player(String username) {
        this.username = username;
        this.board = new Board(this);
        this.leaderCards = new ArrayList<>();
        this.whiteAlt = new ArrayList<>();
        this.sale = new ArrayList<>();
        victoryPoints = new int[5];
    }

    public String getUsername() {
        return username;
    }

    public Board getBoard() {
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

    public boolean isConnected() {
        return isConnected;
    }

    /**
     * In the last turn this method sum all the victory points the player is entitled to
     * <p> index 0 faith path VP
     * <p> index 1 dev card VP
     * <p> index 2 lead card VP
     * <p> index 3 Pope's favor VP
     * <p> index 4 resource VP
     *
     * @return array of int containing VictoryPoints already summed in different indexes
     */
    public int[] getVictoryPoints() {
        // index 0 faith path VP
        int pos = getBoard().getFaithTrack().getPosition();
        if (pos >= 3 && pos < 6) {
            victoryPoints[0] = 1;
        } else if (pos >= 6 && pos < 9) {
            victoryPoints[0] = 2;
        } else if (pos >= 9 && pos < 12) {
            victoryPoints[0] = 4;
        } else if (pos >= 12 && pos < 15) {
            victoryPoints[0] = 6;
        } else if (pos >= 15 && pos < 18) {
            victoryPoints[0] = 9;
        } else if (pos >= 18 && pos < 21) {
            victoryPoints[0] = 12;
        } else if (pos >= 21 && pos < 24) {
            victoryPoints[0] = 16;
        } else if (pos == 24) {
            victoryPoints[0] = 20;
        }
        // index 1 dev card VP
        for (DevelopmentPlace devPlace : board.getDevelopmentSpace()) {
            for (DevelopmentCard devCard : devPlace.getDevelopmentCards()) {
                victoryPoints[1] += devCard.getVictoryPoints();
            }
        }
        // index 2 lead card VP
        for (LeaderCard lead : getBoard().getActiveLeaders()) {
            victoryPoints[2] += lead.getVictoryPoints();
        }
        // index 3 Pope's favor VP
        victoryPoints[3] = board.getFaithTrack().getScoreCard();
        // index 4 resource VP
        victoryPoints[4] = getResourceNumber() / 5;
        return victoryPoints;
    }

    public int getWhiteMarbleChoices() {
        return whiteMarbleChoices;
    }

    public int getInitialResources() {
        return initialResources;
    }

    public int getProcessedResources() {
        return processedResources;
    }

    public int getResourceNumber() {
        int boxRes = 0;
        int storeRes = board.getStrongbox().getResourceMap().values().stream().mapToInt(i -> i).sum();
        for (WarehouseStore stores : board.getStore()) {
            storeRes += stores.getQuantity();
        }
        return boxRes + storeRes;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void addLeaderCards(LeaderCard leader) {
        this.leaderCards.add(leader);
    }

    public void setGame(Game game) {
        this.game = game;
        board.setGame(game);
        board.getFaithTrack().setGame(game);
    }

    public void setInitialResources(int initialResources) {
        this.initialResources = initialResources;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
        if (game.getPlayers().stream().anyMatch(Player::isConnected))
            if (!connected)
                if (game.getCurrentTurnPhase() == null) {
                    if (!game.isReady(this)) {
                        while (initialResources > 0) {
                            //selects random storable resources
                            Resource randomResource = Resource.values()[new Random().nextInt(5)];
                            if (Utility.isStorable(randomResource)) {
                                board.getResHand().add(randomResource);
                                GetResource getRes = new GetResource(randomResource);
                                getRes.setPlayer(getUsername());
                                game.getViewAdapter().sendMessage(getRes);
                                initialResources--;
                            }
                        }
                        ArrayList<Resource> tempResHand = new ArrayList<>(board.getResHand());
                        for (Resource r : tempResHand)
                            //deploys resources from the player's hand into the first free depots
                            for (WarehouseStore w : board.getStore()) {
                                try {
                                    board.deployResource(r, board.getStore().indexOf(w) + 1);
                                    DeployRes deployRes = new DeployRes(r, board.getStore().indexOf(w) + 1);
                                    deployRes.setPlayer(getUsername());
                                    game.getViewAdapter().sendMessage(deployRes);
                                    break;
                                } catch (IllegalArgumentException ignore) {
                                }
                            }
                        //discard initial leader cards
                        while (leaderCards.size() > 2)
                            leaderCards.remove(0);
                    }
                    //sets the player as ready
                    game.setPlayerReady(this);
                    // else if the player was the current player, his turn is forcibly ended and the turn is passed to the
                    // next player
                } else if (this.equals(game.getCurrentPlayer())) {
                    if (!board.getResHand().isEmpty()) {
                        //tries to deploy resources from the hand
                        ArrayList<Resource> resources = new ArrayList<>(board.getResHand());
                        for (Resource r : resources)
                            for (WarehouseStore w : board.getStore()) {
                                try {
                                    board.deployResource(r, board.getStore().indexOf(w) + 1);
                                    DeployRes deployRes = new DeployRes(r, board.getStore().indexOf(w) + 1);
                                    deployRes.setPlayer(getUsername());
                                    game.getViewAdapter().sendMessage(deployRes);
                                    break;
                                } catch (Exception ignore) {
                                }
                            }
                        //if there is not free depot, discard resources.
                        resources = new ArrayList<>(board.getResHand());
                        if (!board.getResHand().isEmpty())
                            for (Resource r : resources)
                                try {
                                    board.discardResource(r);
                                    DiscardRes discardRes = new DiscardRes(r);
                                    discardRes.setPlayer(getUsername());
                                    game.getViewAdapter().sendMessage(discardRes);
                                } catch (Exception ignore) {
                                }
                    }
                    getBoard().getStrongbox().emptyProdBox();
                    game.getCurrentTurnPhase().setFinished(false);
                    game.nextTurnPhase("EndTurn");
                }
    }

    public void addWhiteAlt(Resource resource) {
        whiteAlt.add(resource);
    }

    public void changeWhiteMarbleChoicesNumber(int variation) {
        whiteMarbleChoices += variation;
    }

    public void addSale(Resource resource) {
        sale.add(resource);
    }

    /**
     * Discards one of the 2 leaderCards of your choice in the beginning or
     * or during a player turn.
     *
     * @param leader the leader the player decides to discard
     */
    public void discardLeaderCard(LeaderCard leader) throws IllegalArgumentException {
        if (game.getCurrentTurnPhase() == null)
            if (leaderCards.size() == 2) {
                throw new IllegalArgumentException("You cannot discard any more cards.");
            } else if (leaderCards.isEmpty())
                throw new IllegalArgumentException("You cannot discard any more cards.");
        leaderCards.remove(leader);
        if (game.getCurrentTurnPhase() != null)
            game.getCurrentPlayer().getBoard().getFaithTrack().increasePosition();
    }

    /**
     * Buy method for Development Cards.
     *
     * @param card  the card to buy
     * @param box   box from where take the resource
     * @param index the position of development place where the player wants to place the card
     */
    public void buyDevelopmentCard(DevelopmentCard card, int[] box, int index) {
        processedResources = 0;
        if (box.length < card.getCost().length) {
            throw new IllegalArgumentException("invalid cmd");
        }
        if (sale.isEmpty())
            handlingCost(card.getCost(), box);
        else {
            ArrayList<UtilityProductionAndCost> costArrayList = new ArrayList<>();
            for (UtilityProductionAndCost cost : card.getCost()) {
                if (sale.contains(cost.getResource())) {
                    costArrayList.add(new UtilityProductionAndCost(cost.getQuantity() - 1, cost.getResource()));
                } else
                    costArrayList.add(new UtilityProductionAndCost(cost.getQuantity(), cost.getResource()));
            }
            UtilityProductionAndCost[] utilityProductionAndCosts = new UtilityProductionAndCost[costArrayList.size()];
            handlingCost(costArrayList.toArray(utilityProductionAndCosts), box);
        }
        board.addCard(game.getDevelopmentGrid().buyCard(card.getColor(), card.getLevel()), index);
    }


    /**
     * Activates a leader power.
     *
     * @param leader the leader you want to activate power from
     */
    public void useLeader(LeaderCard leader) {
        if (leaderCards.contains(leader)) {
            if (leader.getRequirements().checkRequirements(game.getCurrentPlayer())) {
                leader.getLeaderPower().activatePower(game.getCurrentPlayer());
                board.addActiveLeader(leader);
                leaderCards.remove(leader);
                game.getViewAdapter().sendLeaderUpdate(leader);
            } else
                throw new IllegalArgumentException("you don't have these requirements");
        } else
            throw new IllegalArgumentException("you don't own this Leader Card");

    }

    /**
     * Board Production.
     *
     * @param box        store from where you take resource
     * @param cost       cost chosen by the player
     * @param production production chose by the player
     */
    public void startBoardProduction(int[] box, String[] cost, Resource production) {
        if (!Utility.isStorable(production)) {
            throw new IllegalArgumentException("Not valid production input");
        } else if (!board.getProductionList().get(0).getProductionCanBeActivate()) {
            throw new IllegalArgumentException("You have already used this production");
        }
        for (int i = 0; i < 2; i++) {
            Resource resource = Utility.mapResource.get(cost[i].toLowerCase());
            if (!Utility.isStorable(resource)) {
                throw new IllegalArgumentException("Not valid input");
            }
            if (box[i] == 0) {
                int j = board.getStrongbox().getResourceMap().get(resource);
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
                Resource resource = Utility.mapResource.get(cost[i].toLowerCase());
                board.getStrongbox().removeResource(resource);
            } else {
                board.getStore().get(box[i] - 1).takeOutResource();
            }
        }
        board.getStrongbox().addToProdBox(production);
    }

    /**
     * Leader Production.
     *
     * @param cost       store from where you take the resource
     * @param production resource chose by the player
     */
    public void startLeaderProduction(int cost, Resource production, int index) {
        Production productionPower = this.getBoard().getProductionList().get(index);
        if (!Utility.isStorable(production)) {
            throw new IllegalArgumentException("Not valid production input");
        } else if (this.getBoard().getProductionList().get(index) == null) {
            throw new IllegalArgumentException("You don't have leader card on your board");
        } else if (!productionPower.getProductionCanBeActivate()) {
            throw new IllegalArgumentException("You have already used this production");
        }
        Resource costResource = productionPower.getCost()[0].getResource();
        if (cost == 0) {
            if (board.getStrongbox().getResourceMap().get(costResource) != 0) {
                board.getStrongbox().removeResource(costResource);
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
        board.getStrongbox().addToProdBox(production);
        board.getFaithTrack().increasePosition();
    }

    /**
     * Development production.
     *
     * @param index index of the production selected
     * @param box   store from where the player take the resources
     */
    public void startDevProduction(int index, int[] box) {
        ArrayList<DevelopmentCard> devCards = this.getBoard().showActiveDevCards();
        processedResources = 0;
        if (devCards.isEmpty() || devCards.get(index - 1) == null) {
            throw new IllegalArgumentException("The Development place selected is empty");
        }
        UtilityProductionAndCost[] cost = devCards.get(index - 1).getProduction().getCost();
        Production production = devCards.get(index - 1).getProduction();
        if (!production.getProductionCanBeActivate()) {
            throw new IllegalArgumentException("You have already used this production");
        }
        if (box.length < cost.length) {
            throw new IllegalArgumentException("Invalid cmd");
        }
        handlingCost(cost, box);
        UtilityProductionAndCost[] productionResource = production.getProd();
        for (UtilityProductionAndCost utilityProductionAndCost : productionResource) {
            Resource resource = utilityProductionAndCost.getResource();
            for (processedResources = 0; processedResources < utilityProductionAndCost.getQuantity(); processedResources++) {
                if (resource == Resource.FAITH) {
                    board.getFaithTrack().increasePosition();
                } else {
                    board.getStrongbox().addToProdBox(resource);
                }
            }
        }
    }

    /**
     * Handles the cost of a card.
     *
     * @throws IllegalArgumentException
     */
    public void handlingCost(UtilityProductionAndCost[] cost, int[] box) throws IllegalArgumentException {
        int j = 0;
        for (UtilityProductionAndCost utilityProductionAndCost : cost) {
            Resource resource = utilityProductionAndCost.getResource();
            j += utilityProductionAndCost.getQuantity();
            for (; processedResources < j; processedResources++) {
                if (box.length == cost.length && utilityProductionAndCost.getQuantity() > 1) {
                    processedResources = processedResources - 1;
                    throw new IllegalArgumentException("Invalid cmd");
                }
                if (box[processedResources] == 0) {
                    if (board.getStrongbox().getResourceMap().get(resource) != 0) {
                        board.getStrongbox().removeResource(resource);
                    } else {
                        throw new IllegalArgumentException("not enough resource");
                    }
                } else {
                    if (box[processedResources] > board.getStore().size()) {
                        processedResources = processedResources - 1;
                        throw new IllegalArgumentException("wrong store");
                    }
                    if (board.getStore().get(box[processedResources] - 1).getTypeOfResource() == resource &&
                            !board.getStore().get(box[processedResources] - 1).getResources().isEmpty()) {
                        board.getStore().get(box[processedResources] - 1).takeOutResource();
                    } else {
                        throw new IllegalArgumentException("not enough resource");
                    }
                }
            }
        }
    }


    /**
     * Method called by the production in case of error, it refunds the stores with the resources erroneously used.
     *
     * @param size number of resource processed before the error
     * @param box  command by the player
     * @param cost cost of the production that need to be refund
     */
    public void refundCost(int size, int[] box, UtilityProductionAndCost[] cost) {
        if (size < 0) return;
        int j = 0;
        processedResources = 0;
        for (UtilityProductionAndCost utilityProductionAndCost : cost) {
            Resource resource = utilityProductionAndCost.getResource();
            j += utilityProductionAndCost.getQuantity();
            for (; processedResources < j; processedResources++) {
                if (processedResources == size) return;
                if (box[processedResources] == 0) {
                    board.getStrongbox().addProdResource(resource);
                } else
                    board.getStore().get(box[processedResources] - 1).addResource(resource);
            }
        }
    }

    /**
     * @return number of dev cards owned by the player.
     */
    public int getNumOfCards() {
        int numOfDevCards = 0;
        for (DevelopmentPlace devPlace : board.getDevelopmentSpace()) {
            numOfDevCards += devPlace.getDevelopmentCards().size();
        }
        return numOfDevCards;
    }

    @Override
    public String toString() {
        StringBuilder playerJson = new StringBuilder();
        playerJson.append("""
                {
                    "nickname": "%s",
                    "board": %s,
                    "whiteAlt": [""".formatted(username, board));
        for (Resource r : whiteAlt) playerJson.append(r.name()).append(",\n");
        if (!whiteAlt.isEmpty()) playerJson.deleteCharAt(playerJson.lastIndexOf(","));
        playerJson.append("""
                    ],
                    "sale": [
                """);
        for (Resource r : sale) playerJson.append(r.name()).append(",\n");
        if (!sale.isEmpty()) playerJson.deleteCharAt(playerJson.lastIndexOf(","));
        playerJson.append("""
                    ]
                }""");
        return playerJson.toString();
    }
}