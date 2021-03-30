package it.polimi.ingsw.model;

public interface Production {

    /**
     * standard operation for the production in the game
     */
    public UtilityProductionAndCost[] startProduction(Player player);
}
