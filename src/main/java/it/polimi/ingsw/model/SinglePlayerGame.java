package it.polimi.ingsw.model;

import java.util.Stack;

public class SinglePlayerGame extends Game {
    private boolean isLost;
    FaithTrack lorenzoTrack = new FaithTrack() {
        @Override
        public boolean isInVatican(int papalSpace) {
            return false;
        }

        @Override
        public void increasePosition() {
            super.increasePosition();
            if(getPosition() == 24) {
                isLost = true;
                setOver(true);
                endGame();
            }
        }
    };
    public SinglePlayerGame(Player player, int playersNum, Market market, Stack<LeaderCard> leaderDeck, DevelopmentGrid developmentGrid) {
        super(player, playersNum, market, leaderDeck, developmentGrid);
        Player lorenzo = new Player("Lorenzo");
        lorenzo.getBoard().setPersonalPath(lorenzoTrack);
        getPlayers().add(lorenzo);
        getTurnPhases().put("EndTurnPhase", new SoloActionPhase(this));
    }

    @Override
    public void setUpPlayers() {
        setCurrentPlayer(getPlayer(0));
    }

    @Override
    public void endGame() {
        setFinished();
        int rank = 1;
        if(isLost)
            rank = 2;
        getViewAdapter().notifyGameEnded(getPlayer(0), rank, getPlayer(0).getVictoryPoints());
    }
}