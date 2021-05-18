package it.polimi.ingsw.commonFiles.model;

import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;

import java.io.Serializable;

public interface Production extends Serializable {

    UtilityProductionAndCost[] getProd();
    UtilityProductionAndCost[] getCost();
    void setProductionCanBeActivate(boolean setProductionCanBeActivate);
    boolean getProductionCanBeActivate();
    String toString();
}
