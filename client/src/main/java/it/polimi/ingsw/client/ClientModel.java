package it.polimi.ingsw.client;

import it.polimi.ingsw.client.model.*;
import it.polimi.ingsw.commonFiles.model.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClientModel {
    private String playerUsername;
    private String currentPlayerInTheGame;
    private String currentTurnPhase;
    private int numOfPlayers;
    private int position;           //position on the table (for turns order)
    private boolean isLast;
    private DevelopmentGrid developmentGrid;
    private Market market;
    private LeaderHand leaderHand;
    private HashMap<String, Board> boards = new HashMap<>();
    private boolean gameStarted = false;                                //true if leader cards are distributed

    public boolean isGameStarted(){
        return gameStarted;
    }

    public String getPlayerUsername() {
        return playerUsername;
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
        return boards.get(playerUsername);
    }

    public LeaderHand getLeaderHand() {
        return leaderHand;
    }

    public Board getBoard(String playerUsername) {
        return boards.get(playerUsername);
    }



    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
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

    public void updateResource(int[] cost, ArrayList<Resource> resource){
        getBoard().removeResource(cost, resource);
    }

    public void setBoards(String username) {
        boards.put(username,new Board());
    }

}
