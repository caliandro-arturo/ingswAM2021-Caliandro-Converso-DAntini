package it.polimi.ingsw.client.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Objects;

public class GamePanel extends SceneHandler {
    @FXML
    TextField text;
    int pos = 1;
    int posB = 1;

    @FXML
    private BorderPane mainPane;
    @FXML
    private TabPane boardPane;

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



    Image coin = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/coin.png")));
    Image serf = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/serf.png")));
    Image shield = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/shield.png")));
    Image stone = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/stone.png")));
    Image blueMarble = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/blue_marble.png")));
    Image greyMarble = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/grey_marble.png")));
    Image purpleMarble = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/purple_marble.png")));
    Image redMarble = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/red_marble.png")));
    Image whiteMarble = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/white_marble.png")));
    Image yellowMarble = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/yellow_marble.png")));

    Image card49 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/cards/49.png")));
    Image card50 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/cards/50.png")));
    Image card51 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/cards/51.png")));
    Image card52 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/cards/52.png")));
    Image card53 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/cards/53.png")));
    Image card54 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/cards/54.png")));
    Image card55 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/cards/55.png")));
    Image card56 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/cards/56.png")));
    Image card57 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/cards/57.png")));
    Image card58 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/cards/58.png")));
    Image card59 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/cards/59.png")));
    Image card60 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/cards/60.png")));
    Image card61 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/cards/61.png")));
    Image card62 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/cards/62.png")));
    Image card63 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/cards/63.png")));
    Image card64 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/png/cards/64.png")));

    ImageView imageView = new ImageView(coin);
    ImageView imageView1 = new ImageView(serf);
    ImageView imageView2 = new ImageView(shield);
    ImageView imageView3 = new ImageView(stone);

    ArrayList<ImageView> handList = new ArrayList<>(){{
        add(imageView);
        add(imageView1);
        add(imageView2);
        add(imageView3);
        add(imageView);
        add(imageView1);
        add(imageView2);
        add(imageView3);
    }};

    ArrayList<ImageView> resPlaces = new ArrayList<>();

    ArrayList<ImageView> marbles = new ArrayList<>();

    ArrayList<ImageView> marketSpots = new ArrayList<>();

