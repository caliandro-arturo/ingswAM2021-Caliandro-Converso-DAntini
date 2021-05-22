package it.polimi.ingsw.server.model;

public class MarketPhase extends TurnPhase {
    public MarketPhase(Game game) {
        super(game,"Market phase",false);
    }

    @Override
    public void start() {
        getGame().getViewAdapter().announceTurnPhase(getGame().getCurrentPlayer(), getName());
    }

    @Override
    public String getPhaseInfo() {
        return "Use the market to get additional resources.";
    }

    @Override
    public TurnPhase nextTurnPhase() {
        setFinished(false);
        return getGame().getTurnPhase("UseAgainLeader");
    }
}
