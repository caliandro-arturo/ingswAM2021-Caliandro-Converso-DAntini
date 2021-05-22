package it.polimi.ingsw.commonFiles.messages.toClient;

import it.polimi.ingsw.commonFiles.messages.Message;

public class AskWhiteMarble extends Message implements ToClientMessage {
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
}
