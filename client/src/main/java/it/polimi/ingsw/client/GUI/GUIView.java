package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.View;
import it.polimi.ingsw.commonFiles.messages.toServer.SetNickname;
import javafx.application.Platform;

public class GUIView extends View {


    @Override
    public void process(String input) {

    }

    @Override
    public void show(String element) {
        switch (element) {
            case "hand": {}
        }
        //Platform.runLater(() -> App.out.setText(element));
    }

    @Override
    public void showError(String error) {
        Platform.runLater(() -> App.error.setText(error));
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
        getController().sendMessage(new SetNickname(commandSlice[0]));
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
    public void back() {

    }

    @Override
    public void discardRes(String[] commandSlice) {

    }

    @Override
    public void displayEndingScore(String[] categories, int[] scores, int ranking) {

    }

    @Override
    public void showTablePosition(int position) {

    }

    @Override
    public void showNicknameSet() {
        Platform.runLater(() -> App.out.setText("Your nickname has been set."));
    }

    @Override
    public void showResume() {

    }

    @Override
    public void showCreateGame() {
        Launcher launcher = (Launcher) App.controller;
        launcher.setCurrentPane(launcher.createGamePane);
    }

    @Override
    public void showGameSet(int playersNum) {

    }

    @Override
    public void showWaitGameCreation() {
        Launcher launcher = (Launcher) App.controller;
        launcher.waitLabel.setText("Waiting the creation of the game...");
        launcher.setCurrentPane(launcher.waitPane);
    }

    @Override
    public void showWaitGameStart() {
        Launcher launcher = (Launcher) App.controller;
        Platform.runLater(() -> launcher.waitLabel.setText("Waiting the start of the game..."));
        launcher.setCurrentPane(launcher.waitPane);
    }

    @Override
    public void showGameStarted() {
        App.setScene("board", "Masters of Renaissance");
    }

    @Override
    public void showNewPlayer(String playerNick) {

    }

    @Override
    public void showPlayerLeft(String playerNick) {

    }

    @Override
    public void showGotResource(String resource) {

    }

    @Override
    public void showResourceTaken() {

    }

    @Override
    public void showMarketUsed() {

    }

    @Override
    public void showVaticanReport(int reportNum, boolean isPassed) {

    }

    @Override
    public void showLastTurns(String reason) {

    }
}
