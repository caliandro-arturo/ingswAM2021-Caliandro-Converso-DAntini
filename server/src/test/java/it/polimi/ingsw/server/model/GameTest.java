package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.model.Resource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Stack;

/**
 * Tests game methods.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GameTest {
    DevelopmentGrid testGrid = new DevelopmentGrid(new DevelopmentCard[]{
            new DevelopmentCard(1, 1, Color.GREEN, null, null)});

    LeaderCard cardPower1 = new LeaderCard(1, null, null);
    LeaderCard cardPower2 = new LeaderCard(1, null, null);
    LeaderCard cardPower3 = new LeaderCard(1, null, null);
    LeaderCard cardPower4 = new LeaderCard(1, null, null);
    Stack<LeaderCard> leaderDeck = new Stack<LeaderCard>() {{
        addAll(Arrays.asList(cardPower1, cardPower2, cardPower3, cardPower4,
                cardPower1, cardPower2, cardPower3, cardPower4,
                cardPower1, cardPower2, cardPower3, cardPower4,
                cardPower1, cardPower2, cardPower3, cardPower4));
    }};
    Player testPlayer0 = new Player("Test0");
    Player testPlayer1 = new Player("Test1");
    Player testPlayer2 = new Player("Test2");
    Player testPlayer3 = new Player("Test3");
    Player testPlayer4 = new Player("Test4");
    Game multiTest;
    Game singleTest;

    @BeforeAll
    void setUp() {
        multiTest = new MultiplayerGame(testPlayer1, 4, null, leaderDeck, testGrid);
        ViewAdapterForTest.setUp();
        multiTest.setViewAdapter(ViewAdapterForTest.testView);
        singleTest = new SinglePlayerGame(testPlayer0, 1, null, leaderDeck, testGrid);
        multiTest.setStarted(false);
        try {
            multiTest.addPlayer(testPlayer2);
        } catch (Exception e) {}
        try {
            multiTest.addPlayer(testPlayer3);
        } catch (Exception e) {}
        try {
            multiTest.addPlayer(testPlayer4);
        } catch (Exception e) {}
    }

    /**
     * Verifies that a player is not set ready until he has discarded two cards and added his
     * starting resources in his warehouse.
     */
    @Test
    void settingPlayersReady() {
        multiTest.setUpPlayers();
        testPlayer1.getBoard().addResource(Resource.COIN);
        testPlayer1.getBoard().addResource(Resource.COIN);
        testPlayer1.setInitialResources(0);
        multiTest.setPlayerReady(testPlayer1);
        assertTrue(multiTest.getPlayersToWait().contains(testPlayer1));
        testPlayer1.getLeaderCards().remove(0);
        testPlayer1.getLeaderCards().remove(0);
        assertTrue(multiTest.getPlayersToWait().contains(testPlayer1));
        testPlayer1.getBoard().getResHand().clear();
        multiTest.setPlayerReady(testPlayer1);
        assertFalse(multiTest.getPlayersToWait().contains(testPlayer1));
        testPlayer2.getLeaderCards().remove(0);
        testPlayer2.getLeaderCards().remove(0);
        testPlayer3.getLeaderCards().remove(0);
        testPlayer3.getLeaderCards().remove(0);
        testPlayer4.getLeaderCards().remove(0);
        testPlayer4.getLeaderCards().remove(0);
        testPlayer2.setInitialResources(0);
        testPlayer3.setInitialResources(0);
        testPlayer4.setInitialResources(0);
        multiTest.setPlayerReady(testPlayer2);
        multiTest.setPlayerReady(testPlayer3);
        multiTest.setPlayerReady(testPlayer4);
        assertTrue(multiTest.isStarted());
    }

    /**
     * Verifies that the last Vatican Report sets over the game.
     */
    @Test
    void vaticanReportThatEndsTheGame() {
        multiTest.getVaticanMap().replace(8, true);
        multiTest.getVaticanMap().replace(16, true);
        testPlayer1.getBoard().getFaithTrack().setPosition(23);
        testPlayer1.getBoard().getFaithTrack().increasePosition();
        assertTrue(multiTest.isOver());
    }
}