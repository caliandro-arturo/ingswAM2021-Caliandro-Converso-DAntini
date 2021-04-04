package it.polimi.ingsw.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PersonalBoardTest {

    private static PersonalBoard board;

    @BeforeEach
    void setUp() {
        board = new PersonalBoard();
    }

    @Test
    public void BuildAndReset(){
        assertEquals(1,(board.getStore().get(0)).getSize());
        assertEquals(2,(board.getStore().get(1)).getSize());
        assertEquals(3,(board.getStore().get(2)).getSize());
        assertEquals(0,(board.getPersonalPath()).getPosition());
        assertEquals(0,(board.getPersonalPath()).getScoreCard());
        assertEquals(4,board.getPersonalBox().getResourceMap().size());
        assertTrue(board.getPersonalBox().getResourceMap().containsKey(Resource.SHIELD));
        assertTrue(board.getPersonalBox().getResourceMap().containsKey(Resource.COIN));
        assertTrue(board.getPersonalBox().getResourceMap().containsKey(Resource.SERF));
        assertTrue(board.getPersonalBox().getResourceMap().containsKey(Resource.STONE));


    }

    @Test
    public void addAndRemoveResourcesTest(){
        board.addResource(Resource.COIN);
        board.addResource(Resource.SHIELD);
        board.addResource(Resource.SHIELD);
        board.addResource(Resource.SERF);
        board.addResource(Resource.SERF);
        board.addResource(Resource.SERF);

        assertEquals(Resource.COIN, board.getStore().get(0).getResource());
        assertEquals(Resource.SHIELD, board.getStore().get(1).getResource());
        assertEquals(Resource.SHIELD, board.getStore().get(1).getResource());
        assertEquals(Resource.SERF, board.getStore().get(2).getResource());
        assertEquals(Resource.SERF, board.getStore().get(2).getResource());
        assertEquals(Resource.SERF, board.getStore().get(2).getResource());
        assertEquals(1,board.getStore().get(0).getQuantity());
        assertEquals(2,board.getStore().get(1).getQuantity());
        assertEquals(3,board.getStore().get(2).getQuantity());



    }

}