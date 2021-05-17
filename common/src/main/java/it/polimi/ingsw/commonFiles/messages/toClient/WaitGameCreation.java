package it.polimi.ingsw.commonFiles.messages.toClient;

import it.polimi.ingsw.commonFiles.messages.Message;

/**
 * Message sent when the player logged correctly but is not the first player, and must wait the game creation.
 */
public class WaitGameCreation extends Message implements ToClientMessage {
    @Override
    public void accept(ToClientMessageVisitor v) {
        v.visit(this);
    }
}
