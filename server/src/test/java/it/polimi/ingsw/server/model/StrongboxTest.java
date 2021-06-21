package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.model.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StrongboxTest {
    private Strongbox chest;


    @BeforeEach
    public  void setUp() {
        chest = new Strongbox();

    }

    @Test
    public void resetTest(){
        assertTrue(chest.isEmptyBox());
    }

    @Test
    public void addProdResourceTest(){
        chest.addProdResource(Resource.SHIELD);
        chest.addProdResource(Resource.SHIELD);
        chest.addProdResource(Resource.SERF);
        chest.addProdResource(Resource.COIN);
        chest.addProdResource(Resource.STONE);
        chest.addProdResource(Resource.COIN);
        chest.addProdResource(Resource.SHIELD);
        chest.addProdResource(Resource.STONE);
        chest.addProdResource(Resource.COIN);
        chest.addProdResource(Resource.COIN);

        assertEquals(3,chest.getResourceMap().get(Resource.SHIELD));
        assertEquals(1,chest.getResourceMap().get(Resource.SERF));
        assertEquals(4,chest.getResourceMap().get(Resource.COIN));
        assertEquals(2,chest.getResourceMap().get(Resource.STONE));

        //test remove method

        chest.removeResource(Resource.SERF);


        chest.removeResource(Resource.SHIELD);
        chest.removeResource(Resource.SHIELD);
        chest.removeResource(Resource.SHIELD);
        chest.removeResource(Resource.COIN);


        assertEquals(0,chest.getResourceMap().get(Resource.SERF));
        assertEquals(0,chest.getResourceMap().get(Resource.SHIELD));
        assertEquals(3,chest.getResourceMap().get(Resource.COIN));

    }

}