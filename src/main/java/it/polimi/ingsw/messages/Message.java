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
    Player player;

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
}