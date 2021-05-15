package it.polimi.ingsw.server.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GameCreatorTest {

    @Test
    public void TestGSONCreation(){
        GameCreator creator = new GameCreator();
        assertEquals(creator.getLeaderDeck().size(),16);
        for (int i=1; i<=3; i++){
            assertEquals(creator.getDevelopmentGrid().getDeck(i, Color.BLUE).getDeck().size(),4);
            assertEquals(creator.getDevelopmentGrid().getDeck(i,Color.YELLOW).getDeck().size(),4);
            assertEquals(creator.getDevelopmentGrid().getDeck(i,Color.PURPLE).getDeck().size(),4);
            assertEquals(creator.getDevelopmentGrid().getDeck(i,Color.GREEN).getDeck().size(),4);
        }
        Game game = creator.create(new Player("pippo"),1);
        Game game1 = creator.create(new Player("pluto"),2);
        assertEquals(game.getDevelopmentGrid(), creator.getDevelopmentGrid());
        assertEquals(game.getLeaderDeck(),creator.getLeaderDeck());
        assertEquals(game1.getDevelopmentGrid(), creator.getDevelopmentGrid());
        assertEquals(game1.getLeaderDeck(),creator.getLeaderDeck());
    }
}
