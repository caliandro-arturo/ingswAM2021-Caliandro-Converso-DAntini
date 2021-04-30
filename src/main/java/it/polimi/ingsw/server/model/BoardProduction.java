package it.polimi.ingsw.server.model;

/**
 * special Board Production
 */
public class BoardProduction implements Production{

    boolean productionCanBeActivate = true;

    @Override
    public boolean getProductionCanBeActivate() {
        return productionCanBeActivate;
    }

    @Override
    public void setProductionCanBeActivate(boolean productionCanBeActivate) {
        this.productionCanBeActivate = productionCanBeActivate;
    }

    @Override
    public UtilityProductionAndCost[] getProd() {
        return new UtilityProductionAndCost[0];
    }

    @Override
    public UtilityProductionAndCost[] getCost() {
        return new UtilityProductionAndCost[0];
    }
}
