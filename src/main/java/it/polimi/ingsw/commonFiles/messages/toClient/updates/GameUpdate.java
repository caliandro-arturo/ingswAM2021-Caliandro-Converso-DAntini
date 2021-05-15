package it.polimi.ingsw.commonFiles.messages.toClient.updates;

import it.polimi.ingsw.client.UpdateHandler;
import it.polimi.ingsw.commonFiles.messages.toClient.ToClientMessage;

/**
 * Messages that updates the client model. In fact, these are the same messages that clients send to the server: once the server
 * sends an {@code Ok} message, the message sent by the client (saved in {@code ClientController} in a buffer) is visited by
 * {@code UpdateHandler}.
 * @see it.polimi.ingsw.commonFiles.messages.toClient.Ok
 * @see it.polimi.ingsw.client.ClientController
 * @see UpdateHandler
 */
public interface GameUpdate extends ToClientMessage {
    void accept(UpdateHandler v);
}
