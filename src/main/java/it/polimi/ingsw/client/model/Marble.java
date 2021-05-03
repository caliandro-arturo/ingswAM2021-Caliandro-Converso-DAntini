package it.polimi.ingsw.client.model;

public class Marble {
    private Color color;

    public Marble(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color.toString();
    }
}
