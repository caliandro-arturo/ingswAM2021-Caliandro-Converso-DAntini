package it.polimi.ingsw.messages.toClient.updates;

import it.polimi.ingsw.client.UpdateHandler;
import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.messages.toClient.ClientMessageVisitor;

/**
 * Message to send to clients when the game starts.
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
