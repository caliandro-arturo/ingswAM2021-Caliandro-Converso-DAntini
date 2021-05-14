package it.polimi.ingsw.server.model;

import it.polimi.ingsw.common_files.message.Message;
import it.polimi.ingsw.common_files.message.toClient.ErrorMessage;
import it.polimi.ingsw.common_files.message.toClient.TablePosition;
import it.polimi.ingsw.common_files.message.toClient.TurnPhaseAnnouncement;
import it.polimi.ingsw.common_files.message.toClient.updates.LastTurn;
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
     *
     * @param message the information to send to all players
     */
    public void sendMessage(Player player, Message message) {
        virtualView.sendMessage(player, message);
    }

    /**
     * Sends a text to the player.
     * @param player
     * @param message
     * @deprecated create a specific method to send specific messages.
     */
    @Deprecated
    public void sendMessage(Player player, String message) {

    }

    /**
     * sends a message to all players.
     *
     * @param message the information to send to all players
     */
    public void sendMessage(Message message) {
        virtualView.sendMessage(message);
    }

    /**
     * Notifies the player how much initial resources he has to choose and store in his warehouse depots.
     *
     * @param player           the player to notify
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
     * Sends the request to the current player that has used the Market and in his selected row/column there is at least
     * one white Marble. This is called only when the current player has two deployed leaders with White Marble
     * Conversion power, and he has to choose which Resource to take from the white Marble.
     */
    public void askWhiteMarbleResource() {
    }

    /**
     * Sends the request to the current player to choose between deploying a leader, discarding a leader or skipping the
     * turn phase.
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
     *
     * @param information the information to send to the current player
     */
    public void giveTurnPhaseInfo(String information) {
    }

    /**
     * This method sends the request to the current player to choose a card to buy from the Development Grid.
     */
    public void askWhichCardToBuyFromTheDevelopmentGrid() {
    }

    /**
     * Notifies that the game is over and returns the final score and rank
     */
    public void notifyGameEnded(Player player, int ranking, int[] points) {
    }

    /**
     * Notifies a player that his turn is a certain number.
     *
     * @param player the player to notify
     * @param number the position order of the player
     */
    public void notifyPlayerTurnNumber(Player player, int number) {
        virtualView.sendMessage(player, new TablePosition(number));
    }

    public void announceTurnPhase(Player player, String turnPhaseName) {
        /*virtualView.sendMessage(player, new TurnPhaseAnnouncement(turnPhaseName));*/
    }

    /**
     * Notifies that the turn will pass to the next player.
     */
    public void notifyTurnPass() {

    }

    /**
     * Sends an error message.
     *
     * @see GameException
     */
    public void sendErrorMessage(Message message, String error) {
        virtualView.sendMessage(message.getPlayer(), new ErrorMessage(message, error));
    }

    /**
     * Notifies that this is the last turn
     *
     * @param reason reason of last turn
     */
    public void notifyLastTurn(String reason) {
        sendMessage(new LastTurn(reason));
    }

}
