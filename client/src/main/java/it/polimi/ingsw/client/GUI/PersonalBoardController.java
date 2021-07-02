package it.polimi.ingsw.client.GUI;

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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.*;

/**
 * Board controller implementation for the player that starts the game in GUI mode. It contains additional methods that
 * implement drag & drop, productions and Lorenzo's cross (Single player).
 */
public class PersonalBoardController extends BoardController {

    /**
     * Black cross for single player.
     */
    @FXML
    private ImageView crossB;

    /**
     * Nodes that implement the board production.
     */
    @FXML
    private Pane boardProdPane;
    @FXML
    private ComboBox<ImageView> resBaseProd;
    @FXML
    private ImageView baseProd1;
    @FXML
    private ImageView baseProd2;
    @FXML
    private Button prodBaseButton;

    /**
     * Nodes that implement leader productions and depots
     */
    @FXML
    private Pane leaderProd1;
    @FXML
    private Pane leaderProd2;
    @FXML
    private ComboBox<ImageView> leadProd1;
    @FXML
    private ComboBox<ImageView> leadProd2;
    @FXML
    private ImageView resToGive1;
    @FXML
    private ImageView resToGive2;
    @FXML
    private Button prodButton1;
    @FXML
    private Button prodButton2;
    @FXML
    private ImageView activeLeaderCard1;
    @FXML
    private ImageView activeLeaderCard2;
    @FXML
    private HBox leaderDepot1;
    @FXML
    private HBox leaderDepot2;

    /**
     * Resource hand pane that includes the discard button.
     */
    @FXML
    private AnchorPane resourceHand;

    /**
     * Nodes that implement the payment pane.
     */
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

    /**
     * Nodes that implement the development card productions.
     */
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
    @FXML
    private Button prodCard1;
    @FXML
    private Button prodCard2;
    @FXML
    private Button prodCard3;

    private ContextMenu contextMenu;
    private MenuItem menuItem;

