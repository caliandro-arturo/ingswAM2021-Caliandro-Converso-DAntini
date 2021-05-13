package it.polimi.ingsw.common_files.message.toClient.updates;

import it.polimi.ingsw.client.UpdateHandler;
import it.polimi.ingsw.common_files.message.toClient.ToClientMessage;

public interface GameUpdate extends ToClientMessage {
    void accept(UpdateHandler v);
}
