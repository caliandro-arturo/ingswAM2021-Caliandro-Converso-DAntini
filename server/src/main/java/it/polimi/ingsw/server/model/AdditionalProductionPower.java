package it.polimi.ingsw.server.model;


import it.polimi.ingsw.commonFiles.model.Production;
import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;

/**
 * Power of the leader that adds a possible production.
 */
public class AdditionalProductionPower implements LeaderPower, Production {

    private final Resource cost;
    private boolean productionCanBeActivate = true;

    public AdditionalProductionPower(Resource cost) {
        this.cost = cost;
    }

    @Override
    public boolean getProductionCanBeActivate() {
        return productionCanBeActivate;
    }

    @Override
    public void setProductionCanBeActivate(boolean productionCanBeActivate) {
        this.productionCanBeActivate = productionCanBeActivate;
    }

    @Override
    public UtilityProductionAndCost[] getCost() {
        return new UtilityProductionAndCost[]{new UtilityProductionAndCost(1, cost)};
    }

    @Override
    public UtilityProductionAndCost[] getProd() {
        return new UtilityProductionAndCost[0];
    }

    @Override
    public void activatePower(Player player) {
        player.getBoard().getProductionList().add(this);
    }

    @Override
    public String[] identifier() {
        return new String[]{"specialProduction", cost.toString()};
    }
}
