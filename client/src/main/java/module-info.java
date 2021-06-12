module it.polimi.ingsw.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires it.polimi.ingsw.common;
    opens it.polimi.ingsw.client.GUI to javafx.fxml, javafx.graphics, javafx.base, javafx.controls;
}