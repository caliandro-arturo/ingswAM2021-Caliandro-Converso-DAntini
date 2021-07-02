package it.polimi.ingsw.client.model;

/**
 * Represents leader powers in the client.
 */
public interface LeaderPower {
    /**
     * Activates a leader power in a player's board.
     *
     * @param board the board, used to collect leader powers
     */
    void activatePower(Board board);
}
