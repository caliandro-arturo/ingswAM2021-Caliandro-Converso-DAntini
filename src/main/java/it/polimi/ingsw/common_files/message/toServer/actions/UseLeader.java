package it.polimi.ingsw.common_files.message.toServer.actions;

import it.polimi.ingsw.common_files.message.Message;
import it.polimi.ingsw.common_files.message.toServer.ToServerMessage;
import it.polimi.ingsw.common_files.message.toServer.ToServerMessageHandler;

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
