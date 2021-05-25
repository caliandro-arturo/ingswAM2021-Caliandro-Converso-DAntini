package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.UI;
import javafx.scene.Scene;

public class GUI extends UI {
    private static Scene scene;
    public void run() {
        setView(new GUIView());
    }


    public static void main(String[] args) {
        new GUI().run();
    }


}
