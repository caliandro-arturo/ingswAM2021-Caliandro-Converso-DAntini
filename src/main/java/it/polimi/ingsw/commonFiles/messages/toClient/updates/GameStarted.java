package it.polimi.ingsw.commonFiles.messages.toClient.updates;

import it.polimi.ingsw.client.UpdateHandler;
import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ClientMessageVisitor;

/**
 * Message sent when the game starts.
 */
public class GameStarted extends Message implements GameUpdate {
    @Override
    public void accept(ClientMessageVisitor v) {
        v.visit(this);
    }

    @Override
    public void accept(UpdateHandler v) {
        v.visit(this);
    }
}
