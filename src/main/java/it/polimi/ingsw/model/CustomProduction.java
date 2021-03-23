package it.polimi.ingsw.model;

/**
 * special production in the case of leader power and the personal board
 */
public class CustomProduction extends ProductionPower{

    public CustomProduction(UtilityProductionAndCost[] cost, UtilityProductionAndCost[] production){
        super(cost, production);
    }

    /**
     * set the quantity of one resource type chose by the player
     * @param quantity int
     */
    public void setcost(int quantity){

    }

    /**
     * set the quantity of one resource type chose by the player
     * @param quantity int
     */
    public void setproduction(int quantity){

    }
}
