package it.polimi.ingsw.client.CLI;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.client.*;

import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Objects;

/**
 * CLI version of the client.
 */
public class CLI implements UI {
    private View view;
    private SocketManager socketManager;

    public static void main(String[] args) {
        //todo insert startup prints here
        new CLI().run();
    }

    /**
     * Starts the connection to the server and reads user input.
     */
    public void run() {
        String hostName = "";
        int portNumber = 0;

        //server configuration
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Insert server IP (or just press Enter to set default values):");
        try {
            hostName = stdIn.readLine();
            if (hostName.equals("")) {
                Gson gson = new Gson();
                //read default ip and port from clientConfig.json
                try {
                    InputStream file = getClass().getClassLoader().getResourceAsStream("clientConfig.json");
                    InputStreamReader reader = new InputStreamReader(Objects.requireNonNull(file));
                    Type mapOfStrings = new TypeToken<Map<String, String>>() {}.getType();
                    Map<String, String> socketId = gson.fromJson(reader, mapOfStrings);
                    hostName = socketId.get("serverName");
                    portNumber = Integer.parseInt(socketId.get("serverPort"));
                    reader.close();
                } catch (NullPointerException e) {
                    System.err.println("Line 51: " + e.getMessage());
                    return;
                }
            } else {
                System.out.println("Insert server port:");
                portNumber = Integer.parseInt(stdIn.readLine());
            }
        } catch (IOException e) {
            System.err.println("Line 59: " + e.getMessage());
            System.exit(1);
        }
        System.out.println("Connecting to " + hostName + " at port " + portNumber + "...");
        try {
            socketManager = new SocketManager(new Socket(hostName, portNumber));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
        System.out.println("connected succesfully.");
        view = new CLIView();
        ClientController controller = new ClientController(view, socketManager);

        //reading user input
        String input;
        do {
            try {
                input = stdIn.readLine();
                view.process(input);
            } catch (IOException e) {
                System.err.println("Line 83: " + e.getMessage());
                System.exit(1);
            }
        } while (true);
    }
}
