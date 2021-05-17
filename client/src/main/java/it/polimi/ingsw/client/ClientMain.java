package it.polimi.ingsw.client;

import it.polimi.ingsw.client.CLI.CLI;
import it.polimi.ingsw.client.GUI.GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientMain {

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Insert CLI or GUI to start the game.");
        try {
            switch (in.readLine()) {
                case "CLI": {
                    CLI.main(null);
                    break;
                }
                case "GUI": {
                    GUI.main(null);
                    break;
                }
                default: {
                    System.err.println("You must select between CLI or GUI.");
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        System.exit(0);
    }
}
