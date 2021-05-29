package it.polimi.ingsw.commonFiles.messages.toServer;

import it.polimi.ingsw.commonFiles.messages.Message;

public class Back extends Message implements ToServerMessage {
    @Override
    public void accept(ToServerMessageHandler v) {
        v.visit(this);
    }
}
