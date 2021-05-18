package it.polimi.ingsw.server.model;

import java.util.Stack;

public class SinglePlayerGame extends Game {
    private boolean isLost;
    private FaithTrack lorenzoTrack = new FaithTrack() {
        @Override
        public boolean isInVatican(int papalSpace) {
            return false;
        }

        @Override
        public void increasePosition() {
            super.increasePosition(); //TODO
            if(getPosition() == 24) {
                isLost = true;
                setOver(true);
            }
        }
    };

    @Override
    public void setOver(boolean over) {
        super.setOver(over);
        endGame();
    }
    public SinglePlayerGame(Player player, int playersNum, Market market, Stack<LeaderCard> leaderDeck,
                            DevelopmentGrid developmentGrid) {
        super(player, playersNum, market, leaderDeck, developmentGrid);
        Player lorenzo = new Player("Lorenzo");
        lorenzo.getBoard().setFaithTrack(lorenzoTrack);
        addPlayer(lorenzo);
        getTurnPhases().put("EndTurnPhase", new SoloActionPhase(this));
    }

    @Override
    public void setUpPlayers() {
        getViewAdapter().sendTable();
        for (int i = 0; i < 4; i++)
            getPlayer(0).getLeaderCards().add(getLeaderDeck().pop());
        getViewAdapter().sendLeaderHand(getPlayer(0));
        setCurrentPlayer(getPlayer(0));
    }

    @Override
    public void endGame() {
        if (!getDevelopmentGrid().isStillWinnable() && this.getPlayers().get(0).getNumOfCards() != 7){
            isLost = true; //TODO
        }
        setFinished();
        int rank = 1;
        if(isLost)
            rank = 2;
        getViewAdapter().notifyGameEnded(getPlayer(0), rank, getPlayer(0).getVictoryPoints());
    }
}