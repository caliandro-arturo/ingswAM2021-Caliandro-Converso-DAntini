package it.polimi.ingsw.commonFiles.messages.toServer;

import it.polimi.ingsw.commonFiles.messages.Message;

import java.util.ArrayList;
import java.util.List;

public class GamesList extends Message implements ToServerMessage {

    private final List<String> lobbiesName = new ArrayList<>();
    private final List<Integer> lobbiesCurrentConnectedClientsNumber = new ArrayList<>();
    private final List<Integer> lobbiesMaxPlayersNum = new ArrayList<>();

    public List<String> getLobbiesName() {
        return lobbiesName;
    }

    public List<Integer> getLobbiesCurrentConnectedClientsNumber() {
        return lobbiesCurrentConnectedClientsNumber;
    }

    public List<Integer> getLobbiesMaxPlayersNum() {
        return lobbiesMaxPlayersNum;
    }

    public void addLobby(String lobbyName, int connectedPLayers, int maxPlayersNum) {
        lobbiesName.add(lobbyName);
        lobbiesCurrentConnectedClientsNumber.add(connectedPLayers);
        lobbiesMaxPlayersNum.add(maxPlayersNum);
    }

    @Override
    public void accept(ToServerMessageHandler v) {
        v.visit(this);
    }
}
