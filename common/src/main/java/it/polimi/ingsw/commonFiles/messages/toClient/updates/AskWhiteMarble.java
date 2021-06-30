package it.polimi.ingsw.commonFiles.messages.toClient.updates;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ToClientMessageVisitor;

public class AskWhiteMarble extends Message implements GameUpdate {
    private final int whiteMarblesToChoose;

    public AskWhiteMarble(int whiteMarblesToChoose) {
        this.whiteMarblesToChoose = whiteMarblesToChoose;
    }

    public int getWhiteMarblesToChoose() {
        return whiteMarblesToChoose;
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
