package it.polimi.ingsw.server;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ErrorMessage;
import it.polimi.ingsw.commonFiles.messages.toClient.Ok;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.PlayerLeft;
import it.polimi.ingsw.commonFiles.messages.toServer.SetNickname;
import it.polimi.ingsw.commonFiles.network.SocketManager;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Handles the connection with the client, and represents him in the virtual view.
 */
public class ClientHandler extends SocketManager implements Runnable {
    private final ServerMain serverMain;
    private String player;
    private final Socket socket;
    private final VirtualView virtualView;

    public ClientHandler(ServerMain serverMain, Socket socket, VirtualView virtualView) throws IOException {
        super(socket);
        this.serverMain = serverMain;
        this.socket = socket;
        this.virtualView = virtualView;
        virtualView.addToWaitingList(this);
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayerName(String nickname) {
        this.player = nickname;
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
                virtualView.sendMessage(new PlayerLeft(name));
            } else
                name = Integer.toString(socket.getPort());
            System.err.println(name + " left.");
        }
        setConnected(false);
        virtualView.remove(this);
        shutdown();
    }

    public Socket getSocket() {
        return socket;
    }

    public void reassign() {
        serverMain.reinsertToTheFirstFreeVirtualView(this);
    }

    @Override
    public void readMessage(Message message) {
        if (message instanceof SetNickname)
            ((SetNickname) message).setClient(this);
        else if (player == null) {
            try {
                sendMessage(new ErrorMessage(message, "You must create a player first."));
            } catch (IOException ignore) {
            }
            return;
        }
        virtualView.readMessage(message);
    }

    public void confirmMove(Message move) {
        try {
            sendMessage(new Ok(move));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
