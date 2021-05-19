package it.polimi.ingsw.commonFiles.messages.toServer.actions;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toServer.ToServerMessage;
import it.polimi.ingsw.commonFiles.messages.toServer.ToServerMessageHandler;

public class UseLeader extends Message implements ToServerMessage {
    int IDCard;

    public UseLeader(int IDCard) {
        this.IDCard = IDCard;
    }

    public int getIDCard() {
        return IDCard;
    }

    @Override
    public void accept(ToServerMessageHandler v) {
        v.visit(this);
    }
}