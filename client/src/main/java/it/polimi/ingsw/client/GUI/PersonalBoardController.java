package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.ClientModel;
import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.model.DevelopmentCard;
import it.polimi.ingsw.client.model.Utility;
import it.polimi.ingsw.commonFiles.model.Resource;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Stack;

public class PersonalBoardController extends BoardController {

    @FXML
    private ImageView crossB;
    @FXML
    private ComboBox<ImageView> resBaseProd;
    @FXML
    private ComboBox<ImageView> leadProd1;
    @FXML
    private ComboBox<ImageView> leadProd2;
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
    private Button prodButton1;
    @FXML
    private ImageView resToGive;
    @FXML
    private ImageView resToGive1;
    @FXML
    private Button prodButton2;
    @FXML
    private Pane leftPane;
    @FXML
    private ImageView activeLeaderCard1;
    @FXML
    private ImageView activeLeaderCard2;
    @FXML
    private Label labelLeaderCards;
    @FXML
    private AnchorPane resourceHand;
    @FXML
    private GridPane leaderDepot1;
    @FXML
    private GridPane leaderDepot2;
    @FXML
    private Button prodBaseButton;
    @FXML
    private Label paymentStone1;
    @FXML
    private Label paymentSerf1;
    @FXML
    private Label paymentShield1;
    @FXML
    private Label paymentCoin1;
    @FXML
    private Label paymentStone2;
    @FXML
    private Label paymentSerf2;
    @FXML
    private Label paymentShield2;
    @FXML
    private Label paymentCoin2;
    @FXML
    private Label paymentStone3;
    @FXML
    private Label paymentSerf3;
    @FXML
    private Label paymentShield3;
    @FXML
    private Label paymentCoin3;
    @FXML
    private ImageView stoneCost1;
    @FXML
    private ImageView shieldCost1;
    @FXML
    private ImageView coinCost1;
    @FXML
    private ImageView serfCost1;
    @FXML
    private ImageView stoneCost2;
    @FXML
    private ImageView shieldCost2;
    @FXML
    private ImageView coinCost2;
    @FXML
    private ImageView serfCost2;
    @FXML
    private ImageView stoneCost3;
    @FXML
    private ImageView shieldCost3;
    @FXML
    private ImageView coinCost3;
    @FXML
    private ImageView serfCost3;



    @FXML
    private Pane devProdPane1;
    @FXML
    private Pane devProdPane2;



    @FXML
    private Pane devProdPane3;

