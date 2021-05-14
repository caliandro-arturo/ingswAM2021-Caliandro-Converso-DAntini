package it.polimi.ingsw.common_files.message.toClient.updates;

import it.polimi.ingsw.client.UpdateHandler;
import it.polimi.ingsw.common_files.message.Message;
import it.polimi.ingsw.common_files.message.toClient.ClientMessageVisitor;

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
