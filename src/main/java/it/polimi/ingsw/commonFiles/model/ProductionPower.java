package it.polimi.ingsw.commonFiles.model;

import it.polimi.ingsw.client.model.Utility;

import java.util.Arrays;

/**
 * Basic type of production power
 */
public class ProductionPower implements Production {
    private final UtilityProductionAndCost[] cost;
    private final UtilityProductionAndCost[] production;
    boolean productionCanBeActivate = true;

    @Override
    public boolean getProductionCanBeActivate() {
        return productionCanBeActivate;
    }

    @Override
    public void setProductionCanBeActivate(boolean productionCanBeActivate) {
        this.productionCanBeActivate = productionCanBeActivate;
    }

    public ProductionPower(UtilityProductionAndCost[] cost, UtilityProductionAndCost[] production){
        this.cost = cost;
        this.production = production;
    }

    @Override
    public UtilityProductionAndCost[] getProd() {
        return production;
    }

    @Override
    public UtilityProductionAndCost[] getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return  "│" + Utility.center(Arrays.toString(cost), 17)+ "│\n" +
                "│" + Utility.center(Arrays.toString(production), 17)+ "│\n";
    }
}
