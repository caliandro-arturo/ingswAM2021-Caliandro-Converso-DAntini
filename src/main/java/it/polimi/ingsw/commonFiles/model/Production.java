package it.polimi.ingsw.commonFiles.model;

import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;

public interface Production {

    UtilityProductionAndCost[] getProd();
    UtilityProductionAndCost[] getCost();
    void setProductionCanBeActivate(boolean setProductionCanBeActivate);
    boolean getProductionCanBeActivate();
    String toString();
}
