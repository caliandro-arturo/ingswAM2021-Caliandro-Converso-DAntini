package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * This class contains attributes and methods useful to both classes SinglePlayerGame and MultiPlayerGame
 */
public abstract class Game {
    private final ArrayList<Player> players = new ArrayList<>();
    private final int playersNum;
    private Player currentPlayer;
    private TurnPhase currentTurnPhase;
    private final Market market;
    private final Stack<LeaderCard> leaderDeck;
    private final DevelopmentGrid developmentGrid;
    private final HashMap<Integer, Boolean> vaticanMap = new HashMap<Integer, Boolean>() {{
        put(8,  false);
        put(16, false);
        put(24, false);
    }};
    private final HashMap<String, TurnPhase> turnPhases;
    private final InputHandler inputHandler;
    private final ViewMethodsCaller viewMethodsCaller;
    private ArrayList<Player> playersToWait = new ArrayList<>();
    private boolean isOver = false;
    private boolean startReady = false;

    public Game(Player player, int playersNum, Market market, Stack<LeaderCard> leaderDeck, DevelopmentGrid developmentGrid) {
        this.players.add(player);
        this.playersNum = playersNum;
        this.market = market;
        this.leaderDeck = leaderDeck;
        this.developmentGrid = developmentGrid;
        this.inputHandler = new InputHandler(this);
        this.viewMethodsCaller = new ViewMethodsCaller(this);
        Game game = this;
        turnPhases = new HashMap<String, TurnPhase>() {{
            put("UseLeader", new UseLeaderPhase(game, true));
            put("ChooseAction", new ChooseActionPhase(game));
            put("UseMarket", new MarketPhase(game));
            put("BuyDevelopmentCard", new BuyDevCardPhase(game));
            put("ActivateProduction", new ActivateProdPhase(game));
            put("UseAgainLeader", new UseLeaderPhase(game, false));
        }};

    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
    public Player getPlayer(int num) {
        return players.get(num);
    }
    public ArrayList<Player> getPlayersToWait() {
        return playersToWait;
    }
    public int getPlayersNum() {
        return playersNum;
    }
    public Market getMarket() {
        return market;
    }
    public DevelopmentGrid getDevelopmentGrid() {
        return developmentGrid;
    }
    public HashMap<Integer, Boolean> getVaticanMap() {
        return vaticanMap;
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public TurnPhase getCurrentTurnPhase() {
        return currentTurnPhase;
    }
    public HashMap<String, TurnPhase> getTurnPhases() {
        return turnPhases;
    }
    public Stack<LeaderCard> getLeaderDeck() {
        return leaderDeck;
    }
    public InputHandler getInputHandler() {
        return inputHandler;
    }
    public ViewMethodsCaller getViewMethodsCaller() {
        return viewMethodsCaller;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public void setCurrentTurnPhase(TurnPhase currentTurnPhase) {
        this.currentTurnPhase = currentTurnPhase;
    }
    public void setPlayersToWait(ArrayList<Player> playersToWait) {
        this.playersToWait = playersToWait;
    }
    public void readyPlayer(Player player) {
        playersToWait.remove(player);
        if(playersToWait.size() == 0)
            startReady = true;
    }
    public void readyPlayer(int num) {
        readyPlayer(playersToWait.get(num));
    }

    /**
     * This method adds a player to the game if the number of players isn't already reached
     * @param player the player to add to the game
     * @return the number of free places in the game
     */
    public int addPlayer(Player player) {
        if(playersNum > players.size())
            players.add(player);
        return playersNum - players.size();
    }

    public void vaticanReport(int popePosition) {
        vaticanMap.replace(popePosition, true);
        players.forEach(player -> player.getBoard().getPersonalPath().isInVatican(popePosition));
        if(popePosition == 24) isOver = true;
    }

    public abstract void setUpPlayers();
}