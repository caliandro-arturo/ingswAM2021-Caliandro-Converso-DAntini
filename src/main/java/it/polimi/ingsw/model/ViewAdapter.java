package it.polimi.ingsw.model;

/**
 * This class contains all the methods used by the model to notify the player and/or to ask him to do an action.
 */
public class ViewAdapter {
    /**
     * To avoid redundancy, this class is initialized with a reference to the game as attribute.
     */
    private final Game game;

    public ViewAdapter(Game game) {
        this.game = game;
    }

    /**
     * This method sends the request to the player to choose some resources.
     * @param player the player to notify
     * @param quantity the number of free resources the player has to choose
     */
    public void askArbitraryResource(Player player, int quantity) {
    }

    /**
     * This method sends the request to the current player that has used the Market and in his selected row/column
     * there is at least one white Marble.
     * This is called only when the current player has two deployed leaders with White Marble Conversion power, and
     * he has to choose which Resource to take from the white Marble.
     */
    public void askWhiteMarbleResource() {
    }

    /**
     * This method sends the request to the current player to choose between deploying a leader, discarding a leader or
     * skipping the turn phase.
     */
    public void askLeaderAction() {
    }

    /**
     * This method sends the request to the current player to choose the main action to do in his turn.
     */
    public void askTurnPhaseAction() {
    }

    /**
     * This method sends the notice to the player that it is not his turn.
     * @param player the player to notify
     */
    public void notifyNotPlayerTurn(Player player) {
    }

    /**
     * This method sends information about the current turn phase the player has to play.
     * @param information the information to send to the current player
     */
    public void giveTurnPhaseInfo(String information) {
    }

    /**
     * This method sends the notice to the player that the choice he made is invalid.
     */
    public void notifyInvalidChoice() {
    }

    /**
     * This method notifies the player that the game is already full.
     * @param player the player who tried to enter the game
     */
    public void notifyGameIsFull(Player player) {
    }

    /**
     * This method notifies the player that he is already in the game.
     * @param player the player who tried to re-enter the game
     */
    public void notifyAlreadyIn(Player player) {
    }

    /**
     * This method notifies the player that he has been added successfully to the game.
     * @param player the player that is
     */
    public void notifyAddedPlayer(Player player) {
    }

    /**
     * This method sends the request to the current player to choose a card to buy from the Development Grid.
     */
    public void askWhichCardToBuyFromTheDevelopmentGrid() {
    }

    /**
     * this method asks to the player where he wants to put a newly bought DevCard
     */
    public void askDevCardPlace(){

    }
}
