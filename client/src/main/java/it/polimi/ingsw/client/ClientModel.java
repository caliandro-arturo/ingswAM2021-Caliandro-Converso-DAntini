package it.polimi.ingsw.client;

import it.polimi.ingsw.client.model.*;
import it.polimi.ingsw.commonFiles.model.Resource;

import java.util.ArrayList;
import java.util.HashMap;

public class ClientModel {
    private String playerUsername;
    private int numOfPlayers;
    private int position;           //position on the table (for turns order)
    private boolean isLast;
    private DevelopmentGrid developmentGrid;
    private Market market;
    private LeaderHand leaderHand;
    private FullBoard fullBoard;
    private HashMap<String, Board> boards = new HashMap<>();
    private String currentTurnPhase;

    public String getPlayerUsername() {
        return playerUsername;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public int getPosition() {
        return position;
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

    public FullBoard getFullBoard() {
        return fullBoard;
    }

    public Board getOtherPlayerBoard(String playerUsername) {
        return boards.get(playerUsername);
    }

    public String getCurrentTurnPhase() {
        return currentTurnPhase;
    }

    public void setPlayerUsername(String playerUsername) {
        this.playerUsername = playerUsername;
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
    public void setFullBoard(FullBoard fullBoard) {
        this.fullBoard = fullBoard;
    }

    public void setBoards(HashMap<String, Board> boards) {
        this.boards = boards;
    }

    public void setCurrentTurnPhase(String currentTurnPhase) {
        this.currentTurnPhase = currentTurnPhase;
    }
}
