package it.polimi.ingsw.client;

import it.polimi.ingsw.client.model.*;
import it.polimi.ingsw.commonFiles.model.Resource;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.*;

public class ClientModel {
    private String playerUsername;
    private String currentPlayerInTheGame;
    private String currentTurnPhase;
    private int numOfPlayers;
    private int position;                                                   //position on the table (for turns order)
    private boolean isLast;
    private DevelopmentGrid developmentGrid;
    private Market market;
    private LeaderHand leaderHand;
    private int resourcesToGet = 0;
    private final ObjectProperty<LinkedHashMap<String, Board>> boards = new SimpleObjectProperty<>();
    private boolean gameStarted = false;                                    //true if leader cards are distributed
    private boolean gameSelected = false;

    public ClientModel() {
        boards.
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
        return currentPlayerInTheGame;
    }

    public String getCurrentTurnPhase() {
        return currentTurnPhase;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public boolean isLast() {
        return isLast;
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
        return leaderHand;
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
        this.currentPlayerInTheGame = currentPlayerInTheGame;
    }

    public void setCurrentTurnPhase(String currentTurnPhase) {
        this.currentTurnPhase = currentTurnPhase;
    }

    public void setBoards(List<String> usernames){
        usernames.forEach(this::setBoards);
    }

    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public void setDevelopmentGrid(DevelopmentGrid developmentGrid) {
        this.developmentGrid = developmentGrid;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public void setLeaderHand(LeaderHand leaderHand) {
        this.leaderHand = leaderHand;
    }

    public void setResourcesToGet(int resourcesToGet) {
        this.resourcesToGet = resourcesToGet;
    }

    public void decrementResourcesToGet() {
        resourcesToGet--;
    }

    public void updateResource(int[] cost, ArrayList<Resource> resource){
        getBoard().removeResource(cost, resource);
    }

    public void setBoards(String username) {
        boards.get().put(username,new Board());
    }
}
