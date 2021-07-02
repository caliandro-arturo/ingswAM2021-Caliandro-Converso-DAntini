package it.polimi.ingsw.client.GUI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Starts the GUI interface. It also contains static fields that are granted to be reachable everywhere in the code.
 */
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

    /**
     * Launches the application.
     */
    public static void run() {
        gui.setView(new GUIView());
        launch();
    }

    /**
     * Loads the stage and starts the launcher.
     *
     * @param stage the stage created by JavaFX
     */
    @Override
    public void start(Stage stage) {
        App.stage = stage;
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/inkwell.png"))));
        stage.setOnCloseRequest(e -> System.exit(0));
        setScene("launcher", "Masters of Renaissance");
    }

    /**
     * Loads and sets a scene.
     *
     * @param fxmlFile   the name of the fxml file to load as a scene
     * @param sceneTitle the title to put on the stage
     */
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
        if (stage == null) {
            stage = new Stage();
        } else if (stage.getScene() != null) {
            stage.hide();
        }
        stage.setTitle(sceneTitle);
        stage.setScene(scene);
        stage.show();
    }
}
