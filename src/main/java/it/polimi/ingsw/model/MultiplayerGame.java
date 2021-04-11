package it.polimi.ingsw.model;

import java.util.*;

public class MultiplayerGame extends Game {
    private final int[] initialFaithPoints = new int[] {0, 0, 1, 1};
    private final int[] initialResources = new int[] {0, 1, 1, 2};

    public MultiplayerGame(Player player, int playersNum, Market market, Stack<LeaderCard> leaderDeck,
                           DevelopmentGrid developmentGrid) {
        super(player, playersNum, market, leaderDeck, developmentGrid);
        getTurnPhases().put("EndTurnPhase", new PassTurnPhase(this));
    }

    @Override
    public void setUpPlayers() {
        ArrayList<Player> players = getPlayers();
        Collections.shuffle(players);
        setPlayersToWait(new ArrayList<>(players));
        setCurrentPlayer(players.get(0));
        for(Player p: players) {
            LeaderCard[] leaderCards = new LeaderCard[4];
            for(int i = 0; i < 4; i++)
                leaderCards[i] = getLeaderDeck().pop();
            p.setLeaderCard(leaderCards);
            for(int j = 0; j < initialFaithPoints[players.indexOf(p)]; j++)  //
                p.getBoard().getPersonalPath().increasePosition();
            getViewAdapter().askArbitraryResource(p, initialResources[players.indexOf(p)]);
        }
        readyPlayer(0);
    }

    /*@Override
    public void endGame() {

    }*/
}
