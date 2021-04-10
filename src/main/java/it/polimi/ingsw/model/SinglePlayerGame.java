package it.polimi.ingsw.model;

import java.util.Stack;

public class SinglePlayerGame extends Game {
    public SinglePlayerGame(Player player, int playersNum, Market market, Stack<LeaderCard> leaderDeck, DevelopmentGrid developmentGrid) {
        super(player, playersNum, market, leaderDeck, developmentGrid);
        Player lorenzo = new Player("Lorenzo");
        addPlayer(lorenzo);
    }


    @Override
    public void setUpPlayers() {
        setCurrentPlayer(getPlayer(1));
    }

}