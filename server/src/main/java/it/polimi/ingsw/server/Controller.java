package it.polimi.ingsw.server;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ErrorMessage;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.GameStamp;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.GameStarted;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.TimeUp;
import it.polimi.ingsw.commonFiles.messages.toServer.SetGame;
import it.polimi.ingsw.commonFiles.messages.toServer.ToServerMessage;
import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.GameCreator;
import it.polimi.ingsw.server.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller element of the MVC architectural pattern: sends received messages to the message visitor and creates the
 * game. It also contains the reference to the game itself, so any virtualView + controller instances couple represents
 * respectively view and controller of only one instance of a Game (the it.polimi.ingsw.client.model).
 */
public class Controller {
    private final VirtualView virtualView;
    private final ServerMessageVisitor messageVisitor = new ServerMessageVisitor(this);
    private Game model;
    private boolean isGameStarted = false;
    private boolean isInPause = false;
    private Thread messageReader;

    public Controller(VirtualView virtualView) {
        this.virtualView = virtualView;
    }

    public VirtualView getVirtualView() {
        return virtualView;
    }

    public Game getModel() {
        return model;
    }

    public boolean isGameStarted() {
        return isGameStarted;
    }

    /**
     * Creates the game. If the game has already been created or the player that wants to create the game is not the
     * first player of the view, this fails and sends an error message to the player.
     *
     * @param setGame the message with information about the game to create (the player and the number of players
     */
    public void createGame(SetGame setGame) throws IllegalArgumentException {
        String playerNick = setGame.getPlayer();
        Player player = new Player(playerNick);
        int playersNum = setGame.getNumberOfPlayers();
        model = new GameCreator().create(player, playersNum);
        messageVisitor.setControllerAdapter(model.getControllerAdapter());
        model.getViewAdapter().setVirtualView(virtualView);
    }

    public int gamePlayersNum() {
        return model.getPlayersNum();
    }

    /**
     * Reads a message coming from the client.
     *
     * @param message
     */
    public void readMessage(Message message) {
        try {
            messageReader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        } catch (NullPointerException ignore) {
        }
        if (isInPause) sendMessage(message.getPlayer(), new ErrorMessage("Game in pause to re-add a player."));
        else if (isFinished()) sendMessage(message.getPlayer(), new ErrorMessage("Game is finished."));
        else {
            messageReader = new Thread(() -> ((ToServerMessage) message).accept(messageVisitor));
            messageReader.start();
        }
    }

    public void sendMessage(String player, Message message) {
        virtualView.sendMessage(player, message);
    }

    public void sendMessage(Player player, Message message) {
        sendMessage(player.getUsername(), message);
    }

    /**
     * Starts the game and sets it sending a message.
     */
    public void startGame() {
        isGameStarted = true;
        virtualView.sendMessage(new GameStarted());
        try {
            List<String> toAdd = new ArrayList<>(
                    virtualView.getClientMap().keySet()).subList(1, virtualView.getClientMap().keySet().size());
            model.addPlayers(toAdd);
        } catch (IndexOutOfBoundsException ignore) {
        }
        model.setUpPlayers();
    }

    public Player getPlayer(String nick) {
        return model.getPlayer(nick);
    }

    /**
     * Sets the game status sending a message.
     *
     * @param nickname
     */
    public void sendGameStatus(String nickname) {
        GameStamp gameStamp = new GameStamp(model.toString());
        gameStamp.setPlayer(nickname);
        sendMessage(nickname, gameStamp);
        model.getViewAdapter().sendTable(getPlayer(nickname));
        model.getViewAdapter().sendLeaderHand(getPlayer(nickname));
    }

    /**
     * Checks if a game is finished.
     *
     * @return
     */
    public boolean isFinished() {
        return model.isFinished();
    }

    /**
     * Pause the game.
     *
     * @param reenteringPlayer
     */
    public void pauseGame(String reenteringPlayer) {
        isInPause = true;
        try {
            messageReader.join();
        } catch (InterruptedException | NullPointerException ignore) {
        }
        virtualView.sendMessageToOthers(reenteringPlayer, new TimeUp(true));
    }

    /**
     * Restarts the game.
     *
     * @param reenteringPlayer
     */
    public void restartGame(String reenteringPlayer) {
        isInPause = false;
        virtualView.sendMessageToOthers(reenteringPlayer, new TimeUp(false));
    }
}
