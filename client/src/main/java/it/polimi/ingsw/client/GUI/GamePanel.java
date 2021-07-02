package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.ClientModel;
import it.polimi.ingsw.client.model.*;
import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GamePanel extends SceneHandler {
    /**
     * List of FXML item on the board.
     */
    @FXML
    private Label out;
    @FXML
    private TabPane boardsTabPane;
    @FXML
    private AnchorPane paneHand;
    @FXML
    private Button marketBtn;
    @FXML
    private GridPane marketTray;
    @FXML
    private ImageView mb00;
    @FXML
    private ImageView mb10;
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
    private VBox paymentBox;
    @FXML
    private HBox paymentPositionQuest;
    @FXML
    private Button paymentButton;

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
    private Pane pausePane;
    @FXML
    private Pane paymentPane;
    @FXML
    private Pane devGridPane;
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
    private ImageView paymentCoinImageView;
    @FXML
    private Label paymentSerf;
    @FXML
    private ImageView paymentSerfImageView;
    @FXML
    private Label paymentShield;
    @FXML
    private ImageView paymentShieldImageView;
    @FXML
    private Label paymentStone;
    @FXML
    private ImageView paymentStoneImageView;

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
    private Pane playerTurnAnnouncementPane;
    @FXML
    private Label newPlayerTurnLabel;

    @FXML
    private ImageView firstChoice;
    @FXML
    private ImageView secondChoice;
    @FXML
    private Pane whiteMarblePowerPane;
    @FXML
    private Label playerTurnLabel;
    @FXML
    private Label turnPhaseLabel;
    @FXML
    private AnchorPane leaderCard1;
    @FXML
    private AnchorPane leaderCard2;

    @FXML
    private Tooltip toolTipHelp;
    @FXML
    private Button help;

    @FXML
    private Pane endGamePane;
    @FXML
    private Label mainResultLabel;
    @FXML
    private Label specificResultLabel;
    @FXML
    private TableView<PlayerScorePair> rankingTabView;
    @FXML
    private TableColumn<PlayerScorePair, String> playersTabColumn;
    @FXML
    private TableColumn<PlayerScorePair, Integer> scoresTabColumn;
    @FXML
    private Label faithTrackVPLabel;
    @FXML
    private Label devCardVPLabel;
    @FXML
    private Label leadCardVPLabel;
    @FXML
    private Label popeFavorVPLabel;
    @FXML
    private Label resVPLabel;
    @FXML
    private Label nickLabelBoard;

    private Pane currentPane;

    private boolean isOver = false;


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
    public static final HashMap<Image, Resource> imageResourceMap = new HashMap<>() {{
        put(GamePanel.imgCoin, Resource.COIN);
        put(GamePanel.imgSerf, Resource.SERF);
        put(GamePanel.imgShield, Resource.SHIELD);
        put(GamePanel.imgStone, Resource.STONE);
    }};
    public static final HashMap<Resource, Image> resourceImageMap = new HashMap<>() {{
        put(Resource.SHIELD, GamePanel.imgShield);
        put(Resource.COIN, GamePanel.imgCoin);
        put(Resource.SERF, GamePanel.imgSerf);
        put(Resource.STONE, GamePanel.imgStone);
    }};

    public static final ImageView imgViewCoin = new ImageView(imgCoin);
    public static final ImageView imgViewSerf = new ImageView(imgSerf);
    public static final ImageView imgViewShield = new ImageView(imgShield);
    public static final ImageView imgViewStone = new ImageView(imgStone);
    public static final HashMap<ImageView, Resource> imageViewResourceMap = new HashMap<>() {{
        put(imgViewCoin, Resource.COIN);
        put(imgViewSerf, Resource.SERF);
        put(imgViewShield, Resource.SHIELD);
        put(imgViewStone, Resource.STONE);
    }};

    public static final Image soloActionBack = new Image(Objects.requireNonNull(BoardController.class.getResourceAsStream("/png/soloTokens/back.png")));
    public static final Image delBlue = new Image(Objects.requireNonNull(BoardController.class.getResourceAsStream("/png/soloTokens/delblue.png")));
    public static final Image delYellow = new Image(Objects.requireNonNull(BoardController.class.getResourceAsStream("/png/soloTokens/delyellow.png")));
    public static final Image delPurple = new Image(Objects.requireNonNull(BoardController.class.getResourceAsStream("/png/soloTokens/delpurple.png")));
    public static final Image delGreen = new Image(Objects.requireNonNull(BoardController.class.getResourceAsStream("/png/soloTokens/delgreen.png")));
    public static final Image twoPositions = new Image(Objects.requireNonNull(BoardController.class.getResourceAsStream("/png/soloTokens/twopositions.png")));
    public static final Image onePositionReset = new Image(Objects.requireNonNull(BoardController.class.getResourceAsStream("/png/soloTokens/onepositionreset.png")));

    private final HashMap<String, Image> soloActionMap = new HashMap<>() {{
        put("delblue", delBlue);
        put("delyellow", delYellow);
        put("delpurple", delPurple);
        put("delgreen", delGreen);
        put("twopositions", twoPositions);
        put("onepositionreset", onePositionReset);
    }};

    private final StringBuilder command = new StringBuilder();
    private PersonalBoardController personalBoardController;

    /**
     * Data structures with the imageView for the images in the board
     * they represents the empty spots in the board.
     */
    private ImageView[][] marketSpots;
    private List<ImageView> leaderHand;
    private final ObjectProperty<ImageView> selectedLeader = new SimpleObjectProperty<>();
    private DevelopmentCard selectedDevCard;
    private ImageView[][] devCardSpots;
    private HashMap<ImageView, String> reinsertMap;
    private final List<ResourceAndDepot> paymentBuffer = new ArrayList<>();
    private final List<Label> scores = new ArrayList<>();

    /**
     * Map for resource and marbles.
     * Color of the marble match with the relative image.
     * Resource match with the relative image.
     */
    private HashMap<Color, Image> colorImageMap;
    private HashMap<Resource, Label> resourceLabelHashMap;
    private HashMap<ImageView, Label> paymentImageLabelHashMap;
    private final HashMap<Tab, BoardController> boardAndControllerMap = new HashMap<>();

    /**
     * Initialization of the images and the data structure used to collect similar objects.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);
        setGui(App.getGui());
        reinsertMap = new HashMap<>() {{
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
        devCardSpots = new ImageView[][]{
                {devcard00, devcard10, devcard20, devcard30},
                {devcard01, devcard11, devcard21, devcard31},
                {devcard02, devcard12, devcard22, devcard32}
        };
        for (int row = 0; row < devCardSpots.length; row++) {
            for (int col = 0; col < devCardSpots[row].length; col++) {
                int finalRow = row;
                int finalCol = col;
                devCardSpots[row][col].setOnMouseClicked(
                        e -> showPayment(getModel().getDevelopmentGrid().getCard(finalRow, finalCol))
                );
            }
        }
        colorImageMap = new HashMap<>() {{
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

        paymentCoinImageView.setImage(imgCoin);
        paymentSerfImageView.setImage(imgSerf);
        paymentShieldImageView.setImage(imgShield);
        paymentStoneImageView.setImage(imgStone);
        paymentImageLabelHashMap = new HashMap<>() {{
            put(paymentCoinImageView, paymentCoin);
            put(paymentSerfImageView, paymentSerf);
            put(paymentShieldImageView, paymentShield);
            put(paymentStoneImageView, paymentStone);
        }};
        resourceLabelHashMap = new HashMap<>() {{
            put(Resource.COIN, paymentCoin);
            put(Resource.SERF, paymentSerf);
            put(Resource.SHIELD, paymentShield);
            put(Resource.STONE, paymentStone);
        }};

        devPosCombo.getItems().addAll("1", "2", "3");
        devPosCombo.valueProperty().addListener(e -> {
            if (!devPosCombo.getSelectionModel().isEmpty()) paymentButton.setDisable(false);
        });
        scores.addAll(Arrays.asList(faithTrackVPLabel, devCardVPLabel, leadCardVPLabel, popeFavorVPLabel, resVPLabel));

        getModel().getMarket().marbleProperty().addListener(e -> Platform.runLater(this::updateMarketListener));
        for (int i = 0; i < 3; i++) {
            getModel().getDevelopmentGrid().gridProperty(i).addListener((InvalidationListener) e -> setDevGridPng());
        }
        selectedLeader.addListener(e -> leaderButtonsProperty());
        if (getModel().getMarket().getGrid() != null) setMarketPng();
        if (getModel().getDevelopmentGrid().getGrid() != null) setDevGridPng();

        FXMLLoader personalBoardLoader = new FXMLLoader(getClass().getResource("/fxml/personalBoard.fxml"));
        AnchorPane board = null;
        try {
            board = personalBoardLoader.load();
        } catch (IOException e) {
            System.err.println("Error when trying to load the personal board.");
            System.exit(0);
        }
        Tab personalBoard = new Tab("Your board", board);
        boardAndControllerMap.put(personalBoard, personalBoardLoader.getController());
        personalBoardController = personalBoardLoader.getController();
        boardsTabPane.getSelectionModel().selectedItemProperty().addListener((e, oldVal, newVal) -> {
            paneHand.getChildren().clear();
            BoardController currentBoardController = boardAndControllerMap.get(newVal);
            leaderCard1.getChildren().clear();
            leaderCard2.getChildren().clear();
            leaderCard1.getChildren().add(currentBoardController.getLeaderCard1());
            leaderCard1.getChildren().forEach(c -> c.relocate(0, 0));
            leaderCard2.getChildren().add(currentBoardController.getLeaderCard2());
            leaderCard2.getChildren().forEach(c -> c.relocate(0, 0));
            paneHand.getChildren().add(currentBoardController.getResourceHand());
            paneHand.getChildren().forEach(c -> c.relocate(0, 0));
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
        getModel().isFinishedProperty().addListener(e -> Platform.runLater(this::nextUpdate));
        if (getModel().getCurrentPlayerInTheGame() != null) updateCurrentPlayerLabel();
        if (getModel().getCurrentTurnPhase() != null) showTurnPhaseAnnouncement();
        getModel().whiteMarbleQuantityProperty().addListener(e -> Platform.runLater(this::updateWhiteMarbleListener));
        help.setTooltip(toolTipHelp);
        nickLabelBoard.setText("Nickname: " + getModel().getPlayerUsername());
    }

    public Tooltip getToolTipHelp() {
        return toolTipHelp;
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

    public VBox getPaymentBox() {
        return paymentBox;
    }

    public HBox getPaymentPositionQuest() {
        return paymentPositionQuest;
    }

    public Button getPaymentButton() {
        return paymentButton;
    }

    public Button getNextButton() {
        return nextButton;
    }

    public Pane getEndGamePane() {
        return endGamePane;
    }

    public Label getMainResultLabel() {
        return mainResultLabel;
    }

    public Label getSpecificResultLabel() {
        return specificResultLabel;
    }

    public List<Label> getScores() {
        return scores;
    }

    public void setMainResultLabel(String mainResult) {
        mainResultLabel.setText(mainResult);
    }

    public void setSpecificResultLabel(String specificResult) {
        specificResultLabel.setText(specificResult);
    }

    public TableView<PlayerScorePair> getRankingTabView() {
        return rankingTabView;
    }

    public boolean isOver() {
        return isOver;
    }

    public void setOver(boolean over) {
        isOver = over;
    }

    /**
     * Prints in the bottom of the game a message passed as parameter.
     */
    public void printOut(String msg) {
        out.setText(msg);
    }

    /**
     * Stops the game when a player is reconnecting to avoid moves meanwhile.
     */
    public void startTimeUp() {
        goFront(pausePane);
    }

    /**
     * Removes the time up.
     */
    public void removeTimeUp() {
        closePopup(null);
    }

    /**
     * Set up the fxml file for the personal boards and sets its controller for each player.
     */
    private void setBoardsTabs() {
        getModel().getBoards().entrySet().stream()
                .filter(p -> boardsTabPane.getTabs().stream().noneMatch(t -> t.getText().equals(p.getKey())))
                .forEach(p -> {
                    if (p.getKey().equals(getModel().getPlayerUsername())) {
                        personalBoardController.setBoard(p.getValue());
                        personalBoardController.setTablePositionLabel(new ArrayList<>(getModel().getBoards().values()).indexOf(p.getValue()) + 1);
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
                    ((BoardController) loader.getController()).setTablePositionLabel(new ArrayList<>(getModel().getBoards().values()).indexOf(p.getValue()) + 1);
                });
    }

    /**
     * Requires as a parameter the development card grid and set the devCardSpots with the
     * relative card images.
     */
    public void setDevGridPng() {
        ArrayList<ObservableList<DevelopmentCard>> devGrid = getModel().getDevelopmentGrid().getGrid();
        for (int row = 0; row < devGrid.size(); row++) {
            for (int col = 0; col < devGrid.get(row).size(); col++) {
                if (devGrid.get(row).get(col) != null) {
                    devCardSpots[row][col].setImage(Utility.getCardPng(devGrid.get(row).get(col).getID()));
                } else
                    devCardSpots[row][col].setImage(null);
            }
        }
    }

    /**
     * Requires as a parameter the marble tray of the market and set the marketSpots
     * so the market is set with the relatives marbles Images.
     */
    public void setMarketPng() {
        Marble[][] tray = getModel().getMarket().getGrid();
        Marble exMarble = getModel().getMarket().getExtraMarble();
        for (int row = 0; row < tray.length; row++) {
            for (int col = 0; col < tray[row].length; col++) {
                marketSpots[2 - row][3 - col].setImage(colorImageMap.get(tray[row][col].getColor()));
            }
        }
        mbEx.setImage(colorImageMap.get(exMarble.getColor()));
    }

    /**
     * Modifies market GUI on each update from the model.
     */
    public void updateMarketListener() {
        setMarketPng();
    }

    /**
     * Sets the resources to choose from the white marble powers.
     */
    public void updateWhiteMarbleListener() {
        if (getModel().getBoard().getPowerWhite().size() == 2 && getModel().getWhiteMarbleQuantity() != 0) {
            firstChoice.setImage(resourceImageMap.get(getModel().getBoard().getPowerWhite().get(0)));
            secondChoice.setImage(resourceImageMap.get(getModel().getBoard().getPowerWhite().get(1)));
            goFront(whiteMarblePowerPane);
        }
    }

    public int getCardId(Image image) {
        return Integer.parseInt(image.getUrl().substring(11, 14));
    }

    /**
     * Shows the Market pane on the click of the relative button.
     */
    @FXML
    public void showMarket(ActionEvent actionEvent) {
        goFront(marketPane);
        Market modelMarket = getGui().getView().getModel().getMarket();
        setMarketPng();
    }

    /**
     * Shows the Dev Grid pane.
     */
    @FXML
    void showDevGrid(ActionEvent event) {
        goFront(devGridPane);
    }

    /**
     * Shows the payment pane on development card click in the grid and sets its cost labels.
     */
    private void showPayment(DevelopmentCard devCard) {
        selectedDevCard = devCard;
        devCardToBuy.setImage(Utility.getCardPng(devCard.getID()));
        for (UtilityProductionAndCost cost : devCard.getCosts()) {
            resourceLabelHashMap.get(cost.getResource()).setText(Integer.toString(cost.getQuantity()));
        }
        for (Resource res : personalBoardController.getBoard().getPowerSale())
            resourceLabelHashMap.get(res).setText(Integer.toString(Integer.parseInt(resourceLabelHashMap.get(res).getText()) - 1));
        colorLabel.setText("Color: " + devCard.getColor().name());
        levelLabel.setText("Level: " + devCard.getLevel());
        goFront(paymentPane);
    }

    /**
     * Accepts drop in the payment pane.
     */
    @FXML
    public void acceptResInBuyCard(DragEvent event) {
        if (!event.getDragboard().getString().equals("hand"))
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
    }

    /**
     * Creates and sends the message for the server to buy the development card.
     * {@link it.polimi.ingsw.commonFiles.messages.toServer.BuyCard}
     */
    @FXML
    public void buyCard(ActionEvent event) {
        int numOfRequiredResources = Arrays.stream(selectedDevCard.getCosts())
                .mapToInt(UtilityProductionAndCost::getQuantity)
                .sum();
        if (numOfRequiredResources != paymentBuffer.size()) return;
        command.setLength(0);
        command.append("buydevcard: ")
                .append(selectedDevCard.getLevel()).append(", ")
                .append(selectedDevCard.getColor().name()).append(", ")
                .append(devPosCombo.getValue()).append(", ");
        for (UtilityProductionAndCost upc :
                selectedDevCard.getCosts()) {
            paymentBuffer.stream()
                    .filter(element -> upc.getResource().equals(element.getResource()))
                    .forEach(res -> command.append(res.getDepot()).append(" "));
        }
        getGui().getView().process(command.toString());
        command.setLength(0);
    }

    /**
     * Goes back to the dev grid from payment.
     */
    public void backToDevGrid(ActionEvent actionEvent) {
        revertBuyCard();
        goFront(devGridPane);
        paymentCoin.setText("0");
        paymentSerf.setText("0");
        paymentShield.setText("0");
        paymentStone.setText("0");
        selectedDevCard = null;
        paymentButton.setDisable(true);
        paymentPositionQuest.setDisable(true);
        devPosCombo.getSelectionModel().clearSelection();
    }

    /**
     * Handles the drag from the stores to the card payment.
     */
    @FXML
    public void dropResInCardCost(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        ImageView paymentDestination = (ImageView) event.getSource();
        Image draggedResource = ((ImageView) event.getGestureSource()).getImage();
        String resourceSource = dragboard.getString();
        if (resourceSource.equals("hand")) {
            event.setDropCompleted(false);
            return;
        } else if (paymentDestination.getImage().equals(draggedResource)) {
            Label resourceNumber = paymentImageLabelHashMap.get(paymentDestination);
            if (Integer.parseInt(resourceNumber.getText()) > 0) {
                paymentBuffer.add(new ResourceAndDepot(
                        imageResourceMap.get(draggedResource),
                        resourceSource.substring(0, 1).matches("\\d") ? Integer.parseInt(resourceSource) : 0
                ));
                resourceNumber.setText(Integer.toString(Integer.parseInt(resourceNumber.getText()) - 1));
                if (resourceLabelHashMap.values().stream()
                        .mapToInt(label -> Integer.parseInt(label.getText()))
                        .sum() == 0)
                    paymentPositionQuest.setDisable(false);
                event.setDropCompleted(true);
                return;
            }
        }
        event.setDropCompleted(false);
    }

    /**
     * Restores the resources in depots and strongbox from where resource have been taken
     * to buy a card.
     */
    public void revertBuyCard() {
        personalBoardController.updateWarehouse();
        personalBoardController.updateStrongbox();
        paymentBuffer.clear();
    }

    //LEADER CARDS PANE

    /**
     * Shows the initial phase of the choose of the 2 leader cards.
     */
    @FXML
    public void showChooseCards(ActionEvent actionEvent) {
        discardButton.setDisable(true);
        deployLButton.setDisable(true);
        selectedLeader.set(null);
        setLeaderCards();
        goFront(chooseCardPane);
    }

    /**
     * Sets leader cards in GUI from model leader hand.
     */
    private void setLeaderCards() {
        LeaderHand hand = getGui().getView().getModel().getLeaderHand();
        if (selectedLeader.get() != null) {
            selectedLeader.get().setEffect(null);
            selectedLeader.set(null);
        }
        int i;
        for (i = 0; i < leaderHand.size(); i++) {
            try {
                LeaderCard card = hand.getHand().get(i);
                leaderHand.get(i).setImage(Utility.getCardPng(card.getID()));
            } catch (IndexOutOfBoundsException e) {
                leaderHand.get(i).setImage(null);
            }
        }
    }

    /**
     * Handles the discard and deploy buttons in the leader hand pane when a card is selected or not.
     */
    private void leaderButtonsProperty() {
        if (selectedLeader.get() == null) {
            discardButton.setDisable(true);
            deployLButton.setDisable(true);
        } else {
            discardButton.setDisable(false);
            deployLButton.setDisable(false);
        }
    }

    /**
     * Manages the selection of a leader card. If no leader was previously selected, the leader is selected.
     * If a leader was selected, removes the effect. If the leader is reselected, removes the selection.
     */
    @FXML
    public void selectLeaderCard(MouseEvent e) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(0.5);
        ImageView selectedCard = (ImageView) e.getSource();
        if (selectedCard.getImage() == null) return;
        if (selectedLeader.get() != null && selectedLeader.get() == selectedCard && selectedCard.getImage() != null) {
            selectedCard.setEffect(null);
            selectedLeader.set(null);
            return;
        } else if (selectedLeader.get() != null) {
            selectedLeader.get().setEffect(null);
        }
        selectedLeader.set(selectedCard);
        selectedCard.setEffect(colorAdjust);
    }

    /**
     * Creates and sends the message to the server to discard a leader.
     * {@link it.polimi.ingsw.commonFiles.messages.toServer.DiscardLeader}
     */
    @FXML
    public void discardLeaderCard(ActionEvent actionEvent) {
        getGui().getView().process("discardleader: " + (leaderHand.indexOf(selectedLeader.get()) + 1));
        selectedLeader.get().setEffect(null);
        selectedLeader.set(null);
    }

    /**
     * Creates and sends the message to the server to use a leader.
     * {@link it.polimi.ingsw.commonFiles.messages.toServer.UseLeader}
     *
     */
    @FXML
    public void deployLeaderCard(ActionEvent actionEvent) {
        getGui().getView().process("useleader: " + (leaderHand.indexOf(selectedLeader.get()) + 1));
        selectedLeader.set(null);
    }

    /**
     * Utility method to show in the front the pane gave as a parameter
     * and disable the other panes in the back.
     *
     * @param pane
     */
    public void goFront(Pane pane) {
        for (int i = 0; i < stackPane.getChildren().size(); i++) {
            if (!stackPane.getChildren().get(i).equals(pane)) {
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
     * Closes any pane in front of the board pane.
     */
    public void closePopup(ActionEvent actionEvent) {
        boardPane.toFront();
        boardPane.setOpacity(1);
        boardPane.setDisable(false);
        boardPane.setEffect(null);
        rightPane.setDisable(false);
        currentPane = null;
    }

    /**
     * Handles the next button on/off.
     */
    private void nextUpdate() {
        nextButton.setDisable(!getModel().isFinished());
    }
    //

    /**
     * Accept drag&drop in the reinsert marble slots of the market.
     * Creates and sens the usemarket message to the server.
     * {@link it.polimi.ingsw.commonFiles.messages.toServer.UseMarket}
     */
    @FXML
    public void dropMarble(DragEvent dragevent) {
        ImageView target = (ImageView) dragevent.getSource();
        StringBuilder command = new StringBuilder();
        command.append(reinsertMap.get(target));
        getGui().getView().process("usemarket: " + command);
    }

    /**
     * Modifies the mouse cursor when a drag is over the designed target.
     */
    @FXML
    public void onDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        event.consume();
    }

    /**
     * Start drag & drop for the extra marble in the market
     *
     */
    @FXML
    public void moveExMarble(MouseEvent event) {
        Dragboard db = mbEx.startDragAndDrop(TransferMode.ANY);
        ClipboardContent content = new ClipboardContent();
        content.putImage(mbEx.getImage());
        db.setContent(content);
    }

    /**
     * Brings the action choose pane on front.
     */
    public void choose(ActionEvent actionEvent) {
        goFront(chooseActionPane);
    }

    /**
     * Creates and sends the next message to the server.
     * {@link it.polimi.ingsw.commonFiles.messages.toServer.Next}
     */
    public void next(ActionEvent actionEvent) {
        getGui().getView().process("next");
    }

    /**
     * Create and sends the back message to the server.
     * {@link it.polimi.ingsw.commonFiles.messages.toServer.Back}
     */
    public void back(ActionEvent actionEvent) {
        getGui().getView().process("back");
    }

    /**
     * Create and sends the choose market message to the server.
     * {@link it.polimi.ingsw.commonFiles.messages.toServer.ChooseTurnPhase}
     */
    public void goToMarketPhase(ActionEvent actionEvent) {
        getGui().getView().process("choose: usemarket");
        closePopup(actionEvent);
    }

    /**
     * Create and sends the choose buy development card message to the server.
     * {@link it.polimi.ingsw.commonFiles.messages.toServer.ChooseTurnPhase}
     */
    public void goToBuyDevCardPhase(ActionEvent actionEvent) {
        getGui().getView().process("choose: buydevelopmentcard");
        closePopup(actionEvent);
    }

    /**
     * Create and sends the choose activate production message to the server.
     * {@link it.polimi.ingsw.commonFiles.messages.toServer.ChooseTurnPhase}
     */
    public void goToProductionPhase(ActionEvent actionEvent) {
        getGui().getView().process("choose: activateproduction");
        closePopup(actionEvent);
    }

    /**
     * Create and sends the get initial resource message to the server.
     * {@link it.polimi.ingsw.commonFiles.messages.toClient.updates.InitialResourcesAmount}
     */
    public void getInitialResource(MouseEvent mouseEvent) {
        String resourceName = ((ImageView) mouseEvent.getSource())
                .getId()
                .replaceAll("initial", "");
        getGui().getView().process("getres:" + resourceName);
        if (initialResourceIndex.getText().equals(totalInitialResourcesAmount.getText())) {
            closePopup(null);
            initialResourcesButton.setOpacity(0);
            initialResourcesButton.setDisable(true);
        } else initialResourceIndex.setText(Integer.toString(Integer.parseInt(initialResourceIndex.getText()) + 1));
    }

    /**
     * Shows Lorenzo's action pane in single player.
     * @param tokenId: string that contains Lorenzo's action.
     */
    public void showSoloAction(String tokenId) {
        soloActionOut.setText("Reveal the action of Lorenzo");
        soloActionOkButton.setDisable(true);
        soloActionOkButton.setOpacity(0);
        soloToken.setImage(soloActionBack);
        soloToken.setOnMouseClicked(e -> revealToken(tokenId));
        goFront(soloActionPane);
    }

    /**
     * Reveals Lorenzo's action in single player.
     * @param tokenId: string that contains Lorenzo's action.
     */
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

    /**
     * Shows the Initial resource pane on front.
     */
    public void getInitialResources(ActionEvent actionEvent) {
        goFront(getInitialResourcesPane);
    }

    /**
     * Shows the actual turn phase with a pop-up pane.
     */
    public void showTurnPhaseAnnouncement() {
        if (!isOver) {
            String turnPhase = getModel().getCurrentTurnPhase();
            if (getModel().getPlayerUsername().equals(getModel().getCurrentPlayerInTheGame()))
                handlingInterface(turnPhase);
            else {
                backButton.setDisable(true);
                nextButton.setDisable(true);
                chooseButton.setDisable(true);
            }
            turnPhaseLabel.setText(turnPhase);
            turnPhaseAnnouncementLabel.setText(turnPhase);
            showOverAndThenHide(phaseAnnouncementPane);
        }
    }

    /**
     * Handles the buttons or hides game objects in the game based on the active phase of the turn.
     * @param phase
     */
    public void handlingInterface(String phase) {
        switch (phase) {
            case "Leader action" -> {
                leadCardGrid.setDisable(false);
                backButton.setDisable(true);
                chooseButton.setDisable(true);
                getModel().getBoard().setProductionInterface(true);
            }
            case "Choose the next action" -> {
                getModel().setFinished(false);
                leadCardGrid.setDisable(true);
                chooseButton.setDisable(false);
                getModel().getBoard().setProductionInterface(true);
            }
            case "Market" -> {
                getModel().setFinished(false);
                backButton.setDisable(false);
                chooseButton.setDisable(true);
            }
            case "Buy a development card" -> {
                getModel().setFinished(false);
                chooseButton.setDisable(true);
                backButton.setDisable(false);
                paymentBox.setDisable(false);
            }
            case "Activate productions" -> {
                getModel().setFinished(false);
                backButton.setDisable(false);
                getModel().getBoard().setProductionInterface(false);
            }
        }
    }

    /**
     * Prints the actual current player in a label on top of the board.
     */
    public void updateCurrentPlayerLabel() {
        boolean isCurrentPlayer = getModel().getPlayerUsername().equals(getModel().getCurrentPlayerInTheGame());
        playerTurnLabel.setText(isCurrentPlayer ? "You" : getModel().getCurrentPlayerInTheGame());
        nextButton.setDisable(!isCurrentPlayer);
        newPlayerTurnLabel.setText(isCurrentPlayer ? "your" : getModel().getCurrentPlayerInTheGame() + "'s");
        showOverAndThenHide(playerTurnAnnouncementPane);
    }

    /**
     * Shows the pop-up that communicate actual phase and subsequently hides them.
     * @param pane: the pane needs to be hidden.
     */
    public synchronized void showOverAndThenHide(Pane pane) {
        DoubleProperty value = new SimpleDoubleProperty(0);
        GaussianBlur blur = new GaussianBlur();
        blur.radiusProperty().bind(value);
        if (boardPane.getEffect() == null) boardPane.setEffect(blur);
        if (currentPane != null) currentPane.setEffect(blur);
        Timeline blurring = new Timeline(new KeyFrame(Duration.seconds(0.1), new KeyValue(value, 10)));
        FadeTransition paneFadeInTransition = new FadeTransition(Duration.seconds(0.3), pane);
        pane.toFront();
        pane.setOpacity(1);
        paneFadeInTransition.setFromValue(0);
        paneFadeInTransition.setToValue(1);
        PauseTransition pause = new PauseTransition(Duration.seconds(0.3));
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

    /**
     * Selects the white marble power and sends the message.
     */
    public void chooseWhiteMarblePower(MouseEvent mouseEvent) {
        ImageView target = (ImageView) mouseEvent.getSource();
        if (target.getImage().equals(firstChoice.getImage())) {
            getGui().getView().process("choosewhite: 1");
        } else {
            getGui().getView().process("choosewhite: 2");
        }
        closePopup(null);
    }

    /**
     * Closes the program.
     */
    public void quit(ActionEvent actionEvent) {
        System.exit(0);
    }
}
