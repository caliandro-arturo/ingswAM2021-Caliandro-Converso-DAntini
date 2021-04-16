package it.polimi.ingsw.model;

public class BuyDevCardPhase extends TurnPhase {
    public BuyDevCardPhase(Game game) {
        super(game, "Buy a development card phase", false);
    }

    @Override
    public void start() {
        getGame().getViewAdapter().askWhichCardToBuyFromTheDevelopmentGrid();
    }

    @Override
    public String getPhaseInfo() {
        return "Use your resources to buy a new Development Card to insert in your board.";
    }

    @Override
    public TurnPhase nextTurnPhase() {
        return getGame().getTurnPhase("UseAgainLeader");
    }
}
