package it.polimi.ingsw.common_files.message;

import it.polimi.ingsw.common_files.message.toClient.ToClientMessage;
import it.polimi.ingsw.common_files.message.toClient.updates.GameUpdate;
import it.polimi.ingsw.common_files.message.toServer.ToServerMessage;
import it.polimi.ingsw.server.model.Player;

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
    private final int id = this.hashCode();

    public String getPlayer() {
        return player;
    }
    public int getId() {
        return id;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
}