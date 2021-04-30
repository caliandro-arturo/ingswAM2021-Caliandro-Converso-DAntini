package it.polimi.ingsw.messages.toServer.actions;

import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.messages.toServer.ServerMessageVisitor;
import it.polimi.ingsw.messages.toServer.ToServerMessage;

public class UseMarket extends Message implements ToServerMessage {
    private final char rowOrColumn;
    private final int num;

    @Override
    public void accept(ServerMessageVisitor v) {
        v.visit(this);
    }

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
}
