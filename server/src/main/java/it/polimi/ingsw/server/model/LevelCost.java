package it.polimi.ingsw.server.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Represents the level requirements the player needs to deploy a leader card.
 */
public class LevelCost extends ColorCost implements Requirements {
    private final int level;

    public LevelCost(Color[] colors, Integer[] quantity, int level) {
        super(colors, quantity);
        this.level = level;
    }

    @Override
    public boolean checkRequirements(Player player) {
        HashMap<Color, Integer> checkColor = new HashMap<>();
        checkColor.put(Color.GREEN, 0);
        checkColor.put(Color.BLUE, 0);
        checkColor.put(Color.YELLOW, 0);
        checkColor.put(Color.PURPLE, 0);
        boolean flag = false;
        DevelopmentPlace[] devPlace = player.getBoard().getDevelopmentSpace();
        for (DevelopmentPlace developmentPlace : devPlace) {
            if (!developmentPlace.getDevelopmentCards().empty()) {
                if (developmentPlace.getDevelopmentCards().size() < level) {
                    flag = false;
                } else {
                    flag = true;
                    break;
                }
            }
        }
        if (!flag) {
            return false;
        } else {
            for (DevelopmentPlace developmentPlace : devPlace) {
                if (developmentPlace.getDevelopmentCards().size() >= level) {
                    if (getCost().get(developmentPlace.getDevelopmentCards().get(level - 1).getColor()) == 1) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    @Override
    public String[] identifier() {
        ArrayList<String> arguments = new ArrayList<>(Arrays.asList(super.identifier()));
        arguments.set(0, "levelCost");
        arguments.add(Integer.toString(level));
        return arguments.toArray(new String[4]);
    }

}
