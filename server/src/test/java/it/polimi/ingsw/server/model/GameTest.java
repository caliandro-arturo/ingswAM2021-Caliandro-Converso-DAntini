package it.polimi.ingsw.server.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.commonFiles.model.Resource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests game methods.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GameTest {
    GameCreator creator;
    DevelopmentGrid testGrid;
    Stack<LeaderCard> leaderDeck;
    Player testPlayer0 = new Player("Test0");
    Player testPlayer1 = new Player("Test1");
    Player testPlayer2 = new Player("Test2");
    Player testPlayer3 = new Player("Test3");
    Player testPlayer4 = new Player("Test4");
    Game multiTest;
    Game singleTest;

    @BeforeAll
    void setUp() {
        creator = new GameCreator();
        testGrid = creator.getDevelopmentGrid();
        leaderDeck = creator.getLeaderDeck();
        multiTest = new MultiplayerGame(testPlayer1, 4, null, leaderDeck, testGrid);
        ViewAdapterForTest.setUp();
        multiTest.setViewAdapter(ViewAdapterForTest.testView);
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

    void singleSetUp() {
        creator = new GameCreator();
        leaderDeck = creator.getLeaderDeck();
        testGrid = creator.getDevelopmentGrid();
        singleTest = new SinglePlayerGame(testPlayer0, 1, null, leaderDeck, testGrid);
        singleTest.setViewAdapter(ViewAdapterForTest.testView);
        singleTest.setUpPlayers();
        for (int i = 0; i < 2; i++) {
            testPlayer0.discardLeaderCard(testPlayer0.getLeaderCards().get(0));
        }
        singleTest.setCurrentPlayer(testPlayer0);
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
        /*System.out.println(multiTest);*/
        JsonElement json = JsonParser.parseString(multiTest.toString());
        JsonObject gameStatus = json.getAsJsonObject();
        JsonArray players = gameStatus.getAsJsonArray("players");
        System.out.println(multiTest);
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

    @Test
    void singlePlayerOverTest() {
        //asserting that the single player game ends when the player reaches the last position
        singleSetUp();
        for (int i = 0; i <= 24; i++) {
            testPlayer0.getBoard().getFaithTrack().increasePosition();
        }
        assertTrue(singleTest.isOver());
        assertTrue(singleTest.isFinished());
        assertFalse(((SinglePlayerGame) singleTest).isLost());

        singleSetUp();
        for (int i = 0; i <= 24; i++) {
            singleTest.getPlayer(1).getBoard().getFaithTrack().increasePosition();
        }
        assertTrue(singleTest.isOver());
        assertTrue(singleTest.isFinished());
        assertTrue(((SinglePlayerGame) singleTest).isLost());

        singleSetUp();
        SoloActionPhase soloPhase = (SoloActionPhase)singleTest.getTurnPhase("EndTurn");
        for (int i = 0; i < 6; i++) {
            singleTest.setCurrentTurnPhase(soloPhase);
            soloPhase.getSoloActions().set(0, SoloAction.DELPURPLE);
            soloPhase.setActionPointer(0);
            soloPhase.start();
            singleTest.nextTurnPhase();
        }
        assertTrue(singleTest.isOver());
        assertTrue(singleTest.isFinished());
        assertTrue(((SinglePlayerGame) singleTest).isLost());
    }
}