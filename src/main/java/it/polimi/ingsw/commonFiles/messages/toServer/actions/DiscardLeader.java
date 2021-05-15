package it.polimi.ingsw.commonFiles.messages.toServer.actions;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toServer.ToServerMessage;
import it.polimi.ingsw.commonFiles.messages.toServer.ToServerMessageHandler;

public class DiscardLeader extends Message implements ToServerMessage {
    private int pos;

    public DiscardLeader(int pos) {
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }

    @Override
    public void accept(ToServerMessageHandler v) {
        v.visit(this);
    }
}
