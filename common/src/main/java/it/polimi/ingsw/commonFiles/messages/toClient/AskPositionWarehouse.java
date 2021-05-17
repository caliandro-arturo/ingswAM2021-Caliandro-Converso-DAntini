package it.polimi.ingsw.commonFiles.messages.toClient;

import it.polimi.ingsw.commonFiles.messages.Message;

public class AskPositionWarehouse extends Message implements ToClientMessage {
    private int position;

    public AskPositionWarehouse(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public void accept(ToClientMessageVisitor v) {
    }

}
