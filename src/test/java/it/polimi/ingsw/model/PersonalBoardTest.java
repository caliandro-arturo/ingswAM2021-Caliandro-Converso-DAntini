package it.polimi.ingsw.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalBoardTest {

    private static PersonalBoard board = null;
    private static WarehouseStore[] store = null;
    private static Strongbox personalBox = null;


    @BeforeEach
    void setUp() {
        board = new PersonalBoard();
        store = new WarehouseStore[3];
        personalBox = new Strongbox();

    }

    @Test
    public void buildandReset(){
        board.resetPersonalBoard();
        assertEquals((board.getStore()[0]).getSize(),1);
        assertEquals((board.getStore()[1]).getSize(),2);
        assertEquals((board.getStore()[2]).getSize(),3);
        assertEquals((board.getPersonalPath()).getPosition(), 0);
        assertEquals((board.getPersonalPath()).getScoreCard(), 0);
        //test hashmap
    }

}