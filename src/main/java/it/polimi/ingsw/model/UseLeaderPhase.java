package it.polimi.ingsw.model;


public class UseLeaderPhase extends TurnPhase {
    private boolean isFirst;
    public UseLeaderPhase(Game game, boolean isFirst) {
        super(game);
        this.isFirst = isFirst;
    }

    @Override
    public void start() {
        if(getGame().getCurrentPlayer().getLeaderCard().length == 0) return;
        getGame().getViewAdapter().askLeaderAction();
    }

    @Override
    public String getPhaseInfo() {
        return "In this turn phase you can deploy or discard the leaders that you have in your hand.\n" +
                "Deploy leader: if you meet the requirements of a leader, you can deploy it on the board;\n" +
                "Discard leader: if you decide that you don't need a leader, you can discard the leader card. " +
                "This operation gives you a Faith point.";
    }

    @Override
    public void nextTurnPhase() {

    }
}
