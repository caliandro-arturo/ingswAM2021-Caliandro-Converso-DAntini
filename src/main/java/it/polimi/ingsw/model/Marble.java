package it.polimi.ingsw.model;

/**
 * This class represents the marbles in the market.
 */
public class Marble {
    private final Color color;

    public Marble(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    /**
     * This method starts the action corresponding to the color of the marble.
     * @param game the game where there is the market from which the marble is picked
     */
    public void pick(Game game) {
        color.act(game);
    }

}
