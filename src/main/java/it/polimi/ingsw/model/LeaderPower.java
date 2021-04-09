package it.polimi.ingsw.model;

/**
 * standard powers of leader card
 */
public interface LeaderPower {

    /**
     * show on screen the type of power
     */
    void getPower();

    /**
     * when the card is deploy activates the power of the leader
     * @param player the player who activate the card
     */
    void activatePower(Player player);
}
