module it.polimi.ingsw.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires common;

    opens it.polimi.ingsw.client.GUI to javafx.fxml;
    exports it.polimi.ingsw.client.GUI;
    exports it.polimi.ingsw.client;
}