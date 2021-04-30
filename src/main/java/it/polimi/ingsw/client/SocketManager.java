package it.polimi.ingsw.client;

import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.messages.toClient.ToClientMessage;
import it.polimi.ingsw.messages.toServer.Disconnect;
import it.polimi.ingsw.messages.Ping;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Manages sending and receiving of messages with the server.
 */
public class SocketManager {
    private final Socket socket;
    private ClientController controller;
    private final ObjectInputStream objIn;
    private final ObjectOutputStream objOut;
    private final Queue<Message> messageBuffer = new ConcurrentLinkedQueue<>();
    private final Thread messageReader;
    private boolean isConnected = false;
    private boolean waitingPong;

    public SocketManager(Socket socket) throws IOException {
        this.socket = socket;
        objIn = new ObjectInputStream(socket.getInputStream());
        objOut = new ObjectOutputStream(socket.getOutputStream());
        messageReader = new Thread(this::receiveMessages);
        messageReader.start();
    }

    public void setController(ClientController controller) {
        this.controller = controller;
    }

    /**
     * Reads messages from server and passes them to controller.
     */
    public void receiveMessages() {
        try {                                                       //todo fare ping e buffer messaggi
            while (true) {
                Object in = objIn.readObject();
                if (in instanceof Message) {
                    controller.readMessage((ToClientMessage) in);
                }
                else if (in instanceof Ping)
                    if (waitingPong) {
                        isConnected = true;
                        notify();
                    }
            }
        } catch (IOException e) {
            System.err.println("Line 53 sm: " + e.getMessage());
            shutdown();
        } catch (ClassNotFoundException e) {
            System.err.println("Line 56 sm: " + e.getMessage());
        }
    }

    /**
     * Sends messages to the client.
     *
     * @param message the message to send
     */
    public synchronized void sendMessage(Object message) throws IOException {
        /*isConnected = false;
        try {
            sendPing();
        } catch (IOException e) {
            System.err.println("Unable to send ping: " + e.getMessage());
            return;
        }
        try {
            while (!isConnected) wait();
        } catch (InterruptedException e) {
            System.err.println("Problem in sendMessage: " + e.getMessage());
            return;
        }*/
        objOut.reset();
        objOut.writeObject(message);
        objOut.flush();
    }

    public void sendPing() throws IOException {
        objOut.reset();
        objOut.writeObject(new Ping());
        waitingPong = true;
        objOut.flush();
    }

    /**
     * Closes the socket.
     */
    public void shutdown() {
        try {
            sendMessage(new Disconnect());
        } catch (IOException ignore) {}
        messageReader.interrupt();
        try {
            objOut.close();
            objIn.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Line 94 sm: " + e.getMessage());
        }
    }
}
