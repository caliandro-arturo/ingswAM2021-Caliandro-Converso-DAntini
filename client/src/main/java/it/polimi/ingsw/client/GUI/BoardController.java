package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.model.*;
import it.polimi.ingsw.commonFiles.model.Resource;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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

    /**
     * strongbox
     */
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
    private ImageView activeLeaderCard1;
    @FXML
    private ImageView activeLeaderCard2;
    @FXML
    private ImageView specialDepot11;
    @FXML
    private ImageView specialDepot12;
    @FXML
    private ImageView specialDepot21;
    @FXML
    private ImageView specialDepot22;
    @FXML
    private Pagination hand;

    @FXML
    private AnchorPane resourceHand;



    @FXML
    private AnchorPane leaderCard1;
    @FXML
    private AnchorPane leaderCard2;
    @FXML
    private HBox leaderDepot1;
    @FXML
    private HBox leaderDepot2;

    private ContextMenu contextMenu;

    private MenuItem menuItem;
    /**
     * strongbox
     */
    private ArrayList<ImageView> strongResources;
    private final ObservableList<ImageView> handListImg = FXCollections.observableArrayList();
    private final ArrayList<ImageView> resSpots = new ArrayList<>();
    private ArrayList<ImageView> activeLeaderCards;
    private ArrayList<ArrayList<ImageView>> devPlace;
    private HashMap<ImageView, Label> strongMap ;
    private ArrayList<ArrayList<ImageView>> storesList;

    public BoardController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        strongMap = new HashMap<>(){{
            put(strongSerf, boxSerf);
            put(strongCoin, boxCoin);
            put(strongShield, boxShield);
            put(strongStone, boxStone);
        }};
        resourceLabelHashMap = new HashMap<>(){{
            put(Resource.SERF, boxSerf);
            put(Resource.COIN, boxCoin);
            put(Resource.SHIELD, boxShield);
            put(Resource.STONE, boxStone);
        }};
        strongResources = new ArrayList<>(Arrays.asList(strongCoin, strongSerf, strongShield, strongStone));
        resSpots.addAll(Arrays.asList(res1, res21, res22, res31, res32, res33, specialDepot11, specialDepot12, specialDepot21, specialDepot22));
        storesList = new ArrayList<>(){{
            add(new ArrayList<>(Arrays.asList(res1)));
            add(new ArrayList<>(Arrays.asList(res21,res22)));
            add(new ArrayList<>(Arrays.asList(res31,res32,res33)));
            add(new ArrayList<>(Arrays.asList(specialDepot11,specialDepot12)));
            add(new ArrayList<>(Arrays.asList(specialDepot21,specialDepot22)));
        }};
        devPlace = new ArrayList<>(){{
            add(new ArrayList<>(Arrays.asList(devP11, devP12, devP13)));
            add(new ArrayList<>(Arrays.asList(devP21, devP22, devP23)));
            add(new ArrayList<>(Arrays.asList(devP31, devP32, devP33)));
        }};
        activeLeaderCards = new ArrayList<>(){{
            add(activeLeaderCard1);
            add(activeLeaderCard2);
        }};
        leaderDepot1.setDisable(true);
        leaderDepot2.setDisable(true);
        contextMenu = new ContextMenu();
        menuItem = new MenuItem("back to hand");
        contextMenu.getItems().add(menuItem);
    }
    public MenuItem getMenuItem() {
        return menuItem;
    }
    public ContextMenu getContextMenu() {
        return contextMenu;
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
    public ObservableList<ImageView> getHandListImg() {
        return handListImg;
    }
    public ArrayList<ArrayList<ImageView>> getStoresList() {
        return storesList;
    }

    public void setBoard(Board board) {
        this.board = board;
        if (board.getFaithTrack().getPosition() != 0) {
            for (int i = 0; i < board.getFaithTrack().getPosition(); i++)
                increasePos(cross, i + 1);
        }
        board.getFaithTrack().positionProperty().addListener(e -> increasePos());
        board.getResHand().getResources().addListener((ListChangeListener<? super Resource>) e -> {
            if (e.next())
                Platform.runLater(() -> updateHandList(e));
        });
        board.getLeaderCards().addListener((InvalidationListener) e -> Platform.runLater(this::updateActiveLeaderCards));
        board.getWarehouseStore().getRes().addListener((ListChangeListener<? super ObservableList<Resource>>)  newValue -> {
            if (newValue.next()) {
                if (newValue.wasAdded()) {
                    newValue.getAddedSubList().forEach(list -> list.addListener((InvalidationListener) e -> Platform.runLater(this::updateWarehouse)));
                }
            }
        });
        for(ObservableList<Resource> slot: board.getWarehouseStore().getRes()){
            slot.addListener((InvalidationListener) e-> Platform.runLater(this::updateWarehouse));
        }
        for (ObservableList<DevelopmentCard> cards: board.getDevelopmentPlace().getDevStack()){
            cards.addListener((InvalidationListener) e -> Platform.runLater(this::updateDevPlace));
        }
        board.getStrongboxObject().addListener((InvalidationListener) e -> Platform.runLater(this::updateStrongbox));
        updateHandList(new ListChangeListener.Change<>(board.getResHand().getResources()) {
            @Override
            public boolean next() {
                return false;
            }

            @Override
            public void reset() {
            }

            @Override
            public int getFrom() {
                return 0;
            }

            @Override
            public int getTo() {
                return 0;
            }

            @Override
            public List<Resource> getRemoved() {
                return null;
            }

            @Override
            public boolean wasAdded() {
                return true;
            }

            @Override
            public List<Resource> getAddedSubList() {
                return getList();
            }

            @Override
            protected int[] getPermutation() {
                return new int[0];
            }
        });
        updateStrongbox();
        updateWarehouse();
        updateDevPlace();
    }

    public void setImage(){
        strongCoin.setImage(GamePanel.imgCoin);
        strongSerf.setImage(GamePanel.imgSerf);
        strongStone.setImage(GamePanel.imgStone);
        strongShield.setImage(GamePanel.imgShield);
    }

    public ImageView getRes1() {
        return res1;
    }

    public ImageView getRes33() {
        return res33;
    }

    public ImageView getRes32() {
        return res32;
    }

    public ImageView getRes31() {
        return res31;
    }

    public ImageView getRes22() {
        return res22;
    }

    public ImageView getRes21() {
        return res21;
    }
    public HashMap<ImageView, Label> getStrongMap() {
        return strongMap;
    }

    public Board getBoard() {
        return board;
    }
    public AnchorPane getLeaderCard1() {
        return leaderCard1;
    }

    public AnchorPane getLeaderCard2() {
        return leaderCard2;
    }
    public HBox getLeaderDepot1() {
        return leaderDepot1;
    }
    public HBox getLeaderDepot2() {
        return leaderDepot2;
    }
    public AnchorPane getResourceHand(){
        return resourceHand;
    }
    public Pagination getHand() {
        return hand;
    }

    public Label getBoxSerf() {
        return boxSerf;
    }

    public Label getBoxShield() {
        return boxShield;
    }

    public Label getBoxStone() {
        return boxStone;
    }

    public Label getBoxCoin() {
        return boxCoin;
    }

    public ImageView getStrongCoin() {
        return strongCoin;
    }

    public ImageView getStrongStone() {
        return strongStone;
    }

    public ImageView getStrongShield() {
        return strongShield;
    }

    public ImageView getStrongSerf() {
        return strongSerf;
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
     * @param position the initial position to increment
     */
    @FXML
    void increasePos(ImageView cross, int position) {
        int oldPosition = position - 1;
        checkTile(oldPosition);
        if (oldPosition<2)
            moveRight(cross);
        else if(oldPosition<4)
            moveUp(cross);
        else if(oldPosition<9)
            moveRight(cross);
        else if(oldPosition<11)
            moveDown(cross);
        else if(oldPosition<16)
            moveRight(cross);
        else if(oldPosition<18)
            moveUp(cross);
        else if(oldPosition<24)
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


    /**
     * Updates the resource hand pagination
     */
    public void updateHandList(ListChangeListener.Change<? extends Resource> e) {
        if (e.wasAdded())
            for (Resource res :
                    e.getAddedSubList()) {
                handListImg.add(new ImageView(GamePanel.resourceImageMap.get(res)));
            }
        else if (e.wasRemoved()) {
            for (Resource res :
                    e.getRemoved()) {
                int currentSelectedResInHandIndex = hand.getCurrentPageIndex();
                ImageView currentResource = handListImg.get(currentSelectedResInHandIndex);
                Image resourceToRemove = GamePanel.resourceImageMap.get(res);
                if (currentResource.getImage().equals(resourceToRemove))
                    handListImg.remove(currentResource);
                else {
                    handListImg.stream()
                            .filter(i -> i.getImage().equals(resourceToRemove))
                            .findFirst()
                            .ifPresent(handListImg::remove);
                }
            }
        }
        if (handListImg.isEmpty()) {
            resourceHand.setOpacity(0);
            resourceHand.setDisable(true);
        } else {
            if (resourceHand.isDisable()) {
                hand.setPageFactory(index -> {
                    ImageView view = null;
                    try {
                        view = handListImg.get(index);
                        view.setFitHeight(60);
                        view.setFitWidth(60);
                    } catch (IndexOutOfBoundsException ignore) {
                    }
                    return view;
                });
            }
            hand.setCurrentPageIndex(hand.getCurrentPageIndex() < hand.getPageCount() ? hand.getCurrentPageIndex() : hand.getCurrentPageIndex() - 1);
            hand.setPageCount(handListImg.size());
            resourceHand.setOpacity(1);
            resourceHand.setDisable(false);
        }
    }

    /**
     * Updates the active leader cards
     */
    public void updateActiveLeaderCards(){
        ObservableList<LeaderCard> cards = board.getLeaderCards();
        for (int i = 0; i < cards.size(); i++) {
            activeLeaderCards.get(i).setImage(new Image(Objects.requireNonNull(getClass().
                    getResourceAsStream("/png/cards/" + cards.get(i).getID() + ".png"))));
        }
    }

    /**
     * reads the model warehouse and updates the relative images
     */
    public void updateWarehouse(){
        for (ArrayList<ImageView> imageViews: storesList){
            WarehouseStore store = board.getWarehouseStore();
            for (ImageView slot : imageViews) {
                if (slot != null) {
                    try {
                        slot.setImage(GamePanel.resourceImageMap.get(store.getSpecificStore(storesList.indexOf(imageViews)).
                                get(imageViews.indexOf(slot))));
                    } catch (IndexOutOfBoundsException e) {
                        slot.setImage(null);
                    }
                }
            }
        }
    }

    public void updateStrongbox(){
        Strongbox strongbox = board.getStrongbox();
        for (Map.Entry<Resource,Label> entry: resourceLabelHashMap.entrySet()){
            entry.getValue().setText(String.valueOf(strongbox.getResourcesQuantity(entry.getKey())));
        }
    }

    public void updateDevPlace(){
        ArrayList<ObservableList<DevelopmentCard>> devPlaces = board.getDevelopmentPlace().getDevStack();
        for (int i = 0; i < devPlaces.size(); i++) {
            for (int j = 0; j < devPlaces.get(i).size(); j++)
                devPlace.get(i).get(j).setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/cards/" + devPlaces.get(i).get(j).getID() + ".png"))));
        }
    }
}
