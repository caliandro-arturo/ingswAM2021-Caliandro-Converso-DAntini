package it.polimi.ingsw.server.model;

public class PassTurnPhase extends TurnPhase {
    public PassTurnPhase(Game game) {
        super(game, "Pass the turn to the next player", false);
    }

    @Override
    public void start() {
        getGame().nextTurnPhase();
    }

    @Override
    public String getPhaseInfo() {
        return null;
    }

    @Override
    public TurnPhase nextTurnPhase() {
        Game game = getGame();
        if (game.getPlayers().indexOf(game.getCurrentPlayer()) == game.getPlayersNum() - 1)
            if (game.isOver()) {
                game.endGame();
                return null;
            }
        do {
            getGame().setCurrentPlayer(nextPlayer());
            game.getViewAdapter().notifyNewTurn(game.getCurrentPlayer());
        } while (!getGame().getCurrentPlayer().isConnected());
        return getGame().getTurnPhase("UseLeader");
    }

    /**
     * Find the next player to pass the turn to.
     *
     * @return the next player
     */
    private Player nextPlayer() {
        Game game = getGame();
        Player nextPlayer;
        try {
            nextPlayer = game.getPlayer(game.getPlayers().indexOf(game.getCurrentPlayer()) + 1);
        } catch (Exception e) {
            nextPlayer = game.getPlayer(0);
        }
        return nextPlayer;
    }

}
