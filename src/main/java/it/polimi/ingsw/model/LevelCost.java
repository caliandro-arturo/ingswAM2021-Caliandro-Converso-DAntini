package it.polimi.ingsw.model;

import java.util.HashMap;

public class LevelCost extends ColorCost{
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
        DevelopmentPlace[] devPlace = player.getBoard().getPersonalDevelopmentSpace();
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
    public void getRequirements(Game game,Player player) {
        super.getRequirements(game,player);
        game.getViewAdapter().sendMessage(player,"of level" );
    }
}
