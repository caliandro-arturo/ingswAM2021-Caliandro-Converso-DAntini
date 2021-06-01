package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.commonFiles.messages.toServer.SetNickname;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Launcher extends SceneHandler {
    @FXML
    public AnchorPane nicknamePane;

    @FXML
    public AnchorPane createGamePane;

    @FXML
    public AnchorPane waitPane;

    @FXML
    public AnchorPane connectionPane;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TextField hostname;

    @FXML
    private TextField nameTextField;

    private AnchorPane currentPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        App.setError(getOut());
        currentPane = connectionPane;
        currentPane.setDisable(false);
    }

    public void connect(ActionEvent actionEvent) {
        if (hostname.getText().equals("")) {
            getOut().setText("You must insert an ip address first.");
            return;
        }
        configureConnection(hostname.getText());
    }

    public void connectDefault(ActionEvent actionEvent) {
        configureConnection("");
    }

    private void configureConnection(String hostname) {

        getGui().configureConnection(hostname);
        if (getGui().isConnected()) {
            getGui().initializeClient();
            setCurrentPane(nicknamePane);
        }
    }

    public void setNickname(ActionEvent e) {
        if (nameTextField.getText().equals(""))
            App.out.setText("You must insert a nickname.");
        else {
            App.out.setText("Logging with nickname: " + nameTextField.getText() + "...");
            getGui().getView().setNick(new String[]{nameTextField.getText()});
        }
    }

    public void setCurrentPane(AnchorPane pane) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(100), currentPane);
        fadeOut.setFromValue(100);
        fadeOut.setToValue(0);
        FadeTransition fadeIn = new FadeTransition(Duration.millis(100), pane);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(100);
        currentPane.setDisable(true);
        SequentialTransition trans = new SequentialTransition(fadeOut, fadeIn);
        trans.play();
        pane.setDisable(false);
    }
}
