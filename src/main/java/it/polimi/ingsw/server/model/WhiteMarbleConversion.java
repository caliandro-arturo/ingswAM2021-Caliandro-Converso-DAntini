package it.polimi.ingsw.server.model;

/**
 * power of the leader that take a white marble in the market and convert into a different color
 */
public class WhiteMarbleConversion implements LeaderPower{
    private final Resource resource;

    public WhiteMarbleConversion(Resource resource){
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }

    @Override
    public void getPower(Game game, Player player) {
        game.getViewAdapter().sendMessage(player, "Convert the white marble in");
    }

    @Override
    public void activatePower(Player player) {
        player.addWhiteAlt(getResource());
    }
}
