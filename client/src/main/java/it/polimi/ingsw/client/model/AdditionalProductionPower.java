package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.Production;
import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;
import it.polimi.ingsw.commonFiles.utility.StringUtility;

/**
 * Represents the production power of leaders.
 */
public class AdditionalProductionPower implements LeaderPower, Production {
    private final Resource cost;

    public AdditionalProductionPower(Resource cost) {
        this.cost = cost;
    }

    /**
     * Adds a production power in the board.
     * @param board
     */
    @Override
    public void activatePower(Board board) {
        board.setPowerProd(cost);
        if (board.getPowerProd().size() == 2){
            board.setIsProductionAlreadyUsed(true,5);
        }else
            board.setIsProductionAlreadyUsed(true,4);
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
        return "│" + StringUtility.center(cost + " -> ?, " + Resource.FAITH ,17) +"│\n";
    }

}
