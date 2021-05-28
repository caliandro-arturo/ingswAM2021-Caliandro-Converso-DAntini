package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.commonFiles.messages.toServer.SetNickname;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Login extends SceneHandler {
    @FXML
    private TextField nameTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        App.setError(getOut());
    }

    public void setNickname(ActionEvent e) {
        if (nameTextField.getText().equals(""))
            getOut().setText("You must insert a nickname.");
        else {
            getGui().getView().getController().sendMessage(new SetNickname(nameTextField.getText()));
        }
    }
}
