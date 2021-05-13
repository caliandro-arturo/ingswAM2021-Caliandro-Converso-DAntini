package it.polimi.ingsw.common_files.model;

import it.polimi.ingsw.client.CLI.CLIColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Utility {
    public static String center(String s, int size) {
        return center(s, size, ' ');
    }

    public static String center(String s, int size, char pad) {
        if (s == null)
            return s;
        int realSize = s.length();
        for (CLIColor c : CLIColor.values()) {
            int colorLength = c.toString().length();
            for (int i = 0; i <= s.length() - colorLength; i++)
                if (s.substring(i, i + colorLength).equals(c.toString()))
                    realSize -= colorLength;
        }
        if (size <= realSize) return s;
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < (size - realSize) / 2; i++) {
            sb.append(pad);
        }
        sb.append(s);
        while (sb.length() < size + s.length() - realSize) {
            sb.append(pad);
        }
        return sb.toString();
    }

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
    }};
    public static ArrayList<Resource> storableResources = new ArrayList<Resource>(){{
        addAll(Arrays.asList(Resource.STONE,Resource.SERF,Resource.COIN,Resource.SHIELD));
    }};
    public static boolean isStorable(Resource resource){
        return storableResources.contains(resource);
    }
}
