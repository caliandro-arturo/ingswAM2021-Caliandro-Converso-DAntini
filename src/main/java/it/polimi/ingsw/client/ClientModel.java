package it.polimi.ingsw.client;

import it.polimi.ingsw.client.model.DevelopmentGrid;

import it.polimi.ingsw.client.model.Board;
import it.polimi.ingsw.client.model.DevelopmentGrid;
import it.polimi.ingsw.client.model.LeaderHand;
import it.polimi.ingsw.client.model.Market;

public class ClientModel {
    private String playerUsername;
    private int numOfPlayers;
    private int position;           //position on the table (for turns order)
    private boolean isLast;
    private DevelopmentGrid developmentGrid;
    private Market market;
    private Board board;
    private LeaderHand leaderHand;

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
