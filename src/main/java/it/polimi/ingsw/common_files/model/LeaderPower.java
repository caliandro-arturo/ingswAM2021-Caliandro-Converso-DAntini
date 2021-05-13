package it.polimi.ingsw.common_files.model;

import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.Player;

/**
 * standard powers of leader card
 */
public interface LeaderPower {

    /**
     * show on screen the type of power
     */
    void getPower(Game game, Player player);

    /**
     * when the card is deploy activates the power of the leader
     * @param player current player
     */
    void activatePower(Player player);

    String toString();

}
