package it.polimi.ingsw.model;

import java.util.HashMap;

public class UtilityMap {
    public static HashMap<String, Resource> mapResource = new HashMap<String, Resource>(){{
        put("faith", Resource.FAITH);
        put("shield", Resource.SHIELD);
        put("serf", Resource.SERF);
        put("coin", Resource.COIN);
        put("stone", Resource.STONE);
    }};
    public static HashMap<String, Color> mapColor = new HashMap<String ,Color>(){{
        put("GREEN" , Color.GREEN);
        put("BLUE" , Color.BLUE);
        put("PURPLE" , Color.PURPLE);
        put("YELLOW" , Color.YELLOW);
    }};
    public static HashMap<Color,Integer> colorPosition = new HashMap<Color,Integer>(){{
        put(Color.GREEN, 0);
        put(Color.BLUE,1);
        put(Color.YELLOW,2);
        put(Color.PURPLE,3);
    }};
}
