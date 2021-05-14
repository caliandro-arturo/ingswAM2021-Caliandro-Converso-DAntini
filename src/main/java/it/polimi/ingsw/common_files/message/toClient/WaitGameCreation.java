package it.polimi.ingsw.common_files.message.toClient;

import it.polimi.ingsw.common_files.message.Message;

/**
 * Message sent when the player logged correctly but is not the first player, and must wait the game creation.
 */
public class WaitGameCreation extends Message implements ToClientMessage {
    @Override
    public void accept(ClientMessageVisitor v) {
        v.visit(this);
    }
}
