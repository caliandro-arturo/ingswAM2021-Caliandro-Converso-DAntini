package it.polimi.ingsw.model;

/**
 * special Board Production
 */
public class BoardProduction implements Production{

    @Override
    public UtilityProductionAndCost[] getProd() {
        return new UtilityProductionAndCost[0];
    }

    @Override
    public UtilityProductionAndCost[] getCost() {
        return new UtilityProductionAndCost[0];
    }
}
