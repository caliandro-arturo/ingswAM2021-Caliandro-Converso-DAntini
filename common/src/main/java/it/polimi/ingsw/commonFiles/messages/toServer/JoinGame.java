package it.polimi.ingsw.commonFiles.messages.toServer;

import it.polimi.ingsw.commonFiles.messages.Message;

public class JoinGame extends Message implements ToServerMessage {
    private final String gameName;

    public JoinGame(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    @Override
    public void accept(ToServerMessageHandler v) {
        v.visit(this);
    }
}
