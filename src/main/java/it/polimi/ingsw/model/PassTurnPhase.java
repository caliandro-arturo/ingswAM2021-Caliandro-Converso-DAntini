package it.polimi.ingsw.model;

public class PassTurnPhase extends TurnPhase {
    public PassTurnPhase(Game game) {
        super(game);
    }

    @Override
    public void start() {

    }

    @Override
    public String getPhaseInfo() {
        return null;
    }

    @Override
    void nextTurnPhase() {

    }
}
