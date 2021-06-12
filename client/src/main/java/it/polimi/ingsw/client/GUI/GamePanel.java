package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.model.*;
import it.polimi.ingsw.commonFiles.messages.toServer.DiscardLeader;
import it.polimi.ingsw.commonFiles.messages.toServer.TakeRes;
import it.polimi.ingsw.commonFiles.model.Resource;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.*;

public class GamePanel extends SceneHandler {

    int pos = 1;
    int posB = 1;

    /**
     * list of FXML item on the board
     */
    @FXML
    private BorderPane mainPane;

    @FXML
    private Tab yourBoard;

    @FXML
    private Pane marketPane;

    @FXML
    private ImageView boardImg;

    @FXML
    private ImageView crossB;

    @FXML
    private ImageView cross;

    @FXML
    private Button right;

    @FXML
    private Button blackButton;

    @FXML
    private Button buy;

    @FXML
    private ImageView tile2;

    @FXML
    private ImageView tile3;

    @FXML
    private ImageView tile4;

    @FXML
    private Pagination hand;

    @FXML
    private Button res;

    @FXML
    private Button marketBtn;

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

    @FXML
    private GridPane marketTray;

    @FXML
    private ImageView mb00 ;

    @FXML
    private ImageView mb10 ;

    @FXML
    private ImageView mb20;

    @FXML
    private ImageView mb30;

    @FXML
    private ImageView mb01;

    @FXML
    private ImageView mb11;

    @FXML
    private ImageView mb21;

    @FXML
    private ImageView mb31;

    @FXML
    private ImageView mb02;

    @FXML
    private ImageView mb12;

    @FXML
    private ImageView mb22;

    @FXML
    private ImageView mb32;

    @FXML
    private ImageView row1;

    @FXML
    private ImageView row0;

    @FXML
    private ImageView row2;

    @FXML
    private ImageView col0;

    @FXML
    private ImageView col1;

    @FXML
    private ImageView col3;

    @FXML
    private ImageView col2;

    @FXML
    private ImageView mbEx;

    @FXML
    private Label infoBox;

    @FXML
    private TableView<String> tabVP;

    @FXML
    private TableColumn<String, String> columnCategory;

    @FXML
    private TableColumn<String, Integer> columnVP;

    @FXML
    private Pane chooseCardPane;

    @FXML
    private ImageView leadCard1;

    @FXML
    private ImageView leadCard2;

    @FXML
    private ImageView leadCard3;

    @FXML
    private ImageView leadCard4;

    @FXML
    private Pane devGridPane;

    @FXML
    private Pane boardPane;

    @FXML
    private StackPane stackPane;

    @FXML
    private Button buyCardButton;

    @FXML
    private ImageView devcard00;

    @FXML
    private ImageView devcard10;

    @FXML
    private ImageView devcard20;

    @FXML
    private ImageView devcard30;

    @FXML
    private ImageView devcard01;

    @FXML
    private ImageView devcard11;

    @FXML
    private ImageView devcard21;

    @FXML
    private ImageView devcard31;

    @FXML
    private ImageView devcard02;

    @FXML
    private ImageView devcard12;

    @FXML
    private ImageView devcard22;

    @FXML
    private ImageView devcard32;

    @FXML
    private Button handButton;

    @FXML
    private ImageView activeLeaderCard1;

    @FXML
    private ImageView activeLeaderCard2;

    @FXML
    private ImageView baseProd1;

    @FXML
    private ImageView baseProd2;

    @FXML
    private Label boxShield;

    @FXML
    private Label boxCoin;

    @FXML
    private Label boxSerf;

    @FXML
    private Label boxStone;

    @FXML
    private SplitPane rightPane;

    @FXML
    private Button chooseCardX;

    @FXML
    private ComboBox resBaseProd;

    private Image blueMarble;
    private Image greyMarble;
    private Image purpleMarble;
    private Image redMarble;
    private Image whiteMarble;
    private Image yellowMarble;
    private Image coin;
    private Image serf;
    private Image shield;
    private Image stone;
    private ContextMenu contextMenu;
    private MenuItem menuItem;


    private ObservableList<ImageView> handListImg;

    /**
     * data structures with the imageView for the images in the board
     * they represents the empty spots in the board
     */
    private ArrayList<ImageView> resSpots;
    private ImageView[][] marketSpots;
    private ObservableList<ImageView> leaderHand;
    private ImageView[][] devCardSpots;
    private ArrayList<ImageView> marketReinsertSpots;

    /**
     * map for resource and marbles.
     * color of the marble match with the relative image
     * resource match with the relative image
     */
    private HashMap<Color, Image> colorImageMap;
    private HashMap<Resource, Image> resourceImageMap;
    private HashMap<Resource, Label> resourceLabelHashMap;

