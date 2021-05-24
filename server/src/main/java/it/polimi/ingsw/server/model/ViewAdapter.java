package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.DisplayEndingScores;
import it.polimi.ingsw.commonFiles.messages.toClient.ErrorMessage;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.InitialResourcesAmount;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.TurnPhaseAnnouncement;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.*;
import it.polimi.ingsw.commonFiles.model.Production;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;
import it.polimi.ingsw.server.VirtualView;

import java.util.ArrayList;

/**
 * This class contains all the methods used by the model to notify the player and/or to ask him to do an action.
 */
public class ViewAdapter {
    /**
     * To avoid redundancy, this class is initialized with a reference to the game as attribute.
     */
    private Game game;
    private VirtualView virtualView;

    public ViewAdapter(Game game) {
        this.game = game;
    }

    public ViewAdapter() {

    }

    public void setVirtualView(VirtualView virtualView) {
        this.virtualView = virtualView;
    }

    public void setGame(Game game) {
        this.game = game;
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
        virtualView.sendMessage(player, new InitialResourcesAmount(resourceQuantity));
    }

    /**
     *
     */
    public void notifyNewTurn(Player player) {
        sendMessage(new NewTurn(player.getUsername()));
    }

    /**
     * Sends the request to the current player to use the market.
     */
    public void askToUseTheMarket() {

    }

    /**
     * Sends the request to the current player to choose between deploying a leader, discarding a leader or skipping the
     * turn phase.
     */
    public void askLeaderAction() {
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
        sendMessage(player, new DisplayEndingScores(points,ranking));
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

    public void announceTurnPhase(Player player, String turnPhaseName, String turnPhaseInfo) {
        virtualView.sendMessage(player, new TurnPhaseAnnouncement(turnPhaseName, turnPhaseInfo));
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

    /**
     * Sends initial configurations of development grid and market.
     */
    public void sendTable(){
        ArrayList<DevelopmentCard> cards = game.getDevelopmentGrid().getTopDevelopmentCards();
        ArrayList<Integer> level = new ArrayList<>();
        ArrayList<Integer> victoryPoints = new ArrayList<>();
        ArrayList<String> colors = new ArrayList<>();
        ArrayList<UtilityProductionAndCost[]> costs = new ArrayList<>();
        ArrayList<Production> productions = new ArrayList<>();
        for (DevelopmentCard card : cards){
            level.add(card.getLevel());
            victoryPoints.add(card.getVictoryPoints());
            colors.add(card.getColorString());
            costs.add(card.getCost());
            productions.add(card.getProduction());
        }
        sendMessage(new InitDevGrid(colors,costs,level,victoryPoints,productions));
        Market market = game.getMarket();
        String extraMarble = market.getExtraMarble().getColorString();
        String[][] tray = new String[market.getRows()][market.getColumns()];
        for (int i = 0; i < market.getRows(); i++){
            for (int j = 0; j < market.getColumns(); j++){
                tray[i][j] = market.getTray()[i][j].getColorString();
            }
        }
        sendMessage(new InitMarket(tray,extraMarble));
    }

    /**
     * Sends to the player his leader cards.
     *
     * @param player the player to send the leader cards to
     */
    public void sendLeaderHand(Player player){
        ArrayList<LeaderCard> cards = player.getLeaderCards();
        ArrayList<String[]> requirements = new ArrayList<>();
        ArrayList<String[]> leaderPowers = new ArrayList<>();
        ArrayList<Integer> victoryPoints = new ArrayList<>();
        for (LeaderCard leaderCard : cards){
            requirements.add(leaderCard.getRequirements().identifier());
            leaderPowers.add(leaderCard.getLeaderPower().identifier());
            victoryPoints.add(leaderCard.getVictoryPoints());
        }
        sendMessage(player,new InitLeaderHand(victoryPoints,requirements,leaderPowers));
    }

    /**
     * Sends an update to increase the player's position on his faith track on the connected clients.
     *
     * @param player the player that has changed his position on the faith track
     */
    public void incrementFaithTrackPosition(Player player) {
        sendMessage(new IncrementFaithTrackPosition(player.getUsername()));
    }
}
