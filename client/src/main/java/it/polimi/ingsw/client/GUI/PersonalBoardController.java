package it.polimi.ingsw.client.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonalBoardController extends BoardController {

    @FXML
    private ImageView crossB;

    @FXML
    private ComboBox<Image> resBaseProd;
    @FXML
    private ComboBox<Image> leadProd1;
    @FXML
    private ComboBox<Image> leadProd2;

    @FXML
    private ImageView baseProd1;
    @FXML
    private ImageView baseProd2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        resBaseProd.getItems().addAll(
                GamePanel.imgCoin,
                GamePanel.imgSerf,
                GamePanel.imgShield,
                GamePanel.imgStone
        );
        leadProd1.getItems().addAll(
                GamePanel.imgCoin,
                GamePanel.imgSerf,
                GamePanel.imgShield,
                GamePanel.imgStone
        );
        leadProd2.getItems().addAll(
                GamePanel.imgCoin,
                GamePanel.imgSerf,
                GamePanel.imgShield,
                GamePanel.imgStone
        );
    }

    public void moveRes(DragEvent dragEvent) {
    }
}
