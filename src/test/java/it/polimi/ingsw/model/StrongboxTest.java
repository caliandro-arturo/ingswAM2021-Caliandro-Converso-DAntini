package it.polimi.ingsw.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class StrongboxTest {
    private Strongbox chest;
    private PersonalBoard personalBoard;
    private HashMap<Resource, Integer> resourceMap ;


    @BeforeEach
    void setUp() {
        chest = new Strongbox();
        personalBoard = new PersonalBoard();
        resourceMap = new HashMap<>();
    }

    @Test
    public void addProdResourceTest(){
        personalBoard.resetPersonalBoard();
        personalBoard.getPersonalBox().addProdResource(Resource.SHIELD);
        personalBoard.getPersonalBox().addProdResource(Resource.SHIELD);
        personalBoard.getPersonalBox().addProdResource(Resource.SERF);
        personalBoard.getPersonalBox().addProdResource(Resource.COIN);
        personalBoard.getPersonalBox().addProdResource(Resource.STONE);
        personalBoard.getPersonalBox().addProdResource(Resource.COIN);
        personalBoard.getPersonalBox().addProdResource(Resource.SHIELD);
        personalBoard.getPersonalBox().addProdResource(Resource.STONE);
        personalBoard.getPersonalBox().addProdResource(Resource.COIN);
        personalBoard.getPersonalBox().addProdResource(Resource.COIN);

        assertEquals(personalBoard.getPersonalBox().getResourceMap().get(Resource.SHIELD),3);
        assertEquals(personalBoard.getPersonalBox().getResourceMap().get(Resource.SERF),1);
        assertEquals(personalBoard.getPersonalBox().getResourceMap().get(Resource.COIN),4);
        assertEquals(personalBoard.getPersonalBox().getResourceMap().get(Resource.STONE),2);



    }
}