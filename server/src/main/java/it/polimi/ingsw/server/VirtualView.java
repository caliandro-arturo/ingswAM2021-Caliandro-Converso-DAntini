package it.polimi.ingsw.server;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.NewPlayer;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.PlayerLeft;
import it.polimi.ingsw.server.model.Player;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * View element of the MVC architectural pattern. It also acts as a waiting room and manages the exchanging of
 * messages.
 */
public class VirtualView {
    /**
     * The name of the virtual view.
     */
    private final String name;
    /**
     * The reference to the server instance.
     */
    private final ServerMain serverMain;
    /**
     * The controller
     */
    private final Controller controller = new Controller(this);
    /**
     * The map of clients connected to this virtual view.
     */
    private final HashMap<String, ClientHandler> clientMap = new LinkedHashMap<>();

    public VirtualView(ServerMain serverMain, String name) {
        this.serverMain = serverMain;
        this.name = name;
    }

    public HashMap<String, ClientHandler> getClientMap() {
        return clientMap;
    }

    public String getName() {
        return name;
    }

    public Controller getController() {
        return controller;
    }

    /**
     * Checks if the game is full.
     *
     * @return {@code true} if the game is full; {@code false} otherwise
     */
    public boolean isFull() {
        return maxPlayersNumber() == connectedClients();
    }

    /**
     * Returns the number of clients connected to this virtual view.
     */
    public int connectedClients() {
        return clientMap.keySet().size();
    }

    /**
     * Returns the expected number of players in the game.
     */
    public int maxPlayersNumber() {
        return controller.gamePlayersNum();
    }

    /**
     * Creates the player for the client.
     *
     * @param nickname the message that the client sent to set the nickname
     */
    public void addPlayer(String nickname, ClientHandler clientHandler) {
        clientMap.put(nickname, clientHandler);
        serverMain.getClientToLobbyMap().put(nickname, this);
        sendMessageToOthers(nickname, new NewPlayer(nickname));
    }

    /**
     * Re-adds a player that has previously lost the connection.
     *
     * @param nickname the previous nickname
     * @param clientHandler the new client handler to link with the nickname
     */
    public synchronized void reAddPlayer(String nickname, ClientHandler clientHandler) {
        controller.pauseGame(nickname);
        clientMap.replace(nickname, clientHandler);
        controller.getPlayer(nickname).setConnected(true);
        controller.sendGameStatus(nickname);
        controller.restartGame(nickname);
    }

    /**
     * Removes a client. If there are no connected clients, removes any reference to this virtual view in the server.
     *
     * @param client the client to remove
     */
    public synchronized void remove(ClientHandler client) {
        if (controller.isGameStarted()) {
            controller.getPlayer(client.getPlayer()).setConnected(false);
            clientMap.replace(client.getPlayer(), null);
        } else {
            clientMap.remove(client.getPlayer());
            serverMain.getClientToLobbyMap().remove(client.getPlayer());
        }
        sendMessageToOthers(client.getPlayer(), new PlayerLeft(client.getPlayer()));
        if (clientMap.isEmpty() || clientMap.values().stream().noneMatch(Objects::nonNull))
            serverMain.removeVirtualView(this);
    }

    /**
     * Calls {@link Controller#readMessage(Message)}.
     *
     * @param message the message to read
     */
    public void readMessage(Message message) {
        controller.readMessage(message);
    }

    /**
     * Calls {@link ClientHandler#sendMessage(Message)} on the client associated to the player.
     *
     * @param player  the player to send the message to
     * @param message the message to send
     */
    public void sendMessage(String player, Message message) {
        sendMessage(clientMap.get(player), message);
    }

    public void sendMessage(Player player, Message message) {
        sendMessage(player.getUsername(), message);
    }

    public void sendMessage(ClientHandler client, Message message) {
        if (client != null) try {
            client.sendMessage(message);
        } catch (IOException e) {
            System.err.println("Problem when sending messages to " + client.getPlayer() + ": " + e.getMessage());
        }
    }

    /**
     * Sends a message to all the players in {@link VirtualView#clientMap}.
     *
     * @param message the message to send
     */
    public void sendMessage(Message message) {
        for (String player : clientMap.keySet()) {
            if (clientMap.get(player) != null)
                sendMessage(player, message);
        }
    }

    /**
     * Sends a message to all the players in {@link VirtualView#clientMap}, except for the player passed as parameter.
     *
     * @param player  the player not to send the message to
     * @param message the message to send to other players
     */
    public void sendMessageToOthers(String player, Message message) {
        for (String p : clientMap.keySet())
            if (!p.equals(player)) sendMessage(p, message);
    }
}