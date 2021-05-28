package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.View;
import javafx.application.Platform;

public class GUIView extends View {

    @Override
    public void process(String input) {
    }

    @Override
    public void show(String element) {
        //Platform.runLater(() -> App.out.setText(element));
    }

    @Override
    public void showError(String error) {
        Platform.runLater(() -> App.error.setText(error));
    }

    @Override
    public void showUpdate(String update) {
        //Platform.runLater(() -> App.out.setText(update));
    }

    @Override
    public void refresh(String... elements) {

    }

    @Override
    public void setToDo(String id, String toDo) {

    }

    @Override
    public void showHandler(String[] commandSlice) {

    }

    @Override
    public void setNick(String[] commandSlice) {

    }

    @Override
    public void setGame(String[] commandSlice) {

    }

    @Override
    public void choose(String[] commandSlice) {

    }

    @Override
    public void activateProduction(String[] commandSlice) {

    }

    @Override
    public void buyDevCard(String[] commandSlice) {

    }

    @Override
    public void useMarket(String[] commandSlice) {

    }

    @Override
    public void chooseWhite(String[] commandSlice) {

    }

    @Override
    public void useLeader(String[] commandSlice) {

    }

    @Override
    public void deployRes(String[] commandSlice) {

    }

    @Override
    public void discardLeader(String[] commandSlice) {

    }

    @Override
    public void takeRes(String[] commandSlice) {

    }

    @Override
    public void getRes(String[] commandSlice) {

    }

    @Override
    public void printHead() {

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
}
