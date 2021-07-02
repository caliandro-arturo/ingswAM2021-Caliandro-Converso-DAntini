package it.polimi.ingsw.client.GUI;

import javafx.event.ActionEvent;

/**
 * Scene called when the connection has been lost. It brings directly to the {@link Launcher}.
 */
public class ConnectionLost extends SceneHandler {
    /**
     * Starts the launcher.
     */
    public void restartLauncher(ActionEvent actionEvent) {
        App.setScene("launcher", "Masters of Renaissance");
    }
}
