package it.polimi.ingsw.model;

/**
 * This class represents the marbles in the market.
 */
public class Marble {
    private Color color;

    public Marble(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    /**
     * This method starts the action corresponding to the color of the marble.
     * @param player the player whom the action is to be applied to
     */
    public void pick(Player player) {
        color.action.act(player);
    }

}
