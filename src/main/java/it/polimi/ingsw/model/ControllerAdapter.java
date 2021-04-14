package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is for handling messages sent from the client.
 */
public class ControllerAdapter {
    private final Game game;
    private final ArrayList<String> selectablePhases = new ArrayList<>(
            Arrays.asList("UseMarket", "BuyDevelopmentCard", "ActivateProduction")
    );
    public ControllerAdapter(Game game) {
        this.game = game;
    }

    //auxiliary methods
    /**
     * Checks that the message is sent or not by the current player.
     *
     * @param player the player who sent a message
     * @return {@code true} if the player is the current player, {@code false} otherwise
     */
    private boolean playerCanDoThisNow(Player player) {
        try {
            if (!player.equals(game.getCurrentPlayer())) {
                throw new GameException.MoveMadeOutsideOfHisTurn();
            }
        } catch (GameException.MoveMadeOutsideOfHisTurn e) {
            game.getViewAdapter().sendErrorMessage(player, e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Checks if the player can do the action he selected (i.e. if it's his turn
     * and it's the right turn phase to do the action)
     * @param player    the player who wants to do an action
     * @param turnPhase the phase in which the action is acceptable
     * @return {@code true} if the action is compatible with the context; {@code false} otherwise
     */
    private boolean isMoveValid(Player player, String turnPhase) {
        if(!playerCanDoThisNow(player)) return false;
        try {
            if (game.getTurnPhase(turnPhase).equals(game.getCurrentTurnPhase()))
                throw new GameException.IllegalMove();
        } catch (GameException.IllegalMove e) {
            game.getViewAdapter().sendErrorMessage(player, e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Checks if the player can do a Leader action.
     * @param player the player who wants to use the leader
     * @return {@code true} if the player is the current player and the current turn phase
     * is a UseLeaderPhase; {@code false} otherwise
     */
    private boolean isLeaderPhase(Player player) {
        if (!playerCanDoThisNow(player)) return false;
        try {
            if(!(game.getCurrentTurnPhase().equals(game.getTurnPhase("UseLeader")) ||
                    game.getCurrentTurnPhase().equals(game.getTurnPhase("UseAgainLeader"))))
                throw new GameException.IllegalMove();
        } catch (GameException.IllegalMove e) {
            game.getViewAdapter().sendErrorMessage(player, e.getMessage());
        }
        return true;
    }

    //main methods
    /**
     * Calls {@link Game#addPlayer(Player)}
     *
     * @param player the player to add to the game
     * @
     */
    public void addPlayer(Player player) {
        try {
            game.addPlayer(player);
        } catch (GameException.GameAlreadyFull | GameException.NicknameAlreadyTaken e) {
            game.getViewAdapter().sendErrorMessage(player, e.getMessage());
        }
    }

    public void initializePlayer(Player player, ArrayList<Resource> resources, LeaderCard[] leaderCards) {
        //
        //todo implement Player.discardLeaderCard(...)
        //
        game.setPlayerReady(player);
    }

    /**
     * Starts the chosen action.
     *
     * @param player    the player that has chosen the action
     * @param turnPhase the action the player has chosen
     */
    public void startChosenTurnPhase(Player player, String turnPhase) {
        if (!isMoveValid(player, "ChooseAction")) return;
        try {
            if (!selectablePhases.contains(turnPhase))
                throw new GameException.IllegalMove();
        } catch (GameException.IllegalMove e) {
            game.getViewAdapter().sendErrorMessage(player, e.getMessage());
            return;
        }
        game.nextTurnPhase(turnPhase);
    }

    /**
     * Calls {@link Player#useLeader(LeaderCard)}
     * @param player the player who asked to use the leader
     */
    public void useLeader(Player player/*, LeaderCard leader*/) {
        if (!isLeaderPhase(player)) return;
        /*player.useLeader(leader);*/
    }

    /**
     * Calls {@link Player#discardLeaderCard(LeaderCard)}
     * @param player the player who asked to discard the leader
     */
    public void discardLeader(Player player/*, LeaderCard leader*/) {
        if (!isLeaderPhase(player)) return;
        /*player.discardLeaderCard(leader);*/
    }

    /**
     * Calls {@link Market#getMarblesResources(Game, char, int)}.
     * @param player the player that asked to use the market
     * @param rowOrColumn selection between row or column
     * @param number position of the selected row/column
     */
    public void useMarket(Player player, char rowOrColumn, int number) {
        if(!isMoveValid(player, "UseMarket")) return;
        try {
            game.getMarket().getMarblesResources(game, rowOrColumn, number);
        } catch (GameException.InvalidArgument e) {
            game.getViewAdapter().sendErrorMessage(player, e.getMessage());
            return;
        }
        game.getViewAdapter().sendMessage(player, "Market used successfully.");
        game.getCurrentTurnPhase().setFinished(true);
    }

    /**
     * Gives the player the resource he has chosen when he picked the white Marble from the Market.
     * Only called when te player has two leaders with White Marble Conversion power.
     *
     * @param player the player to give the resources to
     * @param num    the chosen resource (indicated by its position in the WhiteAlt ArrayList, defined in Player)
     */
    public void giveChosenWhiteMarbleResource(Player player, int num) {
        if (!isMoveValid(player, "UseMarket")) return;
        try {
            if(!game.expectedWhiteMarbleEffectChoice())
                throw new GameException.IllegalMove();
            else if (num != 1 && num != 2)
                throw new GameException.InvalidArgument("the number must be 1 or 2.");
        } catch (GameException.IllegalMove | GameException.InvalidArgument e) {
            game.getViewAdapter().sendErrorMessage(player, e.getMessage());
            return;
        }
        game.getCurrentPlayer().getBoard().addResource(player.getWhiteAlt().get(num - 1));
        game.setExpectedWhiteMarbleEffectChoice(false);
    }

    /**
     * Sends information about a turn to the player.
     *
     * @param player    the player who asked information about the turn phase
     * @param turnPhase the turn phase for which information has been requested
     */
    public void givePlayerTurnPhaseInfo(Player player, String turnPhase) {
        game.getViewAdapter().giveTurnPhaseInfo(game.getTurnPhase(turnPhase).getPhaseInfo());
    }

    /**
     * Calls {@link TurnPhase#nextTurnPhase()}, if possible.
     */
    public void nextTurnPhase(Player player) {
        if (!playerCanDoThisNow(player)) return;
        try {
            if (!(game.getCurrentTurnPhase().isFinished()))
                if (!(game.getCurrentTurnPhase().isSkippable()))
                    throw new GameException.IllegalMove();
        } catch (GameException.IllegalMove e) {
            game.getViewAdapter().sendErrorMessage(player, e.getMessage());
            return;
        }
        game.nextTurnPhase();
    }
}
