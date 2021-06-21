package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.model.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseStoreTest {

    private WarehouseStore depot1;
    private WarehouseStore depot2;
    private WarehouseStore depot3;

    @BeforeEach
    void setUp(){
        depot1 = new WarehouseStore(1);
        depot2 = new WarehouseStore(2);
        depot3 = new WarehouseStore(3);
    }

    @Test
    void addAndRemoveTest(){
        depot1.addResource(Resource.STONE);
        depot2.addResource(Resource.COIN);
        depot2.addResource(Resource.COIN);
        depot3.addResource(Resource.SERF);
        depot3.addResource(Resource.SERF);
        depot3.addResource(Resource.SERF);

        assertEquals(Resource.STONE, depot1.getTypeOfResource());
        assertEquals(Resource.STONE, depot1.getResources().get(0));
        assertEquals(1, depot1.getResources().size());
        assertEquals(Resource.COIN, depot2.getTypeOfResource());
        assertEquals(Resource.COIN, depot2.getResources().get(0));
        assertEquals(Resource.COIN, depot2.getResources().get(1));
        assertEquals(2, depot2.getResources().size());
        assertEquals(Resource.SERF, depot3.getTypeOfResource());
        assertEquals(Resource.SERF, depot3.getResources().get(0));
        assertEquals(Resource.SERF, depot3.getResources().get(1));
        assertEquals(Resource.SERF, depot3.getResources().get(2));
        assertEquals(3, depot3.getResources().size());

        assertEquals(Resource.STONE, depot1.takeOutResource());
        assertNull(depot1.getTypeOfResource());
        assertEquals(Resource.COIN, depot2.takeOutResource());
        assertEquals(Resource.COIN, depot2.takeOutResource());
        assertNull(depot2.getTypeOfResource());
        assertEquals(Resource.SERF, depot3.takeOutResource());
        assertEquals(Resource.SERF, depot3.takeOutResource());
        assertEquals(Resource.SERF, depot3.takeOutResource());
        assertNull(depot3.getTypeOfResource());
        assertTrue(depot1.getResources().isEmpty());
        assertTrue(depot2.getResources().isEmpty());
        assertTrue(depot3.getResources().isEmpty());
    }
}