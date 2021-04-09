package it.polimi.ingsw.model;

public interface Production {

    /**
     * standard operation for the production in the game
     */
    UtilityProductionAndCost[] startProduction(PersonalBoard board);
}
