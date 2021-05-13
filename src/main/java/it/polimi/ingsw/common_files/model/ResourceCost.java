package it.polimi.ingsw.common_files.model;

import it.polimi.ingsw.common_files.model.Requirements;
import it.polimi.ingsw.common_files.model.UtilityProductionAndCost;
import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.Player;
import it.polimi.ingsw.server.model.WarehouseStore;

public class ResourceCost implements Requirements {
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
        return player.getBoard().getStrongbox().getResourceMap().get(cost.getResource()) >= i;
    }

    @Override
    public void getRequirements(Game game, Player player) {
        game.getViewAdapter().sendMessage(player, "to deploy this card you must have: ");
    }

    @Override
    public String toString() {
        Resource resource = cost.getResource();
        int quantity = cost.getQuantity();
        return "│"+ Utility.center(resource + ":"+ quantity,17)+"│\n";
    }
}