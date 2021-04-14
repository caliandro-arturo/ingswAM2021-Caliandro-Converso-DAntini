package it.polimi.ingsw.model;

public class ActivateProdPhase extends TurnPhase {
    public ActivateProdPhase(Game game) {
        super(game, "Activate productions phase", false);
    }

    @Override
    public void start() {

    }

    @Override
    public String getPhaseInfo() {
        return null;
    }

    @Override
    public TurnPhase nextTurnPhase() {
        return getGame().getTurnPhase("UseAgainLeader");
    }
}
