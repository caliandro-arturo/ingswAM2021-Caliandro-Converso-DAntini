package it.polimi.ingsw.model;

public class PassTurnPhase extends TurnPhase {
    public PassTurnPhase(Game game) {
        super(game, "Pass the turn to the next player", false);
    }

    @Override
    public void start() {
        getGame().getViewAdapter().sendMessage("Passing turn to the next player...");
        getGame().nextTurnPhase();
    }

    @Override
    public String getPhaseInfo() {
        return null;
    }

    @Override
    TurnPhase nextTurnPhase() {
        Game game = getGame();
        if(game.getPlayers().indexOf(game.getCurrentPlayer()) == game.getPlayersNum() - 1)
            if(game.isOver()) {
                game.endGame();
                return null;
            }
        getGame().setCurrentPlayer(nextPlayer());
        return getGame().getTurnPhase("UseLeader");
    }

    /**
     * Find the next player to pass the turn to.
     *
     * @return the next player
     */
    private Player nextPlayer() {
        Game game = getGame();
        if(game.getPlayers().indexOf(game.getCurrentPlayer()) == game.getPlayersNum() - 1)
            return game.getPlayer(0);
        return game.getPlayer(game.getPlayers().indexOf(game.getCurrentPlayer()) + 1);
    }
}
