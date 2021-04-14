package it.polimi.ingsw.model;

public class MarketPhase extends TurnPhase {
    public MarketPhase(Game game) {
        super(game,"Market phase",false);
    }

    @Override
    public void start() {
        getGame().getViewAdapter().sendMessage(getGame().getCurrentPlayer(), "");
    }   //todo chiedere di far usare il mercato

    @Override
    public String getPhaseInfo() {
        return null;
    }

    @Override
    public TurnPhase nextTurnPhase() {
        return getGame().getTurnPhase("UseAgainLeader");
    }
}
