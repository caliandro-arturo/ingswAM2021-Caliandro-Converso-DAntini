package it.polimi.ingsw.client.GUI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private static Stage stage;
    private static GUI gui;

    @FXML
    public static Label error;

    public void setGui(GUI gui) {
        App.gui = gui;
    }

    public static void setError(Label error) {
        App.error = error;
    }

    public void run() {
        launch("");
    }

    @Override
    public void start(Stage stage) throws IOException {
        App.stage = stage;
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
        ((SceneHandler) fxmlLoader.getController()).setGui(gui);
        stage.setTitle(sceneTitle);
        stage.setScene(scene);
        stage.show();
    }
}
