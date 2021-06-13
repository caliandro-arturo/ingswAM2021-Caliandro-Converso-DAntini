package it.polimi.ingsw.commonFiles.messages.toClient.updates;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ToClientMessageVisitor;

public class TimeUp extends Message implements GameUpdate {
    private final boolean isTimeEffectivelyUp;

    public TimeUp(boolean isTimeEffectivelyUp) {
        this.isTimeEffectivelyUp = isTimeEffectivelyUp;
    }

    public boolean isTimeEffectivelyUp() {
        return isTimeEffectivelyUp;
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
