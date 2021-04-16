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
        getGame().getCurrentPlayer().getBoard().getPersonalBox().emptyProdBox();
        return getGame().getTurnPhase("UseAgainLeader");
    }
}
