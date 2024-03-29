package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.model.Production;
import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;
import it.polimi.ingsw.server.ServerMessageVisitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Methods of the it.polimi.ingsw.client.model that can be called by {@link ServerMessageVisitor}.
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
     * Checks if the message is sent or not by the current player.
     *
     * @param player the player who sent a message
     */
    private void checkIfPlayerCanDoThingsNow(Player player) throws GameException.IllegalMove {
        if (!player.equals(game.getCurrentPlayer()) && game.getCurrentTurnPhase() != null)
            throw new GameException.IllegalMove();
    }

    /**
     * Checks if the player can do the action he selected (i.e. if it's his turn and it's the right turn phase to do the
     * action).
     *
     * @param player    the player who wants to do an action
     * @param turnPhase the phase in which the action is acceptable
     */
    private void checkIfMoveIsValid(Player player, String turnPhase) throws GameException.IllegalMove {
        checkIfPlayerCanDoThingsNow(player);
        TurnPhase actual = game.getTurnPhase(turnPhase);
        if (!actual.equals(game.getCurrentTurnPhase()) || actual.isFinished())
            throw new GameException.IllegalMove();
    }

    /**
     * Checks if the player can do a Leader action.
     */
    private void checkIfIsLeaderPhase() throws GameException.IllegalMove {
        if (!game.getCurrentTurnPhase().equals(game.getTurnPhase("UseLeader")) &&
                !game.getCurrentTurnPhase().equals(game.getTurnPhase("UseAgainLeader")))
            throw new GameException.IllegalMove();
    }

    /**
     * Checks if the player can activate productions.
     */
    private void checkIfIsProductionPhase() throws GameException.IllegalMove {
        TurnPhase prodPhase = game.getTurnPhase("ActivateProduction");
        if (!prodPhase.equals(game.getCurrentTurnPhase()))
            throw new GameException.IllegalMove();
    }

    //main methods

    /**
     * Gives the player the indicated initial resource.
     *
     * @param player   the player who has selected the resource
     * @param resource the initial resource to give to the player
     */
    public void takeInitialResource(Player player, Resource resource) throws GameException.IllegalMove {
        if (game.getCurrentTurnPhase() != null || player.getInitialResources() == 0)
            throw new GameException.IllegalMove();
        player.getBoard().addResource(resource);
        player.setInitialResources(player.getInitialResources() - 1);
    }

    /**
     * Starts the chosen action.
     *
     * @param player    the player that has chosen the action
     * @param turnPhase the action the player has chosen
     */
    public void startChosenTurnPhase(Player player, String turnPhase) throws GameException.IllegalMove {
        checkIfMoveIsValid(player, "ChooseAction");
        for (String turn : selectablePhases)
            if (turn.equalsIgnoreCase(turnPhase)) {
                game.nextTurnPhase(turn);
                return;
            }
        throw new GameException.IllegalMove();
    }

    /**
     * Calls {@link Player#useLeader(LeaderCard)}.
     *
     * @param player the player who asked to use the leader
     */
    public void useLeader(Player player, int leaderCardNumber) throws IllegalArgumentException, GameException.IllegalMove {
        checkIfPlayerCanDoThingsNow(player);
        checkIfIsLeaderPhase();
        player.useLeader(player.getLeaderCards().get(leaderCardNumber - 1));
    }

    /**
     * Calls {@link Player#discardLeaderCard(LeaderCard)}.
     *
     * @param player the player who asked to discard the leader
     */
    public void discardLeader(Player player, int leaderCardNumber) throws IllegalArgumentException, GameException.IllegalMove {
        checkIfPlayerCanDoThingsNow(player);
        player.discardLeaderCard(player.getLeaderCards().get(leaderCardNumber - 1));
        if (game.getCurrentTurnPhase() == null && game.getPlayersToWait().contains(player))
            game.setPlayerReady(player);
    }

    /**
     * Calls {@link Market#getMarblesResources(Game, char, int)}.
     *
     * @param player      the player that asked to use the market
     * @param rowOrColumn selection between row or column
     * @param number      position of the selected row/column
     */
    public void useMarket(Player player, char rowOrColumn, int number)
            throws IllegalArgumentException, GameException.IllegalMove {
        checkIfMoveIsValid(player, "UseMarket");
        game.getMarket().getMarblesResources(game, rowOrColumn, number);
        game.getCurrentTurnPhase().setFinished(true);
    }

    /**
     * Gives the player the resource he has chosen when he picked the white Marble from the Market. Only called if the
     * player has two leaders with White Marble Conversion power.
     *
     * @param player the player to give the resources to
     * @param num    the chosen resource (indicated by its position in the WhiteAlt ArrayList, defined in Player)
     */
    public void giveChosenWhiteMarbleResource(Player player, int num)
            throws IllegalArgumentException, GameException.IllegalMove {
        checkIfPlayerCanDoThingsNow(player);
        if (!game.getCurrentTurnPhase().equals(game.getTurnPhase("UseMarket")) || player.getWhiteMarbleChoices() == 0)
            throw new GameException.IllegalMove();
        else if (num != 1 && num != 2)
            throw new IllegalArgumentException("The number must be one between 1 and 2.");
        game.getCurrentPlayer().getBoard().addResource(player.getWhiteAlt().get(num - 1));
        player.changeWhiteMarbleChoicesNumber(-1);
    }

    /**
     * Calls {@link Board#takeOutResource(int)}.
     *
     * @param player the player who asked to take out a resource
     * @param pos    position of the warehouse store
     */
    public void takeOutResource(Player player, int pos) throws GameException.IllegalMove {
        checkIfPlayerCanDoThingsNow(player);
        if (!game.getPlayersToWait().isEmpty() && !game.getPlayersToWait().contains(player))
            throw new GameException.IllegalMove();
        player.getBoard().takeOutResource(pos);
    }

    /**
     * Calls {@link Board#deployResource(Resource, int)}.
     *
     * @param player   the player who sent the command
     * @param resource the resource to place in the warehouse
     * @param pos      position of the warehouse store
     */
    public void deployResource(Player player, Resource resource, int pos) throws GameException.IllegalMove {
        checkIfPlayerCanDoThingsNow(player);
        player.getBoard().deployResource(resource, pos);
        if (game.getPlayersToWait().contains(player))
            game.setPlayerReady(player);
    }

    /**
     * Calls {@link Board#takeOutResource(int)}.
     *
     * @param player   the player who sent the command
     * @param resource the resource to discard
     */
    public void discardResource(Player player, Resource resource) throws GameException.IllegalMove {
        TurnPhase actual = game.getTurnPhase("UseMarket");
        if (!actual.equals(game.getCurrentTurnPhase()))
            throw new GameException.IllegalMove();
        player.getBoard().discardResource(resource);
    }

    /**
     * Calls {@link TurnPhase#nextTurnPhase()}, if possible.
     */
    public void nextTurnPhase(Player player) throws GameException.IllegalMove {
        checkIfPlayerCanDoThingsNow(player);
        if (game.getCurrentTurnPhase() == null)
            throw new GameException.IllegalMove();
        if (!player.getBoard().getResHand().isEmpty())
            throw new GameException.IllegalMove();
        else if (!(game.getCurrentTurnPhase().isFinished()))
            if (!(game.getCurrentTurnPhase().isSkippable()))
                throw new GameException.IllegalMove();
        game.nextTurnPhase();
    }

    /**
     * Board Production control
     *
     * @param player current player
     * @param cost   the resources to use for the production
     * @param prod   the resources the player wants to obtain from the production
     * @param box    the depots the player chooses to take resources from
     */
    public void startBoardProduction(Player player, String[] cost, String prod, int[] box) throws IllegalArgumentException, GameException.IllegalMove {
        checkIfPlayerCanDoThingsNow(player);
        checkIfIsProductionPhase();
        Resource production = Utility.mapResource.get(prod.toLowerCase());
        player.startBoardProduction(box, cost, production);
        player.getBoard().getProductionList().get(0).setProductionCanBeActivate(false);
        game.getCurrentTurnPhase().setFinished(true);
    }

    /**
     * Leader Production
     *
     * @param player current player
     * @param cost   command of the player
     * @param prod   command of the player
     * @param index  index of the production selected
     */
    public void startLeaderProduction(Player player, int cost, String prod, int index) throws IllegalArgumentException, GameException.IllegalMove {
        checkIfPlayerCanDoThingsNow(player);
        checkIfIsProductionPhase();
        Resource production = Utility.mapResource.get(prod.toLowerCase());
        player.startLeaderProduction(cost, production, index);
        Production productionPower = player.getBoard().getProductionList().get(index);
        productionPower.setProductionCanBeActivate(false);
        game.getCurrentTurnPhase().setFinished(true);
    }

    /**
     * controls for Development Card Production
     *
     * @param player current player
     * @param box    store from where the player take the resources
     * @param index  index of the production selected
     */
    public void startDevProduction(Player player, int[] box, int index) throws IllegalArgumentException, GameException.IllegalMove {
        checkIfPlayerCanDoThingsNow(player);
        checkIfIsProductionPhase();
        try {
            player.startDevProduction(index, box);
        } catch (IllegalArgumentException e) {
            UtilityProductionAndCost[] cost = player
                    .getBoard().showActiveDevCards().get(index - 1).getProduction().getCost();
            player.refundCost(player.getProcessedResources(), box, cost);
            throw e;
        }
        Production production = player.getBoard().getDevelopmentSpace()[index - 1].
                getDevelopmentCards().peek().getProduction();
        production.setProductionCanBeActivate(false);
        game.getCurrentTurnPhase().setFinished(true);
    }

    /**
     * Goes back to the choose action phase.
     *
     * @throws GameException.IllegalMove when it's not Market,Production or buyCard phase
     */
    public void back(String username) throws GameException.IllegalMove {
        checkIfPlayerCanDoThingsNow(game.getPlayer(username));
        game.back(selectablePhases);
    }

    /**
     * controls for buycard
     *
     * @param player      current player
     * @param level       level of the card
     * @param colorString color of the card
     * @param devSpace    space
     * @param box         box from where take the resource
     */
    public DevelopmentCard buyCard(Player player, int level, String colorString, int devSpace, int[] box) throws IllegalArgumentException, GameException.IllegalMove {
        checkIfMoveIsValid(player, "BuyDevelopmentCard");
        Color color = Utility.mapColor.get(colorString);
        if (level < 1 || level > 3)
            throw new IllegalArgumentException("the level must be 1,2 or 3");
        else if (Utility.colorPosition.get(color) == null)
            throw new IllegalArgumentException("Invalid Color");
        else if (game.getDevelopmentGrid().getDeck(level, color).getDeck().empty())
            throw new IllegalArgumentException("this place in empty");
        else if (!player.getBoard().getDevelopmentSpace()[devSpace - 1].hasRoomForCard(level))
            throw new IllegalArgumentException("this development place is empty");
        DevelopmentCard card = game.getDevelopmentGrid().getDeck(level, color).getTopCard();
        try {
            player.buyDevelopmentCard(card, box, devSpace);
        } catch (IllegalArgumentException e) {
            player.refundCost(player.getProcessedResources(), box, card.getCost());
            throw e;
        }
        game.getCurrentTurnPhase().setFinished(true);
        DevelopmentCard newCard;
        try  {
            newCard = game.getDevelopmentGrid().getDeck(level, color).getTopCard();
        } catch (EmptyStackException e) {
            return null;
        }
        return newCard;
    }
}

