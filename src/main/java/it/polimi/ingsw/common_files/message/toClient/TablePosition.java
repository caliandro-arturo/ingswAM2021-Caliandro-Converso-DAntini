package it.polimi.ingsw.common_files.message.toClient;

import it.polimi.ingsw.common_files.message.Message;

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
