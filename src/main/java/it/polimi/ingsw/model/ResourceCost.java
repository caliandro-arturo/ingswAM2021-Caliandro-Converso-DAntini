package it.polimi.ingsw.model;

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
        for (int j=0;j<player.getBoard().getStore().length;j++){
            if (cost.getResource() == player.getBoard().getStore()[j].getResource()){
                i = i - player.getBoard().getStore()[j].getQuantity();
                break;
            }
        }
        if (player.getBoard().getPersonalBox().getResourceMap().get(cost.getResource()) >= i) {
            return true;
        } else {
            return false;
        }
    }
}
