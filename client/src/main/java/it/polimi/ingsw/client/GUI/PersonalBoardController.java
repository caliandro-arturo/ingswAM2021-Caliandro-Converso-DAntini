package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.ClientModel;
import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.model.DevelopmentCard;
import it.polimi.ingsw.client.model.Utility;
import it.polimi.ingsw.commonFiles.model.Resource;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Stack;

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
    private Pane devProdPane1;
    @FXML
    private Pane devProdPane2;



    @FXML
    private Pane devProdPane3;

    private View view;
    private ArrayList<Pane> devPlaces;
    private ArrayList<String> commands = new ArrayList<>();

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
        //drag & drop for warehouse store
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
            dragEvent.consume();
        });
        baseProd2.setOnDragDropped(dragEvent -> {
            baseProd2.setImage(dragEvent.getDragboard().getImage());
            dragEvent.consume();
        });
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
}
