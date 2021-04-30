package it.polimi.ingsw.server;

import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.messages.Ping;
import it.polimi.ingsw.messages.toClient.AskNickname;
import it.polimi.ingsw.messages.toClient.ErrorMessage;
import it.polimi.ingsw.messages.toClient.ToClientMessage;
import it.polimi.ingsw.messages.toClient.updates.PlayerLeft;
import it.polimi.ingsw.messages.toServer.SetNickname;
import it.polimi.ingsw.server.ServerMain;
import it.polimi.ingsw.server.model.GameException;
import it.polimi.ingsw.server.model.Player;
import it.polimi.ingsw.server.VirtualView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

/**
 * Handles the connection with the client, and represents him in the virtual view.
 */
public class ClientHandler implements Runnable {
    private final ServerMain serverMain;
    private Player player;
    private final int id;
    private final Socket socket;
    private final VirtualView virtualView;
    private ObjectInputStream objIn;
    private ObjectOutputStream objOut;
    private PrintWriter out;
    private Thread messageReader;
    private Thread ping;
    private boolean isConnected;
    private boolean waitingPong;

    public ClientHandler(ServerMain serverMain, int id, Socket socket, VirtualView virtualView) {
        this.serverMain = serverMain;
        this.id = id;
        this.socket = socket;
        this.virtualView = virtualView;
        virtualView.addToWaitingList(this);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream());
            objOut = new ObjectOutputStream(socket.getOutputStream());
            objIn = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.err.println("ObjectIOStreamCreationError " + e.getMessage());
            return;
        }
        try {
            //setting the nickname
            sendMessage(new AskNickname());
            Object message;
            do {
                message = objIn.readObject();
                System.out.println("Message received.");
                if (!(message instanceof SetNickname)) {
                    sendMessage(new ErrorMessage("You must set a nickname first."));
                    continue;
                }
                try {
                    virtualView.createPlayer(((SetNickname) message).getNickname(), this);
                } catch (GameException.NicknameAlreadyTaken e) {
                    sendMessage(new ErrorMessage(e.getMessage()));
                } catch (GameException.GameAlreadyFull e) {
                    reassign();
                    return;
                }
            } while (!virtualView.hasBeenAdded(this));

            /*At this point, the player has been added to the game (if it was not already full).
             * If it's the first player, he must choose the number of players of the game;
             * otherwise, he have to attend (this is handled in virtualView.createPlayer). */

            //main loop of the handler: waits messages from the client and sends them to the virtualView.
            while (true) {
                message = objIn.readObject();
                if (message instanceof Message) {
                    ((Message) message).setPlayer(player);
                    virtualView.readMessage((Message) message);
                }
            }
        } catch (SocketException e) {
            String name;
            if (player != null) {
                name = player.getUsername();
                virtualView.sendMessage(new PlayerLeft(name));
            } else
                name = Integer.toString(socket.getPort());
            System.err.println(name + " has left.");
            virtualView.remove(this);
            shutdown();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            virtualView.remove(this);
            shutdown();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    /*public void receiveMessages() {
        Object in;
        try {
            while (true) {
                in = objIn.readObject();
                if (in instanceof Message) {
                    ((Message) in).setPlayer(player);
                    virtualView.readMessage((Message) in);
                }
                else if (in instanceof Ping)
                    if (waitingPong) {
                        isConnected = true;
                        notify();
                    }
            }
        } catch (IOException e) {
            System.err.println("IOException in ClientHandler receiving messages: " + e.getMessage());
            shutdown();
        } catch (ClassNotFoundException e) {
            System.err.println("IOException in ClientHandler receiving messages: " + e.getMessage());
        }
    }*/

    public void reassign() {
        serverMain.addToVirtualView(socket);    //todo handle re-entry to other matches
        System.out.println();
    }

    public void shutdown() {
        try {
            objIn.close();
            objOut.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Line 105 ch: " + e.getMessage());
        }
    }

    public synchronized void sendMessage(Message message) {
        try {
            objOut.reset();
            objOut.writeObject(message);
            objOut.flush();
        } catch (IOException e) {
            System.err.println("ciao");
        }
    }

    /*public void sendPing() throws IOException {
        objOut.reset();
        objOut.writeObject(new Ping());
        objOut.flush();
    }*/
}
