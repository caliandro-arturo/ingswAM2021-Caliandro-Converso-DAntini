package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.UI;

public class GUI extends UI {

    private final App app = new App();

    public void run() {
        setView(new GUIView());
        app.setGui(this);
        app.run();
    }

    public static void main(String[] args) {
        new GUI().run();
    }
}
