package it.polimi.ingsw.client.model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

class FaithTrackTest {
    private final HashMap<Integer, Boolean> vaticanMap = new HashMap<Integer, Boolean>() {{
        put(1, true);
        put(2, false);
        put(3, true);
    }};
    private FaithTrack track = new FaithTrack(16, vaticanMap);
    @Test
    public void printTest(){
        System.out.println(track);
    }
}