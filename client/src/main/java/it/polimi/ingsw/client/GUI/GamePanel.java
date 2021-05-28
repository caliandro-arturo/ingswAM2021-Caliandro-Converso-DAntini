package it.polimi.ingsw.client.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
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
    ArrayList<ImageView> resPlaces = new ArrayList<>(){{
        add(res1);
        add(res21);
        add(res22);
        add(res31);
        add(res32);
        add(res33);
    }};


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

    public void takeDefault(ActionEvent actionEvent) {

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
    }
    @FXML
    public void moveRes(){
        hand.setOnDragDetected(event1 -> {
            Dragboard db = hand.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString("abbiamo trasferito la risorsa");
            db.setContent(content);
            event1.consume();
        });
    }

    @FXML
    public void acceptMove(){
        res1.setOnDragOver(dragEvent -> {
            dragEvent.acceptTransferModes(TransferMode.ANY);
            dragEvent.consume();
        });
        res1.setOnDragDropped(dragEvent -> {
            res1.setImage(handList.get(hand.getCurrentPageIndex()).getImage());
            dragEvent.consume();
        });
        res21.setOnDragOver(dragEvent -> {
            dragEvent.acceptTransferModes(TransferMode.ANY);
            dragEvent.consume();
        });
        res21.setOnDragDropped(dragEvent -> {
            res21.setImage(handList.get(hand.getCurrentPageIndex()).getImage());
            dragEvent.consume();
        });
        res22.setOnDragOver(dragEvent -> {
            dragEvent.acceptTransferModes(TransferMode.ANY);
            dragEvent.consume();
        });
        res22.setOnDragDropped(dragEvent -> {
            res22.setImage(handList.get(hand.getCurrentPageIndex()).getImage());
            dragEvent.consume();
        });
        res31.setOnDragOver(dragEvent -> {
            dragEvent.acceptTransferModes(TransferMode.ANY);
            dragEvent.consume();
        });
        res31.setOnDragDropped(dragEvent -> {
            res31.setImage(handList.get(hand.getCurrentPageIndex()).getImage());
            dragEvent.consume();
        });
        res32.setOnDragOver(dragEvent -> {
            dragEvent.acceptTransferModes(TransferMode.ANY);
            dragEvent.consume();
        });
        res32.setOnDragDropped(dragEvent -> {
            res32.setImage(handList.get(hand.getCurrentPageIndex()).getImage());
            dragEvent.consume();
        });
        res33.setOnDragOver(dragEvent -> {
            dragEvent.acceptTransferModes(TransferMode.ANY);
            dragEvent.consume();
        });
        res33.setOnDragDropped(dragEvent -> {
            res33.setImage(handList.get(hand.getCurrentPageIndex()).getImage());
            dragEvent.consume();
        });
    }
    @FXML
    public void showMarket(ActionEvent actionEvent){
        marketPane.toFront();
        boardPane.toBack();
        boardPane.setDisable(true);
        boardPane.setOpacity(0.5);
        mb00.setImage(whiteMarble);
        mb01.setImage(yellowMarble);
        mb02.setImage(redMarble);
        mb10.setImage(blueMarble);
        mb11.setImage(greyMarble);
        mb12.setImage(redMarble);
        mb20.setImage(yellowMarble);
        mb21.setImage(blueMarble);
        mb22.setImage(purpleMarble);
        mb30.setImage(purpleMarble);
        mb31.setImage(whiteMarble);
        mb32.setImage(purpleMarble);
        mbEx.setImage(purpleMarble);
    }

    @FXML
    public void acceptMarbleMove(){
        col0.setOnDragOver(dragEvent -> {
            dragEvent.acceptTransferModes(TransferMode.MOVE);
            dragEvent.consume();
        });col0.setOnDragOver(dragEvent ->{
            col0.setImage(dragEvent.getDragboard().getImage());
            dragEvent.consume();
        });
    }
    @FXML
    public void moveMarble(){
        mb00.setOnDragDetected(event1 -> {
            Dragboard db = mb00.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString("abbiamo trasferito la risorsa");
            db.setContent(content);
            event1.consume();
        });
    }
    public void buyResources(ActionEvent actionEvent) {
        marketPane.toBack();
        boardPane.setDisable(false);
        boardPane.setOpacity(1);
    }
}
