package it.polimi.ingsw.server;

import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.messages.toClient.CreateGame;
import it.polimi.ingsw.messages.toClient.GameIsFull;
import it.polimi.ingsw.messages.toClient.WaitGameCreation;
import it.polimi.ingsw.messages.toClient.updates.NewPlayer;
import it.polimi.ingsw.messages.toServer.SetNickname;
import it.polimi.ingsw.server.model.GameException;
import it.polimi.ingsw.server.model.Player;

import java.io.IOException;
import java.net.Socket;
import java.util.*;

/**
 * View element of the MVC architectural pattern. It also acts as a waiting room and manages the exchanging of
 * messages.
 */
public class VirtualView {
    private final ServerMain serverMain;
    private final Controller controller = new Controller(this);
    private Socket firstPlayer;
    private final List<ClientHandler> waitingList = new ArrayList<>();
    private final HashMap<Player, ClientHandler> clientMap = new LinkedHashMap<>();
    private boolean hasBeenSet = false;

    public VirtualView(ServerMain serverMain, Socket firstPlayer) {
        this.serverMain = serverMain;
        this.firstPlayer = firstPlayer;
    }

    public HashMap<Player, ClientHandler> getClientMap() {
        return clientMap;
    }

    /**
     * Checks if the game has been set.
     *
     * @return {@code true} if the game has been set; {@code false} otherwise
     */
    public boolean hasBeenSet() {
        return hasBeenSet;
    }

    /**
     * Checks if the client has already been added to {@link VirtualView#clientMap}.
     *
     * @param clientHandler the client to look for in {@link VirtualView#clientMap}
     * @return {@code true} if the client is in {@link VirtualView#clientMap}; {@code false} otherwise
     */
    public boolean hasBeenAdded(ClientHandler clientHandler) {
        return clientMap.containsValue(clientHandler);
    }

    /**
     * Checks if the nickname is still available.
     *
     * @param nickname the nickname to check
     * @return {@code true} if the nickname is still available; {@code false} otherwise
     */
    public boolean isNickAvailable(String nickname) {
        return clientMap.keySet().stream().noneMatch(p -> nickname.equals(p.getUsername()));
    }

    /**
     * Checks if the game is full.
     *
     * @return {@code true} if the game is full; {@code false} otherwise
     */
    public boolean isFull() {
        if (hasBeenSet) return clientMap.keySet().size() + waitingList.size() >= controller.gamePlayersNum();
        else return clientMap.keySet().size() + waitingList.size() == 4;
    }

    /**
     * Checks if the player is the first player in the virtual view.
     *
     * @param player the player to check
     * @return {@code true} if the player is the first player; {@code false} otherwise
     */
    public boolean isTheFirstPlayer(Player player) {
        return clientMap.get(player).getSocket().equals(firstPlayer);
    }

    /**
     * Returns the number of clients connected to this virtual view.
     *
     * @return the number of connected clients
     */
    public int connectedClients() {
        return clientMap.keySet().size() + waitingList.size();
    }

    public void setHasBeenSet(boolean hasBeenSet) {
        this.hasBeenSet = hasBeenSet;
    }

    /**
     * Adds the client to {@link VirtualView#waitingList}.
     *
     * @param client the client to add in {@link VirtualView#waitingList}
     */
    public synchronized void addToWaitingList(ClientHandler client) {
        waitingList.add(client);
        notify();
    }

    /**
     * Creates the player for the client and removes it from {@link VirtualView#waitingList}.
     *
     * @param setNickname the message that the client sent to set the nickname
     * @throws GameException.NicknameAlreadyTaken if the nickname has been already taken by another player
     */
    public void createPlayer(SetNickname setNickname) throws GameException.NicknameAlreadyTaken {
        String nickname = setNickname.getNickname();
        ClientHandler clientHandler = setNickname.getClient();
        if (!isNickAvailable(nickname))
            throw new GameException.NicknameAlreadyTaken();
        Player player = new Player(nickname);
        waitingList.remove(clientHandler);
        clientMap.put(player, clientHandler);
        clientHandler.setPlayer(player);
        clientHandler.confirmMove(setNickname);
        sendMessageToOthers(player, new NewPlayer(player.getUsername()));
        if (!hasBeenSet) {
            if (clientHandler.getSocket().equals(firstPlayer))
                sendMessage(player, new CreateGame());
            else sendMessage(player, new WaitGameCreation());
        } else {
            if (isFull())
                controller.startGame();
        }
    }

    /**
     * Removes a client from this virtual view (from {@link VirtualView#waitingList} or from {@link
     * VirtualView#clientMap}. If the client to remove is the firstPlayer, replaces firstPlayer value with the socket of
     * the first next client that has entered the virtualView.
     *
     * @param client the client to remove
     */
    public synchronized void remove(ClientHandler client) {
        if (!waitingList.remove(client)) clientMap.remove(client.getPlayer());
        if (client.getSocket().equals(firstPlayer)) {
            Optional<Map.Entry<Player, ClientHandler>> nextFirstPlayer = clientMap.entrySet().stream().findFirst();
            if (nextFirstPlayer.isPresent()) {
                firstPlayer = nextFirstPlayer.get().getValue().getSocket();
                sendMessage(nextFirstPlayer.get().getValue(), new CreateGame());
            } else {
                try {
                    while (waitingList.isEmpty()) wait();
                } catch (InterruptedException e) {
                    System.err.println("In remove() of virtualview: " + e.getMessage());
                    return;
                }
                firstPlayer = waitingList.get(0).getSocket();
            }
        }
    }

    /**
     * Removes the clients present in {@link VirtualView#clientMap} or in {@link VirtualView#waitingList} if there is no
     * place for them in the game.
     *
     * @param expectedNumberOfClients the number of players allowed in this virtual view
     */
    public synchronized void removeExtraClients(int expectedNumberOfClients) {
        waitingList.forEach(c -> {
            waitingList.remove(c);
            c.reassign();
        });
        ArrayList<Player> players = new ArrayList<>(clientMap.keySet());
        while (connectedClients() > expectedNumberOfClients) {
            Player playerToRemove = players.get(expectedNumberOfClients);
            sendMessage(playerToRemove, new GameIsFull());
            remove(clientMap.get(playerToRemove));
            clientMap.get(playerToRemove).reassign();
            players.remove(expectedNumberOfClients);
        }
    }

    /**
     * Calls {@link Controller#readMessage(Message)}.
     *
     * @param message the message to read
     */
    public synchronized void readMessage(Message message) {
        controller.readMessage(message);
    }

    /**
     * Calls {@link ClientHandler#sendMessage(Message)} on the client associated to the player.
     *
     * @param player  the player to send the message to
     * @param message the message to send
     */
    public void sendMessage(Player player, Message message) {
        sendMessage(clientMap.get(player), message);
    }

    public void sendMessage(ClientHandler client, Message message) {
        try {
            client.sendMessage(message);
        } catch (IOException e) {
            //fixme handle this
        }
    }

    /**
     * Sends a message to all the players in {@link VirtualView#clientMap}.
     *
     * @param message the message to send
     */
    public void sendMessage(Message message) {
        for (Player player : clientMap.keySet())
            sendMessage(player, message);
    }

    /**
     * Sends a message to all the players in {@link VirtualView#clientMap}, except for the player passed as parameter.
     *
     * @param player  the player not to send the message to
     * @param message the message to send to other players
     */
    public void sendMessageToOthers(Player player, Message message) {
        for (Player p : clientMap.keySet())
            if (!p.equals(player)) sendMessage(p, message);
    }

}