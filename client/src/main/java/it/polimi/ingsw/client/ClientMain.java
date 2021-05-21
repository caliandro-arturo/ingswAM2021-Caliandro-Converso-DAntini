package it.polimi.ingsw.client;

import it.polimi.ingsw.client.CLI.CLI;
import it.polimi.ingsw.client.GUI.GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientMain {

    public static void main(String[] args) {
        boolean isRunning = false;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Insert CLI or GUI to start the game.");
        do {
            try {
                switch (in.readLine().toUpperCase().trim()) {
                    case "CLI": {
                        CLI.main(null);
                        isRunning = true;
                        break;
                    }
                    case "GUI": {
                        GUI.main(null);
                        isRunning = true;
                        break;
                    }
                    default: {
                        System.err.println("You must select between CLI or GUI.");
                    }
                }
            } catch (IOException e) {
                System.err.println("Closing server...");
                System.exit(1);
            }
        } while (!isRunning);
        System.exit(0);
    }
}
