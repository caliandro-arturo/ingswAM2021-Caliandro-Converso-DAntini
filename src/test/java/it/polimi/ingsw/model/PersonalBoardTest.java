package it.polimi.ingsw.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PersonalBoardTest {

    private PersonalBoard board;
    private Player pippo = new Player(("pippo"));
    private DevelopmentCard level1card = new DevelopmentCard(1, 1, null, null, null);
    private DevelopmentCard level2card = new DevelopmentCard(1, 2, null, null, null);
    private DevelopmentCard level3card = new DevelopmentCard(1, 3, null, null, null);

    Game testGame = new Game(pippo, 4, null, null, null) {
        @Override
        public void setUpPlayers() {

        }

        @Override
        public void endGame() {

        }

    };

    @BeforeEach
    void setUp() throws GameException.GameAlreadyFull, GameException.NicknameAlreadyTaken {
        pippo.setGame(testGame);
        board = pippo.getBoard();
        testGame.addPlayer(new Player("pluto"));
        testGame.addPlayer(new Player("topolino"));
        testGame.addPlayer(new Player("minnie"));
    }

    @Test
    public void buildAndReset() {
        assertEquals(1, (board.getStore().get(0)).getSize());
        assertEquals(2, (board.getStore().get(1)).getSize());
        assertEquals(3, (board.getStore().get(2)).getSize());
        assertEquals(1, (board.getPersonalPath()).getPosition());
        assertEquals(0, (board.getPersonalPath()).getScoreCard());
        assertEquals(4, board.getPersonalBox().getResourceMap().size());
        assertTrue(board.getPersonalBox().getResourceMap().containsKey(Resource.SHIELD));
        assertTrue(board.getPersonalBox().getResourceMap().containsKey(Resource.COIN));
        assertTrue(board.getPersonalBox().getResourceMap().containsKey(Resource.SERF));
        assertTrue(board.getPersonalBox().getResourceMap().containsKey(Resource.STONE));
    }

    @Test
    public void addCardTest() {
        board.addCard(level1card, 2);
        board.addCard(level2card, 2);
        board.addCard(level3card, 2);
        assertEquals(level1card, board.getPersonalDevelopmentSpace()[1].getLevel1Card());
        assertEquals(level2card, board.getPersonalDevelopmentSpace()[1].getLevel2Card());
        assertEquals(level3card, board.getPersonalDevelopmentSpace()[1].getLevel3Card());
        board.addCard(level1card, 1);
        board.addCard(level3card, 1);
        assertEquals(level1card, board.getPersonalDevelopmentSpace()[0].getDevelopmentCards().peek());
        board.addCard(level3card, 3);
        assertTrue(board.getPersonalDevelopmentSpace()[2].getDevelopmentCards().isEmpty());
    }

    @Test
    public void addHandTest(){
        board.addResource(Resource.SHIELD);
        board.addResource(Resource.SHIELD);
        board.addResource(Resource.COIN);
        board.addResource(Resource.COIN);
        board.addResource(Resource.SERF);
        assertTrue(board.getResHand().contains(Resource.COIN));
        assertTrue(board.getResHand().contains(Resource.SHIELD));
        assertTrue(board.getResHand().contains(Resource.SERF));
    }

    @Test
    public void deployTest(){
        board.addResource(Resource.SHIELD);
        board.addResource(Resource.SHIELD);
        board.addResource(Resource.COIN);
        board.addResource(Resource.COIN);
        board.addResource(Resource.SERF);
        ArrayList<Resource> resHH = board.getResHand();

        board.deployResource(resHH.get(0), 2);
        board.deployResource(resHH.get(0), 2);
        board.deployResource(resHH.get(0), 3);
        board.deployResource(resHH.get(0), 3);
        board.deployResource(resHH.get(0), 1);
        assertEquals(Resource.SERF, board.getStore().get(0).getTypeOfResource());
        assertEquals(Resource.COIN, board.getStore().get(2).getTypeOfResource());
        assertEquals(Resource.SHIELD, board.getStore().get(1).getTypeOfResource());
        assertEquals(1,board.getStore().get(0).getResources().size());
        assertEquals(2,board.getStore().get(1).getResources().size());
        assertEquals(2,board.getStore().get(2).getResources().size());
    }

    @Test
    public void takeOutTest(){
        board.addResource(Resource.SHIELD);
        board.addResource(Resource.SHIELD);
        board.addResource(Resource.COIN);
        board.addResource(Resource.COIN);
        board.addResource(Resource.SERF);
        ArrayList<Resource> resHH = board.getResHand();
        board.deployResource(resHH.get(0), 2);
        board.deployResource(resHH.get(0), 2);
        board.deployResource(resHH.get(0), 3);
        board.deployResource(resHH.get(0), 3);
        board.deployResource(resHH.get(0), 1);
        board.takeOutResource(1);
        board.takeOutResource(2);
        board.takeOutResource(3);
        assertTrue(board.getResHand().contains(Resource.SHIELD));
        assertTrue(board.getResHand().contains(Resource.COIN));
        assertTrue(board.getResHand().contains(Resource.SERF));
    }
}