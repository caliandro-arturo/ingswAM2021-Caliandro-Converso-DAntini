package it.polimi.ingsw.commonFiles.messages.toServer;

import it.polimi.ingsw.commonFiles.messages.Message;

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
