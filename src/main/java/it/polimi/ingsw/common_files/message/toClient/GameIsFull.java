package it.polimi.ingsw.common_files.message.toClient;

import it.polimi.ingsw.common_files.message.Message;

public class GameIsFull extends Message implements ToClientMessage {
    @Override
    public void accept(ClientMessageVisitor v) {
        v.visit(this);
    }
}