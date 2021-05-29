package it.polimi.ingsw.server.model;

import java.util.*;

/**
 * Parent of both {@link SinglePlayerGame} and {@link MultiplayerGame} classes.
 *
 * <p>It contains attributes and methods that represents the state of the game:
 * players
 */
public abstract class Game {
    private final HashMap<String, Player> players = new LinkedHashMap<>();
    private final int playersNum;
    private Player currentPlayer;
    private TurnPhase currentTurnPhase;
    private final Market market;
    private final Stack<LeaderCard> leaderDeck;
    private final DevelopmentGrid developmentGrid;
    private final HashMap<Integer, Boolean> vaticanMap = new HashMap<Integer, Boolean>() {{
        put(8, false);
        put(16, false);
        put(24, false);
    }};
    private final HashMap<String, TurnPhase> turnPhases;
    private ControllerAdapter controllerAdapter;
    private ViewAdapter viewAdapter;
    private ArrayList<Player> playersToWait = new ArrayList<>();

    //booleans
    private boolean isStarted = false;                      //the game is started
    private boolean isOver = false;                         //the game is over (last turns)
    private boolean isFinished = false;                     //the game is finished

    public Game(Player player, int playersNum, Market market, Stack<LeaderCard> leaderDeck,
                DevelopmentGrid developmentGrid) {
        player.setGame(this);
        this.players.put(player.getUsername(), player);
        this.playersNum = playersNum;
        this.market = market;
        this.leaderDeck = leaderDeck;
        this.developmentGrid = developmentGrid;
        this.controllerAdapter = new ControllerAdapter(this);
        this.viewAdapter = new ViewAdapter(this);
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
        return new ArrayList<>(players.values());
    }

    public Player getPlayer(int num) {
        return new ArrayList<>(players.values()).get(num);
    }

    public Player getPlayer(String nickname) {
        return players.get(nickname);
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

    public ControllerAdapter getControllerAdapter() {
        return controllerAdapter;
    }

    public ViewAdapter getViewAdapter() {
        return viewAdapter;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setCurrentTurnPhase(TurnPhase currentTurnPhase) {
        this.currentTurnPhase = currentTurnPhase;
    }

    public void setPlayersToWait(ArrayList<Player> playersToWait) {
        playersToWait.forEach(this::setPlayerToWait);
    }

    public void setPlayerToWait(Player player) {
        playersToWait.add(player);
    }

    //booleans management
    public boolean isStarted() {
        return isStarted;
    }

    public boolean isOver() {
        return isOver;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setStarted(boolean isStarted) {
        this.isStarted = isStarted;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    public void setFinished() {
        isFinished = true;
    }

    //auxiliary methods

    /**
     * Returns the reference of the turn phase indicated by {@code name}.
     *
     * @param name the name of the asked turn phase
     * @return the turn phase asked
     */
    public TurnPhase getTurnPhase(String name) {
        return turnPhases.get(name);
    }

    /**
     * command used by the player
     *
     * @param validPhases the phases which the player can go back
     * @throws GameException.IllegalMove if the player can't do this action
     */
    public void back(ArrayList<String> validPhases) throws GameException.IllegalMove {
        ArrayList<TurnPhase> validTurn = new ArrayList<>();
        for (String turn: validPhases){
            validTurn.add(turnPhases.get(turn));
        }
        if (validTurn.contains(currentTurnPhase) && !currentTurnPhase.isFinished()){
            setCurrentTurnPhase(turnPhases.get("ChooseAction"));
            viewAdapter.announceTurnPhase(currentPlayer,getCurrentTurnPhase().getName(),
                    getCurrentTurnPhase().getPhaseInfo());
        } else
            throw new GameException.IllegalMove();
    }


    /**
     * Checks if a player is ready.
     *
     * @param player the player to check
     * @return {@code true} if the player is in the game and it's ready; {@code false} otherwise
     */
    public boolean isReady(Player player) {
        return !playersToWait.contains(player);
    }

    //main methods

    /**
     * Add players to the game.
     */
    public void addPlayers(List<String> players) {
        players.forEach(this::addPlayer);
    }

    /**
     * Adds a player to the game.
     *
     * @param player the player to add to the game
     */
    public void addPlayer(String player) {
        players.put(player, new Player(player));
        players.get(player).setGame(this);
    }

    public void addPlayer(Player player) {
        players.put(player.getUsername(), player);
        player.setGame(this);
    }

    /**
     * Checks if a player is ready and removes the player from {@link Game#playersToWait}. If {@code playersToWait} is
     * empty, set the game ready to start.
     *
     * @param player the ready player to remove
     */
    public void setPlayerReady(Player player) {
        if (player.getBoard().getResHand().isEmpty() &&
        player.getInitialResources() == 0 &&
        player.getLeaderCards().size() == 2) {
            playersToWait.remove(player);
            if (playersToWait.isEmpty()) startGame();
        }
    }

    /**
     * Starts the game.
     */
    public void startGame() {
        setStarted(true);
        setCurrentPlayer(getPlayer(0));
        getViewAdapter().notifyNewTurn(getCurrentPlayer());
        setCurrentTurnPhase(getTurnPhase("UseLeader"));
        currentTurnPhase.start();
    }

    /**
     * Starts the next turn phase.
     */
    public void nextTurnPhase() {
        setCurrentTurnPhase(getCurrentTurnPhase().nextTurnPhase());
        currentTurnPhase.start();
    }

    /**
     * Starts a turn phase.
     *
     * @param turnPhase the turn phase to start
     */
    public void nextTurnPhase(String turnPhase) {
        setCurrentTurnPhase(getTurnPhase(turnPhase));
        currentTurnPhase.start();
    }

    /**
     * Starts a Vatican Report: each player that has his red cross in a Vatican Section earns additional VP.
     *
     * @param popePosition the position that triggered the Vatican Report event
     */
    public void vaticanReport(int popePosition) {
        vaticanMap.replace(popePosition, true);
        players.values().forEach(player -> player.getBoard().getFaithTrack().isInVatican(popePosition));
        if (popePosition == 24) {
            if (!isOver) {
                setOver(true);
            }
        }
    }

    /**
     * Set {@link Game#currentPlayer} and initializes players.
     */
    public abstract void setUpPlayers();

    /**
     * Ends the game, sending to players the results of the game (Victory Points and ranking).
     */
    public abstract void endGame();

    //------------------------------------------------------------------------------------------------------------------
    /* for debug purposes */

    public void setControllerAdapter(ControllerAdapter controllerAdapter) {
        this.controllerAdapter = controllerAdapter;
    }

    public void setViewAdapter(ViewAdapter viewAdapter) {
        this.viewAdapter = viewAdapter;
    }
}