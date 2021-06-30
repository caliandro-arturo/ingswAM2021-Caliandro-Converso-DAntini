package it.polimi.ingsw.client;

import it.polimi.ingsw.client.CLI.CLI;
import it.polimi.ingsw.client.GUI.GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientMain {
    private UI ui;

    public void run(String[] args) {
        boolean isRunning = false;
        String choice;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        if (args.length > 0) choice = args[0].toUpperCase().trim();
        else {
            System.out.println("Insert CLI or GUI to start the game.");
            try {
                choice = in.readLine().toUpperCase().trim();
            } catch (IOException ignore) {
                return;
            }
        }
        do {
            try {
                switch (choice) {
                    case "CLI" -> {
                        ui = new CLI();
                        isRunning = true;
                    }
                    case "GUI" -> {
                        ui = new GUI();
                        isRunning = true;
                    }
                    default -> {
                        System.err.println("You must select between CLI or GUI.");
                        choice = in.readLine().toUpperCase().trim();
                    }
                }
            } catch (IOException e) {
                System.err.println("Closing client...");
                System.exit(1);
            }
        } while (!isRunning);
        try {
            ui.run();
        } catch (Exception ignore) {
        }
        System.err.println("Closing client...");
        System.exit(0);
    }

    public static void main(String[] args) {
        new ClientMain().run(args);
    }
}
