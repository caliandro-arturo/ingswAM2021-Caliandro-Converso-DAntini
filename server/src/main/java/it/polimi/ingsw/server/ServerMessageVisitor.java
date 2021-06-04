package it.polimi.ingsw.server;

import it.polimi.ingsw.commonFiles.messages.toClient.AskWhiteMarble;
import it.polimi.ingsw.commonFiles.messages.toServer.*;
import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ErrorMessage;
import it.polimi.ingsw.commonFiles.model.Card;
import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.server.model.ControllerAdapter;
import it.polimi.ingsw.server.model.DevelopmentCard;
import it.polimi.ingsw.server.model.GameException;
import it.polimi.ingsw.server.model.Player;

/**
 * Visitor element of the visitor pattern. Handles messages from client and calls mostly {@link ControllerAdapter}
 * methods.
 */
public class ServerMessageVisitor implements ToServerMessageHandler {
    private final Controller controller;
    private ControllerAdapter controllerAdapter;

    public ServerMessageVisitor(Controller controller) {
        this.controller = controller;
    }

    public void setControllerAdapter(ControllerAdapter controllerAdapter) {
        this.controllerAdapter = controllerAdapter;
    }

    private Player getPlayer(String nick) {
        return controller.getPlayer(nick);
    }

    private void sendMessage(Player player, Message message) {
        controller.sendMessage(player, message);
    }
    private void sendMessage(String player, Message message) {
        controller.sendMessage(getPlayer(player), message);
    }

    private void confirmMove(Message message) {
        controller.getVirtualView().sendMessage(message);
    }

    private void denyMove(Message message, String error) {
        sendMessage(message.getPlayer(), new ErrorMessage( error));
    }

    /**
     * Calls {@link ControllerAdapter#useMarket(Player, char, int)}.
     */
    public void visit(UseMarket msg) {
        Player player = getPlayer(msg.getPlayer());
        char rowOrColumn = msg.getRowOrColumn();
        int num = msg.getNum();
        try {
            controllerAdapter.useMarket(player, rowOrColumn, num);
        } catch (IllegalArgumentException | GameException.IllegalMove e) {
            denyMove(msg, e.getMessage());
            return;
        }
        confirmMove(msg);
        if (player.getWhiteMarbleChoices() > 0)
            sendMessage(player, new AskWhiteMarble(player.getWhiteMarbleChoices()));
    }

    @Override
    public void visit(Back back){
        try {
            controllerAdapter.back(back.getPlayer());
        } catch (GameException.IllegalMove e){
            denyMove(back, e.getMessage());
        }

    }

    @Override
    public void visit(DiscardRes discardRes) {
        try {
            controllerAdapter.discardResource(getPlayer(discardRes.getPlayer()),discardRes.getResource());
        }  catch (GameException.IllegalMove e) {
            denyMove(discardRes,e.getMessage());
            return;
        }
        confirmMove(discardRes);
    }

    /**
     * Calls a startProduction method based on the production indicated in the message.
     */
    @Override
    public void visit(StartProduction startProduction) {
        int ID = startProduction.getID();
        Player player = getPlayer(startProduction.getPlayer());
        String[] costResource = startProduction.getCostResource();
        String production =  startProduction.getProduction();
        int[] costs = startProduction.getCost().stream().mapToInt(i->i).toArray();
        try{
            if (ID == 0){
                controllerAdapter.startBoardProduction(player,costResource,production,costs);
            } else if (ID<=3){
                controllerAdapter.startDevProduction(player,costs,ID);
            } else {
                controllerAdapter.startLeaderProduction(player,costs[0],production,ID);
            }
        }catch (GameException.IllegalMove e){
            denyMove(startProduction,e.getMessage());
            return;
        }
        confirmMove(startProduction);
    }

    /**
     * Calls {@link ControllerAdapter#buyCard(Player player, int, String, int, int[])}.
     */
    @Override
    public void visit(BuyCard buyCard) {
        DevelopmentCard newCard;
        try {
            newCard = controllerAdapter.buyCard(getPlayer(buyCard.getPlayer()), buyCard.getLevel(), buyCard.getColor(), buyCard.getSpace(),
                    buyCard.getStores());
        } catch (GameException.IllegalMove e) {
            denyMove(buyCard, e.getMessage());
            return;
        }
        buyCard.setNewCard(new Card(newCard.getID(), newCard.getCost(), newCard.getLevel(), newCard.getProduction()));
        confirmMove(buyCard);
    }

