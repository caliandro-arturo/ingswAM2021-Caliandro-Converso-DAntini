package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.messages.toServer.actions.StartProduction;
import it.polimi.ingsw.server.Controller;
import it.polimi.ingsw.messages.toClient.ErrorMessage;
import it.polimi.ingsw.messages.toServer.actions.UseMarket;
import it.polimi.ingsw.server.VirtualView;
import it.polimi.ingsw.server.model.ControllerAdapter;
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

    private void sendMessage(Player player, Message message) {
        controller.sendMessage(player, message);
    }

    private void sendMessageToOthers(Player p, Message msg) {
        controller.getVirtualView().sendMessageToOthers(p,msg);
    }

    private void confirmMove(Message message) {
        controller.getVirtualView().getClientMap().get(message.getPlayer()).confirmMove(message);
    }

    private void denyMove(Message message, String error) {
        sendMessage(message.getPlayer(), new ErrorMessage(message, error));
    }

    /**
     * Calls {@link ControllerAdapter#useMarket(Player, char, int)}.
     */
    public void visit(UseMarket msg) {
        Player player = msg.getPlayer();
        char rowOrColumn = msg.getRowOrColumn();
        int num = msg.getNum();
        try {
            controllerAdapter.useMarket(player, rowOrColumn, num);
        } catch (IllegalArgumentException | GameException.IllegalMove e) {
            denyMove(msg, e.getMessage());
        }
        confirmMove(msg);
        sendMessageToOthers(player, msg);
    }

    /* These methods are dedicated to the initial phases of games (creation of game, nickname setting).
     * For this reason, error handling is implemented differently than the others above.
     * Don't use these as references to write the other methods!
     */
    /**
     * Calls {@link Controller#createGame(SetGame)}.
     */
    public void visit(SetGame setGame) {
        controller.createGame(setGame);
    }

    /**
     * Calls {@link VirtualView#createPlayer(SetNickname)}.
     */
    public void visit(SetNickname setNickname) {
        VirtualView room = controller.getVirtualView();
        try {
            room.createPlayer(setNickname);
        } catch (GameException.NicknameAlreadyTaken e) {
            denyMove(setNickname, "You have already chosen your nickname.");
        }
    }

    /**
     *
     */
    @Override
    public void visit(StartProduction startProduction) {
        int ID = startProduction.getID();
        Player player = startProduction.getPlayer();
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
        }
        confirmMove(startProduction);
    }
}
