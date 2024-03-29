package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.utility.StringUtility;

/**
 * Representation of the white marble conversion power of leader cards for client.
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
    public void activatePower(Board board) {
        board.setPowerWhiteMarble(resource);
    }

    @Override
    public String toString() {
        return "│" + StringUtility.center( "@" + " -> " + resource,17) +"│\n";
    }

}
