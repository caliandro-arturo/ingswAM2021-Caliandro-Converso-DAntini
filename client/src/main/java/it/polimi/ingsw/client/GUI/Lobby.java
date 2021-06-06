package it.polimi.ingsw.client.GUI;

import javafx.beans.property.SimpleStringProperty;

public class Lobby {
    private final SimpleStringProperty lobbyName;
    private final SimpleStringProperty playersNum;

    public Lobby(String lobbyName, int lobbyConnectedPlayers, int maxPlayersNum) {
        this.lobbyName = new SimpleStringProperty(lobbyName);
        this.playersNum = new SimpleStringProperty(lobbyConnectedPlayers + "/" + maxPlayersNum);
    }

    public String getLobbyName() {
        return lobbyName.get();
    }

    public String getPlayersNum() {
        return playersNum.get();
    }

    public void setLobbyName(String lobbyName) {
        this.lobbyName.set(lobbyName);
    }

    public void setPlayersNum(String playersNum) {
        this.playersNum.set(playersNum);
    }
}
