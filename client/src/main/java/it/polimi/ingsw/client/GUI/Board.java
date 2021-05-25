package it.polimi.ingsw.client.GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TabPane;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Objects;

public class Board {
    @FXML
    TabPane pane;
    @FXML
    ImageView cross;
    @FXML
    ImageView crossB;
    @FXML
    ImageView tile2;
    @FXML
    ImageView tile3;
    @FXML
    ImageView tile4;
    @FXML
    Pagination hand;
    @FXML
    ImageView itemToMove;
    @FXML
    ImageView res1;
    @FXML
    ImageView res21;
    @FXML
    ImageView res22;
    @FXML
    ImageView res31;
    @FXML
    ImageView res32;
    @FXML
    ImageView res33;

    int pos = 1;
    int posB = 1;
    Image coin = new Image(String.valueOf(getClass().getResource("/png/coin.png")));
    Image serf = new Image(String.valueOf(getClass().getResource("/png/serf.png")));
    Image shield = new Image(String.valueOf(getClass().getResource("/png/shield.png")));
    Image stone = new Image(String.valueOf(getClass().getResource("/png/stone.png")));
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



    public Board() throws FileNotFoundException {
    }

    @FXML
    public void increasePos(ActionEvent actionEvent){
        ++pos;
        checkTile(pos);
        if (pos<=2)
            moveRight(cross);
        else if(pos<5)
            moveUp(cross);
        else if(pos<10)
            moveRight(cross);
        else if(pos<12)
            moveDown(cross);
        else if(pos<17)
            moveRight(cross);
        else if(pos<19)
            moveUp(cross);
        else if(pos<25)
            moveRight(cross);
    }


    @FXML
    public void increasePosB(ActionEvent actionEvent){
        ++posB;
        checkTile(posB);
        if (posB<=2){
            moveRight(crossB);
            moveRight(crossB);}
        else if(posB<5)
            moveUp(crossB);
        else if(posB<10)
            moveRight(crossB);
        else if(posB<12)
            moveDown(crossB);
        else if(posB<17)
            moveRight(crossB);
        else if(posB<19)
            moveUp(crossB);
        else if(posB<25)
            moveRight(crossB);

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

    public void checkTile(int pos){
        if(pos == 9)
            tile2.setOpacity(100);
        else if (pos == 17)
            tile3.setOpacity(100);
        else if (pos== 24)
            tile4.setOpacity(100);
    }
    @FXML
    public void slideRes(ActionEvent event){
        fill(handList);
    }

    public void fill(ArrayList<ImageView> handList){
        for (ImageView view : handList) {
            view.setFitHeight(70);
            view.setFitWidth(70);
        }
        hand.setPageFactory(handList::get);
    }


    /*@FXML
    public void moveRes(){
        itemToMove.setOnDragDetected(event1 -> {
            Dragboard db = itemToMove.startDragAndDrop(TransferMode.ANY);
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
            res1.setImage(itemToMove.getImage());
            dragEvent.consume();
        });
    }*/
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
}
