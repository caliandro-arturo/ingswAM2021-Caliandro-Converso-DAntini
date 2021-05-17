package it.polimi.ingsw.commonFiles.messages.toClient;

import it.polimi.ingsw.commonFiles.messages.Message;

public class TablePosition extends Message implements ToClientMessage {
    private final int position;
    public TablePosition(int number) {
        this.position = number;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public void accept(ToClientMessageVisitor v) {
        v.visit(this);
    }
}
