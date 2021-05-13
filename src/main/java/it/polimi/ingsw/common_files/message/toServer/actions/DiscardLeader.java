package it.polimi.ingsw.common_files.message.toServer.actions;

import it.polimi.ingsw.common_files.message.Message;
import it.polimi.ingsw.common_files.message.toServer.ToServerMessage;
import it.polimi.ingsw.common_files.message.toServer.ToServerMessageHandler;

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
