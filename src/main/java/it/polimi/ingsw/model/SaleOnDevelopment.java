package it.polimi.ingsw.model;

/**
 * power that make a discount on the development card
 */
public class SaleOnDevelopment implements LeaderPower{
    private final Resource resource;

    public Resource getResource() {
        return resource;
    }

    public SaleOnDevelopment(Resource resource){
        this.resource = resource;
    }

    @Override
    public void getPower(Game game,Player player) {
        game.getViewAdapter().sendMessage(player,
                "Sale of an amount of one");
    }

    @Override
    public void activatePower(Player player) {
        player.addSale(getResource());
    }
}
