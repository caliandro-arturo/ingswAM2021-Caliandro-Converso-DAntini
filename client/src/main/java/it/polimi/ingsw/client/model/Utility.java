package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.utility.CLIColor;
import it.polimi.ingsw.commonFiles.model.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Utility {

    public static HashMap<Color, CLIColor> colorMap = new HashMap<Color, CLIColor>(){{
        put(Color.RED,CLIColor.ANSI_RED);
        put(Color.BLUE,CLIColor.ANSI_BLUE);
        put(Color.PURPLE,CLIColor.ANSI_PURPLE);
        put(Color.GREY, CLIColor.ANSI_GREY);
        put(Color.YELLOW,CLIColor.ANSI_YELLOW);
    }};

    public static HashMap<Color, Integer> colorPosition = new HashMap<Color, Integer>(){{
        put(Color.GREEN, 0);
        put(Color.BLUE,1);
        put(Color.YELLOW,2);
        put(Color.PURPLE,3);
    }};
    public static HashMap<String, Resource> mapResource = new HashMap<String, Resource>(){{
        put("faith", Resource.FAITH);
        put("shield", Resource.SHIELD);
        put("serf", Resource.SERF);
        put("coin", Resource.COIN);
        put("stone", Resource.STONE);
    }};
    public static HashMap<String, Color> mapColor = new HashMap<String , Color>(){{
        put("GREEN" , Color.GREEN);
        put("BLUE" , Color.BLUE);
        put("PURPLE" , Color.PURPLE);
        put("YELLOW" , Color.YELLOW);
        put("WHITE" , Color.WHITE);
        put("GREY" , Color.GREY);
        put("RED" , Color.RED);
    }};
    public static HashMap<Color, Resource> colorResourceMap = new HashMap<Color, Resource>(){{
        put(Color.BLUE, Resource.SHIELD);
        put(Color.PURPLE, Resource.SERF);
        put(Color.GREY, Resource.STONE);
        put(Color.YELLOW, Resource.COIN);
        put(Color.RED, Resource.FAITH);
        put(Color.WHITE, null);
    }};
    public static ArrayList<Resource> storableResources = new ArrayList<Resource>(){{
        addAll(Arrays.asList(Resource.STONE,Resource.SERF,Resource.COIN,Resource.SHIELD));
    }};
    public static boolean isStorable(Resource resource){
        return storableResources.contains(resource);
    }
    public static HashMap<Resource,Integer> mapStrongbox = new HashMap<Resource,Integer>(){{
        put(Resource.COIN,0);
        put(Resource.SERF,1);
        put(Resource.SHIELD,3);
        put(Resource.STONE,2);
    }};
    public static HashMap<String, Resource> mapRepresentationResource = new HashMap<String, Resource>(){{
        put(CLIColor.ANSI_BLUE + "■" + CLIColor.ANSI_RESET, Resource.SHIELD);
        put(CLIColor.ANSI_RED + "■" + CLIColor.ANSI_RESET, Resource.FAITH);
        put(CLIColor.ANSI_PURPLE + "■" + CLIColor.ANSI_RESET, Resource.SERF);
        put(CLIColor.ANSI_GREY + "■" + CLIColor.ANSI_RESET, Resource.STONE);
        put(CLIColor.ANSI_YELLOW + "■" + CLIColor.ANSI_RESET, Resource.COIN);
    }};
}
