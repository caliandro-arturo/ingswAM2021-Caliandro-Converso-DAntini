package it.polimi.ingsw.server.model;

/**
 * Market phase. The player can use the market in this phase.
 */
public class MarketPhase extends TurnPhase {
    public MarketPhase(Game game) {
        super(game, "Market", false);
    }

    @Override
    public void start() {
        getGame().getViewAdapter().announceTurnPhase(getGame().getCurrentPlayer(), getName(), getPhaseInfo());
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
