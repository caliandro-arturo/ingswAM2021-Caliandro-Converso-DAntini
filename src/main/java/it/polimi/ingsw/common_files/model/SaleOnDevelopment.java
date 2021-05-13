package it.polimi.ingsw.common_files.model;

import it.polimi.ingsw.common_files.model.LeaderPower;
import it.polimi.ingsw.common_files.model.Resource;
import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.Player;

/**
 * power that make a discount on the development card
 */
public class SaleOnDevelopment implements LeaderPower {
    private final Resource resource;

    public Resource getResource() {
        return resource;
    }

    public SaleOnDevelopment(Resource resource){
        this.resource = resource;
    }

    @Override
    public void getPower(Game game, Player player) {
        game.getViewAdapter().sendMessage(player,
                "Sale of an amount of one");
    }

    @Override
    public String toString() {
        return "│" + Utility.center("-1 " + resource,17) + "│\n";
    }

    @Override
    public void activatePower(Player player) {
        player.addSale(getResource());
    }
}
