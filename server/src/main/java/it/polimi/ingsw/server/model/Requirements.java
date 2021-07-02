package it.polimi.ingsw.server.model;

/**
 * Standard requirements for the leader card.
 */
public interface Requirements {

    /**
     * Checks if the requirements for the deploying of the leader card are valid.
     *
     * @param player who deploy the card
     * @return true if the requirements are met or false
     */
    boolean checkRequirements(Player player);

    /**
     * Identifier for message to client.
     */
    String[] identifier();
}
