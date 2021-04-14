package it.polimi.ingsw.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MarbleAndColorTest {
    int i = 0;
    ArrayList<Resource> resources = new ArrayList<>();
    PersonalBoard testBoard = new PersonalBoard() {
        @Override
        public void addResource(Resource resource) {
            resources.add(resource);
        }
    };
    Player testPlayer = new Player("test");
    Game testGame = new Game(testPlayer, 1, null, null, null) {
        @Override
        public void setUpPlayers() {
        }

        @Override
        public void endGame() {

        }
    };
    ViewAdapter testCaller = new ViewAdapter(testGame) {
        @Override
        public void askWhiteMarbleResource() {
            testGame.getControllerAdapter().giveChosenWhiteMarbleResource(testPlayer, ++i);
        }
    };
    @BeforeEach
    void setUp() {
        testPlayer.setBoard(testBoard);
        testGame.setCurrentPlayer(testPlayer);
        testGame.setViewAdapter(testCaller);
    }
    /**
     * Verifies that every marble activates correctly its action according to its color
     */
    @Test
    public void gettingResources() {
        new Marble(Color.BLUE).pick(testGame);
        assertEquals(Resource.SHIELD, resources.get(0));
        new Marble(Color.PURPLE).pick(testGame);
        assertEquals(Resource.SERF, resources.get(1));
        new Marble(Color.GREY).pick(testGame);
        assertEquals(Resource.STONE, resources.get(2));
        new Marble(Color.YELLOW).pick(testGame);
        assertEquals(Resource.COIN, resources.get(3));
        int oldPosition = testBoard.getPersonalPath().getPosition();
        new Marble(Color.RED).pick(testGame);
        assertEquals(oldPosition + 1, testBoard.getPersonalPath().getPosition());
        Marble whiteMarble = new Marble(Color.WHITE);
        whiteMarble.pick(testGame);
        assertEquals(4, resources.size());
        testPlayer.addWhiteAlt(Resource.SERF);
        whiteMarble.pick(testGame);
        assertEquals(Resource.SERF, resources.get(4));
        testPlayer.addWhiteAlt(Resource.STONE);
        whiteMarble.pick(testGame);
        assertEquals(Resource.SERF, resources.get(5));
        whiteMarble.pick(testGame);
        assertEquals(Resource.STONE, resources.get(6));
    }
}