package it.polimi.ingsw.client;

import it.polimi.ingsw.client.model.LeaderHand;
import it.polimi.ingsw.common_files.message.toClient.updates.*;
import it.polimi.ingsw.common_files.message.toServer.SetGame;
import it.polimi.ingsw.common_files.message.toServer.SetNickname;
import it.polimi.ingsw.common_files.message.toServer.ToServerMessageHandler;
import it.polimi.ingsw.common_files.message.toServer.actions.*;
import it.polimi.ingsw.common_files.model.DevelopmentCard;
import it.polimi.ingsw.common_files.model.Production;
import it.polimi.ingsw.common_files.model.Utility;
import it.polimi.ingsw.common_files.model.UtilityProductionAndCost;

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

    /**
     *
     */
    public void visit(GridUpdate msg){
        model.getDevelopmentGrid().setGrid(msg.getGrid());
    }

    /**
     * updates the hand of the player with the recently bought resources from the market
     * @param msg
     */
    public void visit(ResourceUpdate msg){
        model.getBoard().setResHand(msg.getResHandUpdate());
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

    /**
     *
     * @param msg
     */
    @Override
    public void visit(SetGame msg) {
        model.setNumOfPlayers(msg.getNumberOfPlayers());
    }


    @Override
    public void visit(StartProduction msg) {
        if (msg.getID() == 0){
            model.getBoard().getStrongbox().addResources(1,Utility.mapResource.
                    get(msg.getProduction()));
        } else if (msg.getID()<=3){
            UtilityProductionAndCost[] prod = model.getBoard().
                    getDevelopmentPlace().getTopCard(msg.getID()).getProduction().getProd();
            for (int i = 0; i<prod.length; i++) {
                if (Utility.isStorable(prod[i].getResource())) {
                    model.getBoard().getStrongbox().addResources(prod[i].getQuantity(),
                            prod[i].getResource());
                }else {
                    for (int k=0; k<prod[i].getQuantity(); i++){
                        model.getBoard().getFaithTrack().addPosition();
                    }
                }
            }
        } else {
            model.getBoard().getStrongbox().addResources(1,Utility.mapResource.
                    get(msg.getProduction()));
            model.getBoard().getFaithTrack().addPosition();
        }
    }

    @Override
    public void visit(BuyCard msg) {

    }

    @Override
    public void visit(DeployRes deployRes) {

    }

    @Override
    public void visit(UseLeader msg) {
        LeaderHand leaderHand = model.getLeaderHand();
        model.getFullBoard().getLeaderCards().add(leaderHand.getHand().get(msg.getIDCard()-1));
        leaderHand.removeCardFromHand(msg.getIDCard());
    }

    @Override
    public void visit(DiscardLeader msg){
        model.getLeaderHand().removeCardFromHand(msg.getPos());
        model.getBoard().getFaithTrack().addPosition();
    }

    @Override
    public void visit(TakeRes takeRes) {

    }
}
