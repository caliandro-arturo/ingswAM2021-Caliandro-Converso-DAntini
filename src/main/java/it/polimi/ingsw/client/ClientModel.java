package it.polimi.ingsw.client;

public class ClientModel {
    private String playerUsername;
    private int numOfPlayers;
    private int position;           //position on the table (for turns order)
    private boolean isLast;

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
}
