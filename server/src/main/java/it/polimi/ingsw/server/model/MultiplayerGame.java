package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.messages.toClient.updates.InitBoards;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiplayerGame extends Game {
    private final int[] initialFaithPoints = new int[] {0, 0, 1, 1};
    private final int[] initialResources = new int[] {0, 1, 1, 2};

    public MultiplayerGame(Player player, int playersNum, Market market, Stack<LeaderCard> leaderDeck,
                           DevelopmentGrid developmentGrid) {
        super(player, playersNum, market, leaderDeck, developmentGrid);
        getTurnPhases().put("EndTurn", new PassTurnPhase(this));
    }

    @Override
    public void setUpPlayers() {
        getViewAdapter().sendTable();
        //shuffling players
        ArrayList<Map.Entry<String, Player>> playersEntryList = new ArrayList<>(getPlayersMap().entrySet());
        Collections.shuffle(playersEntryList);
        getPlayersMap().clear();
        playersEntryList.forEach(p -> getPlayersMap().put(p.getKey(), p.getValue()));
        //notifying players about their position
        ArrayList<Player> players = getPlayers();
        ArrayList<String> usernames = new ArrayList<>();
        players.forEach(p -> {
            usernames.add(p.getUsername());
            getViewAdapter().notifyPlayerTurnNumber(p, players.indexOf(p) + 1);
        });
        //sending player names to all
        getViewAdapter().sendMessage(new InitBoards(usernames));
        setPlayersToWait(new ArrayList<>(players));
        for(Player p : players) {
            //assigning leader cards
            for (int i = 0; i < 4; i++)
                p.getLeaderCards().add(getLeaderDeck().pop());
            getViewAdapter().sendLeaderHand(p);
            //assigning initial faith points
            for (int i = 0; i < initialFaithPoints[players.indexOf(p)]; i++)
                p.getBoard().getFaithTrack().increasePosition();
            //assigning initial resource values
            p.setInitialResources(initialResources[players.indexOf(p)]);
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

    @Override
    public String toString() {
        return super.toString();
    }
}
