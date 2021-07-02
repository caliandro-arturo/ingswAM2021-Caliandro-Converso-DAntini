package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.model.Resource;

/**
 * Leader power that takes a white marble in the market and convert into a different color.
 */
public class WhiteMarbleConversion implements LeaderPower {
    private final Resource resource;

    public WhiteMarbleConversion(Resource resource){
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }

    @Override
    public void activatePower(Player player) {
        player.addWhiteAlt(resource);
    }

    @Override
    public String[] identifier() {
        return new String[]{"whiteMarbleConversion", resource.toString()};
    }
}
