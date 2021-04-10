package it.polimi.ingsw.model;

/**
 * This class represents the turn phases. Each phase of the turn extends this class.
 */
public abstract class TurnPhase {
    private Game game;
    public TurnPhase(Game game) {
        this.game = game;
    }
    public Game getGame() {
        return game;
    }

    /**
     * This method starts the turn phase.
     */
    abstract void start();

    /**
     * This method gives information about the turn phase to the player.
     * @return a String with information about the turn phase.
     */
    abstract String getPhaseInfo();
    void nextTurnPhase() {}
}
