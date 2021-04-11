package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is for handling messages sent from the client.
 *
 */
public class ControllerAdapter {
    private Game game;
    private final ArrayList<String> selectablePhases = new ArrayList<>(
            Arrays.asList("UseMarket", "BuyDevelopmentCard", "ActivateProduction")
    );
    public ControllerAdapter(Game game) {
        this.game = game;
    }

    /**
     * This method checks that the message is sent or not by the current player.
     * @param player the player who sent a message
     * @return true if the player is not the current player, false otherwise
     */
    private boolean notExpectedPlayerInput(Player player) {
        if (!player.equals(game.getCurrentPlayer())) {
            game.getViewAdapter().notifyNotPlayerTurn(player);
            return true;
        }
        return false;
    }

    /**
     * This method starts a turn phase.
     * @param phase the turn phase to start
     */
    private void turnStart(String phase) {
        game.setCurrentTurnPhase(game.getTurnPhases().get(phase));
        game.getCurrentTurnPhase().start();
    }

    /**
     * This method starts the chosen action, checking if that is valid.
     * @param player the player that has chosen the action
     * @param turnPhase the action the player has chosen
     */
    public void startChosenTurnPhase(Player player, String turnPhase) {
        if(notExpectedPlayerInput(player)) return;
        else if(!selectablePhases.contains(turnPhase))
            game.getViewAdapter().notifyInvalidChoice();
        else turnStart(turnPhase);
    }

    /**
     * This method gives the player the resource he has chosen when he picked the white Marble from the Market.
     * Only called when te player has two leaders with White Marble Conversion power.
     * @param player the player to give the resources to
     * @param resource the resource to be given to the player
     */
    public void giveChosenWhiteMarbleResource(Player player, Resource resource) {
        if(notExpectedPlayerInput(player)) return;
        game.getCurrentPlayer().getBoard().addResource(resource);
    }

    /**
     * This method sends information about a turn to the player.
     * @param player the player who asked information about the turn phase
     * @param turnPhase the turn phase for which information has been requested
     */
    public void givePlayerTurnPhaseInfo(Player player, String turnPhase) {
        game.getViewAdapter().giveTurnPhaseInfo(game.getTurnPhases().get(turnPhase).getPhaseInfo());
    }

    public void addPlayer(Player player) {
        game.addPlayer(player);
    }
}
