package it.polimi.ingsw.server;

import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.messages.toClient.ErrorMessage;
import it.polimi.ingsw.messages.toServer.ServerMessageVisitor;
import it.polimi.ingsw.messages.toServer.ToServerMessage;
import it.polimi.ingsw.messages.toClient.updates.GameHasBeenSet;
import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.GameCreator;
import it.polimi.ingsw.server.model.Player;

/**
 * Controller element of the MVC architectural pattern: sends received messages to the message visitor and creates the
 * game. It also contains the reference to the game itself, so any virtualView + controller instances couple represents
 * respectively view and controller of only one instance of a Game (the model).
 */
public class Controller {
    private final VirtualView virtualView;
    private final ServerMessageVisitor messageVisitor = new ServerMessageVisitor(this);
    private Game model;

    public Controller(VirtualView virtualView) {
        this.virtualView = virtualView;
    }

    public VirtualView getVirtualView() {
        return virtualView;
    }

    /**
     * Creates the game. If the game has already been created or the player that wants to create the game is not the first
     * player of the view, this fails and sends an error message to the player.
     * @param player
     * @param playersNum
     */
    public void createGame(Player player, int playersNum) {
        if (!virtualView.isTheFirstPlayer(player)) {
            sendMessage(player, new ErrorMessage("You cannot create the game."));
            return;
        } else if (virtualView.hasBeenSet()) {
            sendMessage(player, new ErrorMessage("The game has already been created."));
            return;
        }
        GameCreator gameCreator = new GameCreator();
        try {
            model = gameCreator.create(player, playersNum);
        } catch (IllegalArgumentException e) {
            virtualView.sendMessage(player, new ErrorMessage(e.getMessage()));
            return;
        }
        messageVisitor.setControllerAdapter(model.getControllerAdapter());
        model.getViewAdapter().setVirtualView(virtualView);
        virtualView.setHasBeenSet(true);
        virtualView.removeExtraClients(playersNum);
        virtualView.sendMessageToOthers(player, new GameHasBeenSet());
    }

    public int gamePlayersNum() {
        return model.getPlayersNum();
    }

    public void readMessage(Message message) {
        ((ToServerMessage) message).accept(messageVisitor);
    }

    public void sendMessage(Player player, Message message) {
        virtualView.sendMessage(player, message);
    }
}
