package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.commonFiles.model.Resource;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;

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
    @FXML
    private ImageView resToGive;
    @FXML
    private ImageView resToGive1;

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
        resToGive.setDisable(true);
        resToGive1.setDisable(true);
    }

    /**
     * move resource from hand to warehouse store
     */
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

    /**
     * fill the hands with the relative resource images in the pagination
     * @param handList
     */
    public void fillHand(ObservableList<ImageView> handList){
        for (ImageView view : handList) {
            view.setFitHeight(70);
            view.setFitWidth(70);
        }
        if(!handList.isEmpty()){
            getHand().setPageFactory(handList::get);
        }else
            getHand().setPageFactory(null);
        getHand().setPageCount(handList.size());
    }

    @Override
    public void moveRes(MouseEvent mouseEvent) {
        super.moveRes(mouseEvent);

        //drag 6 drop for warehouse store
        getHand().setOnDragDetected(event1 -> {
            Dragboard db = getHand().startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            Image res = getHandListImg().get(getHand().getCurrentPageIndex()).getImage();
            content.putImage(res);
            db.setContent(content);
            getHandListImg().remove(getHand().getCurrentPageIndex());
            event1.consume();
        });


        //drag & drop for leader card production
        resToGive.setOnDragOver(dragEvent -> {
            dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            dragEvent.consume();
        });
        resToGive.setOnDragDropped(dragEvent -> {
            resToGive.setImage(dragEvent.getDragboard().getImage());
            dragEvent.consume();
        });

        //drag & drop for base production
        /*baseProd1.setOnDragOver(dragEvent -> {
            dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            dragEvent.consume();
        });
        baseProd2.setOnDragOver(dragEvent -> {
            dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            dragEvent.consume();
        });
        baseProd1.setOnDragDropped(dragEvent -> {
            baseProd1.setImage(dragEvent.getDragboard().getImage());
            dragEvent.consume();
        });
        baseProd2.setOnDragDropped(dragEvent -> {
            baseProd2.setImage(dragEvent.getDragboard().getImage());
            dragEvent.consume();
        });*/
    }


    public void discard(ActionEvent actionEvent) {
    }
}
