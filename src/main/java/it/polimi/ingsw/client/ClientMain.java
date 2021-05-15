package it.polimi.ingsw.client;

import it.polimi.ingsw.client.CLI.CLI;
import it.polimi.ingsw.client.GUI.GUI;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ClientMain {
    private static UI ui;

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            do {
                switch (in.readLine()) {
                    case "CLI": {
                        ui = new CLI();
                        break;
                    }
                    case "GUI": {
                        ui = new GUI();
                        break;
                    }
                    default: {
                        System.err.println("You must select between CLI or GUI.");
                    }
                }
            } while (ui == null);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        ui.run();
    }
}
