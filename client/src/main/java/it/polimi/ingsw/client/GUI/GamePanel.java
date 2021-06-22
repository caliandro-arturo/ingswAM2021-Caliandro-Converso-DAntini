package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.ClientModel;
import it.polimi.ingsw.client.model.*;
import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class GamePanel extends SceneHandler {
    /**
     * list of FXML item on the board
     */
    @FXML
    private Label out;
    @FXML
    private BorderPane mainPane;
    @FXML
    private TabPane boardsTabPane;
    @FXML
    private ImageView boardImg;
    @FXML
    private Pane paneHand;
    @FXML
    private Button buy;
    @FXML
    private Button res;
    @FXML
    private Button marketBtn;
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
    private StackPane stackPane;
    @FXML
    private SplitPane rightPane;
    @FXML
    private ComboBox<String> devPosCombo;
    @FXML
    private Button cardsButton;
    @FXML
    private ImageView devCardToBuy;
    @FXML
    private Button backToGridBtn;
    @FXML
    private Button gridButton;
    @FXML
    private Label colorLabel;
    @FXML
    private Label levelLabel;
    @FXML
    private Pane chooseCardPane;
    @FXML
    private GridPane leadCardGrid;
    @FXML
    private Button discardButton;
    @FXML
    private Button deployLButton;
    @FXML
    private Button chooseCardX;
    @FXML
    private ImageView leadCard1;
    @FXML
    private ImageView leadCard2;
    @FXML
    private ImageView leadCard3;
    @FXML
    private ImageView leadCard4;
    @FXML
    private Pane pause;
    @FXML
    private Pane leftPane;
    @FXML
    private Pane paymentPane;
    @FXML
    private Pane devGridPane;
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
    private Pane marketPane;
    @FXML
    private Pane boardPane;
    @FXML
    private Label paymentCoin;
    @FXML
    private Label paymentSerf;
    @FXML
    private Label paymentShield;
    @FXML
    private Label paymentStone;

    @FXML
    private VBox vShield;
    @FXML
    private VBox vStone;
    @FXML
    private VBox vCoin;
    @FXML
    private VBox vSerf;
    @FXML
    private Button nextButton;
    @FXML
    private Button chooseButton;
    @FXML
    private Button backButton;
    @FXML
    private Pane chooseActionPane;

    private final Image blueMarble = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/blue_marble.png")));
    private final Image greyMarble = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/grey_marble.png")));
    private final Image purpleMarble = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/purple_marble.png")));
    private final Image redMarble = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/red_marble.png")));
    private final Image whiteMarble = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/white_marble.png")));
    private final Image yellowMarble = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/yellow_marble.png")));

    public static final Image imgCoin = new Image(Objects.requireNonNull(BoardController.class.getResourceAsStream("/png/coin.png")));
    public static final Image imgSerf = new Image(Objects.requireNonNull(BoardController.class.getResourceAsStream("/png/serf.png")));
    public static final Image imgShield = new Image(Objects.requireNonNull(BoardController.class.getResourceAsStream("/png/shield.png")));
    public static final Image imgStone = new Image(Objects.requireNonNull(BoardController.class.getResourceAsStream("/png/stone.png")));

    public static final ImageView imgViewCoin = new ImageView(imgCoin);
    public static final ImageView imgViewSerf = new ImageView(imgSerf);
    public static final ImageView imgViewShield = new ImageView(imgShield);
    public static final ImageView imgViewStone = new ImageView(imgStone);


    private String command;
    private PersonalBoardController personalBoardController;

    /**
     * data structures with the imageView for the images in the board
     * they represents the empty spots in the board
     */
    private ImageView[][] marketSpots;
    private List<ImageView> leaderHand;
    private final ObjectProperty<ImageView> selectedLeader = new SimpleObjectProperty<>();
    private ImageView[][] devCardSpots;
    private ArrayList<ImageView> marketReinsertSpots;
    private ArrayList<VBox> paymentSpots;

    /**
     * map for resource and marbles.
     * color of the marble match with the relative image
     * resource match with the relative image
     */
    private HashMap<Color, Image> colorImageMap;
    private HashMap<Resource, Image> resourceImageMap;
    private HashMap<Resource, Label> resourceLabelHashMap;
    private HashMap<Tab, BoardController> boardAndControllerMap = new HashMap<>();

    /**
     * initialization of the images and the data structure used to collect similar objects
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        setGui(App.getGui());
        marketReinsertSpots = new ArrayList<>(Arrays.asList(row0, row1, row2, col0, col1, col2, col3));
        marketSpots = new ImageView[][]{
                {mb00, mb10, mb20, mb30},
                {mb01, mb11, mb21, mb31},
                {mb02, mb12, mb22, mb32}
        };
        leaderHand = new ArrayList<>(Arrays.asList(leadCard1, leadCard2, leadCard3, leadCard4));
        paymentSpots = new ArrayList<>(Arrays.asList(vShield, vCoin, vSerf, vStone));
        devCardSpots = new ImageView[][]{
                {devcard00, devcard10, devcard20, devcard30},
                {devcard01, devcard11, devcard21, devcard31},
                {devcard02, devcard12, devcard22, devcard32}
        };
        resourceImageMap = new HashMap<>(){{
            put(Resource.SHIELD, imgShield);
            put(Resource.COIN, imgCoin);
            put(Resource.SERF, imgSerf);
            put(Resource.STONE, imgStone);
        }};
        colorImageMap = new HashMap<>(){{
            put(Color.BLUE, blueMarble);
            put(Color.GREY, greyMarble);
            put(Color.PURPLE, purpleMarble);
            put(Color.RED, redMarble);
            put(Color.WHITE, whiteMarble);
            put(Color.YELLOW, yellowMarble);
        }};
        imgViewCoin.setFitHeight(40);
        imgViewCoin.setFitWidth(40);
        imgViewSerf.setFitHeight(40);
        imgViewSerf.setFitWidth(40);
        imgViewShield.setFitHeight(40);
        imgViewShield.setFitWidth(40);
        imgViewStone.setFitHeight(40);
        imgViewStone.setFitWidth(40);

        devPosCombo.getItems().addAll("1", "2", "3");

        getModel().getMarket().gridProperty().addListener(e -> setMarketPng());
        getModel().getDevelopmentGrid().gridProperty().addListener(e-> setDevGridPng());
        getGui().getView().getModel().getLeaderHand().handProperty().addListener((InvalidationListener) e -> Platform.runLater(this::setLeaderCards));
        selectedLeader.addListener(e -> leaderButtonsProperty());
        if (getModel().getMarket().getGrid() != null) setMarketPng();
        if (getModel().getDevelopmentGrid().getGrid() != null) setDevGridPng();

        FXMLLoader personalBoardLoader = new FXMLLoader(getClass().getResource("/fxml/personalBoard.fxml"));
        AnchorPane board = null;
        try {
            board = personalBoardLoader.load();
        }catch (IOException e) {
            System.err.println("Error when trying to load the personal board.");
            System.exit(0);
        }
        Tab personalBoard = new Tab("Your board", board);
        boardAndControllerMap.put(personalBoard, personalBoardLoader.getController());
        personalBoardController = personalBoardLoader.getController();
        boardsTabPane.getSelectionModel().selectedItemProperty().addListener(e -> {
            leftPane.getChildren().clear();
            paneHand.getChildren().clear();
            leftPane.getChildren().add(boardAndControllerMap.get(boardsTabPane.getSelectionModel().getSelectedItem()).getLeftPane());
            paneHand.getChildren().add(boardAndControllerMap.get(boardsTabPane.getSelectionModel().getSelectedItem()).getResourceHand());
        });
       ((PersonalBoardController)boardAndControllerMap.get(personalBoard)).setView(getGui().getView());
        boardsTabPane.getSelectionModel().select(personalBoard);
        boardsTabPane.getTabs().add(personalBoard);
        getModel().boardsProperty().addListener(e -> setBoardsTabs());
        if (!getModel().getBoards().isEmpty()) setBoardsTabs();
        if (getGui().getView().getModel().getLeaderHand() != null &&
                getGui().getView().getModel().getLeaderHand().getHand().size() > 2) showChooseCards(null);
        else goFront(boardPane);
        backButton.setDisable(true);
        chooseButton.setDisable(true);
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

    private void setBoardsTabs() {
        getModel().getBoards().entrySet().stream()
                .filter(p -> boardsTabPane.getTabs().stream().noneMatch(t -> t.getText().equals(p.getKey())))
                .forEach(p -> {
                    if (p.getKey().equals(getModel().getPlayerUsername())) {
                        boardAndControllerMap.entrySet().stream()
                                .filter(e -> e.getKey().getText().equals("Your board"))
                                .findAny()
                                .get()
                                .getValue()
                                .setBoard(p.getValue());
                        return;
                    }
                    AnchorPane board = null;
                    FXMLLoader loader = null;
                    try {
                        loader = new FXMLLoader(getClass().getResource("/fxml/playersBoard.fxml"));
                        board = loader.load();
                    } catch (IOException e) {
                        System.err.println("Error when trying to load a player's board.");
                        System.exit(1);
                    }
                    Tab newBoard = new Tab(p.getKey(), board);
                    boardsTabPane.getTabs().add(newBoard);
                    boardAndControllerMap.put(newBoard, loader.getController());
                    ((BoardController) loader.getController()).setBoard(p.getValue());
                });
    }

    /**
     * requires as a parameter the development card grid and set the devCardSpots with the
     * relative card images
     */
    public void setDevGridPng(){
        DevelopmentCard[][] devGrid = getModel().getDevelopmentGrid().getGrid();
        for(int row=0; row<devGrid.length; row++){
            for(int col=0; col<devGrid[row].length; col++){
                devCardSpots[row][col].setImage(Utility.getCardPng(devGrid[row][col].getID()));
            }
        }
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
                marketSpots[2-row][3-col].setImage(colorImageMap.get(tray[row][col].getColor()));
            }
        }
        mbEx.setImage(colorImageMap.get(exMarble.getColor()));
    }


    public int getCardId(Image image){
        return Integer.parseInt(image.getUrl().substring(11,14));
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
        for(int row=0; row<devCardSpots.length; row++) {
            for (int col = 0; col < devCardSpots[row].length; col++) {
                int finalRow = row;
                int finalCol = col;
                devCardSpots[row][col].setOnMouseClicked(event1 -> {
                    showPayment(getModel().getDevelopmentGrid().getGrid()[finalRow][finalCol]);
                });
            }
        }
    }

    private void showPayment(DevelopmentCard devCard) {

        HashMap<Resource, Label> resourceLabelHashMap = new HashMap<>(){{
            put(Resource.COIN, paymentCoin);
            put(Resource.SERF, paymentSerf);
            put(Resource.SHIELD, paymentShield);
            put(Resource.STONE, paymentStone);
        }};
        StringBuilder cmd = new StringBuilder("buydevcard: ");
        cmd.append(devCard.getLevel() + ", " + devCard.getColor().name() + ", " + devPosCombo.getValue() + ", ");
        devCardToBuy.setImage(Utility.getCardPng(devCard.getID()));
        for(UtilityProductionAndCost cost: devCard.getCosts()){
            resourceLabelHashMap.get(cost.getResource()).setText(Integer.toString(cost.getQuantity()));
        }
        colorLabel.setText("Color: " + devCard.getColor().name());
        levelLabel.setText("Level: " + devCard.getLevel());
        for(VBox res: paymentSpots){
            res.setOnDragOver(dragEvent -> {
                dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                dragEvent.consume();
            });
        }
        ArrayList<Integer> storesStone = new ArrayList<>();
        ArrayList<Integer> storesShield = new ArrayList<>();
        ArrayList<Integer> storesSerf = new ArrayList<>();
        ArrayList<Integer> storesCoin = new ArrayList<>();

        for(VBox res: paymentSpots){
            res.setOnDragDropped(dragEvent -> {
                Resource resource = resourceImageMap.entrySet().stream()
                        .filter(entry->entry.getValue().equals(dragEvent.getDragboard().getImage()))
                        .findAny().get().getKey();
                String cost = resourceLabelHashMap.get(resource).getText();

                resourceLabelHashMap.get(resource).setText(Integer.toString(Integer.parseInt(cost)-1));

                switch (resource){
                    case COIN -> storesCoin.add(Integer.parseInt(dragEvent.getDragboard().getString()));
                    case SERF -> storesSerf.add(Integer.parseInt(dragEvent.getDragboard().getString()));
                    case SHIELD -> storesShield.add(Integer.parseInt(dragEvent.getDragboard().getString()));
                    case STONE -> storesStone.add(Integer.parseInt(dragEvent.getDragboard().getString()));
                }
            });
        }
        if(paymentCoin.getText().equals("0") && paymentStone.getText().equals("0") &&
                paymentShield.getText().equals("0") && paymentSerf.getText().equals("0")){
            for(UtilityProductionAndCost cost: devCard.getCosts()){
                switch (cost.getResource()){
                    case COIN -> {
                        for(Integer store: storesCoin){
                            cmd.append(store + " ");
                        }
                    }
                    case SERF -> {
                        for(Integer store: storesSerf){
                            cmd.append(store + " ");
                        }
                    }
                    case SHIELD -> {
                        for(Integer store: storesShield){
                            cmd.append(store + " ");
                        }
                    }
                    case STONE -> {
                        for(Integer store: storesStone){
                            cmd.append(store + " ");
                        }
                    }
                }
            }
            command = cmd.toString();
        }
        goFront(paymentPane);
    }

    /**
     * shows the initial phase of the choose of the 2 leader cards
     * @param actionEvent
     */
    @FXML
    public void showChooseCards(ActionEvent actionEvent){
        //TODO: should be activated only in the initial phase and not with the button
        setLeaderCards();
        goFront(chooseCardPane);
    }

    private void setLeaderCards() {
        LeaderHand hand = getGui().getView().getModel().getLeaderHand();
        if (selectedLeader.get() != null) {
            selectedLeader.get().setEffect(null);
            selectedLeader.set(null);
        }
        for (ImageView img : leaderHand) {
            img.setImage(null);
        }
        try {
            leaderHand.get(0).setImage(Utility.getCardPng(hand.getHand().get(0).getID()));
            leaderHand.get(1).setImage(Utility.getCardPng(hand.getHand().get(1).getID()));
            leaderHand.get(2).setImage(Utility.getCardPng(hand.getHand().get(2).getID()));
            leaderHand.get(3).setImage(Utility.getCardPng(hand.getHand().get(3).getID()));
        }catch(IndexOutOfBoundsException ignore){
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
        boardPane.toFront();
        if(!pane.equals(paymentPane)){
            boardPane.setDisable(true);
            boardPane.setEffect(new GaussianBlur());
        }else{
            boardPane.setDisable(false);
            boardPane.setEffect(null);
        }

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
        rightPane.setDisable(false);
    }


    @FXML
    public void buyResources(ActionEvent actionEvent) {
        //TODO: add here the message
    }

    @FXML
    public void buyCard(ActionEvent event){
        getGui().getView().process(command);
    }

    private void leaderButtonsProperty() {
        if (selectedLeader.get()==null) {
            discardButton.setDisable(true);
            deployLButton.setDisable(true);
        } else {
            discardButton.setDisable(false);
            deployLButton.setDisable(false);
        }
    }

    @FXML
    public void selectLeaderCard(MouseEvent e){
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(0.5);
        ImageView selectedCard = (ImageView)e.getSource();
        //if no leader was previously selected, the leader is selected.
        //if a leader was selected, remove the effect.
        //if the leader is reselected, remove the selection.
        if (selectedLeader.get() != null && selectedLeader.get() == selectedCard) {
            selectedCard.setEffect(null);
            selectedLeader.set(null);
            return;
        } else if (selectedLeader.get() != null) {
            selectedLeader.get().setEffect(null);
        }
        selectedLeader.set(selectedCard);
        selectedCard.setEffect(colorAdjust);
    }

    @FXML
    public void discardLeaderCard(ActionEvent actionEvent) {
        getGui().getView().discardLeader(new String[]{"", Integer.toString(leaderHand.indexOf(selectedLeader.get()) + 1)});
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
                AtomicReference<String> cmd = null;
                marketSpot.setOnDragDropped(dragEvent ->{
                    if(dragEvent.getSource()==row0) {
                        cmd.set("usemarket: r, 1");
                        getGui().getView().process(cmd.get());
                    }else if(dragEvent.getSource()==row1){
                        cmd.set("usemarket: r, 2");
                        getGui().getView().process(cmd.get());
                    }
                    else if(dragEvent.getSource()==row2){
                        cmd.set("usemarket: r, 3");
                        getGui().getView().process(cmd.get());
                    }
                    else if(dragEvent.getSource()==col0){
                        cmd.set("usemarket: c, 1");
                        getGui().getView().process(cmd.get());
                    }
                    else if(dragEvent.getSource()==col1){
                        cmd.set("usemarket: c, 2");
                        getGui().getView().process(cmd.get());
                    }
                    else if(dragEvent.getSource()==col2){
                        cmd.set("usemarket: c, 3");
                        getGui().getView().process(cmd.get());
                    }
                    else if(dragEvent.getSource()==col3){
                        cmd.set("usemarket: c, 4");
                        getGui().getView().process(cmd.get());
                    }
                });
            }
        }


    public void backToDevGrid(ActionEvent actionEvent) {
        //TODO refund cost need to be implemented
        paymentCoin.setText("0");
        paymentSerf.setText("0");
        paymentShield.setText("0");
        paymentStone.setText("0");
        goFront(devGridPane);
    }

    public void choose(ActionEvent actionEvent) {
        goFront(chooseActionPane);
    }

    public void next(ActionEvent actionEvent) {
        getGui().getView().process("next");
        if (getModel().getBoard().getResHand().isEmpty()) {
            if (getModel().getCurrentTurnPhase().equals("Choose the next action")) {
                chooseButton.setDisable(false);
            }
            personalBoardController.setProductionOff();
            backButton.setDisable(true);
        }
    }

    public void back(ActionEvent actionEvent) {
        getGui().getView().process("back");
        backButton.setDisable(true);
    }

    public void goToMarketPhase(ActionEvent actionEvent) {
        getGui().getView().process("choose: usemarket");
        backButton.setDisable(false);
        nextButton.setDisable(true);
        closePopup(actionEvent);
    }

    public void goToBuyDevCardPhase(ActionEvent actionEvent) {
        getGui().getView().process("choose: buydevelopmentcard");
        backButton.setDisable(false);
        nextButton.setDisable(true);
        closePopup(actionEvent);
    }

    public void goToProductionPhase(ActionEvent actionEvent) {
        getGui().getView().process("choose: activateproduction");
        backButton.setDisable(false);
        nextButton.setDisable(true);
        personalBoardController.setProductionOn();
        closePopup(actionEvent);
    }

}
