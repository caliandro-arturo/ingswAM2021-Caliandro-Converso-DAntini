package it.polimi.ingsw.Network;

import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.messages.Ping;
import it.polimi.ingsw.messages.toClient.ToClientMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*public abstract class SocketManager {
/*    private final Socket socket;
    private final ObjectInputStream objIn;
    private final ObjectOutputStream objOut;
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

    *//**
     * Sends messages to the client.
     *
     * @param message the message to send
     *//*
    public synchronized void sendMessage(Object message) throws IOException {
        isConnected = false;
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
        }
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
}*/
