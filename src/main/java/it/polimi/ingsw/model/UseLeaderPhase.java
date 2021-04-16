package it.polimi.ingsw.model;


public class UseLeaderPhase extends TurnPhase {
    private final boolean isFirst;
    public UseLeaderPhase(Game game, boolean isFirst) {
        super(game, "Leader action's phase", true);
        this.isFirst = isFirst;
    }

    @Override
    public void start() {
        if(getGame().getCurrentPlayer().getLeaderCards().isEmpty()) {
            getGame().nextTurnPhase();
            return;
        }
        getGame().getViewAdapter().sendMessage(getGame().getCurrentPlayer(), getName());
        getGame().getViewAdapter().askLeaderAction();
    }

    @Override
    public String getPhaseInfo() {
        return "In this turn phase you can deploy or discard leaders that you have in your hand.\n" +
                "Deploy leader: if you meet the requirements of a leader, you can deploy it on your board;\n" +
                "Discard leader: if you decide that you don't need a leader, you can discard the leader card. " +
                "This operation gives you a Faith point.";
    }

    @Override
    public TurnPhase nextTurnPhase() {
        if(isFirst)
            return getGame().getTurnPhase("ChooseActionPhase");
        else
            return getGame().getTurnPhase("EndTurnGame");
    }
}
