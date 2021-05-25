package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.UI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class GUI extends UI {
    private static Scene scene;
    public void run() {
        setView(new GUIView());
    }


    public static void main(String[] args) {
        new GUI().run();
    }


}
