package it.polimi.ingsw.server;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ErrorMessage;
import it.polimi.ingsw.commonFiles.messages.toClient.GameRejoin;
import it.polimi.ingsw.commonFiles.messages.toClient.WaitGameStart;
import it.polimi.ingsw.commonFiles.messages.toServer.GamesList;
import it.polimi.ingsw.commonFiles.messages.toServer.JoinGame;
import it.polimi.ingsw.commonFiles.messages.toServer.SetGame;
import it.polimi.ingsw.commonFiles.messages.toServer.SetNickname;
import it.polimi.ingsw.commonFiles.network.SocketManager;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Handles connection with the client. It also manages the initial nickname setting, the game creation and the game
 * joining.
 */
public class ClientHandler extends SocketManager implements Runnable {
    private final ServerMain serverMain;
    private String player;
    private final Socket socket;
    private VirtualView virtualView;

    public ClientHandler(ServerMain serverMain, Socket socket) throws IOException {
        super(socket);
        this.serverMain = serverMain;
        this.socket = socket;
    }

    public String getPlayer() {
        return player;
    }

    /**
     * Starts the message receiving system. If the connection is lost, flags the player as offline and closes the
     * socket.
     */
    public void run() {
        try {
            receiveMessages();
        } catch (SocketTimeoutException e) {
            System.err.println("Connection with " + socket.getPort() + " has been lost.");
        } catch (IOException e) {
            String name;
            if (player != null) {
                name = player;
            } else
                name = Integer.toString(socket.getPort());
            System.err.println(name + " left.");
        }
        setConnected(false);
        if (virtualView != null)
            virtualView.remove(this);
        serverMain.getConnectedClients().replace(player, false);
        shutdown();
    }

    /**
     * Reads player's messages. If the virtual view is set, the message is directly delegated to it, else it is managed
     * here.
     *
     * @param message the message to read
     */
    @Override
    public void readMessage(Message message) {
        if (virtualView == null) {
            if (message instanceof SetNickname) {
                setNickname((SetNickname) message);
            } else if (player == null) {
                try {
                    sendMessage(new ErrorMessage( "You must create a player first."));
                } catch (IOException ignore) {
                }
            } else if (message instanceof GamesList) {
                sendGamesList((GamesList) message);
            } else if (message instanceof JoinGame) {
                addPlayerToLobby((JoinGame) message);
            } else if (message instanceof SetGame) {
                createGame((SetGame) message);
            }
        }
        else virtualView.readMessage(message);
    }

    /**
     * Sets the player's nickname. If the player was previously added in a game, then he is re-added to that game.
     *
     * @param setNickname the nickname to set
     */
    private void setNickname(SetNickname setNickname) {
        String nickname = setNickname.getNickname();
        ConcurrentHashMap<String, Boolean> connectedClients = serverMain.getConnectedClients();
        synchronized (connectedClients) {
            //searching if there is another player with this nickname
            Optional<Boolean> playerIsConnected = Optional.ofNullable(serverMain.getConnectedClients().get(nickname));
            //if the nickname is present checks if the player with that nickname is currently connected
            playerIsConnected.ifPresentOrElse(i -> {
                        //if the nickname owner is currently connected, the player cannot use this nickname
                        if (i) {
                            try {
                                sendMessage(new ErrorMessage( "This nickname is already used."));
                            } catch (IOException ignore) {
                            }
                        //else we assume that this new player is the previous nickname owner, and then it's checked if
                        //the player has been linked with a lobby
                        } else {
                            Optional<VirtualView> virtualView = Optional.ofNullable(serverMain.getClientToLobbyMap().get(nickname));
                            virtualView.ifPresentOrElse(
                                    //if there is the lobby, the client is linked to that nickname
                                    v -> {
                                        player = nickname;
                                        v.reAddPlayer(nickname, this);
                                        this.virtualView = v;
                                        serverMain.getConnectedClients().replace(nickname, true);
                                        //notifying the other players in the lobby
                                        v.sendMessage(new GameRejoin(nickname));
                                    },
                                    //else the player is simply re-added into the server
                                    () -> {
                                        player = nickname;
                                        serverMain.getConnectedClients().put(nickname, true);
                                        try {
                                            sendMessage(setNickname);
                                        } catch (IOException e) {
                                            serverMain.getConnectedClients().remove(nickname);
                                        }
                                    });
                        }
                    },
                    //else a new player is added to the server
                    () -> {
                        player = nickname;
                        serverMain.getConnectedClients().put(nickname, true);
                        try {
                            sendMessage(setNickname);
                            //if the sendMessage fails, the user is removed immediately
                        } catch (IOException e) {
                            serverMain.getConnectedClients().remove(nickname);
                        }
                    }
            );
        }
    }

    /**
     * Sends the free games list to the player.
     *
     * @param gamesList the received message to fill up with available games
     */
    private void sendGamesList(GamesList gamesList) {
        List<VirtualView> views = serverMain.getFreeVirtualViews();
        synchronized (views) {
            views.forEach(v -> gamesList.addLobby(v.getName(), v.connectedClients(), v.maxPlayersNumber()));
            try {
                sendMessage(gamesList);
            } catch (IOException ignore) {
            }
        }
    }

    /**
     * Adds the player to the selected game.
     *
     * @param joinGame the game that the player wants to join
     */
    private void addPlayerToLobby(JoinGame joinGame) {
        String lobbyName = joinGame.getGameName();
        Optional<VirtualView> lobby = serverMain.getVirtualView(lobbyName);
        lobby.ifPresentOrElse(
                l -> {
                    if (l.isFull())
                        try {
                            sendMessage(new ErrorMessage("This game is full."));
                        } catch (IOException ignore) {
                        }
                    else  {
                        l.addPlayer(player, this);
                        virtualView = l;
                        try {
                            sendMessage(joinGame);
                        } catch (IOException e) {
                            virtualView = null;
                            l.remove(this);
                            return;
                        }
                        if (l.isFull())
                            l.getController().startGame();
                        else try {
                            sendMessage(new WaitGameStart());
                        } catch (IOException ignore) {
                        }
                    }
                },
                () -> {
                    try {
                        sendMessage(new ErrorMessage("There is no game called " + lobbyName + "."));
                    } catch (IOException ignore) {
                    }
                }
        );
    }

    /**
     * Creates a game with the specified number of players. The name of the game is the nickname of the player who
     * created the game.
     *
     * @param setGame the message with the number of players selected for the game creation
     */
    private void createGame(SetGame setGame) {
        VirtualView view = new VirtualView(serverMain, player);
        try {
            view.getController().createGame(setGame);
        } catch (IllegalArgumentException e) {
            try {
                sendMessage(new ErrorMessage(e.getMessage()));
            } catch (IOException ignore) {
            }
            return;
        }
        view.addPlayer(player, this);
        virtualView = view;
        serverMain.addVirtualView(view);
        try {
            sendMessage(setGame);
        } catch (IOException e) {
            view.remove(this);
            virtualView = null;
            return;
        }
        if (view.isFull())
            view.getController().startGame();
    }
}
