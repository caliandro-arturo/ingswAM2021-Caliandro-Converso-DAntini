package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.model.Board;
import it.polimi.ingsw.client.model.Utility;
import it.polimi.ingsw.commonFiles.model.Resource;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.*;

public class BoardController implements Initializable {

    private Board board;
    /**
     * Cross
     */
    @FXML
    private ImageView cross;

    /**
     * Tiles of the faith track
     */
    @FXML
    private ImageView tile2;
    @FXML
    private ImageView tile3;
    @FXML
    private ImageView tile4;

    /**
     * Warehouse depots slots
     */
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

    /**
     * Displayed number of resources in strongbox
     */
    @FXML
    private Label boxSerf;
    @FXML
    private Label boxShield;
    @FXML
    private Label boxStone;
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
    @FXML
    private ImageView strongCoin;
    @FXML
    private ImageView strongStone;
    @FXML
    private ImageView strongShield;
    @FXML
    private ImageView strongSerf;

    /**
     * Leader cards pane
     */
    @FXML
    private Pane leftPane;

    private ArrayList<ImageView> strongResources;



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
        strongResources = new ArrayList<>(Arrays.asList(strongCoin, strongSerf, strongShield, strongStone));
        resSpots.addAll(Arrays.asList(res1, res21, res22, res31, res32, res33));
        devPlace = new ArrayList<>(){{
            add(new ArrayList<>(Arrays.asList(devP11, devP12, devP13)));
            add(new ArrayList<>(Arrays.asList(devP21, devP22, devP23)));
            add(new ArrayList<>(Arrays.asList(devP31, devP32, devP33)));
        }};

    }
    public ArrayList<ImageView> getResSpots() {
        return resSpots;
    }
    public ArrayList<ImageView> getStrongResources() {
        return strongResources;
    }

    public HashMap<Resource, Label> getResourceLabelHashMap() {
        return resourceLabelHashMap;
    }

    public void setBoard(Board board) {
        this.board = board;
        for (int i = 1; i < board.getFaithTrack().getPosition(); i++)
            increasePos();
        board.getFaithTrack().positionProperty().addListener(e -> increasePos());
    }

    public Pane getLeftPane() {
        return leftPane;
    }

    /**
     * prints the relative image for a development card
     * @param cardId
     * @param lev
     * @param pos
     */
    public void setDevCard(int pos, int lev, int cardId){
        pos--;
        lev--;
        devPlace.get(pos).get(lev).setImage(Utility.getCardPng(cardId));
    }

    /**
     * increase the position of the cross in the GUI
     * @param oldPosition the initial position to increment
     */
    @FXML
    void increasePos(ImageView cross, int oldPosition) {
        checkTile(oldPosition);
        if (oldPosition<=2)
            moveRight(cross);
        else if(oldPosition<5)
            moveUp(cross);
        else if(oldPosition<10)
            moveRight(cross);
        else if(oldPosition<12)
            moveDown(cross);
        else if(oldPosition<17)
            moveRight(cross);
        else if(oldPosition<19)
            moveUp(cross);
        else if(oldPosition<25)
            moveRight(cross);
    }

    public void increasePos() {
        increasePos(cross, board.getFaithTrack().getPosition());
    }

    /**
     * utility methods to move the cross in the faith track
     *
     * @param cross the cross to move
     */
    @FXML
    private void moveUp(ImageView cross) {
        cross.setLayoutY(cross.getLayoutY() - 46);
    }

    @FXML
    private void moveRight(ImageView cross) {
        cross.setLayoutX(cross.getLayoutX() + 46);
    }

    @FXML
    private void moveDown(ImageView cross) {
        cross.setLayoutY(cross.getLayoutY() + 46);
    }

    /**
     * check the position and set the relative tile visiblle
     * @param pos
     */
    private void checkTile(int pos){
        if(pos == 9)
            tile2.setOpacity(100);
        else if (pos == 17)
            tile3.setOpacity(100);
        else if (pos== 24)
            tile4.setOpacity(100);
    }


}
