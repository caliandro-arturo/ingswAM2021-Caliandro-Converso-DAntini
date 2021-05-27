package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Objects;

public class GUIView extends View {
    @FXML
    TextField text;
    int pos = 1;
    int posB = 1;
    @FXML
    private TabPane mainPane;


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


    @Override
    public void process(String input) {

    }

    @Override
    public void show(String element) {

    }


    @Override
    public void showError(String error) {

    }

    @Override
    public void showUpdate(String update) {

    }

    @Override
    public void refresh(String... elements) {

    }

    @Override
    public void setToDo(String id, String toDo) {

    }

    @Override
    public void deleteToDo(String id) {

    }

    @Override
    public void displayEndingScore(String[] categories, int[] scores, int ranking) {

    }

    @Override
    public void showTablePosition(int position) {

    }

    public void takeDefault(ActionEvent actionEvent) {

    }

    public void takeText(ActionEvent actionEvent) {
        System.out.println("ciao");
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
        mainPane.toBack();
    }
    public void buyResources(ActionEvent actionEvent) {
        marketPane.toBack();
    }
}
