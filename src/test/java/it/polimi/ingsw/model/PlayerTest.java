package it.polimi.ingsw.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player pippo= new Player("pippo");

    Game testGame = new Game(pippo, 4, null, null, null) {
        @Override
        public void setUpPlayers() {

        }

        @Override
        public void endGame() {

        }
    };

    @BeforeEach
    void setUp() {

    }

    @Test
    void victoryPointsTest() {
        pippo.getBoard().getPersonalPath().increasePosition();
        pippo.getBoard().getPersonalPath().increasePosition();
        pippo.getBoard().getPersonalPath().increasePosition();
        pippo.getBoard().addCard(new DevelopmentCard(2,1,null,null, null), 2);
        //2 VP in [1]
        pippo.getBoard().addActiveLeader(new LeaderCard(3,null,null));
        //3 VP in [2]
        pippo.getBoard().getPersonalPath().increasePosition();
        pippo.getBoard().getPersonalPath().increasePosition();
        pippo.getBoard().getPersonalPath().increasePosition();
        pippo.getBoard().getPersonalPath().increasePosition();
        pippo.getBoard().getPersonalPath().increasePosition();
        //2 VP in [3]
        pippo.getBoard().getPersonalBox().addProdResource(Resource.COIN);
        pippo.getBoard().getPersonalBox().addProdResource(Resource.COIN);
        pippo.getBoard().getPersonalBox().addProdResource(Resource.COIN);
        pippo.getBoard().getPersonalBox().addProdResource(Resource.COIN);
        pippo.getBoard().getPersonalBox().addProdResource(Resource.COIN);
        pippo.getBoard().getPersonalBox().addProdResource(Resource.COIN);
        pippo.getBoard().getPersonalBox().addProdResource(Resource.STONE);
        pippo.getBoard().getPersonalBox().addProdResource(Resource.STONE);
        pippo.getBoard().getPersonalBox().addProdResource(Resource.STONE);
        pippo.getBoard().getPersonalBox().addProdResource(Resource.STONE);
        // 1 VP in [4]
        int[] vic = pippo.getVictoryPoints();
        assertEquals(4, vic[0]);
        assertEquals(2, vic[1]);
        assertEquals(3, vic[2]);
        assertEquals(2, vic[3]);
        assertEquals(2, vic[4]);
    }
}