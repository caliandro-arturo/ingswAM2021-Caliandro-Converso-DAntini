package it.polimi.ingsw.server.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FaithTrackTest {
    private FaithTrack track;
    private Player playerTest = new Player(("pippo"));

    Game multiplayerGame = new Game(playerTest, 4, null, null, null){
        @Override
        public void setUpPlayers() {
        }

        @Override
        public void endGame() {
            
        }
    };

    @BeforeEach
    void setUp() throws GameException.GameAlreadyFull, GameException.NicknameAlreadyTaken {
        ViewAdapterForTest.setUp();
        multiplayerGame.setViewAdapter(ViewAdapterForTest.testView);
        track = playerTest.getBoard().getFaithTrack();
        multiplayerGame.addPlayer( new Player("pluto"));
        multiplayerGame.addPlayer( new Player("topolino"));
        multiplayerGame.addPlayer( new Player("minnie"));
    }

    @Test
    public void faithTest(){
        Player pippo = multiplayerGame.getPlayer(0);
        Player pluto = multiplayerGame.getPlayer(1);
        Player topolino = multiplayerGame.getPlayer(2);
        Player minnie = multiplayerGame.getPlayer(3);

        assertEquals(1,track.getPosition());
        assertEquals(0, track.getScoreCard());
        track.increasePosition();
        assertEquals(2, track.getPosition());
        for (int i4 = 0; i4 < 3; i4++) {
            track.increasePosition();
        }
        assertEquals(5, track.getPosition());
        for (int i4 = 0; i4 < 2; i4++) {
            track.increasePosition();
        }//pippo 7 pos
        for (int i3 = 0; i3 < 3; i3++) {
            pluto.getBoard().getFaithTrack().increasePosition();
        }
        for (int i2 = 0; i2 < 2; i2++) {
            pluto.getBoard().getFaithTrack().increasePosition();
        }//pluto, 5 pos
        for (int i1 = 0; i1 < 3; i1++) {
            topolino.getBoard().getFaithTrack().increasePosition();
        }// topolino, 3 pos
        for (int k = 0; k < 8; k++) {
            minnie.getBoard().getFaithTrack().increasePosition();
        }//minnie, 8 pos
        assertEquals(2, pippo.getBoard().getFaithTrack().getScoreCard());
        assertEquals(2, pluto.getBoard().getFaithTrack().getScoreCard());
        assertEquals(0, topolino.getBoard().getFaithTrack().getScoreCard());
        assertEquals(2, minnie.getBoard().getFaithTrack().getScoreCard());
        // primo papal space
        track.increasePosition();
        track.increasePosition();//pippo pos 9
        assertEquals(2, pippo.getBoard().getFaithTrack().getScoreCard());
        for (int j = 0; j < 5; j++) {
            minnie.getBoard().getFaithTrack().increasePosition();
        }//minnie pos 13
        for (int j = 0; j < 7; j++) {
            track.increasePosition();
        }//pippo pos 16
        assertEquals(5, minnie.getBoard().getFaithTrack().getScoreCard());
        //secondo papal space
        assertEquals(5, pippo.getBoard().getFaithTrack().getScoreCard());
        for (int i = 0; i < 15; i++) {
            topolino.getBoard().getFaithTrack().increasePosition();
        }//topolino pos 18
        assertEquals(5, minnie.getBoard().getFaithTrack().getScoreCard());
        assertEquals(0, topolino.getBoard().getFaithTrack().getScoreCard());
        assertEquals(5, pippo.getBoard().getFaithTrack().getScoreCard());
        assertEquals(2, pluto.getBoard().getFaithTrack().getScoreCard());

    }
}