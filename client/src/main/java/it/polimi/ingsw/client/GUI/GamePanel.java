package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.ClientModel;
import it.polimi.ingsw.client.model.*;
import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableIntegerValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
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
    private AnchorPane paneHand;
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
    private Pane rightPane;
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
    private Pane getInitialResourcesPane;
    @FXML
    private Label initialResourceIndex;
    @FXML
    private Label totalInitialResourcesAmount;
    @FXML
    private Button initialResourcesButton;

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

    @FXML
    private Pane soloActionPane;
    @FXML
    private Label soloActionOut;
    @FXML
    private ImageView soloToken;
    @FXML
    private Button soloActionOkButton;

    @FXML
    private Pane phaseAnnouncementPane;
    @FXML
    private Label turnPhaseAnnouncementLabel;
    @FXML
    private Pane announcementMaskPane;

    @FXML
    private Label playerTurnLabel;
    @FXML
    private Label turnPhaseLabel;

    private Pane currentPane;


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
    public static final HashMap<Image,Resource> imageResourceMap = new HashMap<>(){{
        put(GamePanel.imgCoin,Resource.COIN);
        put(GamePanel.imgSerf,Resource.SERF);
        put(GamePanel.imgShield,Resource.SHIELD);
        put(GamePanel.imgStone,Resource.STONE);
    }};
    public static final  HashMap<Resource,Image>resourceImageMap = new HashMap<>(){{
        put(Resource.SHIELD, GamePanel.imgShield);
        put(Resource.COIN, GamePanel.imgCoin);
        put(Resource.SERF, GamePanel.imgSerf);
        put(Resource.STONE, GamePanel.imgStone);
    }};

    public static final ImageView imgViewCoin = new ImageView(imgCoin);
    public static final ImageView imgViewSerf = new ImageView(imgSerf);
    public static final ImageView imgViewShield = new ImageView(imgShield);
    public static final ImageView imgViewStone = new ImageView(imgStone);
    public static final HashMap<ImageView,Resource> imageViewResourceMap = new HashMap<>(){{
        put(imgViewCoin,Resource.COIN);
        put(imgViewSerf,Resource.SERF);
        put(imgViewShield,Resource.SHIELD);
        put(imgViewStone,Resource.STONE);
    }};

    public static final Image soloActionBack = new Image(Objects.requireNonNull(BoardController.class.getResourceAsStream("/png/soloTokens/back.png")));
    public static final Image delBlue = new Image(Objects.requireNonNull(BoardController.class.getResourceAsStream("/png/soloTokens/delblue.png")));
    public static final Image delYellow = new Image(Objects.requireNonNull(BoardController.class.getResourceAsStream("/png/soloTokens/delyellow.png")));
    public static final Image delPurple = new Image(Objects.requireNonNull(BoardController.class.getResourceAsStream("/png/soloTokens/delpurple.png")));
    public static final Image delGreen = new Image(Objects.requireNonNull(BoardController.class.getResourceAsStream("/png/soloTokens/delgreen.png")));
    public static final Image twoPositions = new Image(Objects.requireNonNull(BoardController.class.getResourceAsStream("/png/soloTokens/twopositions.png")));
    public static final Image onePositionReset = new Image(Objects.requireNonNull(BoardController.class.getResourceAsStream("/png/soloTokens/onepositionreset.png")));

    private final HashMap<String, Image> soloActionMap = new HashMap<>(){{
        put("delblue", delBlue);
        put("delyellow", delYellow);
        put("delpurple", delPurple);
        put("delgreen", delGreen);
        put("twopositions", twoPositions);
        put("onepositionreset", onePositionReset);
    }};

    private String command;
    private PersonalBoardController personalBoardController;

    /**
     * data structures with the imageView for the images in the board
     * they represents the empty spots in the board
     */
    private ImageView[][] marketSpots;
    private List<ImageView> leaderHand;
    private final ObjectProperty<ImageView> selectedLeader = new SimpleObjectProperty<>();
    private DevelopmentCard selectedDevCard;
    private ImageView[][] devCardSpots;
    private ArrayList<VBox> paymentSpots;
    private HashMap<ImageView, String> reinsertMap;

    /**
     * map for resource and marbles.
     * color of the marble match with the relative image
     * resource match with the relative image
     */
    private HashMap<Color, Image> colorImageMap;
    private HashMap<Resource, Label> resourceLabelHashMap;
    private HashMap<Tab, BoardController> boardAndControllerMap = new HashMap<>();

    public GamePanel() {
    }

    /**
     * initialization of the images and the data structure used to collect similar objects
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        setGui(App.getGui());
        reinsertMap = new HashMap<>(){{
            put(row0, "r, 3");
            put(row1, "r, 2");
            put(row2, "r, 1");
            put(col0, "c, 4");
            put(col1, "c, 3");
            put(col2, "c, 2");
            put(col3, "c, 1");
        }};
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

        getModel().getMarket().gridProperty().addListener(e ->Platform.runLater(this::setMarketPng));
        getModel().getDevelopmentGrid().gridProperty().addListener(e-> setDevGridPng());
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
        boardsTabPane.getSelectionModel().selectedItemProperty().addListener((e, oldVal, newVal) -> {
            leftPane.getChildren().clear();
            paneHand.getChildren().clear();
            BoardController currentBoardController = boardAndControllerMap.get(newVal);
            leftPane.getChildren().add(currentBoardController.getLeftPane());
            paneHand.getChildren().add(currentBoardController.getResourceHand());
            paneHand.getChildren().forEach(c -> c.relocate(0, 0));
            /*paneHand.setTranslateX(-5);
            paneHand.setTranslateY(-150);*/
        });
        personalBoardController.setView(getGui().getView());
        boardsTabPane.getSelectionModel().select(personalBoard);
        boardsTabPane.getTabs().add(personalBoard);
        getModel().boardsProperty().addListener(e -> setBoardsTabs());
        if (!getModel().getBoards().isEmpty()) setBoardsTabs();
        goFront(boardPane);
        getModel().getLeaderHand().handProperty().addListener((ListChangeListener<? super LeaderCard>) e -> {
            if (e.next())
                if (e.wasAdded() && currentPane != chooseCardPane) Platform.runLater(() -> showChooseCards(null));
                else Platform.runLater(this::setLeaderCards);
        });
        if (getModel().getLeaderHand() != null && getModel().getLeaderHand().getHand().size() > 2)
            showChooseCards(null);
        getModel().currentPlayerInTheGameProperty().addListener(e -> Platform.runLater(this::updateCurrentPlayerLabel));
        getModel().currentTurnPhaseProperty().addListener(e -> Platform.runLater(this::showTurnPhaseAnnouncement));
        if (getModel().getCurrentPlayerInTheGame() != null) updateCurrentPlayerLabel();
        if (getModel().getCurrentTurnPhase() != null) showTurnPhaseAnnouncement();
        backButton.setDisable(true);
        chooseButton.setDisable(true);
    }

    private ClientModel getModel() {
        return getGui().getView().getModel();
    }

    public Pane getGetInitialResourcesPane() {
        return getInitialResourcesPane;
    }

    public Label getInitialResourceIndex() {
        return initialResourceIndex;
    }

    public Label getTotalInitialResourcesAmount() {
        return totalInitialResourcesAmount;
    }

    public Button getInitialResourcesButton() {
        return initialResourcesButton;
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
                        personalBoardController.setBoard(p.getValue());
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
        selectedDevCard = devCard;
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
        if (!pane.equals(paymentPane)) {
            boardPane.setDisable(true);
            boardPane.setEffect(new GaussianBlur());
        } else {
            boardPane.setDisable(false);
            boardPane.setEffect(null);
        }
        currentPane = pane;
        pane.toFront();
        pane.setOpacity(1);
        pane.setDisable(false);
    }

    /**
     * Closes the pane and brings to front the Board pane.
     */
    public void closePopup(ActionEvent actionEvent){
        boardPane.toFront();
        boardPane.setOpacity(1);
        boardPane.setDisable(false);
        boardPane.setEffect(null);
        rightPane.setDisable(false);
        currentPane = null;
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
        //TODO use process instead of this
        getGui().getView().discardLeader(new String[]{"", Integer.toString(leaderHand.indexOf(selectedLeader.get()) + 1)});
    }

    /**
     * accept drag&drop in the reinsert marble slots of the market
     * @param dragevent
     */
    @FXML
    public void dropMarble(DragEvent dragevent){
        ImageView target = (ImageView) dragevent.getSource();
        StringBuilder command = new StringBuilder();
        command.append(reinsertMap.get(target));
        getGui().getView().process("usemarket: " + command);
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
     * start drag & drop for the extra marble in the market
     * @param event
     */
    @FXML
    public void moveExMarble(MouseEvent event){
        Dragboard db = mbEx.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putImage(mbEx.getImage());
        db.setContent(content);
    }

    public void backToDevGrid(ActionEvent actionEvent) {
        revertBuyCard();
        paymentCoin.setText("0");
        paymentSerf.setText("0");
        paymentShield.setText("0");
        paymentStone.setText("0");
        selectedDevCard = null;
        goFront(devGridPane);
    }

    public void revertBuyCard() {
        
    }

    public void choose(ActionEvent actionEvent) {
        goFront(chooseActionPane);
    }

    public void next(ActionEvent actionEvent) {
        getGui().getView().process("next");
        if (getModel().getCurrentTurnPhase()!= null) {
            if (getModel().getBoard().getResHand().isEmpty()) {
                if (getModel().getCurrentTurnPhase().equals("Choose the next action")) {
                    chooseButton.setDisable(false);
                }
                backButton.setDisable(true);
            }
        }
    }

    public void back(ActionEvent actionEvent) {
        getGui().getView().process("back");
        backButton.setDisable(true);
    }

    public void goToMarketPhase(ActionEvent actionEvent) {
        getGui().getView().process("choose: usemarket");
        backButton.setDisable(false);
        //nextButton.setDisable(true);
        closePopup(actionEvent);
    }

    public void goToBuyDevCardPhase(ActionEvent actionEvent) {
        getGui().getView().process("choose: buydevelopmentcard");
        backButton.setDisable(false);
        //nextButton.setDisable(true);
        closePopup(actionEvent);
    }

    public void goToProductionPhase(ActionEvent actionEvent) {
        getGui().getView().process("choose: activateproduction");
        backButton.setDisable(false);
        //nextButton.setDisable(true);
        closePopup(actionEvent);
    }

    public void getInitialResource(MouseEvent mouseEvent) {
        String resourceName = ((ImageView)mouseEvent.getSource())
                .getId()
                .replaceAll("initial", "");
        getGui().getView().process("getres:" + resourceName);
        if (initialResourceIndex.getText().equals(totalInitialResourcesAmount.getText())) {
            closePopup(null);
            initialResourcesButton.setOpacity(0);
            initialResourcesButton.setDisable(true);
        } else initialResourceIndex.setText(Integer.toString(Integer.parseInt(initialResourceIndex.getText()) + 1));
    }

    public void showSoloAction(String tokenId) {
        soloActionOut.setText("Reveal the action of Lorenzo");
        soloActionOkButton.setDisable(true);
        soloActionOkButton.setOpacity(0);
        soloToken.setImage(soloActionBack);
        soloToken.setOnMouseClicked(e -> revealToken(tokenId));
        goFront(soloActionPane);
    }

    public void revealToken(String tokenId) {
        switch (tokenId) {
            case "onepositionreset" -> soloActionOut.setText("Lorenzo goes one position ahead");
            case "twopositions" -> soloActionOut.setText("Lorenzo goes two positions ahead");
            default -> soloActionOut.setText("Lorenzo removes two " + tokenId.replaceAll("del", "") + " cards");
        }
        soloToken.setImage(soloActionMap.get(tokenId));
        soloActionOkButton.setDisable(false);
        soloActionOkButton.setOpacity(1);
    }

    public void getInitialResources(ActionEvent actionEvent) {
        goFront(getInitialResourcesPane);
    }

    public void showTurnPhaseAnnouncement() {
        String turnPhase = getModel().getCurrentTurnPhase();
        turnPhaseLabel.setText(turnPhase);
        turnPhaseAnnouncementLabel.setText(turnPhase);
        showOverAndThenHide(phaseAnnouncementPane);
    }

    public void updateCurrentPlayerLabel() {
        playerTurnLabel.setText(
                getModel().getPlayerUsername().equals(getModel().getCurrentPlayerInTheGame()) ?
                        "You" :
                        getModel().getCurrentPlayerInTheGame());
    }

    public void showOverAndThenHide(Pane pane) {
        DoubleProperty value = new SimpleDoubleProperty(0);
        GaussianBlur blur = new GaussianBlur();
        blur.radiusProperty().bind(value);
        if (boardPane.getEffect() == null) boardPane.setEffect(blur);
        if (currentPane != null) currentPane.setEffect(blur);
        Timeline blurring = new Timeline(new KeyFrame(Duration.seconds(0.5), new KeyValue(value, 10)));
        FadeTransition paneFadeInTransition = new FadeTransition(Duration.seconds(0.5), pane);
        pane.toFront();
        pane.setOpacity(1);
        paneFadeInTransition.setFromValue(0);
        paneFadeInTransition.setToValue(1);
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        SequentialTransition trans = new SequentialTransition(blurring, paneFadeInTransition, pause);
        pane.setDisable(false);
        trans.setAutoReverse(true);
        trans.setCycleCount(2);
        rightPane.setDisable(true);
        trans.play();
        trans.setOnFinished(e -> {
            if (boardPane.getEffect() == blur) boardPane.setEffect(null);
            if (currentPane != null) currentPane.setEffect(null);
            rightPane.setDisable(false);
            pane.setDisable(true);
            pane.toBack();
        });
    }
}
