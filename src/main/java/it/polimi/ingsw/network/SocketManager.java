package it.polimi.ingsw.network;

import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.messages.Ping;
import it.polimi.ingsw.server.ServerMain;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public abstract class SocketManager {
    private final Socket socket;
    private final ObjectInputStream objIn;
    private final ObjectOutputStream objOut;
    private Thread pinger;
    private static final Ping ping = new Ping();

    private boolean isConnected = true;

    public SocketManager(Socket socket) throws IOException {
        this.socket = socket;
        objOut = new ObjectOutputStream(socket.getOutputStream());
        objIn = new ObjectInputStream(socket.getInputStream());
    }

    public void receiveMessages() throws SocketTimeoutException, SocketException {
        try {
            pinger = new Thread(this::sendPing);
            pinger.start();
            Object in;
            do {
                in = objIn.readObject();
                if (in instanceof Message)
                    readMessage((Message) in);
                else if (in instanceof Ping) {
                    isConnected = true;
                    //System.out.println("ping received.");
                }
            } while (true);
        } catch (SocketTimeoutException | SocketException e) {
            isConnected = false;
            throw e;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        shutdown();
    }

    public abstract void readMessage(Message message);

    /**
     * Sends messages to the client.
     *
     * @param message the message to send
     */
    public synchronized void sendMessage(Message message) throws IOException {
        if (!isConnected) {
            System.err.println("sendMessage error :(");
            return;
        }
        try {
            //objOut.reset();
            objOut.writeObject(message);
            objOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void sendPing() {
        while (true) {
            try {
                Thread.sleep(ServerMain.timeout / 2);
                objOut.reset();
                objOut.writeObject(ping);
                objOut.flush();
            } catch (IOException | InterruptedException ignore) {
                isConnected = false;
            }
        }
    }

    public void shutdown() {
        isConnected = false;
        try {
            objIn.close();
            objOut.close();
            socket.close();
        } catch (IOException ignore) {
        }
    }
}
