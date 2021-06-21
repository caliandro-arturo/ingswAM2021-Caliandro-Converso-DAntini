package it.polimi.ingsw.client.GUI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    public static Stage stage;
    private static GUI gui;
    public static SceneHandler controller;

    @FXML
    public static Label out;

    @FXML
    public static Label error;

    public static void setGui(GUI gui) {
        App.gui = gui;
    }

    public static void setOut(Label out) {
        App.out = out;
    }

    public static GUI getGui() {
        return gui;
    }

    public static void setError(Label error) {
        App.error = error;
    }

    public static void run() {
        gui.setView(new GUIView());
        launch();
    }

    @Override
    public void start(Stage stage) {
        App.stage = stage;
        stage.setOnCloseRequest(e -> System.exit(0));
        setScene("launcher", "Masters of Renaissance");
    }

    public static void setScene(String fxmlFile, String sceneTitle) {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/" + fxmlFile + ".fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }
        controller = fxmlLoader.getController();
        controller.setGui(gui);
        if(stage==null) {
            stage = new Stage();
        }else if(stage.getScene()!= null){
            stage.hide();
        }
        stage.setTitle(sceneTitle);
        stage.setScene(scene);
        stage.show();
    }
}
