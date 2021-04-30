package it.polimi.ingsw.server.model;


/**
 * Power of the leader that add a possible production
 */
public class AdditionalProductionPower  implements LeaderPower,Production{

    private final Resource cost;
    private boolean productionCanBeActivate = true;

    public AdditionalProductionPower(Resource cost){
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
        return new UtilityProductionAndCost[]{new UtilityProductionAndCost(1,cost)};
    }

    @Override
    public UtilityProductionAndCost[] getProd() {
        return new UtilityProductionAndCost[0];
    }

    @Override
    public void getPower(Game game, Player player) {
        game.getViewAdapter().sendMessage(player,
                "add an additional production of a FAITH point and a chose resource for the cost of");
    }

    @Override
    public void activatePower(Player player) {
        player.getBoard().getProductionList().add(this);
    }

}
