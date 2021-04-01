package it.polimi.ingsw.model;

/**
 * power of the leader that take a white marble in the market and convert into a differente color
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
    public void getPower() {
        System.out.println("Convert the white marble in" +getResource());
    }

    @Override
    public void activatePower(Player player) {
        player.addWhiteAlt(getResource());
    }
}