    /**
     * initialization of the images and the data structure used to collect similar objects
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        blueMarble = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/blue_marble.png")));
        greyMarble = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/grey_marble.png")));
        purpleMarble = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/purple_marble.png")));
        redMarble = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/red_marble.png")));
        whiteMarble = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/white_marble.png")));
        yellowMarble = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/yellow_marble.png")));
        coin = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/coin.png")));
        serf = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/serf.png")));
        shield = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/shield.png")));
        stone = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/stone.png")));
        handListImg = FXCollections.observableArrayList();
        resSpots = new ArrayList<>(Arrays.asList(res1, res21, res22, res31, res32, res33));
        marketReinsertSpots = new ArrayList<>(Arrays.asList(row0, row1, row2, col0, col1, col2, col3));
        marketSpots = new ImageView[][]{
                {mb00, mb10, mb20, mb30},
                {mb01, mb11, mb21, mb31},
                {mb02, mb12, mb22, mb32}
        };
        leaderHand = FXCollections.observableArrayList(leadCard1, leadCard2, leadCard3, leadCard4);
        devCardSpots = new ImageView[][]{
                {devcard00, devcard10, devcard20, devcard30},
                {devcard01, devcard11, devcard21, devcard31},
                {devcard02, devcard12, devcard22, devcard32}
        };
        resourceImageMap = new HashMap<>(){{
            put(Resource.SHIELD, shield);
            put(Resource.COIN, coin);
            put(Resource.SERF,serf);
            put(Resource.STONE, stone);
        }};
        colorImageMap = new HashMap<>(){{
            put(Color.BLUE, blueMarble);
            put(Color.GREY, greyMarble);
            put(Color.PURPLE, purpleMarble);
            put(Color.RED, redMarble);
            put(Color.WHITE, whiteMarble);
            put(Color.YELLOW, yellowMarble);
        }};
        resourceLabelHashMap = new HashMap<>(){{
            put(Resource.SERF, boxSerf);
            put(Resource.COIN, boxCoin);
            put(Resource.SHIELD, boxShield);
            put(Resource.STONE, boxStone);
        }};
        ImageView imgCoin = new ImageView(coin);
        imgCoin.setFitHeight(40);
        imgCoin.setFitWidth(40);
        ImageView imgSerf = new ImageView(serf);
        imgSerf.setFitHeight(40);
        imgSerf.setFitWidth(40);
        ImageView imgShield = new ImageView(shield);
        imgShield.setFitHeight(40);
        imgShield.setFitWidth(40);
        ImageView imgStone = new ImageView(stone);
        imgStone.setFitHeight(40);
        imgStone.setFitWidth(40);
        resBaseProd.getItems().addAll(
                imgCoin,
                imgSerf,
                imgShield,
                imgStone
        );
        contextMenu = new ContextMenu();
        menuItem = new MenuItem("back to hand");
        contextMenu.getItems().add(menuItem);



        //prove
        goFront(chooseCardPane);
        chooseCardX.setDisable(true);
        chooseCardX.setOpacity(0);
    }


    /**
     * takes the leader cards from the model and print the relative images
     */
    public void addLeaderCards(LeaderHand hand){
        for(int i = 0; i<4; i++){
            leaderHand.get(i).setImage(getCardPng(hand.getHand().get(i).getID()));
        }
    }
    /**
     * is called by the production to add resources in the strongbox
     * @param num: the number of resources to add
     * @param resource: the type of resources to add
     */
    public void addBoxResource(int num, Resource resource){
        int quantity = Integer.parseInt(resourceLabelHashMap.get(resource).getText());
        quantity+=num;
        resourceLabelHashMap.get(resource).setText((Integer.toString(quantity)));
    }

    /**
     * remove a specific resource from the strongbox
     * @param resource
     */
    public void removeBoxResources(Resource resource){
        int quantity = Integer.parseInt(resourceLabelHashMap.get(resource).getText());
        int compare = quantity;
        if(--compare>0){
            quantity--;
            resourceLabelHashMap.get(resource).setText((Integer.toString(quantity)));
        } //TODO: error message
    }
    /**
     * requires as a parameter the development card grid and set the devCardSpots with the
     * relative card images
     * @param devGrid
     */
    public void setDevGridPng(DevelopmentCard[][] devGrid){
        for(int row=0; row<devGrid.length; row++){
            for(int col=0; col<devGrid[row].length; col++){
                devCardSpots[row][col].setImage(getCardPng(devGrid[row][col].getID()));
            }
        }
    }

    /**
     * requires as parameter the handlist and set the handList of images of resources
     * @param hand
     */
    public void setHandList(ArrayList<Resource> hand){
        for(Resource res: hand){
            handListImg.add(new ImageView(resourceImageMap.get(res)));
        }
    }

