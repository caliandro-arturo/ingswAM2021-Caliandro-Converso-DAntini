package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.EndingScores;
import it.polimi.ingsw.commonFiles.messages.toClient.ErrorMessage;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.*;
import it.polimi.ingsw.commonFiles.model.Production;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;
import it.polimi.ingsw.server.VirtualView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * This class contains all the methods used by the it.polimi.ingsw.client.model to notify the player and/or to ask him
 * to do an action.
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
     * Sends a message to a player.
     *
     * @param player  the player who receive the message
     * @param message the information to send to a player
     */
    public void sendMessage(Player player, Message message) {
        virtualView.sendMessage(player, message);
    }

    /**
     * Sends a message to all players.
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
     * Notifies that the game is over and returns the final score and rank.
     */
    public void notifyGameEnded(String player, int[] points, LinkedHashMap<String, Integer> ranking) {
        virtualView.sendMessage(player, new EndingScores(points, ranking));
    }

    /**
     * Announces the next turn phase.
     *
     * @param player        the player to send the message to
     * @param turnPhaseName the name of the turn phase
     * @param turnPhaseInfo information about the turn phase
     */
    public void announceTurnPhase(Player player, String turnPhaseName, String turnPhaseInfo) {
        virtualView.sendMessage(new TurnPhaseAnnouncement(turnPhaseName, turnPhaseInfo));
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
     * Send the update of leader on board after a useLeader command.
     *
     * @param leaderCard the card to sent
     */
    public void sendLeaderUpdate(LeaderCard leaderCard) {
        UpdateLeaderCards updateLeaderCards = new UpdateLeaderCards(leaderCard.getID(),
                leaderCard.getVictoryPoints(), leaderCard.getRequirements().identifier(),
                leaderCard.getLeaderPower().identifier());
        updateLeaderCards.setPlayer(game.getCurrentPlayer().getUsername());
        sendMessage(updateLeaderCards);
    }

    /**
     * Sends initial configurations of development grid and market to the player.
     *
     * @param player to send the message to
     */
    public void sendTable(Player player) {
        ArrayList<DevelopmentCard> cards = game.getDevelopmentGrid().getTopDevelopmentCards();
        ArrayList<Integer> level = new ArrayList<>();
        ArrayList<Integer> victoryPoints = new ArrayList<>();
        ArrayList<String> colors = new ArrayList<>();
        ArrayList<UtilityProductionAndCost[]> costs = new ArrayList<>();
        ArrayList<Production> productions = new ArrayList<>();
        ArrayList<Integer> IDs = new ArrayList<>();
        for (DevelopmentCard card : cards) {
            IDs.add(card.getID());
            level.add(card.getLevel());
            victoryPoints.add(card.getVictoryPoints());
            colors.add(card.getColorString());
            costs.add(card.getCost());
            productions.add(card.getProduction());
        }
        sendMessage(player, new InitDevGrid(IDs, colors, costs, level, victoryPoints, productions));
        Market market = game.getMarket();
        String extraMarble = market.getExtraMarble().getColorString();
        String[][] tray = new String[market.getRows()][market.getColumns()];
        for (int i = 0; i < market.getRows(); i++) {
            for (int j = 0; j < market.getColumns(); j++) {
                tray[i][j] = market.getTray()[i][j].getColorString();
            }
        }
        sendMessage(player, new InitMarket(tray, extraMarble));
    }

    /**
     * Sends initial configurations of development grid and market to all the players.
     */
    public void sendTable() {
        for (Player p :
                game.getPlayers()) {
            sendTable(p);
        }
    }

    /**
     * Sends to the player his leader cards.
     *
     * @param player the player to send the leader cards to
     */
    public void sendLeaderHand(Player player) {
        ArrayList<LeaderCard> cards = player.getLeaderCards();
        ArrayList<String[]> requirements = new ArrayList<>();
        ArrayList<String[]> leaderPowers = new ArrayList<>();
        ArrayList<Integer> victoryPoints = new ArrayList<>();
        ArrayList<Integer> IDs = new ArrayList<>();
        for (LeaderCard leaderCard : cards) {
            IDs.add(leaderCard.getID());
            requirements.add(leaderCard.getRequirements().identifier());
            leaderPowers.add(leaderCard.getLeaderPower().identifier());
            victoryPoints.add(leaderCard.getVictoryPoints());
        }
        sendMessage(player, new InitLeaderHand(IDs, victoryPoints, requirements, leaderPowers));
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
