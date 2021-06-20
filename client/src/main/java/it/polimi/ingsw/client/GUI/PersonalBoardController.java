package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.commonFiles.model.Resource;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
    @FXML
    private ImageView strongCoin;
    @FXML
    private ImageView strongStone;
    @FXML
    private ImageView strongShield;
    @FXML
    private ImageView strongSerf;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
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
        leadProd1.setOpacity(0);
        leadProd1.setDisable(true);
        leadProd2.setOpacity(0);
        leadProd2.setDisable(true);
    }

    public void moveRes() {
        for(ImageView warSpot: getResSpots()){
            warSpot.setOnDragOver(dragEvent -> {
                dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                dragEvent.consume();
            });
        }

        for(ImageView warSpot: getResSpots()){
            warSpot.setOnDragDropped(dragEvent -> {
                //TODO: needs to add the legitimacy of the moves in the spots of the warehouse store
                warSpot.setImage(dragEvent.getDragboard().getImage());
                dragEvent.consume();
            });
        }

        for(ImageView res : getStrongResources()){
            res.setOnDragDetected(e->{
                Dragboard db = res.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putImage(res.getImage());
                content.putString("0");
                db.setContent(content);
                e.consume();
            } );
        }
    }

    /**
     * is called by the production to add resources in the strongbox
     * @param num: the number of resources to add
     * @param resource: the type of resources to add
     */
    public void addBoxResource(int num, Resource resource){
        int quantity = Integer.parseInt(getResourceLabelHashMap().get(resource).getText());
        quantity+=num;
        getResourceLabelHashMap().get(resource).setText((Integer.toString(quantity)));
    }

}
