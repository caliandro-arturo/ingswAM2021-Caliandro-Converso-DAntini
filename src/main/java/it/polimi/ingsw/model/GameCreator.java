package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * This class
 */
public class GameCreator {
    private final Market market = new Market();
    private final Stack<LeaderCard> leaderDeck;
    private final DevelopmentGrid developmentGrid;

    public GameCreator(LeaderCard[] leaderDeck, DevelopmentCard[] developmentGrid) {
        this.leaderDeck = new Stack<LeaderCard>() {{
            addAll(Arrays.asList(leaderDeck));
        }};
        this.developmentGrid = new DevelopmentGrid(developmentGrid);
    }

    public Game create(Player player, int playersNum) {
        if(playersNum == 1)
            return new SinglePlayerGame(player, playersNum, market, leaderDeck, developmentGrid);
        else if(playersNum >= 2 && playersNum <= 4)
            return new MultiplayerGame(player, playersNum, market, leaderDeck, developmentGrid);
        return null; //TODO error handling
    }
}
