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
 * Handles the connection with the client, and represents him in the virtual view.
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

    public void setPlayerName(String nickname) {
        this.player = nickname;
    }

    public void setVirtualView(VirtualView virtualView) {
        this.virtualView = virtualView;
    }

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

    public Socket getSocket() {
        return socket;
    }

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
                        //if the nickname owner is currently connected, the player cannot use this nickname...
                        if (i) {
                            try {
                                sendMessage(new ErrorMessage( "This nickname is already used."));
                            } catch (IOException ignore) {
                            }
                        //...else we assume that the previous nickname owner and the new player are the same person,
                        //and then is checked if this player is linked with a lobby
                        } else {
                            Optional<VirtualView> virtualView = Optional.ofNullable(serverMain.getClientToLobbyMap().get(nickname));
                            virtualView.ifPresentOrElse(
                                    //if there is the lobby, the client is linked to that nickname
                                    v -> {
                                        v.reAddPlayer(nickname, this);
                                        this.virtualView = v;
                                        serverMain.getConnectedClients().replace(nickname, true);
                                        //notifying the other players in the lobby
                                        v.sendMessageToOthers(nickname, new GameRejoin(nickname));
                                        /*v.sendCurrentGameState(nickname);*/
                                    },
                                    //else the player is simply readded to the server
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
                        } catch (IOException ignore) {
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
        } catch (IOException ignore) {
        }
        if (view.isFull())
            view.getController().startGame();
    }
}
