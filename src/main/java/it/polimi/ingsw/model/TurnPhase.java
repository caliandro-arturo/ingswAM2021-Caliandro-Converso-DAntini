package it.polimi.ingsw.model;

/**
 * This class represents the turn phases. Each phase of the turn extends this class.
 */
public abstract class TurnPhase {
    private final Game game;
    private final String name;

    private final boolean isSkippable;
    private boolean isFinished = false;
    public TurnPhase(Game game, String name, boolean isSkippable) {
        this.game = game;
        this.name = name;
        this.isSkippable = isSkippable;
    }

    public Game getGame() {
        return game;
    }
    public String getName() {
        return name;
    }
    public boolean isSkippable() {
        return isSkippable;
    }
    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    /**
     * Starts the turn phase.
     */
    abstract void start();

    /**
     * Gives information about the turn phase to the player.
     * @return a String with information about the turn phase.
     */
    abstract String getPhaseInfo();

    /**
     * Returns the next turn phase, eventually setting a new player.
     * @return the next turn phase
     */
    abstract TurnPhase nextTurnPhase();
}
