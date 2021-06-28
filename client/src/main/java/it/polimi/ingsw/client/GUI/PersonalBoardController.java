package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.ClientModel;
import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.model.Board;
import it.polimi.ingsw.client.model.DevelopmentCard;
import it.polimi.ingsw.client.model.LeaderCard;
import it.polimi.ingsw.client.model.Utility;
import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
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
    @FXML
    private Pane boardProdPane;
    @FXML
    private Pane leaderProd1;
    @FXML
    private Pane leaderProd2;

    private View view;
    private ArrayList<Pane> devPlaces;
    private ArrayList<ArrayList<ResourceAndDepot>> resourceAndDepotBuffer = new ArrayList<>(){{
        add(new ArrayList<>());
        add(new ArrayList<>());
        add(new ArrayList<>());
        add(new ArrayList<>());
        add(new ArrayList<>());
        add(new ArrayList<>());
    }};
    private HashMap<ImageView, Label> devCostMap1;
    private HashMap<ImageView, Label> devCostMap2;
    private HashMap<ImageView, Label> devCostMap3;
    private HashMap<Image, Label> strongImageMap;
    private ArrayList<ArrayList<ImageView>> leaderProdImageView;
    private ArrayList<GridPane> leaderDepots;
    private ArrayList<HashMap<Resource, Label>> paymentLabels;
    private ArrayList<Pane> productionsPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        strongImageMap = new HashMap<>() {{
            put(getStrongCoin().getImage(), getBoxCoin());
            put(getStrongSerf().getImage(), getBoxSerf());
            put(getStrongShield().getImage(), getBoxShield());
            put(getStrongStone().getImage(), getBoxStone());
        }};
        setImage();
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
        productionsPane = new ArrayList<>(){{
            add(boardProdPane);
            add(devProdPane1);
            add(devProdPane2);
            add(devProdPane3);
            add(leaderProd1);
            add(leaderProd2);
        }};
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

    public void addElementToCost(ImageView destination, int depot, int i){
        resourceAndDepotBuffer.get(i).add(new ResourceAndDepot(GamePanel.imageResourceMap.get(destination.getImage()),depot));
    }

    @Override
    public void setBoard(Board board) {
        super.setBoard(board);
        board.getIsProductionAlreadyUsed().addListener((InvalidationListener) e -> Platform.runLater(this::updateProductionInterface));
        board.getFaithTrack().positionBProperty().addListener(e -> {
            if (crossB.getOpacity() < 1) crossB.setOpacity(1);
            increasePos(crossB, board.getFaithTrack().getPositionB());
        });
        if (board.getFaithTrack().getPositionB() == 0) crossB.setOpacity(1);
        board.setIsProductionAlreadyUsed(true,0);
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
            getContextMenu().show(target, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY());
            String posRes;
            if(target == getRes1()){
                posRes = "1";
            }
            else if(target == getRes21() || target == getRes22())
                posRes = "2";
            else{
                posRes = "3";
            }

            getMenuItem().setOnAction(event-> {
                view.process("takeres: "+ posRes);
            });
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

    public void clearBuffer(){
        for (ArrayList<ResourceAndDepot> depot: resourceAndDepotBuffer){
            depot.clear();
        }
    }
    /**
     * start drag & drop from HAND
     */
    public void moveFromResHand(MouseEvent mouseEvent) {
        Dragboard db = ((Node)mouseEvent.getSource()).startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        Image res = getHandListImg().get(getHand().getCurrentPageIndex()).getImage();
        content.putImage(res);
        content.putString("hand");
        db.setContent(content);
    }
    /**
     * Accepts drop into warehouse if the drag source corresponds to the hand.
     */
    @FXML
    public void dragOverWarehouse(DragEvent event) {
        if (event.getDragboard().getString().equals("hand"))
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
    }

    /**
     * Removes a resource from the depot from which the drag & drop started. Used in production and payment.
     */
    @FXML
    public void withdrawRes(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        String source = dragboard.getString();
        if (!event.isAccepted()) return;
        int depot = source.substring(0, 1).matches("\\d") ? Integer.parseInt(source) : 0;
        if (depot == 0) {
            Label strongBoxLabel = strongImageMap.get(dragboard.getImage());
            strongBoxLabel.setText(Integer.toString(Integer.parseInt(strongBoxLabel.getText()) - 1));
        } else {
            ListIterator<ImageView> iterator = getStoresList().get(depot - 1).listIterator(getStoresList().get(depot - 1).size());
            while (iterator.hasPrevious()) {
                ImageView currentImageView = iterator.previous();
                if (currentImageView.getImage() != null) {
                    currentImageView.setImage(null);
                    return;
                }
            }
        }
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
        if (!event.getDragboard().getString().equals("hand")) {
            event.setDropCompleted(false);
            return;
        }
        StringBuilder command = new StringBuilder();
        ImageView destination = (ImageView) event.getSource();
        Image image = getHandListImg().get(getHand().getCurrentPageIndex()).getImage();
        command.append(GamePanel.imageResourceMap.get(image).name());
        command.append(", " + returnResourcePosition(destination));
        view.process("deployres: " + command);
    }

    public String returnResourcePosition(ImageView res){
        for(ArrayList<ImageView> slot: getStoresList()){
            if(slot.contains(res)){
                return String.valueOf(getStoresList().indexOf(slot)+1);
            }
        }
        return "0";
    }

    /**
     * accept drag & drop for the board production
     * @param event
     */
    @FXML
    public void dropResProd(DragEvent event) {
        ImageView destination = (ImageView) event.getSource();
        int depot = 0;
        try {
            depot = Integer.parseInt(event.getDragboard().getString()) -1;
            for (ImageView store : getStoresList().get(depot)) {
                if (store.getImage() != null) {
                    destination.setImage(store.getImage());
                    depot++;
                    break;
                }
            }
        }catch (NumberFormatException e){
            destination.setImage(GamePanel.resourceImageMap.get(Utility.mapResource.get(event.getDragboard().getString().toLowerCase())));
            depot = 0;
        }
        if(destination == baseProd1 || destination == baseProd2) {
            addElementToCost(destination, depot, 0);
        }
        else if (destination == resToGive1){
            addElementToCost(destination , depot, 4);
        }
        else if (destination == resToGive2){
            addElementToCost(destination , depot, 5);
        }
    }

    /**
     * start drag & drop for STRONGBOX
     */
    @FXML
    public void moveResFromBox(MouseEvent event){
        ImageView target = (ImageView) event.getSource();
        if (Integer.parseInt(getStrongMap().get(target).getText()) > 0) {
            Dragboard db = ((Node)event.getSource()).startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(target.getImage());
            content.putString(GamePanel.imageResourceMap.get(target.getImage()).name());
            db.setContent(content);
        }
    }

    /**
     * accept drag & drop to the cost request in the development card production
     * @param event
     */
    @FXML
    public void dropProdCost(DragEvent event){
        int i = 0;

        ImageView destination = (ImageView) event.getSource();
        int depot = Integer.parseInt(event.getDragboard().getString()) - 1;
        int costInt = Integer.parseInt(devCostMap1.get(destination).getText());

        if(destination == coinCost1 || destination == shieldCost1 || destination == stoneCost1 || destination == serfCost1){
            i = 1;
            if(costInt>0){
                devCostMap1.get(destination).setText(String.valueOf((costInt-1)));
                addElementToCost(destination, depot + 1, i);

            } else {
                incrementDepotQuantity(Integer.parseInt(event.getDragboard().getString()),destination.getImage());
            }
        }
        else if (destination == coinCost2 || destination == shieldCost2 || destination == stoneCost2 || destination == serfCost2){
            i = 2;
            if(costInt>0) {
                devCostMap2.get(destination).setText(String.valueOf((costInt-1)));
                addElementToCost(destination, depot + 1, i);
            } else {
                incrementDepotQuantity(Integer.parseInt(event.getDragboard().getString()),destination.getImage());
            }

        }
        else if (destination == coinCost3 || destination == shieldCost3 || destination == stoneCost3 || destination == serfCost3){
            i = 3;
            if(costInt>0) {
                devCostMap3.get(destination).setText(String.valueOf((costInt-1)));
                addElementToCost(destination, depot + 1, i);
            } else {
                incrementDepotQuantity(Integer.parseInt(event.getDragboard().getString()),destination.getImage());
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
                clearBuffer();
            } else {
                refundResource(ID);
            }
            baseProd1.setImage(null);
            baseProd2.setImage(null);
        }
    }

    public void updateProductionInterface(){
        ObservableList<Boolean> productionUsage = view.getModel().getBoard().getIsProductionAlreadyUsed();
        for (int i = 0; i < productionsPane.size(); i++) {
            if (productionUsage.get(i)!=null){
                if (productionUsage.get(i)){
                    productionsPane.get(i).setDisable(true);
                    productionsPane.get(i).setOpacity(0);
                } else {
                    productionsPane.get(i).setDisable(false);
                    productionsPane.get(i).setOpacity(1);
                }
            } else {
                productionsPane.get(i).setDisable(true);
                productionsPane.get(i).setOpacity(0);
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

    /**
     * discard the actual shown resource in the hand sending the message DISCARDRES
     * @param event
     */
    @FXML
    void discardRes(ActionEvent event) {
        Image image = getHandListImg().get(getHand().getCurrentPageIndex()).getImage();
        String resToDiscard = GamePanel.imageResourceMap.get(image).name();
        view.process("discardres: "+ resToDiscard);

    }

    @Override
    public void updateDevPlace() {
        super.updateDevPlace();
        ArrayList<DevelopmentCard> cards = view.getModel().getBoard().getDevelopmentPlace().getTopCards();
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i) == null) continue;
            for (UtilityProductionAndCost cost: cards.get(i).getCosts()){
                paymentLabels.get(i).get(cost.getResource()).setText(String.valueOf(cost.getQuantity()));
            }
        }
    }
}
