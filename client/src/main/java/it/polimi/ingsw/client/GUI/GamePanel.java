package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.ClientModel;
import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.model.*;
import it.polimi.ingsw.commonFiles.messages.toServer.DiscardLeader;
import it.polimi.ingsw.commonFiles.messages.toServer.UseMarket;
import it.polimi.ingsw.commonFiles.model.Resource;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
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
import java.util.concurrent.atomic.AtomicInteger;

public class GamePanel extends SceneHandler {

    int pos = 1;
    int posB = 1;

    /**
     * list of FXML item on the board
     */

    @FXML
    private Label out;
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
    @FXML
    private ComboBox devPosCombo;
    @FXML
    private ComboBox leadProd1;
    @FXML
    private ComboBox leadProd2;
    @FXML
    private ImageView resToGive;
    @FXML
    private ImageView resToGive1;
    @FXML
    private Button prodButton1;
    @FXML
    private Button prodButton2;
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
    @FXML
    private Pane pause;
    @FXML
    private Pane leftPane;
    @FXML
    private Button cardsButton;
    @FXML
    private Button discardButton;
    @FXML
    private Button deployLButton;
    @FXML
    private Pane paymentPane;
    @FXML
    private ImageView devCardToBuy;

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
    private ArrayList<ArrayList<ImageView>> devPlace;

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
        while (getModel().getLeaderHand() == null) try {
            this.wait();
        } catch (InterruptedException e) {
            System.exit(1);
        }
        setGui(App.getGui());
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
        leadProd1.getItems().addAll(
                imgCoin,
                imgSerf,
                imgShield,
                imgStone
        );
        leadProd2.getItems().addAll(
                imgCoin,
                imgSerf,
                imgShield,
                imgStone
        );
        devPosCombo.getItems().addAll(
                "1",
                "2",
                "3"
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
        getModel().getMarket().gridProperty().addListener(e -> setMarketPng());
        if (getModel().getMarket().getGrid() != null) setMarketPng();

        contextMenu = new ContextMenu();
        menuItem = new MenuItem("back to hand");
        contextMenu.getItems().add(menuItem);
        devPlace = new ArrayList<>(){{
            add(new ArrayList<>(Arrays.asList(devP11, devP12, devP13)));
            add(new ArrayList<>(Arrays.asList(devP21, devP22, devP23)));
            add(new ArrayList<>(Arrays.asList(devP31, devP32, devP33)));
        }};

        //prove

        cardsButton.fire();
        chooseCardX.setDisable(true);
        deployLButton.setDisable(true);
    }

    private ClientModel getModel() {
        return getGui().getView().getModel();
    }

    /**
     * prints in the bottom of the game a message passed as parameter
     * @param msg
     */
    public void printOut(String msg){
        out.setText(msg);
    }
    /**
     * activated when the connection is lost. Freeze the game and show "pause"
     */
    public void pause(){
        //if(connection problems...)
        goFront(pause);
        leftPane.setDisable(true);
        rightPane.setDisable(true);
    }

