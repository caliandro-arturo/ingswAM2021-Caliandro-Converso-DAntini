package it.polimi.ingsw.server;

import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.messages.toClient.ErrorMessage;
import it.polimi.ingsw.messages.toClient.updates.GameStarted;
import it.polimi.ingsw.messages.toServer.ServerMessageVisitor;
import it.polimi.ingsw.messages.toServer.SetGame;
import it.polimi.ingsw.messages.toServer.ToServerMessage;
import it.polimi.ingsw.messages.toServer.actions.StartProduction;
import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.GameCreator;
import it.polimi.ingsw.server.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
     * Creates the game. If the game has already been created or the player that wants to create the game is not the
     * first player of the view, this fails and sends an error message to the player.
     *
     * @param setGame the message with information about the game to create (the player and the number of players
     */
    public void createGame(SetGame setGame) {
        Player player = setGame.getPlayer();
        int playersNum = setGame.getNumberOfPlayers();
        if (!virtualView.isTheFirstPlayer(player)) {
            sendMessage(player, new ErrorMessage(setGame, "You cannot create the game."));
            return;
        } else if (virtualView.hasBeenSet()) {
            sendMessage(player, new ErrorMessage(setGame, "The game has already been created."));
            return;
        }
        try {
            model = new GameCreator().create(player, playersNum);
        } catch (IllegalArgumentException e) {
            virtualView.sendMessage(player, new ErrorMessage(setGame, e.getMessage()));
            return;
        }
        virtualView.getClientMap().get(player).confirmMove(setGame);
        messageVisitor.setControllerAdapter(model.getControllerAdapter());
        model.getViewAdapter().setVirtualView(virtualView);
        virtualView.setHasBeenSet(true);
        if (virtualView.isFull()) {
            virtualView.removeExtraClients(playersNum);
            startGame();
        }
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

    public void startGame() {
        virtualView.sendMessage(new GameStarted());
        try {
            List<Player> toAdd = new ArrayList<>(
                    virtualView.getClientMap().keySet()).subList(1, virtualView.getClientMap().keySet().size());
            model.addPlayers(toAdd);
        } catch (IndexOutOfBoundsException ignore) {
        }
        model.setUpPlayers();
    }

    public boolean isFinished() {
        return model.isFinished();
    }

}
