package it.polimi.ingsw.client;

import it.polimi.ingsw.client.model.*;
import it.polimi.ingsw.server.model.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class ClientModel {
    private String playerUsername;
    private int numOfPlayers;
    private int position;           //position on the table (for turns order)
    private boolean isLast;
    private DevelopmentGrid developmentGrid;
    private Market market;
    private Board board;
    private LeaderHand leaderHand;
    private FullBoard fullBoard;
    private HashMap<String,Board> othersBoards = new HashMap<>();

    public int getPosition() {
        return position;
    }

    public FullBoard getFullBoard() {
        return fullBoard;
    }

    public void setFullBoard(FullBoard fullBoard) {
        this.fullBoard = fullBoard;
    }

    public HashMap<String, Board> getOthersBoards() {
        return othersBoards;
    }

    public void setOthersBoards(HashMap<String, Board> othersBoards) {
        this.othersBoards = othersBoards;
    }

    public DevelopmentGrid getDevelopmentGrid() {
        return developmentGrid;
    }

    public void setDevelopmentGrid(DevelopmentGrid developmentGrid) {
        this.developmentGrid = developmentGrid;
    }

    public String getPlayerUsername() {
        return playerUsername;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public boolean isLast() {
        return isLast;
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

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public LeaderHand getLeaderHand() {
        return leaderHand;
    }

    public void setLeaderHand(LeaderHand leaderHand) {
        this.leaderHand = leaderHand;
    }

}
