package it.polimi.ingsw.messages.toClient.updates;

import it.polimi.ingsw.client.UpdateHandler;
import it.polimi.ingsw.messages.toClient.ToClientMessage;

public interface GameUpdate extends ToClientMessage {
    void accept(UpdateHandler v);
}
