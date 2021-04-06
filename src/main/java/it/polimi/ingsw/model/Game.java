package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * This class contains attributes and methods useful to both classes SinglePlayerGame and MultiPlayerGame
 */
public abstract class Game {
    private final ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private final Market market;
    private final Stack<LeaderCard> leaderDeck;
    private final DevelopmentGrid developmentGrid;
    private final HashMap<Integer, Boolean> vaticanReportMap = new HashMap<Integer, Boolean>(){{
        put(1, false);
        put(2, false);
        put(3, false);
    }};
    private final ArrayList<TurnPhase> turnPhases = new ArrayList<>();
    protected final Boolean isOver = false;

    public Game(Player player, Market market, Stack<LeaderCard> leaderDeck, DevelopmentGrid developmentGrid) {
        players.add(player);
        this.market = market;
        this.leaderDeck = leaderDeck;
        this.developmentGrid = developmentGrid;
        setTurnPhases();
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
    public Market getMarket() {
        return market;
    }
    public DevelopmentGrid getDevelopmentGrid() {
        return developmentGrid;
    }
    public HashMap<Integer, Boolean> getVaticanReportMap() {
        return vaticanReportMap;
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public Stack<LeaderCard> getLeaderDeck() {
        return leaderDeck;
    }
    public ArrayList<TurnPhase> getTurnPhases() {
        return turnPhases;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * This method sets, in order, the player's turn phases
     */
    public void setTurnPhases() {
        turnPhases.add(new UseLeaderPhase());
        turnPhases.add(new ActionPhase());
        turnPhases.add(new UseLeaderPhase());
        turnPhases.add(new EndTurnPhase());
    }

    /**
     * This method adds a player to the game if the number of players isn't already reached
     * @param player the player to add to the game
     * @return the number of free places in the game
     */
    public abstract int addPlayer(Player player);

    /**
     * This method ends the turn of a player and returns the next player in game
     * @param player the player who ended the turn
     * @return the next player in game
     */
    public abstract Player endTurn(Player player);

    public abstract void vaticanReport();

    public abstract void setUpPlayers();

    public abstract void start();
}
