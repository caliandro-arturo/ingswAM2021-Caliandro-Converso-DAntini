package it.polimi.ingsw.server.model;

import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

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
        players.forEach(p -> getViewAdapter().notifyPlayerTurnNumber(p, players.indexOf(p) + 1));
        setPlayersToWait(new ArrayList<>(players));
        setCurrentPlayer(players.get(0));
        for(Player p : players) {
            for (int i = 0; i < 4; i++)
                p.getLeaderCards().add(getLeaderDeck().pop());
            for (int i = 0; i < initialFaithPoints[players.indexOf(p)]; i++)
                p.getBoard().getPersonalPath().increasePosition();
            getViewAdapter().notifyInitialResourcesAmount(p, initialResources[players.indexOf(p)]);

        }
    }

    @Override
    public void endGame() {
        setFinished();
        AtomicInteger i = new AtomicInteger();
        getPlayers().stream()
                .sorted((p1, p2) ->
                        Arrays.stream(p2.getVictoryPoints()).sum() - Arrays.stream(p1.getVictoryPoints()).sum())
                .forEach(p ->
                     getViewAdapter().notifyGameEnded(p, i.incrementAndGet(), p.getVictoryPoints()));
    }


}