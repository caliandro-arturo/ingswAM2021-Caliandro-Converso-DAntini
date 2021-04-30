package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.server.Controller;
import it.polimi.ingsw.messages.toClient.ErrorMessage;
import it.polimi.ingsw.messages.toServer.actions.UseMarket;
import it.polimi.ingsw.server.VirtualView;
import it.polimi.ingsw.server.model.ControllerAdapter;
import it.polimi.ingsw.server.model.GameException;
import it.polimi.ingsw.server.model.Player;

/**
 * Handles messages from client and calls {@link ControllerAdapter} methods.
 */
public class ServerMessageVisitor {
    private final Controller controller;
    private ControllerAdapter controllerAdapter;

    public ServerMessageVisitor(Controller controller) {
        this.controller = controller;
    }

    public void setControllerAdapter(ControllerAdapter controllerAdapter) {
        this.controllerAdapter = controllerAdapter;
    }

    /**
     * Calls {@link ControllerAdapter#useMarket(Player, char, int)}.
     */
    public void visit(UseMarket useMarket) {
        Player player = useMarket.getPlayer();
        char rowOrColumn = useMarket.getRowOrColumn();
        int num = useMarket.getNum();
        controllerAdapter.useMarket(player, rowOrColumn, num);
    }

    /**
     * Calls {@link Controller#createGame(Player, int)}.
     */
    public void visit(SetGame setGame) {
        Player player = setGame.getPlayer();
        int playersNum = setGame.getNumberOfPlayers();
        controller.createGame(player, playersNum);
    }

    /**
     * Disconnects the player.
     */
    public void visit(Disconnect disconnect) {
        //todo handle manual disconnection
    }

    /**
     * Called when a player tries to change nickname after having already chosen one.
     */
    public void visit(SetNickname setNickname) {
        VirtualView room = controller.getVirtualView();
        try {
            room.createPlayer(setNickname.getNickname(), room.getClientMap().get(setNickname.getPlayer()));
        } catch (GameException.NicknameAlreadyTaken e) {
            controller.sendMessage(setNickname.getPlayer(), new ErrorMessage("You have already chosen your nickname."));
        } catch (GameException.GameAlreadyFull e) {
            /*controller.*/
        }
    }
}
