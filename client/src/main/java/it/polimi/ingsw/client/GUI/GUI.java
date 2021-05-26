package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.UI;


public class GUI extends UI {
    public void run() {
        setView(new GUIView());
        App.main(new String[]{});
    }

    public static void main(String[] args) {
        new GUI().run();
    }
}