    /**
     * setter for the active leader card images
     * @param cardID
     */
    public void setActiveLeaderCard(int cardID){
        if(activeLeaderCard1.getImage()==null){
            activeLeaderCard1.setImage(getCardPng(cardID));
        }
        else if(activeLeaderCard2.getImage()== null){
            activeLeaderCard2.setImage(getCardPng(cardID));
        }
        //TODO : else error message FULL ACTIVE LEADER CARDS
    }
    /**
     * requires as a parameter the marble tray of the market and set the marketSpots
     * so the market is set with the relatives marbles Images
     * @param tray : the Marble tray of the market and the extra marble
     */
    public void setMarketPng(Marble[][] tray, Marble exMarble){
        for(int row=0; row< tray.length; row++){
            for(int col=0; col< tray[row].length; col++){
                marketSpots[row][col].setImage(colorImageMap.get(tray[row][col].getColor()));
            }
        }
        mbEx.setImage(colorImageMap.get(exMarble.getColor()));
    }

    /**
     * can be used to get the card image for giving the cardId as a parameter
     * @param cardId
     * @return
     */
    public Image getCardPng(int cardId){
        return new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/cards/"+cardId+".png")));
    }

    public int getCardId(Image image){
        return Integer.parseInt(image.getUrl().substring(11,14));
    }

    /**
     * increase the position of the cross in the GUI
     * @param event
     */
    @FXML
    void increasePos(ActionEvent event) {
        //TODO: needs to be activated NOT with a button, but updated with a faith resource
        int posi=0;
        ImageView newCross = null;
        if(event.getSource() == blackButton){
            ++posB;
            if(posB<=2)
                moveRight(crossB);
            posi= posB;
            newCross = crossB;
        }else if(event.getSource() == right){
            ++pos;
            posi = pos;
            newCross = cross;
        }
        checkTile(posi);
        if (posi<=2)
            moveRight(newCross);
        else if(posi<5)
            moveUp(newCross);
        else if(posi<10)
            moveRight(newCross);
        else if(posi<12)
            moveDown(newCross);
        else if(posi<17)
            moveRight(newCross);
        else if(posi<19)
            moveUp(newCross);
        else if(posi<25)
            moveRight(newCross);

    }

    /**
     * check the position and set the relative tile visiblle
     * @param pos
     */
    public void checkTile(int pos){
        if(pos == 9)
            tile2.setOpacity(100);
        else if (pos == 17)
            tile3.setOpacity(100);
        else if (pos== 24)
            tile4.setOpacity(100);
    }

    /**
     * utility methods to move the cross in the faith track
     * @param img
     */
    public void moveUp(ImageView img){
        img.setLayoutY(img.getLayoutY()-46);
    }
    public void moveRight(ImageView img){
        img.setLayoutX(img.getLayoutX()+46);
    }
    public void moveDown(ImageView img){
        img.setLayoutY(img.getLayoutY()+46);
    }


