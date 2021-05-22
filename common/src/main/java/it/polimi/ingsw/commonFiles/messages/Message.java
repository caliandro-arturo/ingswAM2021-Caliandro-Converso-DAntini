package it.polimi.ingsw.commonFiles.messages;

import it.polimi.ingsw.commonFiles.messages.toClient.ToClientMessage;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.GameUpdate;
import it.polimi.ingsw.commonFiles.messages.toServer.ToServerMessage;

import java.io.Serializable;

/**
 * An abstract class representing messages that server and clients sends to each other.
 *
 * @see ToServerMessage
 * @see ToClientMessage
 * @see GameUpdate
 */
public abstract class Message implements Serializable {
    private String player;

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
}