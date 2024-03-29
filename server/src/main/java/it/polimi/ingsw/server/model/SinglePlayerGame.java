package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.messages.toClient.updates.LorenzoPosition;

import java.util.LinkedHashMap;
import java.util.Stack;

public class SinglePlayerGame extends Game {
    private boolean isLost;

    public SinglePlayerGame(Player player, int playersNum, Market market, Stack<LeaderCard> leaderDeck,
                            DevelopmentGrid developmentGrid) {
        super(player, playersNum, market, leaderDeck, developmentGrid);
        Player lorenzo = new Player("Lorenzo");
        lorenzo.setGame(this);
        lorenzo.setConnected(false);
        FaithTrack lorenzoTrack = new FaithTrack(lorenzo) {
            @Override
            public void isInVatican(int papalSpace) {
            }

            @Override
            public void notifyPositionChange() {
                getGame().getViewAdapter().sendMessage(new LorenzoPosition());
            }

            @Override
            public void checkPosition() {
                if (getPosition() == 8 || getPosition() == 16) {
                    if (!getGame().getVaticanMap().get(getPosition())) {
                        getGame().vaticanReport(getPosition());
                    }
                } else if (getPosition() == 24) {
                    getPlayer(0).getBoard().getFaithTrack().isInVatican(24);
                    isLost = true;
                    setOver(true);
                }
            }
        };
        lorenzo.getBoard().setFaithTrack(lorenzoTrack);
        addPlayer(lorenzo);
        getTurnPhases().put("EndTurn", new SoloActionPhase(this));
    }

    @Override
    public void setUpPlayers() {
        getViewAdapter().sendTable();
        for (int i = 0; i < 4; i++)
            getPlayer(0).getLeaderCards().add(getLeaderDeck().pop());
        getViewAdapter().sendLeaderHand(getPlayer(0));
        setPlayerToWait(getPlayer(0));
    }

    @Override
    public void endGame() {
        if (!getDevelopmentGrid().isStillWinnable() && this.getPlayers().get(0).getNumOfCards() != 7) {
            isLost = true;
        }
        setFinished();
        LinkedHashMap<String, Integer> ranking = new LinkedHashMap<>();
        ranking.put(getPlayer(0).getUsername(), isLost ? -1 : -2);
        getViewAdapter().notifyGameEnded(getPlayer(0).getUsername(), getPlayer(0).getVictoryPoints(), ranking);
    }

    @Override
    public void setOver(boolean over) {
        super.setOver(over);
        endGame();
    }

    public boolean isLost() {
        return isLost;
    }
}