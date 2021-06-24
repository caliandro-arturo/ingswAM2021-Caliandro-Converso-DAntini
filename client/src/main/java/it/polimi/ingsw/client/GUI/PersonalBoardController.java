package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.ClientModel;
import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.model.Board;
import it.polimi.ingsw.client.model.DevelopmentCard;
import it.polimi.ingsw.client.model.LeaderCard;
import it.polimi.ingsw.client.model.Utility;
import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;
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
import java.util.*;

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
    private ImageView resToGive1;
    @FXML
    private ImageView resToGive2;
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
    private Button prodCard1;
    @FXML
    private Button prodCard2;
    @FXML
    private Button prodCard3;
    @FXML
    private Pane devProdPane3;

    private View view;
    private ArrayList<Pane> devPlaces;
    private ArrayList<ArrayList<ResourceAndDepot>> resourceAndDepotBuffer = new ArrayList<>();
    private HashMap<ImageView, Label> devCostMap1;
    private HashMap<ImageView, Label> devCostMap2;
    private HashMap<ImageView, Label> devCostMap3;
    private ArrayList<ArrayList<ImageView>> leaderProdImageView;
    private ArrayList<GridPane> leaderDepots;
    private ArrayList<HashMap<Resource, Label>> paymentLabels;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        devCostMap1 = new HashMap<>(){{
            put(stoneCost1, paymentStone1);
            put(serfCost1, paymentSerf1);
            put(shieldCost1, paymentShield1);
            put(coinCost1, paymentCoin1);
        }};
        devCostMap2 = new HashMap<>(){{
            put(stoneCost2, paymentStone2);
            put(serfCost2, paymentSerf2);
            put(shieldCost2, paymentShield2);
            put(coinCost2, paymentCoin2);
        }};
        devCostMap3 = new HashMap<>(){{
            put(stoneCost3, paymentStone3);
            put(serfCost3, paymentSerf3);
            put(shieldCost3, paymentShield3);
            put(coinCost3, paymentCoin3);
        }};
        paymentLabels = new ArrayList<>(){{
            add(new HashMap<>(){{
                put(Resource.STONE,paymentStone1);
                put(Resource.COIN,paymentCoin1);
                put(Resource.SERF,paymentSerf1);
                put(Resource.SHIELD,paymentShield1);
            }});
            add(new HashMap<>(){{
                put(Resource.STONE,paymentStone2);
                put(Resource.COIN,paymentCoin2);
                put(Resource.SERF,paymentSerf2);
                put(Resource.SHIELD,paymentShield2);
            }});
            add(new HashMap<>(){{
                put(Resource.STONE,paymentStone3);
                put(Resource.COIN,paymentCoin3);
                put(Resource.SERF,paymentSerf3);
                put(Resource.SHIELD,paymentShield3);
            }});
        }};

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
        leaderDepots = new ArrayList<>(){{
            add(leaderDepot1);
            add(leaderDepot2);
        }};
        leaderProdImageView = new ArrayList<>(){{
           add(new ArrayList<>(Arrays.asList(resToGive1,leadProd1.getSelectionModel().getSelectedItem())));
           add(new ArrayList<>(Arrays.asList(resToGive2,leadProd2.getSelectionModel().getSelectedItem())));
        }};
        setProductionOff();
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
                    resToGive1.setDisable(false);
                    leadProd1.setDisable(false);
                    leadProd1.setOpacity(1);
                    prodButton1.setOpacity(1);
                    prodButton1.setDisable(false);
                }else {
                    resToGive1.setDisable(true);
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
                    resToGive2.setDisable(false);
                    leadProd2.setDisable(false);
                    leadProd2.setOpacity(1);
                    prodButton2.setDisable(false);
                    prodButton2.setOpacity(1);

                }else {
                    resToGive2.setDisable(true);
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

    public void addElementToCost(ImageView cost, DragEvent event, int i){
        try {
            ArrayList<ResourceAndDepot> resourceAndDepots = new ArrayList<>();
            resourceAndDepots.add(new ResourceAndDepot(GamePanel.imageViewResourceMap.get(cost),Integer.parseInt(event.getDragboard().getString())));
            resourceAndDepotBuffer.set(i,resourceAndDepots);
        } catch (IndexOutOfBoundsException e){
            resourceAndDepotBuffer.add(new ArrayList<>());
            resourceAndDepotBuffer.get(i).add(new ResourceAndDepot(GamePanel.imageViewResourceMap.get(cost),Integer.parseInt(event.getDragboard().getString())));
        }
    }

    @Override
    public void setBoard(Board board) {
        super.setBoard(board);
        board.getFaithTrack().positionBProperty().addListener(e -> {
            if (crossB.getOpacity() < 1) crossB.setOpacity(1);
            increasePos(crossB, board.getFaithTrack().getPositionB());
        });
        if (board.getFaithTrack().getPositionB() == 0) crossB.setOpacity(1);
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
    public void backToHand(ContextMenuEvent contextMenuEvent){
        ImageView target = (ImageView)contextMenuEvent.getSource();
        if(target.getImage()!= null){
            target.setOnContextMenuRequested(e->
                    getContextMenu().show(target, e.getScreenX(), e.getScreenY()));
            int posRes;
            if(target==getRes1()){
                posRes = 1;
            }
            else if(target==getRes21() || target == getRes22())
                posRes = 2;
            else
                posRes = 3;
            getMenuItem().setOnAction(event->
                target.setImage(null));
            view.process("takeres: "+ posRes);
        }
    }

    public void discard(ActionEvent actionEvent) {
        Resource resource;
        try {
            resource = GamePanel.imageViewResourceMap.get(getHandListImg().get(getHand().getCurrentPageIndex()));
            getHandListImg().remove(getHand().getCurrentPageIndex());
        }catch (IndexOutOfBoundsException e){
            return;
        }
        view.process("discardres: " + resource.name());
    }

    public void setProductionOn(){
        ClientModel model = view.getModel();
        if (model.getBoard().getLeaderCards().size() == 1 && model.getBoard().getLeaderCards().get(0).getID() > 60 && model.getBoard().getLeaderCards().get(0).getID() < 65){
            leadProd1.setOpacity(1);
            leadProd1.setDisable(false);
            resToGive1.setDisable(false);
        } else if (model.getBoard().getLeaderCards().size() == 2){
            if (model.getBoard().getLeaderCards().get(0).getID() > 60 && model.getBoard().getLeaderCards().get(0).getID() < 65) {
                leadProd1.setOpacity(1);
                leadProd1.setDisable(false);
                resToGive1.setDisable(false);
                prodButton1.setDisable(false);
                prodButton1.setOpacity(1);
            }
            if (model.getBoard().getLeaderCards().get(1).getID() > 60 && model.getBoard().getLeaderCards().get(0).getID() < 65) {
                leadProd2.setOpacity(1);
                leadProd2.setDisable(false);
                resToGive2.setDisable(false);
                prodButton2.setDisable(false);
                prodButton2.setOpacity(1);
            }
        }
        resBaseProd.setOpacity(1);
        resBaseProd.setDisable(false);
        prodBaseButton.setOpacity(1);
        prodBaseButton.setDisable(false);
        leaderDepot1.setDisable(false);
        leaderDepot2.setDisable(false);
        int i = 0;
        for (DevelopmentCard card: model.getBoard().getDevelopmentPlace().getTopCards()) {
            if (card!=null){
                devPlaces.get(i).setDisable(false);
                devPlaces.get(i).setOpacity(1);
                for(UtilityProductionAndCost cost: card.getProduction().getCost()){
                    paymentLabels.get(i).get(cost.getResource()).setText(String.valueOf(cost.getQuantity()));
                }
            }
            i++;
        }
    }

    public void setProductionOff(){
        leadProd1.setOpacity(0);
        leadProd1.setDisable(true);
        leadProd2.setOpacity(0);
        leadProd2.setDisable(true);
        resToGive1.setDisable(true);
        resToGive2.setDisable(true);
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
    /**
     * start drag & drop from HAND
     */
    public void moveFromResHand(MouseEvent mouseEvent) {
        Dragboard db = ((Node)mouseEvent.getSource()).startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        Image res = getHandListImg().get(getHand().getCurrentPageIndex()).getImage();
        content.putImage(res);
        db.setContent(content);
    }
    /**
     * accept drag & drop
     */
    @FXML
    public void onDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        event.consume();
    }

    /**
     * start drag & drop from WAREHOUSE
     */
    @FXML
    public void moveResFromWar(MouseEvent event) {
        ImageView target = (ImageView) event.getSource();
        if(target.getImage()!=null){
            Dragboard db = ((Node)event.getSource()).startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(target.getImage());
            content.putString(returnResourcePosition(target));
            db.setContent(content);
        }
    }

    /**
     * accept drag & drop for WAREHOUSE store and send the relative DEPLOYRES message
     */
    @FXML
    public void dropResWar(DragEvent event){
        StringBuilder command = new StringBuilder();
        ImageView target = (ImageView) event.getSource();
        Image image = event.getDragboard().getImage();
        Resource resource = GamePanel.imageResourceMap.get(image);
        command.append(GamePanel.imageResourceMap.get(event.getDragboard().getImage()).name());
        command.append(", " + returnResourcePosition(target));
        view.process("deployres: " + command);
    }

    public String returnResourcePosition(ImageView res){
        for(ArrayList<ImageView> slot: getStoresList()){
            if(slot.contains(res)){
                return String.valueOf(getStoresList().indexOf(slot)+1);
            }
        }
        return "";
    }

    @FXML
    public void dropResProd(DragEvent event) {
        ImageView target = (ImageView) event.getSource();
        target.setImage(event.getDragboard().getImage());
        if(target == baseProd1 || target == baseProd2) {
            addElementToCost(target, event, 0);
        }
        else if (target == resToGive1){
            addElementToCost(target, event, 4);
        }
        else if (target == resToGive2){
            addElementToCost(target, event, 5);
        }
    }

    /**
     * start drag & drop for STRONGBOX
     */
    @FXML
    public void moveResFromBox(MouseEvent event){
        ImageView res = (ImageView) event.getSource();

        if (Integer.parseInt(getStrongMap().get(res).getText()) > 0) {
            res.setOnDragDetected(e -> {
                Dragboard db = res.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putImage(res.getImage());
                content.putString("0");
                db.setContent(content);
            });
        }
    }

    /**
     * accept drag & drop to the cost request in the development card production
     * @param event
     */
    @FXML
    public void dropProdCost(DragEvent event){
        int i = 0;

        ImageView target = (ImageView) event.getSource();
        int costInt = Integer.parseInt(devCostMap1.get(target).getText());

        if(target == coinCost1 || target == shieldCost1 || target == stoneCost1 || target == serfCost1){
            i = 1;
            if(costInt>0){
                devCostMap1.get(target).setText(String.valueOf((costInt-1)));
                addElementToCost(target, event, i);

            } else {
                incrementDepotQuantity(Integer.parseInt(event.getDragboard().getString()),target.getImage());
            }
        }
        else if (target == coinCost2 || target == shieldCost2 || target == stoneCost2 || target == serfCost2){
            i = 2;
            if(costInt>0) {
                devCostMap2.get(target).setText(String.valueOf((costInt-1)));
                addElementToCost(target, event, i);
            } else {
                incrementDepotQuantity(Integer.parseInt(event.getDragboard().getString()),target.getImage());
            }

        }
        else if (target == coinCost3 || target == shieldCost3 || target == stoneCost3 || target == serfCost3){
            i = 3;
            if(costInt>0) {
                devCostMap3.get(target).setText(String.valueOf((costInt-1)));
                addElementToCost(target, event, i);
            } else {
                incrementDepotQuantity(Integer.parseInt(event.getDragboard().getString()),target.getImage());
            }
        }
    }

    public void production(ActionEvent actionEvent) {
        HashMap<Button,Integer> prodMap = new HashMap<>(){{
            put(prodBaseButton,0);
            put(prodCard1, 1);
            put(prodCard2,2);
            put(prodCard3, 3);
            put(prodButton1,4);
            put(prodButton2,5);
        }};
        int ID = prodMap.get((Button) actionEvent.getSource());
        StringBuilder cmd = new StringBuilder("activateprod: ");
        if (ID>3){
            if (leaderProdImageView.get(ID-4).get(0).getImage() != null && leaderProdImageView.get(ID-4).get(1).getImage()!=null) {
                if (isOneLeaderProductionActive() && !isAProductionCard(view.getModel().getBoard().getLeaderCards().get(0).getID())){
                    cmd.append(ID-1 + ", ");
                } else
                    cmd.append(ID + ", ");
                cmd.append(resourceAndDepotBuffer.get(ID).get(0).getDepot()).append(", ").append(GamePanel.imageResourceMap.
                        get(leaderProdImageView.get(ID - 4).get(1).getImage()));
                view.process(cmd.toString());
                return;
            } else {
                refundResource(ID);
                leaderProdImageView.get(ID-4).get(0).setImage(null);
            }
        }
        cmd.append(ID + ", ");
        if (ID>0){
            if (checkIfTheConditionsForTheProdAreMet(ID)){
                StringBuilder cost = new StringBuilder();
                for (UtilityProductionAndCost cost1: view.getModel().getBoard().getDevelopmentPlace().getTopCard(ID).getProduction().getCost()){
                    for (ResourceAndDepot resourceAndDepot : resourceAndDepotBuffer.get(ID)){
                        if (resourceAndDepot.getResource() == cost1.getResource()){
                            cost.append(resourceAndDepot.getDepot() + " ");
                        }
                    }
                }
                cmd.append(cost);
                view.process(cmd.toString());
            } else {
                refundResource(ID);
            }
        } else {
            if (baseProd1.getImage() != null || baseProd2.getImage() != null || resBaseProd.getSelectionModel().getSelectedItem() != null){
                StringBuilder cost = new StringBuilder();
                StringBuilder store = new StringBuilder();
                for (ResourceAndDepot resourceAndDepot :resourceAndDepotBuffer.get(0)){
                    store.append(resourceAndDepot.getDepot() + " ");
                    cost.append(resourceAndDepot.getResource().name() + " ");
                }
                cmd.append(cost + ", " + GamePanel.imageResourceMap.get(resBaseProd.getSelectionModel().getSelectedItem().getImage()).name() + ", "+ store);
                view.process(cmd.toString());
            } else {
                refundResource(ID);
                baseProd1.setImage(null);
                baseProd2.setImage(null);
            }
        }
    }

    public boolean isAProductionCard(int ID){
        return ID > 60 && ID < 65;
    }

    public boolean isOneLeaderProductionActive(){
        return view.getModel().getBoard().getPowerProd().size() == 1;
    }

    public void incrementDepotQuantity(int ID, Image image){
        if (ID == 0) {
            int quantity = Integer.parseInt(getResourceLabelHashMap().get(GamePanel.imageResourceMap.get(image)).getText());
            quantity++;
            getResourceLabelHashMap().get(GamePanel.imageResourceMap.get(image)).setText(String.valueOf(quantity));
        } else {
            try {
                Objects.requireNonNull(firstFreeDepot(ID)).setImage(image);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    private ImageView firstFreeDepot(int ID){
        for (ImageView depot: getStoresList().get(ID-1)){
            if (depot.getImage() == null){
                return depot;
            }
        }
        return null;
    }

    public void refundResource(int ID){
        for (ResourceAndDepot resourceAndDepot: resourceAndDepotBuffer.get(ID)){
            incrementDepotQuantity(resourceAndDepot.getDepot(),GamePanel.resourceImageMap.get(resourceAndDepot.getResource()));
        }
        resourceAndDepotBuffer.set(ID, new ArrayList<>());
    }

    @Override
    public void updateActiveLeaderCards() {
        super.updateActiveLeaderCards();
        ObservableList<LeaderCard> cards = view.getModel().getBoard().getLeaderCards();
        for (int i = 0; i < cards.size(); i++) {
            if (isASpecialWarehousePower(cards.get(i).getID())){
                leaderDepots.get(i).setDisable(false);
            }
        }
    }

    private boolean isASpecialWarehousePower(int ID){
        return ID >52 && ID<57;
    }

    private boolean checkIfTheConditionsForTheProdAreMet(int ID){
        switch (ID) {
            case 1 -> {
                return paymentCoin1.getText().equals("0") && paymentShield1.getText().equals("0") && paymentStone1.getText().equals("0") && paymentSerf1.getText().equals("0");
            }
            case 2 -> {
                return paymentCoin2.getText().equals("0") && paymentShield2.getText().equals("0") && paymentStone2.getText().equals("0") && paymentSerf2.getText().equals("0");
            }
            case 3 -> {
                return paymentCoin3.getText().equals("0") && paymentShield3.getText().equals("0") && paymentStone3.getText().equals("0") && paymentSerf3.getText().equals("0");
            }
        }
        return false;
    }
}