    private View view;
    private final ArrayList<ArrayList<ResourceAndDepot>> resourceAndDepotBuffer = new ArrayList<>() {{
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
    private ArrayList<ArrayList<ImageView>> prodImageView;
    private ArrayList<HBox> leaderDepots;
    private ArrayList<HashMap<Resource, Label>> paymentLabels;
    private ArrayList<Pane> productionsPane;
    private ArrayList<ArrayList<ImageView>> productionsImageView;
    private ArrayList<HashMap<ImageView, Label>> devCostList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        setImage();
        strongImageMap = new HashMap<>() {{
            put(getStrongCoin().getImage(), getBoxCoin());
            put(getStrongSerf().getImage(), getBoxSerf());
            put(getStrongShield().getImage(), getBoxShield());
            put(getStrongStone().getImage(), getBoxStone());
        }};
        devCostMap1 = new HashMap<>() {{
            put(stoneCost1, paymentStone1);
            put(serfCost1, paymentSerf1);
            put(shieldCost1, paymentShield1);
            put(coinCost1, paymentCoin1);
        }};
        devCostMap2 = new HashMap<>() {{
            put(stoneCost2, paymentStone2);
            put(serfCost2, paymentSerf2);
            put(shieldCost2, paymentShield2);
            put(coinCost2, paymentCoin2);
        }};
        devCostMap3 = new HashMap<>() {{
            put(stoneCost3, paymentStone3);
            put(serfCost3, paymentSerf3);
            put(shieldCost3, paymentShield3);
            put(coinCost3, paymentCoin3);
        }};
        devCostList = new ArrayList<>() {{
            add(devCostMap1);
            add(devCostMap2);
            add(devCostMap3);
        }};
        paymentLabels = new ArrayList<>() {{
            add(new HashMap<>() {{
                put(Resource.STONE, paymentStone1);
                put(Resource.COIN, paymentCoin1);
                put(Resource.SERF, paymentSerf1);
                put(Resource.SHIELD, paymentShield1);
            }});
            add(new HashMap<>() {{
                put(Resource.STONE, paymentStone2);
                put(Resource.COIN, paymentCoin2);
                put(Resource.SERF, paymentSerf2);
                put(Resource.SHIELD, paymentShield2);
            }});
            add(new HashMap<>() {{
                put(Resource.STONE, paymentStone3);
                put(Resource.COIN, paymentCoin3);
                put(Resource.SERF, paymentSerf3);
                put(Resource.SHIELD, paymentShield3);
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
        leaderDepots = new ArrayList<>() {{
            add(leaderDepot1);
            add(leaderDepot2);
        }};
        prodImageView = new ArrayList<>() {{
            add(new ArrayList<>(Arrays.asList(baseProd1, baseProd2, resBaseProd.getSelectionModel().getSelectedItem())));
            add(new ArrayList<>(Arrays.asList(resToGive1, leadProd1.getSelectionModel().getSelectedItem())));
            add(new ArrayList<>(Arrays.asList(resToGive2, leadProd2.getSelectionModel().getSelectedItem())));
        }};
        productionsPane = new ArrayList<>() {{
            add(boardProdPane);
            add(devProdPane1);
            add(devProdPane2);
            add(devProdPane3);
            add(leaderProd1);
            add(leaderProd2);
        }};
        productionsImageView = new ArrayList<>() {{
            add(new ArrayList<>(Arrays.asList(baseProd1, baseProd2)));
            add(new ArrayList<>(Arrays.asList(coinCost1, shieldCost1, serfCost1, stoneCost1)));
            add(new ArrayList<>(Arrays.asList(coinCost2, shieldCost2, serfCost2, stoneCost2)));
            add(new ArrayList<>(Arrays.asList(coinCost3, shieldCost3, serfCost3, stoneCost3)));
            add(new ArrayList<>(Collections.singletonList(resToGive1)));
            add(new ArrayList<>(Collections.singletonList(resToGive2)));
        }};
        contextMenu = new ContextMenu();
        menuItem = new MenuItem("Back to hand");
        contextMenu.getItems().add(menuItem);
    }

    /**
     * Sets images for productions.
     */
    @Override
    public void setImage() {
        super.setImage();
        coinCost1.setImage(GamePanel.imgCoin);
        coinCost2.setImage(GamePanel.imgCoin);
        coinCost3.setImage(GamePanel.imgCoin);
        stoneCost1.setImage(GamePanel.imgStone);
        stoneCost2.setImage(GamePanel.imgStone);
        stoneCost3.setImage(GamePanel.imgStone);
        serfCost1.setImage(GamePanel.imgSerf);
        serfCost2.setImage(GamePanel.imgSerf);
        serfCost3.setImage(GamePanel.imgSerf);
        shieldCost1.setImage(GamePanel.imgShield);
        shieldCost2.setImage(GamePanel.imgShield);
        shieldCost3.setImage(GamePanel.imgShield);
    }

    public void setView(View view) {
        this.view = view;
    }

    public ImageView getCrossB() {
        return crossB;
    }

    /**
     * Adds element into the production buffer.
     *
     * @param destination the slot from which the resource has been moved
     * @param depot the depot from which the resource has moved
     * @param i the index of the production
     */
    public void addElementToCost(ImageView destination, int depot, int i) {
        resourceAndDepotBuffer.get(i).add(new ResourceAndDepot(GamePanel.imageResourceMap.get(destination.getImage()), depot));
    }

    /**
     * Sets the player board, adding listeners for the black cross if the game is single player.
     *
     * @param board the board to set
     */
    @Override
    public void setBoard(Board board) {
        super.setBoard(board);
        board.getIsProductionAlreadyUsed().addListener((InvalidationListener) e -> Platform.runLater(this::updateProductionInterface));
        board.getFaithTrack().positionBProperty().addListener(e -> {
            if (crossB.getOpacity() < 1) crossB.setOpacity(1);
            increasePos(crossB, board.getFaithTrack().getPositionB());
        });
        if (board.getFaithTrack().getPositionB() == 0) crossB.setOpacity(1);
        board.setIsProductionAlreadyUsed(true, 0);
    }

    /**
     * Puts the images of resources in the context menu for the base production of the board.
     */
    @FXML
    public void backToHand(ContextMenuEvent contextMenuEvent) {
        ImageView target = (ImageView) contextMenuEvent.getSource();
        if (target.getImage() != null) {
            contextMenu.show(target, contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY());
            menuItem.setOnAction(e -> view.process("takeres: " + returnResourcePosition(target)));
        }
    }

    /**
     * Clears the depot buffer and resets the depots.
     */
    public void clearBuffer() {
        for (ArrayList<ResourceAndDepot> depot : resourceAndDepotBuffer) {
            depot.clear();
        }
        refundResource();
    }

    /**
     * Start drag & drop from HAND.
     */
    public void moveFromResHand(MouseEvent mouseEvent) {
        Dragboard db = ((Node) mouseEvent.getSource()).startDragAndDrop(TransferMode.ANY);
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
     * Accepts drop if the drag source does not corresponds to the hand.
     */
    @FXML
    public void dragOverProduction(DragEvent event) {
        if (!event.getDragboard().getString().equals("hand"))
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
            Image strongbox = ((ImageView) event.getGestureSource()).getImage();
            Label strongBoxLabel = strongImageMap.get(strongbox);
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
     * Start drag & drop from WAREHOUSE.
     */
    @FXML
    public void moveResFromWar(MouseEvent event) {
        ImageView target = (ImageView) event.getSource();
        if (target.getImage() != null) {
            Dragboard db = ((Node) event.getSource()).startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(target.getImage());
            content.putString(returnResourcePosition(target));
            db.setContent(content);
        }
    }

    /**
     * Accepts drag & drop for WAREHOUSE store and send the relative DEPLOYRES message.
     */
    @FXML
    public void dropResWar(DragEvent event) {
        if (!event.getDragboard().getString().equals("hand")) {
            event.setDropCompleted(false);
            return;
        }
        StringBuilder command = new StringBuilder();
        ImageView destination = (ImageView) event.getSource();
        Image image = getHandListImg().get(getHand().getCurrentPageIndex()).getImage();
        command.append(GamePanel.imageResourceMap.get(image).name());
        command.append(", ").append(returnResourcePosition(destination));
        view.process("deployres: " + command);
    }

    /**
     * Returns the depot ID from which the resource has been taken.
     *
     * @param res the resource to find the corresponding depot
     * @return the ID of the depot the resource has been taken from
     */
    public String returnResourcePosition(ImageView res) {
        int depot = 0;
        for (ArrayList<ImageView> slot : getStoresList()) {
            if (slot.contains(res)) {
                depot = getStoresList().indexOf(slot) + 1;
                break;
            }
        }
        if (depot == 5 && getBoard().getWarehouseStore().getRes().size() == 4) depot--;
        return Integer.toString(depot);
    }

    /**
     * Accepts drag & drop for board and leader productions.
     */
    @FXML
    public void dropResProd(DragEvent event) {
        ImageView destination = (ImageView) event.getSource();
        int depot;
        try {
            depot = Integer.parseInt(event.getDragboard().getString()) - 1;
            for (ImageView store : getStoresList().get(depot)) {
                if (store.getImage() != null) {
                    destination.setImage(store.getImage());
                    depot++;
                    break;
                }
            }
        } catch (NumberFormatException e) {
            destination.setImage(GamePanel.resourceImageMap.get(Utility.mapResource.get(event.getDragboard().getString().toLowerCase())));
            depot = 0;
        }
        addElementToCost(destination, depot, finderProductionID(destination));
        event.setDropCompleted(true);
    }

    /**
     * Finds the ID of the production that contains the image view.
     *
     * @param resource the image view to find
     * @return the ID of the production corresponding to the image view
     */
    public int finderProductionID(ImageView resource) {
        for (ArrayList<ImageView> slot : productionsImageView) {
            if (slot.contains(resource)) {
                return productionsImageView.indexOf(slot);
            }
        }
        return -1; //if this line is reached there is something wrong
    }

    /**
     * Starts drag & drop from STRONGBOX.
     */
    @FXML
    public void moveResFromBox(MouseEvent event) {
        ImageView target = (ImageView) event.getSource();
        if (Integer.parseInt(getStrongMap().get(target).getText()) > 0) {
            Dragboard db = ((Node) event.getSource()).startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(target.getImage());
            content.putString(GamePanel.imageResourceMap.get(target.getImage()).name());
            db.setContent(content);
        }
    }

    /**
     * Accepts drag & drop to the cost request in the development card production.
     */
    @FXML
    public void dropProdCost(DragEvent event) {
        ImageView destination = (ImageView) event.getSource();
        int depot;
        int productionID = finderProductionID(destination);
        int costInt = Integer.parseInt(devCostList.get(productionID - 1).get(destination).getText());
        try {
            depot = Integer.parseInt(event.getDragboard().getString()) - 1;
        } catch (NumberFormatException e) {
            depot = -1;
        }
        if (costInt > 0) {
            addElementToCost(destination, depot + 1, productionID);
            devCostList.get(productionID - 1).get(destination).setText(String.valueOf(costInt - 1));
            event.setDropCompleted(true);
        } else {
            event.setDropCompleted(false);
        }
    }

    /**
     * Handles the production activated by the user
     */
    public void production(ActionEvent actionEvent) {
        HashMap<Button, Integer> prodMap = new HashMap<>() {{
            put(prodBaseButton, 0);
            put(prodCard1, 1);
            put(prodCard2, 2);
            put(prodCard3, 3);
            put(prodButton1, 4);
            put(prodButton2, 5);
        }};
        int ID = prodMap.get((Button) actionEvent.getSource());
        StringBuilder cmd = new StringBuilder("activateprod: ");
        setSelectedImage();
        if (ID > 3) {
            if (checkIfTheConditionsForTheProdAreMet(ID)) {
                if (isOneLeaderProductionActive() && !isAProductionCard(view.getModel().getBoard().getLeaderCards().get(0).getID())) {
                    cmd.append(ID - 1).append(", ");
                } else
                    cmd.append(ID).append(", ");
                cmd.append(resourceAndDepotBuffer.get(ID).get(0).getDepot()).append(", ").append(GamePanel.imageResourceMap.
                        get(prodImageView.get(ID - 3).get(1).getImage()).name());
                view.process(cmd.toString());
                return;
            } else {
                refundResource();
            }
            return;
        }
        cmd.append(ID).append(", ");
        if (ID > 0) {
            if (checkIfTheConditionsForTheProdAreMet(ID)) {
                StringBuilder cost = new StringBuilder();
                for (UtilityProductionAndCost cost1 : view.getModel().getBoard().getDevelopmentPlace().getTopCard(ID).getProduction().getCost()) {
                    for (ResourceAndDepot resourceAndDepot : resourceAndDepotBuffer.get(ID)) {
                        if (resourceAndDepot.getResource() == cost1.getResource()) {
                            cost.append(resourceAndDepot.getDepot()).append(" ");
                        }
                    }
                }
                cmd.append(cost);
                view.process(cmd.toString());
            } else {
                refundResource();
            }
        } else {
            if (checkIfTheConditionsForTheProdAreMet(ID)) {
                StringBuilder cost = new StringBuilder();
                StringBuilder store = new StringBuilder();
                for (ResourceAndDepot resourceAndDepot : resourceAndDepotBuffer.get(0)) {
                    store.append(resourceAndDepot.getDepot()).append(" ");
                    cost.append(resourceAndDepot.getResource().name()).append(" ");
                }
                cmd.append(cost).append(", ").append(GamePanel.imageResourceMap.get(resBaseProd.getSelectionModel().getSelectedItem().getImage()).name()).append(", ").append(store);
                view.process(cmd.toString());
                clearBuffer();
            } else {
                refundResource();
            }
        }
    }

    /**
     * Takes the combo box selection of leader and board productions.
     */
    public void setSelectedImage() {
        prodImageView.get(0).set(2, resBaseProd.getSelectionModel().getSelectedItem());
        prodImageView.get(1).set(1, leadProd1.getSelectionModel().getSelectedItem());
        prodImageView.get(2).set(1, leadProd2.getSelectionModel().getSelectedItem());
    }


    /**
     * Handles the activation of the production interface
     */
    public void updateProductionInterface() {
        ObservableList<Boolean> productionUsage = view.getModel().getBoard().getIsProductionAlreadyUsed();
        for (int i = 0; i < productionsPane.size(); i++) {
            if (productionUsage.get(i) != null) {
                if (productionUsage.get(i)) {
                    productionsPane.get(i).setDisable(true);
                    productionsPane.get(i).setOpacity(0);
                    refundResource();
                } else {
                    if (i == 4 && !isAProductionCard(getBoard().getLeaderCards().get(0).getID())) {
                        i = 5;
                    }
                    productionsPane.get(i).setDisable(false);
                    productionsPane.get(i).setOpacity(1);
                }
            } else {
                productionsPane.get(i).setDisable(true);
                productionsPane.get(i).setOpacity(0);
            }
        }
    }

    /**
     * Resets the image and label for the productions.
     *
     * @param ID identifier for the production
     */
    public void setImageAfterProduction(int ID) {
        for (ImageView spot : productionsImageView.get(ID)) {
            if (ID == 0 || ID > 3) {
                spot.setImage(null);
            } else {
                try {
                    resetToZeroPaymentLabel(ID);
                    for (UtilityProductionAndCost cost : getBoard().getDevelopmentPlace().getTopCard(ID).getProduction().getCost()) {
                        paymentLabels.get(ID - 1).get(cost.getResource()).setText(String.valueOf(cost.getQuantity()));
                    }
                } catch (IndexOutOfBoundsException ignore) {
                }
            }
        }
    }

    /**
     * Resets the payment information labels to 0
     * @param ID identifier of the specific payment
     */
    private void resetToZeroPaymentLabel(int ID){
        paymentLabels.get(ID - 1).get(Resource.STONE).setText("0");
        paymentLabels.get(ID - 1).get(Resource.SERF).setText("0");
        paymentLabels.get(ID - 1).get(Resource.COIN).setText("0");
        paymentLabels.get(ID - 1).get(Resource.SHIELD).setText("0");
    }

    /**
     * Checks if the leader card has the production power.
     *
     * @param ID the ID of the leader card to check
     * @return true if the leader card has the production power, false otherwise
     */
    public boolean isAProductionCard(int ID) {
        return !getBoard().getLeaderCards().isEmpty() && ID > 60 && ID < 65;
    }

    /**
     * Checks if there is one and only one active leader card that has the production power.
     *
     * @return true if there is one and is one and only one active leader card with the production power., false
     * otherwise
     */
    public boolean isOneLeaderProductionActive() {
        return view.getModel().getBoard().getPowerProd().size() == 1;
    }

    public void incrementDepotQuantity(int ID, Image image) {
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

    /**
     * Finds the first free slot in a depot.
     *
     * @param ID the depot in which to do the research
     * @return the first free slot, starting from the left
     */
    private ImageView firstFreeDepot(int ID) {
        for (ImageView depot : getStoresList().get(ID - 1)) {
            if (depot.getImage() == null) {
                return depot;
            }
        }
        return null;
    }

    /**
     * Resets the condition for the productions that are not used in case of a success and in case of failing
     */
    public void refundResource() {
        for (ArrayList<ResourceAndDepot> resourceAndDepot : resourceAndDepotBuffer) {
            resourceAndDepot.clear();
        }
        for (int i = 0; i < productionsImageView.size(); i++) {
            setImageAfterProduction(i);
        }
        updateWarehouse();
        updateStrongbox();
    }

    /**
     * Updates deployed leader cards, checking if they have production or storage power.
     */
    @Override
    public void updateActiveLeaderCards() {
        super.updateActiveLeaderCards();
        ObservableList<LeaderCard> cards = view.getModel().getBoard().getLeaderCards();
        for (int i = 0; i < cards.size(); i++) {
            if (isASpecialWarehousePower(cards.get(i).getID())) {
                leaderDepots.get(i).setDisable(false);
            }
        }
    }

    private boolean isASpecialWarehousePower(int ID) {
        return ID > 52 && ID < 57;
    }

    /**
     * Checks if the preliminary condition for a specific production are met
     *
     * @param ID production identifier
     */
    private boolean checkIfTheConditionsForTheProdAreMet(int ID) {
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
            case 4, 5 -> {
                return prodImageView.get(ID - 3).get(0).getImage() != null && prodImageView.get(ID - 3).get(1).getImage() != null;
            }
            case 0 -> {
                return prodImageView.get(ID).get(0).getImage() != null && prodImageView.get(ID).get(1).getImage() != null && prodImageView.get(ID).get(2).getImage() != null;
            }
        }
        return false;
    }

    /**
     * Discards the actual shown resource in the hand sending the message DISCARDRES.
     */
    @FXML
    void discardRes(ActionEvent event) {
        Image image = getHandListImg().get(getHand().getCurrentPageIndex()).getImage();
        String resToDiscard = GamePanel.imageResourceMap.get(image).name();
        view.process("discardres: " + resToDiscard);

    }

    /**
     * Handles the GUI update of the development places.
     */
    @Override
    public void updateDevPlace() {
        super.updateDevPlace();
        ArrayList<DevelopmentCard> cards = view.getModel().getBoard().getDevelopmentPlace().getTopCards();
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i) == null) continue;
            for (UtilityProductionAndCost cost : cards.get(i).getProduction().getCost()) {
                paymentLabels.get(i).get(cost.getResource()).setText(String.valueOf(cost.getQuantity()));
            }
        }
    }
}
