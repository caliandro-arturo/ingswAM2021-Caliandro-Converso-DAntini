package it.polimi.ingsw.commonFiles.messages.toClient.updates;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ToClientMessageVisitor;

public class InitialResourcesAmount extends Message implements GameUpdate {
    private final int resourcesAmount;

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

    @Override
    public void accept(UpdateHandler v) {
        v.visit(this);
    }
}
