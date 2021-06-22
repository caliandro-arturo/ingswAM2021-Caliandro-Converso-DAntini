package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.model.Board;
import it.polimi.ingsw.client.model.Utility;
import it.polimi.ingsw.commonFiles.model.Resource;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
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
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

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
    private Pane leftPane;
    @FXML
    private ImageView activeLeaderCard1;
    @FXML
    private ImageView activeLeaderCard2;

    @FXML
    private Pagination hand;

    @FXML
    private AnchorPane resourceHand;

    @FXML
    private GridPane leaderDepot1;
    @FXML
    private GridPane leaderDepot2;

    private ContextMenu contextMenu;

    private MenuItem menuItem;
    /**
     * strongbox
     */
    private ArrayList<ImageView> strongResources;
    private final ObservableList<ImageView> handListImg = FXCollections.observableArrayList();;
    private final ArrayList<ImageView> resSpots = new ArrayList<>();
    private ArrayList<ArrayList<ImageView>> devPlace;
    private HashMap<Resource, Image> resourceImageMap;
    private HashMap<ImageView, Label> strongMap ;
    private HashMap<ImageView, Resource> resourceImageViewHashMap;

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
        resSpots.addAll(Arrays.asList(res1, res21, res22, res31, res32, res33));
        devPlace = new ArrayList<>(){{
            add(new ArrayList<>(Arrays.asList(devP11, devP12, devP13)));
            add(new ArrayList<>(Arrays.asList(devP21, devP22, devP23)));
            add(new ArrayList<>(Arrays.asList(devP31, devP32, devP33)));
        }};
        resourceImageMap = new HashMap<>(){{
            put(Resource.SHIELD, GamePanel.imgShield);
            put(Resource.COIN, GamePanel.imgCoin);
            put(Resource.SERF, GamePanel.imgSerf);
            put(Resource.STONE, GamePanel.imgStone);
        }};
        resourceImageViewHashMap = new HashMap<>(){{
            put(GamePanel.imgViewShield,Resource.SHIELD);
            put(GamePanel.imgViewCoin, Resource.COIN);
            put(GamePanel.imgViewSerf, Resource.SERF);
            put(GamePanel.imgViewStone,Resource.STONE);
        }};
        hand.setPageFactory((index) -> {
            ImageView view = null;
            try {
                view = handListImg.get(index);
                view.setFitHeight(60);
                view.setFitWidth(60);
            } catch (IndexOutOfBoundsException ignore) {
            }
            return view;
        });
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
    public HashMap<Resource, Image> getResourceImageMap() {
        return resourceImageMap;
    }
    public HashMap<Resource, Label> getResourceLabelHashMap() {
        return resourceLabelHashMap;
    }
    public ObservableList<ImageView> getHandListImg() {
        return handListImg;
    }
    public HashMap<ImageView, Resource> getResourceImageViewHashMap(){
        return resourceImageViewHashMap;
    }

    public void setBoard(Board board) {
        this.board = board;
        for (int i = 1; i < board.getFaithTrack().getPosition(); i++)
            increasePos();
        board.getFaithTrack().positionProperty().addListener(e -> increasePos());
        updateHandList();
        board.getResHand().getResources().addListener((InvalidationListener) e -> Platform.runLater(this::updateHandList));
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
    public Pane getLeftPane() {
        return leftPane;
    }

    public GridPane getLeaderDepot1() {
        return leaderDepot1;
    }

    public GridPane getLeaderDepot2() {
        return leaderDepot2;
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

    public Pane getResourceHand(){
        return resourceHand;
    }

    public Pagination getHand() {
        return hand;
    }

    /**
     * Updates the resource hand pagination
     */
    public void updateHandList(){
        handListImg.clear();
        for(Resource res: board.getResHand().getResources()){
            handListImg.add(new ImageView(resourceImageMap.get(res)));
        }
        hand.setPageCount(handListImg.size());
    }
}
