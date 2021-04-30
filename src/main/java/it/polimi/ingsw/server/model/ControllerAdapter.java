package it.polimi.ingsw.server.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * methods of the model that can be called by {@link it.polimi.ingsw.messages.toServer.ServerMessageVisitor}.
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
     * and it's the right turn phase to do the action).
     *
     * @param player    the player who wants to do an action
     * @param turnPhase the phase in which the action is acceptable
     * @return {@code true} if the action is compatible with the context; {@code false} otherwise
     */
    private boolean isMoveValid(Player player, String turnPhase) {
        if(!playerCanDoThisNow(player)) return false;
        TurnPhase actual = game.getTurnPhase(turnPhase);
        try {
            if (actual.equals(game.getCurrentTurnPhase()) || actual.isFinished())
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
     * Calls {@link Game#addPlayer(Player)}.
     *
     * @param player the player to add to the game
     */
    public void addPlayer(Player player) throws GameException.GameAlreadyFull,
            GameException.NicknameAlreadyTaken {
        game.addPlayer(player);
    }

    /**
     * Gives the player the indicated initial resource.
     *
     * @param player the player who has selected the resource
     * @param resource the initial resource to give to the player
     */
    public void takeInitialResource(Player player, Resource resource) {
        try {
            if (game.isStarted() || player.getInitialResources() == 0)
                throw new GameException.IllegalMove();
        } catch (GameException.IllegalMove e) {
            game.getViewAdapter().sendErrorMessage(player, e.getMessage());
            return;
        }
        player.getBoard().addResource(resource);
        player.setInitialResources(player.getInitialResources() - 1);
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
     * Calls {@link Player#useLeader(LeaderCard)}.
     *
     * @param player the player who asked to use the leader
     */
    public void useLeader(Player player, int leaderCardNumber) {
        if (!isLeaderPhase(player)) return;
        try {
            player.useLeader(player.getLeaderCards().get(leaderCardNumber - 1));
        } catch (IllegalArgumentException e) {
            game.getViewAdapter().sendErrorMessage(player, e.getMessage());
        }
    }

    /**
     * Calls {@link Player#discardLeaderCard(LeaderCard)}.
     *
     * @param player the player who asked to discard the leader
     */
    public void discardLeader(Player player, int leaderCardNumber) {
        try {
            player.discardLeaderCard(player.getLeaderCards().get(leaderCardNumber - 1));
        } catch (IllegalArgumentException e) {
            game.getViewAdapter().sendErrorMessage(player, e.getMessage());
        }
        if (!game.isStarted() && !game.getPlayersToWait().contains(player))
            game.setPlayerReady(player);
    }

    /**
     * Calls {@link Market#getMarblesResources(Game, char, int)}.
     *
     * @param player      the player that asked to use the market
     * @param rowOrColumn selection between row or column
     * @param number      position of the selected row/column
     */
    public void useMarket(Player player, char rowOrColumn, int number) {
        if (!isMoveValid(player, "UseMarket")) return;
        try {
            game.getMarket().getMarblesResources(game, rowOrColumn, number);
        } catch (IllegalArgumentException e) {
            game.getViewAdapter().sendErrorMessage(player, e.getMessage());
            return;
        }
        game.getViewAdapter().sendMessage(player, "Now deploy your resources.");
        game.getCurrentTurnPhase().setFinished(true);
    }

    /**
     * Gives the player the resource he has chosen when he picked the white Marble from the Market.
     * Only called if the player has two leaders with White Marble Conversion power.
     *
     * @param player the player to give the resources to
     * @param num    the chosen resource (indicated by its position in the WhiteAlt ArrayList, defined in Player)
     */
    public void giveChosenWhiteMarbleResource(Player player, int num) {
        if (!isMoveValid(player, "UseMarket")) return;
        try {
            if (player.getWhiteMarbleChoices() == 0)
                throw new GameException.IllegalMove();
            else if (num != 1 && num != 2)
                throw new IllegalArgumentException("the number must be 1 or 2.");
        } catch (GameException.IllegalMove | IllegalArgumentException e) {
            game.getViewAdapter().sendErrorMessage(player, e.getMessage());
            return;
        }
        game.getCurrentPlayer().getBoard().addResource(player.getWhiteAlt().get(num - 1));
        player.changeWhiteMarbleChoicesNumber(-1);
    }

    /**
     * Calls {@link PersonalBoard#takeOutResource(int)}.
     *
     * @param player the player who asked to take out a resource
     * @param pos    position of the warehouse store
     */
    public void takeOutResource(Player player, int pos) {
        if(game.isStarted() && !playerCanDoThisNow(player)) return;
        try {
            if (!game.isStarted() && !game.getPlayersToWait().contains(player))
                throw new GameException.IllegalMove();
            else
                player.getBoard().takeOutResource(pos);
        } catch (IllegalArgumentException | GameException.IllegalMove e) {
            game.getViewAdapter().sendErrorMessage(player, e.getMessage());
        }
    }

    /**
     * Calls {@link PersonalBoard#deployResource(Resource, int)}.
     *
     * @param player   the player who sent the command
     * @param resource the resource to place in the warehouse
     * @param pos      position of the warehouse store
     */
    public void deployResource(Player player, Resource resource, int pos) {
        if (game.isStarted() && !playerCanDoThisNow(player)) return;
        try {
            player.getBoard().deployResource(resource, pos);
        } catch (IllegalArgumentException e) {
            game.getViewAdapter().sendErrorMessage(player, e.getMessage());
            return;
        }
        if (!game.isStarted() && !game.getPlayersToWait().contains(player))
            game.setPlayerReady(player);
    }

    /**
     * Calls {@link PersonalBoard#takeOutResource(int)}.
     *
     * @param player   the player who sent the command
     * @param resource the resource to discard
     */
    public void discardResource(Player player, Resource resource) {
        if (!isMoveValid(player, "UseMarket")) return;
        try {
            player.getBoard().discardResource(resource);
        } catch (GameException.IllegalMove e) {
            game.getViewAdapter().sendErrorMessage(player, e.getMessage());
        }
    }

    /**
     * Sends information about a turn phase to the player.
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
            if (!player.getBoard().getResHand().isEmpty())
                throw new GameException.IllegalMove();
            else if (!(game.getCurrentTurnPhase().isFinished()))
                if (!(game.getCurrentTurnPhase().isSkippable()))
                    throw new GameException.IllegalMove();
        } catch (GameException.IllegalMove e) {
            game.getViewAdapter().sendErrorMessage(player, e.getMessage());
            return;
        }
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
    public void startBoardProduction(Player player, String[] cost, String prod, int[] box) {
        if (!isMoveValid(player,"ActivateProduction"))
            return;
        Resource production = UtilityMap.mapResource.get(prod);
        try {
            player.startBoardProduction(box,cost,production);
        }catch (IllegalArgumentException e){
            game.getViewAdapter().sendErrorMessage(player,e.getMessage());
            return;
        }
        player.getBoard().getProductionList().get(0).setProductionCanBeActivate(false);
    }

    /**
     * Leader Production
     * @param player current player
     * @param cost command of the player
     * @param prod command of the player
     * @param index index of the production selected
     */
    public void startLeaderProduction(Player player,int cost,String prod, int index){
        if (!isMoveValid(player,"ActivateProduction"))
            return;
        Resource production = UtilityMap.mapResource.get(prod);
        try {
            player.startLeaderProduction(cost,production,index);
        } catch (IllegalArgumentException e){
            game.getViewAdapter().sendErrorMessage(player,e.getMessage());
            return;
        }
        Production productionPower = player.getBoard().getProductionList().get(index);
        productionPower.setProductionCanBeActivate(false);
    }

    /**
     * controls for Development Card Production
     * @param player current player
     * @param box store from where the player take the resources
     * @param index index of the production selected
     */
    public void startDevProduction(Player player,int[] box, int index) {
        if (!isMoveValid(player,"ActivateProduction"))
            return;
        try {
            player.startDevProduction(index,box);
        }catch (IllegalArgumentException e){
            game.getViewAdapter().sendErrorMessage(player,e.getMessage());
            return;
        }
        Production production = player.getBoard().getPersonalDevelopmentSpace()[index - 1].
                getDevelopmentCards().peek().getProduction();
        production.setProductionCanBeActivate(false);
    }

    /**
     * controls for buycard
     * @param player current player
     * @param level level of the card
     * @param color color of the card
     * @param devSpace space
     * @param box box from where take the resource
     */
    public void buyCard(Player player,int level,Color color,int devSpace, int[] box) {
        if (!isMoveValid(player,"BuyDevelopmentCard"))
            return;
        try{
            if (level<1 || level>3 )
                throw new IllegalArgumentException("the level must be 1,2 or 3");
            else if (UtilityMap.colorPosition.get(color) == null)
                throw new IllegalArgumentException("Invalid Color");
            else if (game.getDevelopmentGrid().getDeck(level,color).getDeck().empty())
                throw new IllegalArgumentException("this place in empty");
            else if (player.getBoard().getPersonalDevelopmentSpace()[devSpace-1].hasRoomForCard(level)){
                throw new IllegalArgumentException("this development place is empty");
            }
        } catch (IllegalArgumentException e){
            game.getViewAdapter().sendErrorMessage(player, e.getMessage());
            return;
        }
        DevelopmentCard card = game.getDevelopmentGrid().getDeck(level,color).getTopCard();
        player.buyDevelopmentCard(card,box,devSpace);
        game.getCurrentTurnPhase().setFinished(true);
    }
}

