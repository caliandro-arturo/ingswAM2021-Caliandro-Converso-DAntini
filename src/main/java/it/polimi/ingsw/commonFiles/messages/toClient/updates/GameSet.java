package it.polimi.ingsw.commonFiles.messages.toClient.updates;

import it.polimi.ingsw.client.UpdateHandler;
import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ClientMessageVisitor;

/**
 * Message sent when the game has been set.
 */
public class GameSet extends Message implements GameUpdate {
    private final int playersNum;

    public GameSet(int playersNum) {
        this.playersNum = playersNum;
    }

    public int getPlayersNum() {
        return playersNum;
    }

    @Override
    public void accept(UpdateHandler v) {
        v.visit(this);
    }

    @Override
    public void accept(ClientMessageVisitor v) {
        v.visit(this);
    }
}
