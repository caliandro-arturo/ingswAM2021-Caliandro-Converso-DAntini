package it.polimi.ingsw.commonFiles.messages.toServer;

import it.polimi.ingsw.commonFiles.messages.Message;

/**
 * Message sent to go to the next turn phase.
 */
public class Next extends Message implements ToServerMessage {
    @Override
    public void accept(ToServerMessageHandler v) {
        v.visit(this);
    }
}
