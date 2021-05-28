package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.commonFiles.messages.toServer.SetNickname;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Login extends SceneHandler {
    @FXML
    private TextField nameTextField;

    public void setNickname(ActionEvent e) {
        if (nameTextField.getText().equals(""))
            getError().setText("You must insert a nickname.");
        else {
            getGui().getView().getController().sendMessage(new SetNickname(nameTextField.getText()));
        }
    }
}
