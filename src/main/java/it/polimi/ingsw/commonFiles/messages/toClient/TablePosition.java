package it.polimi.ingsw.commonFiles.messages.toClient;

import it.polimi.ingsw.commonFiles.messages.Message;

public class TablePosition extends Message implements ToClientMessage {
    private int position;
    public TablePosition(int number) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public void accept(ClientMessageVisitor v) {
        v.visit(this);
    }
}
