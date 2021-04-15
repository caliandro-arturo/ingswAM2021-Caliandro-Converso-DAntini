package it.polimi.ingsw.model;

/**
 * standard powers of leader card
 */
public interface LeaderPower {

    /**
     * show on screen the type of power
     */
    void getPower(Game game,Player player);

    /**
     * when the card is deploy activates the power of the leader
     * @param player current player
     */
    void activatePower(Player player);
}
