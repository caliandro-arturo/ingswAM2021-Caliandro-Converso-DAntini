package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Arrays;
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
    public static ArrayList<Resource> storableResources = new ArrayList<Resource>(){{
        addAll(Arrays.asList(Resource.STONE,Resource.SERF,Resource.COIN,Resource.SHIELD));
    }};
    public static boolean isStorable(Resource resource){
        return storableResources.contains(resource);
    }
}
