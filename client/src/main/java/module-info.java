module it.polimi.ingsw.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires it.polimi.ingsw.common;
    requires com.google.gson;
    opens it.polimi.ingsw.client.GUI to javafx.fxml, javafx.graphics, javafx.base, javafx.controls;
    opens it.polimi.ingsw.client.model to javafx.fxml, javafx.graphics, javafx.base, javafx.controls;
    opens it.polimi.ingsw.client to javafx.fxml, javafx.graphics, javafx.base, javafx.controls;
}