package it.polimi.ingsw.commonFiles.messages.toClient;

import it.polimi.ingsw.commonFiles.messages.Message;

public class InitialResourcesAmount extends Message implements ToClientMessage {
    private int resourcesAmount;

    public InitialResourcesAmount(int resourcesAmount) {
        this.resourcesAmount = resourcesAmount;
    }

    public int getResourcesAmount() {
        return resourcesAmount;
    }

    @Override
    public void accept(ToClientMessageVisitor v) {
        v.visit(this);
    }
}
