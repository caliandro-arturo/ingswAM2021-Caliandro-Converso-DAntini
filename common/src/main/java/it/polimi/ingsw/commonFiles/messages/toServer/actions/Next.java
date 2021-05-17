package it.polimi.ingsw.commonFiles.messages.toServer.actions;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toServer.ToServerMessage;
import it.polimi.ingsw.commonFiles.messages.toServer.ToServerMessageHandler;

/**
 * Message sent to go to the next turn phase.
 */
public class Next extends Message implements ToServerMessage {
    @Override
    public void accept(ToServerMessageHandler v) {
        v.visit(this);
    }
}
