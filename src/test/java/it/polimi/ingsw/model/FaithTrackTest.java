package it.polimi.ingsw.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FaithTrackTest {
    private static FaithTrack track;
    private static Game game;

    @BeforeEach
    void setUp() {
        track = new FaithTrack();
    }

    @Test
    public void faithTest(){
        assertEquals(1,track.getPosition());
        assertEquals(0, track.getScoreCard());
        track.increasePosition();
        assertEquals(2, track.getPosition());
        track.increasePosition();
        track.increasePosition();
        track.increasePosition();
        assertEquals(5, track.getPosition());
        track.increasePosition();
        track.increasePosition();

        track.isInVatican(8);
        assertEquals(2, track.getScoreCard());
        track.isInVatican(16);
        track.isInVatican(24);
        assertEquals(2, track.getScoreCard());



    }
}