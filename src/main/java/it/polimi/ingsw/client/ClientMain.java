package it.polimi.ingsw.client;

import it.polimi.ingsw.client.CLI.CLI;

import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        switch (in.nextLine()) {
            case "CLI":
                CLI.main(null);
        }
    }
}
