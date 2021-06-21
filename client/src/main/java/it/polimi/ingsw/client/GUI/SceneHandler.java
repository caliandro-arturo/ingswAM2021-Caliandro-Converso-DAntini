package it.polimi.ingsw.client.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class SceneHandler implements Initializable {
    private GUI gui;

    @FXML
    private Label out;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        App.setOut(out);
    }


    public GUI getGui() {
        return gui;
    }

    public Label getOut() {
        return out;
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

}
