package it.polimi.ingsw.messages.toServer.actions;

import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.messages.toServer.ToServerMessage;
import it.polimi.ingsw.messages.toServer.ToServerMessageHandler;

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

    }
}
