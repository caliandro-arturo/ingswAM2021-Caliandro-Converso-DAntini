package it.polimi.ingsw.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GameCreatorTest {

    @Test
    public void TestGSONCreation(){
        GameCreator creator = new GameCreator();
        assertEquals(creator.getLeaderDeck().size(),16);
    }
}
