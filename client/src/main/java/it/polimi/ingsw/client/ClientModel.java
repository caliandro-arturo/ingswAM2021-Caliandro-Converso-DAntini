package it.polimi.ingsw.client;

import it.polimi.ingsw.client.model.*;
import it.polimi.ingsw.commonFiles.model.Resource;
import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * Representation of the model for client.
 */
public class ClientModel {
    private String playerUsername;
    private final StringProperty currentPlayerInTheGame = new SimpleStringProperty();
    private final StringProperty currentTurnPhase = new SimpleStringProperty();
    private final BooleanProperty isFinished = new SimpleBooleanProperty(true);
    private int numOfPlayers;
    private DevelopmentGrid developmentGrid;
    private Market market;
    private final ObjectProperty<LeaderHand> leaderHand = new SimpleObjectProperty<>(new LeaderHand());
    private int resourcesToGet = 0;
    private final ObjectProperty<LinkedHashMap<String, Board>> boards = new SimpleObjectProperty<>(new LinkedHashMap<>());
    private boolean gameStarted = false;                                    //true if leader cards are distributed
    private boolean gameSelected = false;
    private final ObjectProperty<Integer> whiteMarbleQuantity = new SimpleObjectProperty<>(0);

    public int getWhiteMarbleQuantity() {
        return whiteMarbleQuantity.get();
    }

    public ObjectProperty<Integer> whiteMarbleQuantityProperty() {
        return whiteMarbleQuantity;
    }

    public boolean isGameStarted(){
        return gameStarted;
    }

    public boolean isGameSelected() {
        return gameSelected;
    }

    public String getPlayerUsername() {
        return playerUsername;
    }

    public Set<String> getPlayersUsernames() {
        return boards.get().keySet();
    }

    public String getCurrentPlayerInTheGame() {
        return currentPlayerInTheGame.get();
    }

    public StringProperty currentPlayerInTheGameProperty() {
        return currentPlayerInTheGame;
    }

    public String getCurrentTurnPhase() {
        return currentTurnPhase.get();
    }

    public StringProperty currentTurnPhaseProperty() {
        return currentTurnPhase;
    }

    public boolean isFinished() {
        return isFinished.get();
    }

    public BooleanProperty isFinishedProperty() {
        return isFinished;
    }

    public void setFinished(boolean isFinished) {
        this.isFinished.set(isFinished);
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public DevelopmentGrid getDevelopmentGrid() {
        return developmentGrid;
    }

    public Market getMarket() {
        return market;
    }

    public Board getBoard() {
        return boards.get().get(playerUsername);
    }

    public ObjectProperty<LinkedHashMap<String, Board>> boardsProperty() {
        return boards;
    }

    public LinkedHashMap<String, Board> getBoards() {
        return boards.get();
    }

    public LeaderHand getLeaderHand() {
        return leaderHand.get();
    }

    public int getResourcesToGet() {
        return resourcesToGet;
    }

    public Board getBoard(String playerUsername) {
        return boards.get().get(playerUsername);
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public void setGameSelected(boolean gameSelected) {
        this.gameSelected = gameSelected;
    }

    public void setPlayerUsername(String playerUsername) {
        this.playerUsername = playerUsername;
    }

    public void setCurrentPlayerInTheGame(String currentPlayerInTheGame) {
        this.currentPlayerInTheGame.set(currentPlayerInTheGame);
    }

    public void setWhiteMarbleQuantity(int quantity){
        whiteMarbleQuantity.set(quantity);
    }

    public void setCurrentTurnPhase(String currentTurnPhase) {
        this.currentTurnPhase.set(currentTurnPhase);
    }

    public void setBoards(List<String> usernames){
        usernames.forEach(this::setBoards);
    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public void setDevelopmentGrid(DevelopmentGrid developmentGrid) {
        this.developmentGrid = developmentGrid;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public void setLeaderHand(ArrayList<LeaderCard> leaderHand) {
        this.leaderHand.get().handProperty().addAll(leaderHand);
    }

    public void setResourcesToGet(int resourcesToGet) {
        this.resourcesToGet = resourcesToGet;
    }

    public void decrementResourcesToGet() {
        resourcesToGet--;
    }

    public void removeResource(int[] cost, ArrayList<Resource> resource){
        getBoard().removeResource(cost, resource);
    }

    public void setBoards(String username) {
        boards.get().put(username,new Board());
    }
}
