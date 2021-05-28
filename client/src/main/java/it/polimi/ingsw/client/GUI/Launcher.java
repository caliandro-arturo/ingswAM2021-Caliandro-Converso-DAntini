package it.polimi.ingsw.client.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Launcher extends SceneHandler {
    @FXML
    private TextField text;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        App.setError(getOut());
    }

    public void takeText(ActionEvent actionEvent) {
        if (text.getText().equals("")) {
            getOut().setText("You must insert an ip address first.");
            return;
        }
        configureConnection(text.getText());
    }

    public void takeDefault(ActionEvent actionEvent) {
        configureConnection("");
    }

    private void configureConnection(String hostname) {

        getGui().configureConnection(hostname);
        if (getGui().isConnected()) {
            getGui().initializeClient();
            App.setScene("login", "Set your nickname");
        }
    }
}
