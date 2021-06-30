package it.polimi.ingsw.commonFiles.messages.toClient.updates;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ToClientMessageVisitor;

public class InitMarket extends Message implements GameUpdate {
    private final String[][] tray;
    private final String extraMarble;

    public InitMarket(String[][] tray, String extraMarble) {
        this.tray = tray;
        this.extraMarble = extraMarble;
    }

    public String[][] getTray() {
        return tray;
    }

    public String getExtraMarble() {
        return extraMarble;
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
