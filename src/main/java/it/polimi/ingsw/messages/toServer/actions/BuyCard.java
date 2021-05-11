package it.polimi.ingsw.messages.toServer.actions;

import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.messages.toServer.ToServerMessage;
import it.polimi.ingsw.messages.toServer.ToServerMessageHandler;

public class BuyCard extends Message implements ToServerMessage {
    @Override
    public void accept(ToServerMessageHandler v) {

    }
}
