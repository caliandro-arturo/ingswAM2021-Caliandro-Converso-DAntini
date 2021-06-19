package it.polimi.ingsw.client.GUI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardController implements Initializable {

    /**
     * Cross
     */
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

    /**
     * Map that links resources and their quantity number in strongbox
     */
    private HashMap<Resource, Label> resourceLabelHashMap;

    /**
     * Development cards on the board
     */
    @FXML
    private ImageView devP11;
    @FXML
    private ImageView devP31;
    @FXML
    private ImageView devP21;
    @FXML
    private ImageView devP32;
    @FXML
    private ImageView devP22;
    @FXML
    private ImageView devP12;
    @FXML
    private ImageView devP33;
    @FXML
    private ImageView devP23;
    @FXML
    private ImageView devP13;

    private final ArrayList<ImageView> resSpots = new ArrayList<>();
    private ArrayList<ArrayList<ImageView>> devPlace;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resourceLabelHashMap = new HashMap<>(){{
            put(Resource.SERF, boxSerf);
            put(Resource.COIN, boxCoin);
            put(Resource.SHIELD, boxShield);
            put(Resource.STONE, boxStone);
        }};
        resSpots.addAll(Arrays.asList(res1, res21, res22, res31, res32, res33));
        devPlace = new ArrayList<>(){{
            add(new ArrayList<>(Arrays.asList(devP11, devP12, devP13)));
            add(new ArrayList<>(Arrays.asList(devP21, devP22, devP23)));
            add(new ArrayList<>(Arrays.asList(devP31, devP32, devP33)));
        }};
    }
}
