package it.polimi.ingsw.commonFiles.messages.toClient.updates;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ToClientMessageVisitor;

public class VaticanReport extends Message implements GameUpdate {
    private int num;
    private boolean passed;

    public int getNum() {
        return num;
    }

    public boolean isPassed(){
        return passed;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
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
