package it.polimi.ingsw.common_files.model;

import it.polimi.ingsw.common_files.model.Color;
import it.polimi.ingsw.common_files.model.ColorCost;
import it.polimi.ingsw.server.model.DevelopmentPlace;
import it.polimi.ingsw.server.model.Game;
import it.polimi.ingsw.server.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LevelCost extends ColorCost {
    private final int level;

    public LevelCost(Color[] colors, Integer[] quantity, int level){
        super(colors,quantity);
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
        if (!flag){
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
    public void getRequirements(Game game, Player player) {
        super.getRequirements(game,player);
        game.getViewAdapter().sendMessage(player,"of level" );
    }

    @Override
    public String toString() {
        ArrayList<Color> colors = new ArrayList<>();
        ArrayList<Integer> quantity = new ArrayList<>();
        for (Map.Entry<Color, Integer> entry: getCost().entrySet()){
            if (entry.getValue()!=null && entry.getKey()!=null){
                colors.add(entry.getKey());
                quantity.add(entry.getValue());
            }
        }
        return "│"+ Utility.center(colors +":"+ quantity + "  level:"+level,17) +"│\n";
    }
}
