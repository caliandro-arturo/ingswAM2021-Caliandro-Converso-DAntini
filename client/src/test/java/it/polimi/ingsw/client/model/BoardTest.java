package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    private Board board = new Board();
    private ArrayList<Resource> resources = new ArrayList<>(Arrays.asList(Resource.SERF, Resource.SERF));

    @BeforeEach
    void setUp(){
        board.getWarehouseStore().setRes(Resource.SERF,2);
        board.getWarehouseStore().setRes(Resource.SERF,2);
    }

    @Test
    void updateResourceTest(){
        board.removeResource(new int[]{2, 2}, resources);
        assertEquals(0,board.getWarehouseStore().getRes().get(1).size());
    }

}