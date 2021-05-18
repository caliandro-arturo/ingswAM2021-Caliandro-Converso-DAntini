package it.polimi.ingsw.server.model;

/**
 * standard requirements for the leader card
 */
public interface Requirements {

    /**
     * check if the requirements for the deploying of the leader card are met
     * @param player who deploy the card
     * @return true if the requirements are met or false
     */
    boolean checkRequirements(Player player);

    /**
     * show on screen the requirements for the card
     */
    void getRequirements(Game game, Player player);

    /**
     * Identifier for message to client
     */
    String[] identifier();
}
