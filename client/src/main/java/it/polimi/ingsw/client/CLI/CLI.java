package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.client.UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * CLI version of the client.
 */
public class CLI extends UI {
    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

    /**
     *
     */
    public void run() {
        setView(new CLIView());
        getView().refresh("");
        //todo insert startup prints here
        System.out.println("Insert server hostname:port (or just press Enter to set default values):");
        try {
            configureConnection(stdIn.readLine());
        } catch (IOException e) {
            showError(e.getMessage());
            System.exit(1);
        }
        initializeClient();
        //reading user input
        getView().show("asknickname");
        inputReader();
    }

    private void inputReader() {
        do {
            try {
                getView().process(stdIn.readLine());
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        } while (true);
    }

    public static void main(String[] args) {
        new CLI().run();
    }
}