    /**
     * activated when the connection is back after pause
     */
    public void reconnect(){
        pause.toBack();
        leftPane.setDisable(false);
        rightPane.setDisable(false);
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
        devPlace.get(pos).get(lev).setImage(getCardPng(cardId));
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
     * @param cardID must be greater than 48
     */
    public void setActiveLeaderCard(int cardID){
        if(cardID>48){
            if(activeLeaderCard1.getImage()==null){
                activeLeaderCard1.setImage(getCardPng(cardID));
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
                activeLeaderCard2.setImage(getCardPng(cardID));
                //let the production drag&drop appear
                if(cardID > 60){
                    resToGive1.setDisable(false);
                    leadProd2.setDisable(false);
                    leadProd2.setOpacity(1);
                    prodButton2.setDisable(false);
                    prodButton2.setOpacity(1);
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


    /**
     * requires as a parameter the marble tray of the market and set the marketSpots
     * so the market is set with the relatives marbles Images
     */
    public void setMarketPng(){
        Marble[][] tray = getModel().getMarket().getGrid();
        Marble exMarble = getModel().getMarket().getExtraMarble();
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

        //drag 6 drop for warehouse store
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

        for(ImageView warSpot: resSpots){
            warSpot.setOnDragDropped(dragEvent -> {
                //TODO: needs to add the legitimacy of the moves in the spots of the warehouse store
                warSpot.setImage(dragEvent.getDragboard().getImage());
                fillHand(handListImg);
                dragEvent.consume();
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




    }

    /**
     * shows the Market pane on the click of the relative button
     * @param actionEvent
     */
    @FXML
    public void showMarket(ActionEvent actionEvent){
        goFront(marketPane);
        Market modelMarket = getGui().getView().getModel().getMarket();
        setMarketPng();

    }

    /**
     * shows the Dev Grid pane on the click of the relative button
     * @param event
     */
    @FXML
    void showDevGrid(ActionEvent event) {
        goFront(devGridPane);
        for(ImageView[] row: devCardSpots){
            for (ImageView card: row){
                card.setOnMouseClicked(event1 -> {
                    showPayment(card.getImage());
                });
            }
        }
        int k=1;
        DevelopmentGrid developmentGrid = getGui().getView().getModel().getDevelopmentGrid();

        for(int i=0; i<3; i++){
            for(int j=0; j<4; j++){
                devCardSpots[i][j].setImage(getCardPng(developmentGrid.getGrid()[i][j].getID()));
                k++;

            }
        }

    }

    private void showPayment(Image card) {
        goFront(paymentPane);
        devCardToBuy.setImage(card);
    }

    /**
     * shows the initial phase of the choose of the 2 leader cards
     * @param actionEvent
     */
    @FXML
    public void showChooseCards(ActionEvent actionEvent){
        //TODO: should be activated only in the initial phase and not with the button
        LeaderHand hand = getGui().getView().getModel().getLeaderHand();
        goFront(chooseCardPane);
        try {
            leaderHand.get(0).setImage(getCardPng(hand.getHand().get(0).getID()));
            leaderHand.get(1).setImage(getCardPng(hand.getHand().get(1).getID()));
            leaderHand.get(2).setImage(getCardPng(hand.getHand().get(2).getID()));
            leaderHand.get(3).setImage(getCardPng(hand.getHand().get(3).getID()));
        }catch(ArrayIndexOutOfBoundsException ignore){

        }
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
        colorAdjust.setSaturation(0.5);
        AtomicInteger discarded= new AtomicInteger();
        for(int i=0; i<4; i++){
            int finalI = i;
            leaderHand.get(i).setOnMouseClicked(event ->{
                leaderHand.get(finalI).setEffect(colorAdjust);
                discardButton.setOnAction(e->{
                    getGui().getView().getController().sendMessage(new DiscardLeader(finalI+1));
                    printOut("You have discarded card number "+(finalI+1));
                    leaderHand.get(finalI).setImage(null);
                    discarded.set(discarded.intValue()+1);
                    if(discarded.intValue()==2){
                        printOut("You are ready to play! ");
                        discardButton.setDisable(true);
                        chooseCardX.setDisable(false);
                        deployLButton.setDisable(false);
                    }
                    e.consume();
            });
                event.consume();
            });
        }
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
                marketSpot.setOnDragDropped(dragEvent ->{
                    if(dragEvent.getSource()==row0) {
                        getGui().getView().getController().sendMessage(new UseMarket('r', 1));
                    }else if(dragEvent.getSource()==row1){
                        getGui().getView().getController().sendMessage(new UseMarket('r', 2));
                    }
                    else if(dragEvent.getSource()==row2){
                        getGui().getView().getController().sendMessage(new UseMarket('r', 3));
                    }
                    else if(dragEvent.getSource()==col0){
                        getGui().getView().getController().sendMessage(new UseMarket('c', 1));
                    }
                    else if(dragEvent.getSource()==col1){
                        getGui().getView().getController().sendMessage(new UseMarket('c', 2));
                    }
                    else if(dragEvent.getSource()==col2){
                        getGui().getView().getController().sendMessage(new UseMarket('c', 3));
                    }
                    else if(dragEvent.getSource()==col3){
                        getGui().getView().getController().sendMessage(new UseMarket('c', 4));
                    }
                });
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
