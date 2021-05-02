package it.polimi.ingsw.client.model;

public enum Resource {
    SHIELD("shield"),
    FAITH("faith"),
    SERF("serf"),
    STONE("stone"),
    COIN("coin");
    private final String representation;

    Resource(String representation){
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}
