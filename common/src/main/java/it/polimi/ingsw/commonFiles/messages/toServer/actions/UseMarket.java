package it.polimi.ingsw.commonFiles.messages.toServer.actions;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toServer.ToServerMessage;
import it.polimi.ingsw.commonFiles.messages.toServer.ToServerMessageHandler;

/**
 * Message sent to use the market.
 */
public class UseMarket extends Message implements ToServerMessage {
    private final char rowOrColumn;
    private final int num;

    public UseMarket(char rowOrColumn, int num) {
        this.rowOrColumn = rowOrColumn;
        this.num = num;
    }

    public char getRowOrColumn() {
        return rowOrColumn;
    }

    public int getNum() {
        return num;
    }

    @Override
    public void accept(ToServerMessageHandler v) {
        v.visit(this);
    }
}
