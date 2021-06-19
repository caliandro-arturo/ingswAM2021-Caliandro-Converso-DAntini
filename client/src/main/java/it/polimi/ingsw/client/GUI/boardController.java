package it.polimi.ingsw.client.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class boardController implements Initializable {

    @FXML
    private ImageView crossB;
    @FXML
    private ImageView cross;
    @FXML
    private ImageView tile2;
    @FXML
    private ImageView tile3;
    @FXML
    private ImageView tile4;
    @FXML
    private ImageView res1;
    @FXML
    private ImageView res33;
    @FXML
    private ImageView res32;
    @FXML
    private ImageView res31;
    @FXML
    private ImageView res22;
    @FXML
    private ImageView res21;
    @FXML
    private GridPane strongBox;
    @FXML
    private HBox serf;
    @FXML
    private Label boxSerf;
    @FXML
    private HBox shield;
    @FXML
    private Label boxShield;
    @FXML
    private HBox stone;
    @FXML
    private Label boxStone;
    @FXML
    private HBox coin;
    @FXML
    private Label boxCoin;
    @FXML
    private ComboBox<?> resBaseProd;
    @FXML
    private ImageView baseProd1;
    @FXML
    private ImageView baseProd2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        resBaseProd.getItems().addAll(
                imgCoin,
                imgSerf,
                imgShield,
                imgStone
        );
        leadProd1.getItems().addAll(
                imgCoin,
                imgSerf,
                imgShield,
                imgStone
        );
        leadProd2.getItems().addAll(
                imgCoin,
                imgSerf,
                imgShield,
                imgStone
        );
    }
}
