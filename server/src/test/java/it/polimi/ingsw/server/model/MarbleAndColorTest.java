package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.model.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarbleAndColorTest {
    /**
     * Index used to scroll on white alteration list in Player
     */
    int i = 0;
    List<Resource> resources = new ArrayList<>();
    Player testPlayer = new Player("test");
    Board testBoard = new Board(testPlayer) {
        @Override
        public void addResource(Resource resource) {
            resources.add(resource);
        }
    };
    Game testGame;
    ViewAdapter testCaller = new ViewAdapter(testGame) {
        @Override
        public void incrementFaithTrackPosition(Player player) {
        }
    };

    @BeforeEach
    void setUp() {
        testPlayer.setBoard(testBoard);
         testGame = new Game(testPlayer, 1, null, null, null) {
            @Override
            public void setUpPlayers() {
            }

            @Override
            public void endGame() {
            }
        };
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
        int oldPosition = testBoard.getFaithTrack().getPosition();
        new Marble(Color.RED).pick(testGame);
        assertEquals(oldPosition + 1, testBoard.getFaithTrack().getPosition());
        Marble whiteMarble = new Marble(Color.WHITE);
        whiteMarble.pick(testGame);
        assertEquals(4, resources.size());
        testPlayer.addWhiteAlt(Resource.SERF);
        whiteMarble.pick(testGame);
        assertEquals(Resource.SERF, resources.get(4));
        testPlayer.addWhiteAlt(Resource.STONE);
        assertEquals(5, resources.size());
    }
}