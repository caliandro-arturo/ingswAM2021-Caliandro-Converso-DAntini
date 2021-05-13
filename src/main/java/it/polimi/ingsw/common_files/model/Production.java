package it.polimi.ingsw.common_files.model;

public interface Production {

    UtilityProductionAndCost[] getProd();
    UtilityProductionAndCost[] getCost();
    void setProductionCanBeActivate(boolean setProductionCanBeActivate);
    boolean getProductionCanBeActivate();
    String toString();
}
