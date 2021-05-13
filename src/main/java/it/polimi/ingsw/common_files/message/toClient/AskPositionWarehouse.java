package it.polimi.ingsw.common_files.message.toClient;

import it.polimi.ingsw.common_files.message.Message;

public class AskPositionWarehouse extends Message implements ToClientMessage {
    private int position;

    public AskPositionWarehouse(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public void accept(ClientMessageVisitor v) {
    }

}
