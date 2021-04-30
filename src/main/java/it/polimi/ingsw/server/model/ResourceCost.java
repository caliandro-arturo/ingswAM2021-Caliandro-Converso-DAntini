package it.polimi.ingsw.server.model;

public class ResourceCost implements Requirements{
    private final UtilityProductionAndCost cost;

    public ResourceCost(UtilityProductionAndCost cost){
        this.cost = cost;
    }

    public UtilityProductionAndCost getCost(){
        return cost;
    }

    @Override
    public boolean checkRequirements (Player player) {
        int i = cost.getQuantity();
        for (WarehouseStore store: player.getBoard().getStore()){
            if (cost.getResource() == store.getTypeOfResource()){
                i = i - store.getQuantity();
            }
        }
        return player.getBoard().getPersonalBox().getResourceMap().get(cost.getResource()) >= i;
    }

    @Override
    public void getRequirements(Game game, Player player) {
        game.getViewAdapter().sendMessage(player, "to deploy this card you must have: ");
    }
}
