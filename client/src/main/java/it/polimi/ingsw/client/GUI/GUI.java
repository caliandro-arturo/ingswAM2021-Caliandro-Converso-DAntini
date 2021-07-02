package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.UI;

/**
 * Acts as intermediary between the main and the application launcher.
 */
public class GUI extends UI {
    public void run() {
        App.setGui(this);
        App.run();
    }

    public static void main(String[] args) {
        new GUI().run();
    }
}
