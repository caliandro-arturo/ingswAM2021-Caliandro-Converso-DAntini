package it.polimi.ingsw.model;

public interface Production {

    UtilityProductionAndCost[] getProd();
    UtilityProductionAndCost[] getCost();
    void setProductionCanBeActivate(boolean setProductionCanBeActivate);
    boolean getProductionCanBeActivate();
}
