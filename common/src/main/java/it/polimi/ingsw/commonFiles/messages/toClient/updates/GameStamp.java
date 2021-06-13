package it.polimi.ingsw.commonFiles.messages.toClient.updates;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ToClientMessageVisitor;

import java.util.HashMap;

public class GameStamp extends Message implements GameUpdate {
    private final String gameStatus;

    public GameStamp(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    @Override
    public void accept(ToClientMessageVisitor v) {
        v.visit(this);
    }

    @Override
    public void accept(UpdateHandler v) {
        v.visit(this);
    }
}
