package it.polimi.ingsw.common_files.message.toClient.updates;

import it.polimi.ingsw.client.UpdateHandler;
import it.polimi.ingsw.common_files.message.Message;
import it.polimi.ingsw.common_files.message.toClient.ClientMessageVisitor;

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