    @FXML
    public void slideRes(ActionEvent event){
        //TODO: needs to be activated NOT with a button, but in the end of each market usage
        handListImg.add(new ImageView(resourceImageMap.get(Resource.COIN)));

        if(!handListImg.isEmpty()){
            fillHand(handListImg);
        }
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
            hand.setPageFactory(handList::get);
        }else
            hand.setPageFactory(null);
        hand.setPageCount(handList.size());
    }

    /**
     * methods implementing the drag and drop from handListImg to the spots of the warehouse
     */
    @FXML
    public void moveRes(){
        hand.setOnDragDetected(event1 -> {
            Dragboard db = hand.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            Image res = handListImg.get(hand.getCurrentPageIndex()).getImage();
            content.putImage(res);
            db.setContent(content);
            handListImg.remove(hand.getCurrentPageIndex());
            event1.consume();
        });

        for(ImageView warSpot: resSpots){
            warSpot.setOnDragOver(dragEvent -> {
                dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                dragEvent.consume();
            });
        }

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

        for(ImageView warSpot: resSpots){
            warSpot.setOnDragDropped(dragEvent -> {
                //TODO: needs to add the legitimacy of the moves in the spots of the warehouse store
                warSpot.setImage(dragEvent.getDragboard().getImage());
                fillHand(handListImg);
                dragEvent.consume();
            });
        }
    }

    /**
     * shows the Market pane on the click of the relative button
     * @param actionEvent
     */
    @FXML
    public void showMarket(ActionEvent actionEvent){
        goFront(marketPane);

        //temporary initialization of the marbles in the market
        mb00.setImage(yellowMarble);
        mb10.setImage(redMarble);
        mb20.setImage(yellowMarble);
        mb30.setImage(redMarble);
        mb01.setImage(blueMarble);
        mb11.setImage(greyMarble);
        mb21.setImage(yellowMarble);
        mb31.setImage(whiteMarble);
        mb02.setImage(yellowMarble);
        mb12.setImage(purpleMarble);
        mb22.setImage(yellowMarble);
        mb32.setImage(purpleMarble);
        mbEx.setImage(whiteMarble);
    }

    /**
     * shows the Dev Grid pane on the click of the relative button
     * @param event
     */
    @FXML
    void showDevGrid(ActionEvent event) {
        goFront(devGridPane);
        int k=1;
        for(int i=0; i<3; i++){
            for(int j=0; j<4; j++){
                devCardSpots[i][j].setImage(getCardPng(k));
                k++;

            }
        }

    }

    /**
     * shows the initial phase of the choose of the 2 leader cards
     * @param actionEvent
     */
    @FXML
    public void showChooseCards(ActionEvent actionEvent){
        //TODO: should be activated only in the initial phase and not with the button
        goFront(chooseCardPane);
        leaderHand.get(0).setImage(getCardPng(61));
        leaderHand.get(1).setImage(getCardPng(62));
        leaderHand.get(2).setImage(getCardPng(63));

        leaderHand.get(3).setImage(getCardPng(64));


    }

    /**
     * utility method to show in the front the pane gave as a parameter
     * and disable the other panes in the back
     * @param pane
     */
    public void goFront(Pane pane){
        for(int i=0; i<stackPane.getChildren().size(); i++){
            if(!stackPane.getChildren().get(i).equals(pane)){
                stackPane.getChildren().get(i).toBack();
                stackPane.getChildren().get(i).setOpacity(0);
                stackPane.getChildren().get(i).setDisable(true);
            }
        }
        rightPane.setDisable(true);
        handButton.setDisable(true);
        boardPane.toFront();
        boardPane.setDisable(true);
        boardPane.setEffect(new GaussianBlur());
        pane.toFront();
        pane.setOpacity(1);
        pane.setDisable(false);

    }

    /**
     * utility method to close the pane and bring to front the Board pane again
     */
    public void closePopup(ActionEvent actionEvent){
        boardPane.toFront();
        boardPane.setOpacity(1);
        boardPane.setDisable(false);
        boardPane.setEffect(null);
        handButton.setDisable(false);
        rightPane.setDisable(false);
    }


    @FXML
    public void buyResources(ActionEvent actionEvent) {
        //TODO: add here the message
    }

    @FXML
    public void buyCard(ActionEvent event){
        //TODO: add here the message
    }

    @FXML
    public void discardLeaderCard(){
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(1);

        for(int i=0; i<4; i++){
            int finalI = i;
            leaderHand.get(i).setOnMouseClicked(event ->{
                leaderHand.get(finalI).setEffect(colorAdjust);
              event.consume(); });
        }
        getGui().getView().getController().sendMessage(new DiscardLeader(pos));
    }
    @FXML
    public void chooseCard(ActionEvent actionEvent){
        closePopup(actionEvent);
        //TODO: add here the message
    }

    /**
     * methods implementing the drag and drop in the reinsert phase of the extra marble
     */
    @FXML
    public void moveMarble() {
                mbEx.setOnDragDetected(event -> {
                    Dragboard db = mbEx.startDragAndDrop(TransferMode.ANY);
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(mbEx.getImage());
                    db.setContent(content);
                    event.consume();
                });

            for (ImageView marketSpot : marketReinsertSpots) {
                marketSpot.setOnDragOver(dragEvent -> {
                    dragEvent.acceptTransferModes(TransferMode.MOVE);
                    dragEvent.consume();
                });
            }
            for (ImageView marketSpot : marketReinsertSpots) {
                //TODO: insert here the call to the function for reinsert the extra marble with col/row , int
                marketSpot.setOnDragDropped(dragEvent -> marketSpot.setImage(dragEvent.getDragboard().getImage()));
            }
        }

        @FXML
    public void ctxMenuRes(ContextMenuEvent contextMenuEvent){
            for(ImageView img: resSpots){
                if(img.getImage()!= null){
                    img.setOnContextMenuRequested(e->
                            contextMenu.show(img, e.getScreenX(), e.getScreenY()));
                    int posRes;
                    if(img==res1){
                        posRes = 1;
                    }
                    else if(img==res21 || img == res22)
                        posRes = 2;
                    else
                        posRes = 3;
                    menuItem.setOnAction(event->{
                        img.setImage(null);
                        //getGui().getView().getController().sendMessage(new TakeRes(posRes));
                         });
                }
            }
        }
}
