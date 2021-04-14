package it.polimi.ingsw.model;

import java.util.Stack;

public class SinglePlayerGame extends Game {
    private boolean isLose;
    FaithTrack lorenzoTrack = new FaithTrack() {
        @Override
        public void isInVatican(int papalSpace) {
        }

        @Override
        public void increasePosition() {
            super.increasePosition();
            if(getPosition() == 24) {
                isLose = true;
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
    }
}