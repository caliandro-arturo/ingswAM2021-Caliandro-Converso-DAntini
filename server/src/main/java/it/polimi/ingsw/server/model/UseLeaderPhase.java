package it.polimi.ingsw.server.model;

/**
 * In this phase the player can deploy or discard a leader.
 * This phase is in the beginning and in the end of a game turn.
 */
public class UseLeaderPhase extends TurnPhase {
    private final boolean isFirst;

    public UseLeaderPhase(Game game, boolean isFirst) {
        super(game, "Leader action", true);
        this.isFirst = isFirst;
    }

    @Override
    public void start() {
        if (getGame().getCurrentPlayer().getLeaderCards().isEmpty()) {
            getGame().nextTurnPhase();
            return;
        }
        getGame().getViewAdapter().announceTurnPhase(getGame().getCurrentPlayer(), getName(), getPhaseInfo());
        getGame().getViewAdapter().askLeaderAction();
    }

    @Override
    public String getPhaseInfo() {
        return """
                In this turn phase you can deploy or discard leaders that you have in your hand.
                Deploy leader: if you meet the requirements of a leader, you can deploy it on your board;
                Discard leader: if you decide that you don't need a leader, you can discard the leader card. This operation gives you a Faith point.""";
    }

    @Override
    public TurnPhase nextTurnPhase() {
        if (isFirst)
            return getGame().getTurnPhase("ChooseAction");
        else
            return getGame().getTurnPhase("EndTurn");
    }
}
