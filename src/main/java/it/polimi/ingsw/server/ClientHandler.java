package it.polimi.ingsw.server;

import it.polimi.ingsw.common_files.message.Message;
import it.polimi.ingsw.common_files.message.toClient.ErrorMessage;
import it.polimi.ingsw.common_files.message.toClient.Ok;
import it.polimi.ingsw.common_files.message.toClient.updates.PlayerLeft;
import it.polimi.ingsw.common_files.message.toServer.SetNickname;
import it.polimi.ingsw.common_files.network.SocketManager;
import it.polimi.ingsw.server.model.Player;

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

    public VirtualView getVirtualView() {
        return virtualView;
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
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        message.setPlayer(player);
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