    /**
     * Calls {@link ControllerAdapter#discardResource(Player, Resource)}.
     */
    @Override
    public void visit(DeployRes msg) {
        try {
            controllerAdapter.deployResource(getPlayer(msg.getPlayer()), msg.getResource(), msg.getDepot());
        } catch (IllegalArgumentException | GameException.IllegalMove e) {
            denyMove(msg, e.getMessage());
            return;
        } catch (IndexOutOfBoundsException e) {
            denyMove(msg, "The indicated depot does not exist.");
            return;
        }
        confirmMove(msg);
    }

    @Override
    public void visit(UseLeader useLeader) {
        try{
            controllerAdapter.useLeader(getPlayer(useLeader.getPlayer()), useLeader.getIDCard());
        } catch (GameException.IllegalMove e){
            denyMove(useLeader,e.getMessage());
            return;
        }
        confirmMove(useLeader);
    }

    @Override
    public void visit(DiscardLeader discardLeader) {
        try{
            controllerAdapter.discardLeader(getPlayer(discardLeader.getPlayer()), discardLeader.getPos());
        } catch (IllegalArgumentException | GameException.IllegalMove e){
            denyMove(discardLeader,e.getMessage());
            return;
        }
        confirmMove(discardLeader);
    }

    @Override
    public void visit(TakeRes msg) {
        try{
            controllerAdapter.takeOutResource(getPlayer(msg.getPlayer()), msg.getDepot());
        } catch (Exception e){
            denyMove(msg, e.getMessage());
            return;
        }
        confirmMove(msg);
    }

    /**
     * Handles the taking of an initial resource by a player.
     */
    @Override
    public void visit(GetResource msg) {
        try {
            controllerAdapter.takeInitialResource(getPlayer(msg.getPlayer()), msg.getResource());
        } catch (GameException.IllegalMove e) {
            denyMove(msg, e.getMessage());
            return;
        }
        confirmMove(msg);
    }

    /**
     * Handles the choice of the leader to use when a white marble has been picked.
     */
    @Override
    public void visit(ChooseWhiteMarble msg) {
        try {
            controllerAdapter.giveChosenWhiteMarbleResource(getPlayer(msg.getPlayer()), msg.getLeaderPosition());
        } catch (IllegalArgumentException | GameException.IllegalMove e) {
            denyMove(msg, e.getMessage());
        }
        Message message = new GetResource(getPlayer(msg.getPlayer()).getWhiteAlt().get(msg.getLeaderPosition() - 1));
        message.setPlayer(msg.getPlayer());
        confirmMove(message);
    }

    /**
     * Handles requests to go to the next turn phase.
     */
    @Override
    public void visit(Next next) {
        try {
            controllerAdapter.nextTurnPhase(getPlayer(next.getPlayer()));
        } catch (GameException.IllegalMove e) {
            denyMove(next, e.getMessage());
        }
    }

    /**
     * Handles requests to choose the turn action.
     */
    @Override
    public void visit(ChooseTurnPhase msg) {
        Player player = getPlayer(msg.getPlayer());
        String turnPhase = msg.getTurnPhaseName();
        try {
            controllerAdapter.startChosenTurnPhase(player, turnPhase);
        } catch (GameException.IllegalMove e) {
            denyMove(msg, e.getMessage());
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    /* These methods are dedicated to the initial phases of game.
     * For this reason, error handling is implemented differently than the others above.
     */
    /**
     * Calls {@link Controller#createGame(SetGame)}.
     */
    @Override
    public void visit(SetGame setGame) {
        controller.createGame(setGame);
    }

    @Override
    public void visit(SetNickname setNickname) {
    }

    @Override
    public void visit(GamesList gamesList) {
    }

    @Override
    public void visit(JoinGame joinGame) {
    }
}
