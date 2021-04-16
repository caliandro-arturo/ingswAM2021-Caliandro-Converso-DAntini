package it.polimi.ingsw.model;

import java.util.Scanner;

/**
 * Basic type of production power
 */
public class ProductionPower implements Production{
    private final UtilityProductionAndCost[] cost;
    private final UtilityProductionAndCost[] production;


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
}
