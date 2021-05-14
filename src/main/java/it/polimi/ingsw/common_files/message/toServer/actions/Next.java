package it.polimi.ingsw.common_files.message.toServer.actions;

import it.polimi.ingsw.common_files.message.Message;
import it.polimi.ingsw.common_files.message.toServer.ToServerMessage;
import it.polimi.ingsw.common_files.message.toServer.ToServerMessageHandler;

/**
 * Message sent to go to the next turn phase.
 */
public class Next extends Message implements ToServerMessage {
    @Override
    public void accept(ToServerMessageHandler v) {
        v.visit(this);
    }
}
