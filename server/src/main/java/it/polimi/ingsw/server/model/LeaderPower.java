package it.polimi.ingsw.server.model;


/**
 * Standard powers of leader card.
 */
public interface LeaderPower {

    /**
     * When the card is deploy activates the power of the leader.
     *
     * @param player current player
     */
    void activatePower(Player player);

    /**
     * Identifier for message to client.
     */
    String[] identifier();
}