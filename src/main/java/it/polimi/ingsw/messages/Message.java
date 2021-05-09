package it.polimi.ingsw.messages;

import it.polimi.ingsw.server.model.Player;

import java.io.Serializable;

/**
 * An abstract class representing messages that server and clients sends to each other.
 *
 * @see it.polimi.ingsw.messages.toServer.ToServerMessage
 * @see it.polimi.ingsw.messages.toClient.ToClientMessage
 * @see it.polimi.ingsw.messages.toClient.updates.GameUpdate
 */
public abstract class Message implements Serializable {
    private Player player;
    private final int id = this.hashCode();

    public Player getPlayer() {
        return player;
    }
    public int getId() {
        return id;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}