    private View view;
    private ArrayList<Pane> devPlaces;
    private ArrayList<String> commands = new ArrayList<>();
    private ArrayList<ArrayList<ResourceAndDepot>> resourceAndDepotBuffer = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        resBaseProd.getItems().addAll(
                GamePanel.imgViewCoin,
                GamePanel.imgViewSerf,
                GamePanel.imgViewShield,
                GamePanel.imgViewStone
        );
        leadProd1.getItems().addAll(
                GamePanel.imgViewCoin,
                GamePanel.imgViewSerf,
                GamePanel.imgViewShield,
                GamePanel.imgViewStone
        );
        leadProd2.getItems().addAll(
                GamePanel.imgViewCoin,
                GamePanel.imgViewSerf,
                GamePanel.imgViewShield,
                GamePanel.imgViewStone
        );
        leadProd1.setOpacity(0);
        leadProd1.setDisable(true);
        leadProd2.setOpacity(0);
        leadProd2.setDisable(true);
        resToGive.setDisable(true);
        resToGive1.setDisable(true);
        prodButton1.setDisable(true);
        prodButton1.setOpacity(0);
        prodButton2.setDisable(true);
        prodButton2.setOpacity(0);
        resBaseProd.setOpacity(0);
        resBaseProd.setDisable(true);
        prodBaseButton.setOpacity(0);
        prodBaseButton.setDisable(true);
        leaderDepot1.setDisable(true);
        leaderDepot2.setDisable(true);
        devProdPane1.setOpacity(0);
        devProdPane2.setOpacity(0);
        devProdPane3.setOpacity(0);
        devProdPane1.setDisable(true);
        devProdPane2.setDisable(true);
        devProdPane3.setDisable(true);
        devPlaces = new ArrayList<>();
        devPlaces.add(devProdPane1);
        devPlaces.add(devProdPane2);
        devPlaces.add(devProdPane3);

    }

    public Pane getDevProdPane1() {
        return devProdPane1;
    }

    public Pane getDevProdPane2() {
        return devProdPane2;
    }

    public Pane getDevProdPane3() {
        return devProdPane3;
    }

    /**
     * setter for the active leader card images
     * @param cardID must be greater than 48
     */
    public void setActiveLeaderCard(int cardID){
        if(cardID>48){

            if(activeLeaderCard1.getImage()==null){
                activeLeaderCard1.setImage(Utility.getCardPng(cardID));
                //let the production drag&drop appear
                if(cardID > 60){
                    resToGive.setDisable(false);
                    leadProd1.setDisable(false);
                    leadProd1.setOpacity(1);
                    prodButton1.setOpacity(1);
                    prodButton1.setDisable(false);
                }else {
                    resToGive.setDisable(true);
                    leadProd1.setDisable(true);
                    leadProd1.setOpacity(0);
                    prodButton1.setDisable(true);
                    prodButton1.setOpacity(0);

                }
            }
            else if(activeLeaderCard2.getImage()== null){
                activeLeaderCard2.setImage(Utility.getCardPng(cardID));
                //let the production drag&drop appear
                if(cardID > 60){
                    resToGive1.setDisable(false);
                    leadProd2.setDisable(false);
                    leadProd2.setOpacity(1);
                    prodButton2.setDisable(false);
                    prodButton2.setOpacity(1);

                    //drag & drop for leader card production
                    resToGive.setOnDragOver(dragEvent -> {
                        dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        dragEvent.consume();
                    });
                    resToGive.setOnDragDropped(dragEvent -> {
                        resToGive.setImage(dragEvent.getDragboard().getImage());
                        dragEvent.consume();
                    });
                }else {
                    resToGive1.setDisable(true);
                    leadProd2.setDisable(true);
                    leadProd2.setOpacity(0);
                    prodButton2.setDisable(true);
                    prodButton2.setOpacity(0);
                }
            }
        }
        //TODO : else error message FULL ACTIVE LEADER CARDS
    }

    public void setView(View view) {
        this.view = view;
    }

    /**
     * move resource from hand to warehouse store
     */
    public void moveRes() {
        /**
         * start drag & drop from HAND
         */
        if(!getHandListImg().isEmpty()){
        getHand().setOnDragDetected(event1 -> {
            Dragboard db = getHand().startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            Image res = getHandListImg().get(getHand().getCurrentPageIndex()).getImage();
            content.putImage(res);
            db.setContent(content);
            getHandListImg().remove(getHand().getCurrentPageIndex());
            event1.consume();
        });
        }
        //------------------------------------------------------------------------------------------------------------
        /**
         * accept drag & drop for LEADERCARD production
         */
        resToGive.setOnDragOver(dragEvent -> {
            dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            dragEvent.consume();
        });
        resToGive1.setOnDragOver(dragEvent -> {
            dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            dragEvent.consume();
        });
        resToGive.setOnDragDropped(dragEvent -> {
            resToGive.setImage(dragEvent.getDragboard().getImage());
            addElementToCost(resToGive,dragEvent,4);
            dragEvent.consume();
        });
        resToGive1.setOnDragDropped(dragEvent -> {
            resToGive.setImage(dragEvent.getDragboard().getImage());
            addElementToCost(resToGive1,dragEvent,5);
            dragEvent.consume();
        });
        //------------------------------------------------------------------------------------------------------------
        /**
         * accept drag & drop for BASE production
         */

        //drag & drop for production
        baseProd1.setOnDragOver(dragEvent -> {
            dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            dragEvent.consume();
        });
        baseProd2.setOnDragOver(dragEvent -> {
            dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            dragEvent.consume();
        });
        baseProd1.setOnDragDropped(dragEvent -> {
            baseProd1.setImage(dragEvent.getDragboard().getImage());
            addElementToCost(baseProd1,dragEvent,0);
            dragEvent.consume();
        });
        baseProd2.setOnDragDropped(dragEvent -> {
            baseProd2.setImage(dragEvent.getDragboard().getImage());
            addElementToCost(baseProd2,dragEvent,0);
            dragEvent.consume();
        });
        ArrayList<ImageView> elements = new ArrayList<>(Arrays.asList(serfCost1,shieldCost1,coinCost1,stoneCost1));
        addElementToADevCardCost(elements,1);
        elements = new ArrayList<>(Arrays.asList(serfCost2,shieldCost2,coinCost2,stoneCost2));
        addElementToADevCardCost(elements,2);
        elements = new ArrayList<>(Arrays.asList(serfCost3,shieldCost3,coinCost3,stoneCost3));
        addElementToADevCardCost(elements,3);
        //------------------------------------------------------------------------------------------------------------
        /**
         * accept drag & drop for WAREHOUSE
         */

        for(ImageView warSpot: getResSpots()){
            warSpot.setOnDragOver(dragEvent -> {
                dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                dragEvent.consume();
            });
        }
        /**
         * start drag & drop from WAREHOUSE
         */
        for(ImageView warSpot: getResSpots()){
            warSpot.setOnDragDetected(dragEvent -> {
                if(warSpot.getImage()!=null){
                    Dragboard db = warSpot.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(warSpot.getImage());
                    if(dragEvent.getSource()==getRes1()){
                        content.putString("1");
                    }
                    else if (dragEvent.getSource()==getRes21() || dragEvent.getSource()==getRes22() ){
                        content.putString("2");
                    }
                    else if( dragEvent.getSource()== getRes31() || dragEvent.getSource()== getRes32()
                            || dragEvent.getSource()== getRes33()){
                        content.putString("3");
                    }
                }
            });
        }
        //------------------------------------------------------------------------------------------------------------
        /**
         * accept drag & drop for WAREHOUSE store and send the relative DEPLOYRES message
         */
        for(ImageView warSpot: getResSpots()){
            warSpot.setOnDragDropped(dragEvent -> {
                StringBuilder command = new StringBuilder();
                command.append(Arrays.toString(dragEvent.getDragboard().getImage().getUrl().split("\\.png")));
                if(dragEvent.getSource()==getRes1()){
                    command.append(", 1");
                }
                else if (dragEvent.getSource()==getRes21() || dragEvent.getSource()==getRes22() ){
                    command.append(", 2");
                }
                else if( dragEvent.getSource()== getRes31() || dragEvent.getSource()== getRes32()
                        || dragEvent.getSource()== getRes33()){
                    command.append(", 3");
                }
                view.process("deployres: " + command);
                dragEvent.consume();
            });
        }

        /**
         * start drag & drop for STRONGBOX
         */
        for (ImageView res : getStrongResources()) {
            if (Integer.parseInt(getStrongMap().get(res).getText()) > 0) {
                res.setOnDragDetected(e -> {
                    Dragboard db = res.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(res.getImage());
                    content.putString("0");
                    db.setContent(content);
                    e.consume();
                });
            }
        }
    }

    public void addElementToADevCardCost(ArrayList<ImageView> elements, int i){
        for (ImageView element: elements){
            element.setOnDragOver(dragEvent -> {
                dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                dragEvent.consume();
            });
            element.setOnDragDropped(dragEvent -> {
                element.setImage(dragEvent.getDragboard().getImage());
                addElementToCost(element,dragEvent,i);
                dragEvent.consume();
            });
        }
    }

    public void addElementToCost(ImageView cost, DragEvent event, int i){
        try {
            ArrayList<ResourceAndDepot> resourceAndDepots = new ArrayList<>();
            resourceAndDepots.add(new ResourceAndDepot(getResourceImageViewHashMap().get(cost),Integer.parseInt(event.getDragboard().getString())));
            resourceAndDepotBuffer.set(i,resourceAndDepots);
        } catch (ArrayIndexOutOfBoundsException e){
            resourceAndDepotBuffer.add(new ArrayList<>());
            resourceAndDepotBuffer.get(i).add(new ResourceAndDepot(getResourceImageViewHashMap().get(cost),Integer.parseInt(event.getDragboard().getString())));
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
     * put the images of resources in the context menu for the base production of the board
     * @param contextMenuEvent
     */
    @FXML
    public void ctxMenuRes(ContextMenuEvent contextMenuEvent){
        for(ImageView img: getResSpots()){
            if(img.getImage()!= null){
                img.setOnContextMenuRequested(e->
                        getContextMenu().show(img, e.getScreenX(), e.getScreenY()));
                int posRes;
                if(img==getRes1()){
                    posRes = 1;
                }
                else if(img==getRes21() || img == getRes22())
                    posRes = 2;
                else
                    posRes = 3;
                getMenuItem().setOnAction(event->{
                    img.setImage(null);
                    //getGui().getView().getController().sendMessage(new TakeRes(posRes));
                });
            }
        }
    }

    public void discard(ActionEvent actionEvent) {
        Resource resource;
        try {
            resource = getResourceImageViewHashMap().get(getHandListImg().get(getHand().getCurrentPageIndex()));
            getHandListImg().remove(getHand().getCurrentPageIndex());
        }catch (IndexOutOfBoundsException e){
            return;
        }
        view.process("discardres: " + resource.name());
    }

    public void setProductionOn(){
        ClientModel model = view.getModel();
        if (model.getBoard().getLeaderCards().size() == 1 && model.getBoard().getLeaderCards().get(0).getID() > 60 && model.getBoard().getLeaderCards().get(0).getID() < 65){
            leadProd1.setOpacity(100);
            leadProd1.setDisable(false);
            resToGive.setDisable(false);
        } else if (model.getBoard().getLeaderCards().size() == 2){
            if (model.getBoard().getLeaderCards().get(0).getID() > 60 && model.getBoard().getLeaderCards().get(0).getID() < 65) {
                leadProd1.setOpacity(100);
                leadProd1.setDisable(false);
                resToGive.setDisable(false);
                prodButton1.setDisable(false);
                prodButton1.setOpacity(100);
            }
            if (model.getBoard().getLeaderCards().get(1).getID() > 60 && model.getBoard().getLeaderCards().get(0).getID() < 65) {
                leadProd2.setOpacity(100);
                leadProd2.setDisable(false);
                resToGive1.setDisable(false);
                prodButton2.setDisable(false);
                prodButton2.setOpacity(100);
            }
        }
        resBaseProd.setOpacity(100);
        resBaseProd.setDisable(false);
        prodBaseButton.setOpacity(100);
        prodBaseButton.setDisable(false);
        leaderDepot1.setDisable(false);
        leaderDepot2.setDisable(false);
        int i = 0;
        for (Stack<DevelopmentCard> place: model.getBoard().getDevelopmentPlace().getDevStack()) {
            if (!place.isEmpty()){
                devPlaces.get(i).setOpacity(100);
                devPlaces.get(i).setDisable(false);
            }
            i++;
        }
    }

    public void setProductionOff(){
        leadProd1.setOpacity(0);
        leadProd1.setDisable(true);
        leadProd2.setOpacity(0);
        leadProd2.setDisable(true);
        resToGive.setDisable(true);
        resToGive1.setDisable(true);
        prodButton1.setDisable(true);
        prodButton1.setOpacity(0);
        prodButton2.setDisable(true);
        prodButton2.setOpacity(0);
        resBaseProd.setOpacity(0);
        resBaseProd.setDisable(true);
        prodBaseButton.setOpacity(0);
        prodBaseButton.setDisable(true);
        leaderDepot1.setDisable(true);
        leaderDepot2.setDisable(true);
        devProdPane1.setOpacity(0);
        devProdPane2.setOpacity(0);
        devProdPane3.setOpacity(0);
        devProdPane1.setDisable(true);
        devProdPane2.setDisable(true);
        devProdPane3.setDisable(true);
    }

    public void boardProduction(ActionEvent actionEvent) {
        if (baseProd1.getImage() == null || baseProd2.getImage() == null || resBaseProd.getSelectionModel().getSelectedItem() == null){
            return;
        }
        view.process(commands.get(0));
    }

    public void production1(ActionEvent actionEvent) {
        if (paymentCoin1.getText().equals("0") && paymentShield1.getText().equals("0") && paymentStone1.getText().equals("0") && paymentSerf1.getText().equals("0")){
            view.process(commands.get(1));
        }
    }

    public void production2(ActionEvent actionEvent) {
        if (paymentCoin2.getText().equals("0") && paymentShield2.getText().equals("0") && paymentStone2.getText().equals("0") && paymentSerf2.getText().equals("0")){
            view.process(commands.get(2));
        }
    }

    public void production3(ActionEvent actionEvent) {
        if (paymentCoin3.getText().equals("0") && paymentShield3.getText().equals("0") && paymentStone3.getText().equals("0") && paymentSerf3.getText().equals("0")){
            view.process(commands.get(3));
        }
    }

    public void productionLeader1(ActionEvent actionEvent) {
        if (resToGive.getImage() != null && leadProd1.getSelectionModel().getSelectedItem()!=null){
            view.process(commands.get(4));
        }
    }

    public void productionLeader2(ActionEvent actionEvent) {
        if (resToGive1.getImage() != null && leadProd2.getSelectionModel().getSelectedItem()!=null){
            view.process(commands.get(5));
        }
    }

    public void moveFromResHand(MouseEvent mouseEvent) {
        Dragboard db = ((Node)mouseEvent.getSource()).startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        Image res = getHandListImg().get(getHand().getCurrentPageIndex()).getImage();
        content.putImage(res);
        db.setContent(content);
    }
}
