package it.polimi.ingsw.commonFiles.model;

import java.io.Serializable;

public interface Production extends Serializable {

    UtilityProductionAndCost[] getProd();
    UtilityProductionAndCost[] getCost();
    void setProductionCanBeActivate(boolean setProductionCanBeActivate);
    boolean getProductionCanBeActivate();
    String toString();
}
