package it.polimi.ingsw.client.model;

public class WhiteMarbleConversion implements LeaderPower{
    private Resource resource;

    public WhiteMarbleConversion(Resource resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "│     " + Color.WHITE + " -> " + resource +"      │\n";
    }
}
