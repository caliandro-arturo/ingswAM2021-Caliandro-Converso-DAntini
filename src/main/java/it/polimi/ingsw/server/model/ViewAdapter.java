package it.polimi.ingsw.server.model;

import it.polimi.ingsw.server.VirtualView;

/**
 * This class contains all the methods used by the model to notify the player and/or to ask him to do an action.
 */
public class ViewAdapter {
    /**
     * To avoid redundancy, this class is initialized with a reference to the game as attribute.
     */
    private final Game game;
    private VirtualView virtualView;

    public ViewAdapter(Game game) {
        this.game = game;
    }

    public void setVirtualView(VirtualView virtualView) {
        this.virtualView = virtualView;
    }

    /**
     * sends a message to a player.
     * @param message the information to send to all players
     */
    public void sendMessage(Player player, String message) {
    }

    /**
     * sends a message to all players.
     * @param message the information to send to all players
     */
    public void sendMessage(String message) {
        game.getPlayers().forEach(p -> sendMessage(p, message));
    }

    /**
     * Notifies the player how much initial resources he has to choose and store in his warehouse depots.
     * @param player the player to notify
     * @param resourceQuantity the number of resources the player has to choose
     */
    public void notifyInitialResourcesAmount(Player player, int resourceQuantity) {
    }

    /**
     * Sends the request to the current player to use the market.
     */
    public void askToUseTheMarket() {
    }

    /**
     * Sends the request to the current player that has used the Market and in his selected row/column
     * there is at least one white Marble.
     * This is called only when the current player has two deployed leaders with White Marble Conversion power, and
     * he has to choose which Resource to take from the white Marble.
     */
    public void askWhiteMarbleResource() {
    }

    /**
     * Sends the request to the current player to choose between deploying a leader, discarding a leader or
     * skipping the turn phase.
     */
    public void askLeaderAction() {
    }

    /**
     * Sends the request to the current player to choose the main action to do in his turn.
     */
    public void askTurnPhaseAction() {
    }

    /**
     * Sends information about the current turn phase the player has to play.
     * @param information the information to send to the current player
     */
    public void giveTurnPhaseInfo(String information) {
    }

    /**
     * This method notifies all the players that the player has been added successfully to the game.
     * @param player the player that has been added to the game
     */
    public void notifyAddedPlayer(Player player) {
        sendMessage(player.getUsername() + " has entered the game.");
    }

    /**
     * This method sends the request to the current player to choose a card to buy from the Development Grid.
     */
    public void askWhichCardToBuyFromTheDevelopmentGrid() {
    }

    /**
     * this method asks to the player where he wants to put a newly bought DevCard
     * @param p the player to notify
     */
    public void askDevCardPlace(Player p){
        sendMessage(p, "choose where to put this Development Card ");
    }

    /**
     * ask to the player where he wants to put a resource in the warehouse store
     * @param p the player to notify
     * @param resource the resource to store
     */
    public void askResPlace(Player p, Resource resource) {
        sendMessage(p, "choose where to put this " + resource + "resource.");
    }

    /**
     * Notifies that the game is over and returns
     */
    public void notifyGameEnded(Player player, int ranking, int[] points) {
    }

    /**
     * Notifies a player that his turn is a certain number.
     * @param player the player to notify
     * @param number the position order of the player
     */
    public void notifyPlayerTurnNumber(Player player, int number) {
        sendMessage(player, "You are the player n. " + number + ".");
    }

    /**
     * Sends an error message.
     * @see GameException
     */
    public void sendErrorMessage(Player player, String error) {
    }

    /**
     * notify last turn
     * @param string reason of last turn
     */
    public void notifyLastTurn(String string){

    }

}
