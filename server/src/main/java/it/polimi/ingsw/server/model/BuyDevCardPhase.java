package it.polimi.ingsw.server.model;

public class BuyDevCardPhase extends TurnPhase {
    public BuyDevCardPhase(Game game) {
        super(game, "Buy a development card phase", false);
    }

    @Override
    public void start() {
        getGame().getViewAdapter().announceTurnPhase(getGame().getCurrentPlayer(), getName(), getPhaseInfo());
    }

    @Override
    public String getPhaseInfo() {
        return "Use your resources to buy a new Development Card to insert in your board.";
    }

    @Override
    public TurnPhase nextTurnPhase() {
        if (getGame().getCurrentPlayer().getNumOfCards() == 7) {
            getGame().getViewAdapter().notifyLastTurn("someone has bought his seven card");
            getGame().setOver(true);
        }
        return getGame().getTurnPhase("UseAgainLeader");
    }
}
