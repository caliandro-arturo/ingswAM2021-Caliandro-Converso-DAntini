package it.polimi.ingsw.model;

public class Marble {
    private Color color;

    public Marble(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    public void pick(Player player) {
        color.action(player);
    }

}
