package it.polimi.ingsw.client.GUI;

import javafx.event.ActionEvent;

public class ConnectionLost extends SceneHandler {
    public void restartLauncher(ActionEvent actionEvent) {
        App.setScene("launcher", "Masters of Renaissance");
    }
}
