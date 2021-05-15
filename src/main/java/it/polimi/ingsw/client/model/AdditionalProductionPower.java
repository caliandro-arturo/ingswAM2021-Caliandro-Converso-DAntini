package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.Production;
import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;

public class AdditionalProductionPower implements LeaderPower, Production {
    private final Resource cost;

    public AdditionalProductionPower(Resource cost) {
        this.cost = cost;
    }

    @Override
    public void activatePower() {

    }

    @Override
    public UtilityProductionAndCost[] getProd() {
        return new UtilityProductionAndCost[0];
    }

    @Override
    public UtilityProductionAndCost[] getCost() {
        return new UtilityProductionAndCost[0];
    }

    @Override
    public void setProductionCanBeActivate(boolean setProductionCanBeActivate) {

    }

    @Override
    public boolean getProductionCanBeActivate() {
        return false;
    }

    @Override
    public String toString() {
        return "│" + Utility.center(cost + " -> ?, " + Resource.FAITH ,17) +"│\n";
    }

}
