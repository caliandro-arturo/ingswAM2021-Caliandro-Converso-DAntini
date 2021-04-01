package it.polimi.ingsw.model;

/**
 * standard requirements for the leader card
 */
public interface Requirements {

    public void checkRequirements (Player player) throws NotMetRequirementsException;
}
