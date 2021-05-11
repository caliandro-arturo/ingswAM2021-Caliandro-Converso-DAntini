package it.polimi.ingsw.client;

import it.polimi.ingsw.messages.toClient.updates.*;
import it.polimi.ingsw.messages.toServer.SetGame;
import it.polimi.ingsw.messages.toServer.SetNickname;
import it.polimi.ingsw.messages.toServer.ToServerMessageHandler;
import it.polimi.ingsw.messages.toServer.actions.UseMarket;

/**
 * Handles model updates and service communications.
 */
public class UpdateHandler implements ToServerMessageHandler {
    private final ClientController controller;
    private final ClientModel model;

    public UpdateHandler(ClientController controller, ClientModel model) {
        this.controller = controller;
        this.model = model;
    }

    /*messages from server*/

    /**
     * Handles the entry of a new player.
     */
    public void visit(NewPlayer msg) {
        controller.showUpdate(msg.getName() + " has entered the game.");
    }

    /**
     * Handles the exit of a player from the game.
     */
    public void visit(PlayerLeft msg) {
        controller.showUpdate(msg.getName() + " has left the game.");
    }

    /**
     * Handles the start of the game.
     */
    public void visit(GameStarted msg) {

    }
    public void visit(LastTurn lastTurn) {
        if (!model.isLast())
            controller.showUpdate("Last turns: " + lastTurn.getReason());
    }
    //------------------------------------------------------------------------------------------------------------------
    /*messages from client*/

    /**
     * Sets the nickname of the player.
     */
    @Override
    public void visit(SetNickname msg) {
        model.setPlayerUsername(msg.getNickname());
        controller.showUpdate("Your nickname has been set.");
    }

    /**
     * Updates the market when the player uses it.
     */
    @Override
    public void visit(UseMarket msg) {
        model.getMarket().reinsertExtraMarble(msg.getRowOrColumn(),msg.getNum());
    }

    @Override
    public void visit(SetGame msg) {
        model.setNumOfPlayers(msg.getNumberOfPlayers());
    }


}
