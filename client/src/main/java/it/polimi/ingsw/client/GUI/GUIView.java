package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.View;
import it.polimi.ingsw.commonFiles.messages.toServer.SetNickname;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUIView extends View {

    @Override
    public void process(String input) {

    }

    @Override
    public void show(String element) {
        switch (element) {
            case "hand": {}
        }
        Platform.runLater(() -> App.out.setText(element));
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
    public void joinGame(String[] commandSlice) {

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
    public void showGamesList(List<String> lobbiesName, List<Integer> lobbiesCurrentConnectedClientsNumber, List<Integer> lobbiesMaxPlayersNum) {
        Launcher launcher = (Launcher) App.controller;
        for (int i = 0; i < lobbiesName.size(); i++) {
            launcher.lobbies.add(new Lobby(lobbiesName.get(i), lobbiesCurrentConnectedClientsNumber.get(i), lobbiesMaxPlayersNum.get(i)));
        }

    }

    @Override
    public void showLorenzoAction(String announcement) {

    }

    @Override
    public void showTablePosition(int position) {

    }

    @Override
    public void showNicknameSet() {
        Platform.runLater(() -> App.out.setText("Your nickname has been set."));
        Launcher launcher = (Launcher) App.controller;
        launcher.setCurrentPane(launcher.createOrJoinPane);
    }

    @Override
    public void showGameJoined() {
        show("You joined the game.");
        Launcher launcher = (Launcher) App.controller;
        launcher.setCurrentPane(launcher.waitPane);
    }

    @Override
    public void showResume() {
        Launcher launcher = (Launcher) App.controller;
        launcher.setCurrentPane(launcher.resumePane);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4),
                event -> Platform.runLater(() -> App.setScene("board", "Masters of Renaissance"))));
        timeline.play();
    }

    @Override
    public void showCreateGame() {
        Launcher launcher = (Launcher) App.controller;
        launcher.setCurrentPane(launcher.createGamePane);
    }

    @Override
    public void showGameSet(int playersNum) {
        show("Your game has been set.");
        Launcher launcher = (Launcher) App.controller;
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
        Platform.runLater(() -> App.setScene("board", "Masters of Renaissance"));
    }

    @Override
    public void showNewPlayer(String playerNick) {
        show(playerNick + " joined the game.");
    }

    @Override
    public void showPlayerLeft(String playerNick) {
        show(playerNick + " has disconnected.");
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

    @Override
    public void showConnectionLost() {
        Platform.runLater(() -> App.setScene("connectionLost", "Masters of Renaissance"));
    }
}
