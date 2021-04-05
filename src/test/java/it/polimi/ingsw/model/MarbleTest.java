package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MarbleTest {
    private ArrayList<Resource> resources = new ArrayList<>();
    private PersonalBoard testBoard = new PersonalBoard() {
        @Override
        public void addResource(Resource resource) {
            resources.add(resource);
        }
    };
    Player testPlayer = new Player("test", testBoard, new LeaderCard[0]);

    /**
     * This method verify that every marble activates correctly its action according to its color
     */
    @Test
    public void gettingResources() {
        Marble blueMarble = new Marble(Color.BLUE);
        blueMarble.pick(testPlayer);
        assertEquals(Resource.SHIELD, resources.get(0));
        Marble purpleMarble = new Marble(Color.PURPLE);
        purpleMarble.pick(testPlayer);
        assertEquals(Resource.SERF, resources.get(1));
        Marble greyMarble = new Marble(Color.GREY);
        greyMarble.pick(testPlayer);
        assertEquals(Resource.STONE, resources.get(2));
        Marble yellowMarble = new Marble(Color.YELLOW);
        yellowMarble.pick(testPlayer);
        assertEquals(Resource.COIN, resources.get(3));
        int oldPosition = testBoard.getPersonalPath().getPosition();
        Marble redMarble = new Marble(Color.RED);
        redMarble.pick(testPlayer);
        assertEquals(oldPosition + 1, testBoard.getPersonalPath().getPosition());
        Marble whiteMarble = new Marble(Color.WHITE);
        whiteMarble.pick(testPlayer);
        assertEquals(4, resources.size());
        testPlayer.addWhiteAlt(Resource.COIN);
        whiteMarble.pick(testPlayer);
        assertEquals(Resource.COIN, resources.get(4));
        testPlayer.addWhiteAlt(Resource.STONE);
        InputStream oldIn= System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        whiteMarble.pick(testPlayer);
        assertEquals(Resource.COIN, resources.get(5));
        in = new ByteArrayInputStream("2".getBytes());
        System.setIn(in);
        whiteMarble.pick(testPlayer);
        assertEquals(Resource.STONE, resources.get(6));
        System.setIn(oldIn);
    }
}