    public void buildCardArray(){
        ArrayList<ImageView> devCardsArray = new ArrayList<>();
        for(int i=0; i<48; i++){
            devCardsArray.add(new ImageView());
            String imgD = i+".png";
            devCardsArray.get(i).setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imgD))));
        }
        ArrayList<ImageView> leadCardArray = new ArrayList<>();
        for(int j=48; j<64;j++){
            leadCardArray.add(new ImageView());
            String imgL = j+".png";
            leadCardArray.get(j).setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imgL))));
        }
    }

    @FXML
    void increasePos(ActionEvent event) {
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

    public void checkTile(int pos){
        if(pos == 9)
            tile2.setOpacity(100);
        else if (pos == 17)
            tile3.setOpacity(100);
        else if (pos== 24)
            tile4.setOpacity(100);
    }

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
        fillHand(handList);
    }

    public void fillHand(ArrayList<ImageView> handList){
        for (ImageView view : handList) {
            view.setFitHeight(70);
            view.setFitWidth(70);
        }
        hand.setPageFactory(handList::get);
        resPlaces.add(res1);
        resPlaces.add(res21);
        resPlaces.add(res22);
        resPlaces.add(res31);
        resPlaces.add(res32);
        resPlaces.add(res33);
        hand.setMaxPageIndicatorCount(handList.size());
        hand.setPageCount(handList.size());

    }
    @FXML
    public void moveRes(){
        hand.setOnDragDetected(event1 -> {
            Dragboard db = hand.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            Image res = handList.get(hand.getCurrentPageIndex()).getImage();
            content.putImage(res);
            db.setContent(content);
            handList.remove(hand.getCurrentPageIndex());
            event1.consume();
        });

        for(ImageView warSpot: resPlaces){
            warSpot.setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    dragEvent.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    dragEvent.consume();
                }

            });
        }

        for(ImageView warSpot: resPlaces){
            warSpot.setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    warSpot.setImage(dragEvent.getDragboard().getImage());
                    fillHand(handList);
                    hand.setMaxPageIndicatorCount(hand.getPageCount()-1);
                    dragEvent.consume();
                }
            });
        }

    }

    @FXML
    public void showMarket(ActionEvent actionEvent){
        marketPane.toFront();
        marketPane.setOpacity(1);
        goBack(boardPane);
        chooseCardPane.toBack();
        chooseCardPane.setOpacity(0);
        devGridPane.toBack();
        devGridPane.setOpacity(0);
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
        marbles.add(mb00);
        marbles.add(mb10);
        marbles.add(mb20);
        marbles.add(mb30);
        marbles.add(mb01);
        marbles.add(mb11);
        marbles.add(mb21);
        marbles.add(mb31);
        marbles.add(mb02);
        marbles.add(mb12);
        marbles.add(mb22);
        marbles.add(mb32);
        marketSpots.add(col0);
        marketSpots.add(col1);
        marketSpots.add(col2);
        marketSpots.add(col3);
        marketSpots.add(row0);
        marketSpots.add(row1);
        marketSpots.add(row2);
    }

    @FXML
    void showDevGrid(ActionEvent event) {
        devGridPane.toFront();
        devGridPane.setOpacity(1);
        goBack(boardPane);
        marketPane.toBack();
        marketPane.setOpacity(0);
        chooseCardPane.toBack();
        chooseCardPane.setOpacity(0);
        devcard00.setImage(card50);
        devcard01.setImage(card50);
        devcard02.setImage(card50);
        devcard10.setImage(card50);
        devcard11.setImage(card50);
        devcard12.setImage(card50);
        devcard20.setImage(card50);
        devcard21.setImage(card50);
        devcard22.setImage(card50);
        devcard30.setImage(card50);
        devcard31.setImage(card50);
        devcard32.setImage(card50);
    }

    @FXML
    public void showChooseCards(ActionEvent actionEvent){
        chooseCardPane.toFront();
        chooseCardPane.setOpacity(1);
        goBack(boardPane);
        marketPane.toBack();
        marketPane.setOpacity(0);
        devGridPane.toBack();
        devGridPane.setOpacity(0);
        leadCard1.setImage(card51);
        leadCard2.setImage(card50);
        leadCard3.setImage(card53);
        leadCard4.setImage(card54);
    }

    public void goBack(TabPane pane){
        pane.toBack();
        pane.setDisable(true);
        pane.setOpacity(0.5);
    }


    public void goFront(TabPane pane){
        pane.setDisable(false);
        pane.setOpacity(1);
    }
    @FXML
    public void buyResources(ActionEvent actionEvent) {
        marketPane.toBack();
        goFront(boardPane);
    }
    @FXML
    public void buyCard(ActionEvent event){
        devGridPane.toBack();
        goFront(boardPane);
    }
    @FXML
    public void chooseCard(ActionEvent actionEvent){
        chooseCardPane.toBack();
        goFront(boardPane);
    }


    @FXML
    public void moveMarble() {
        for(ImageView m: marbles){
            m.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Dragboard db = m.startDragAndDrop(TransferMode.ANY);
                ClipboardContent content = new ClipboardContent();
                content.putImage(m.getImage());
                db.setContent(content);
                event.consume();
            }
        });}

        for(ImageView s: marketSpots){
            s.setOnDragOver(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    dragEvent.acceptTransferModes(TransferMode.MOVE);
                    dragEvent.consume();
                }

            });
        }

        for(ImageView s: marketSpots){
            s.setOnDragDropped(new EventHandler<DragEvent>() {
                @Override
                public void handle(DragEvent dragEvent) {
                    s.setImage(dragEvent.getDragboard().getImage());
                }
            });
        }



    }

}
