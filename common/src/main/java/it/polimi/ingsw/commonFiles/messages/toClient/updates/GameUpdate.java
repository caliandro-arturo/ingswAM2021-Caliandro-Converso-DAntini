package it.polimi.ingsw.commonFiles.messages.toClient.updates;

import it.polimi.ingsw.commonFiles.messages.toClient.ToClientMessage;

/**
 * Messages that updates the client model. In fact, these are the same messages that clients send to the server.
 * @see UpdateHandler
 */
public interface GameUpdate extends ToClientMessage {
    void accept(UpdateHandler v);
}
