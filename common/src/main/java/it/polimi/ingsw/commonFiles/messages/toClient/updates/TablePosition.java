package it.polimi.ingsw.commonFiles.messages.toClient.updates;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ToClientMessage;
import it.polimi.ingsw.commonFiles.messages.toClient.ToClientMessageVisitor;

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
