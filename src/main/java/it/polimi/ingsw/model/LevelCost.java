package it.polimi.ingsw.model;

import java.util.HashMap;
import java.util.Map;

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
        int j;
        DevelopmentPlace[] devPlace = player.getBoard().getPersonalDevelopmentSpace();
        for (int i = 0; i < devPlace.length; i++) {
            if (!devPlace[i].getDevelopmentCards().empty()) {
                for (int k = 0; k < devPlace[i].getDevelopmentCards().size(); k++) {
                    j = checkColor.get(devPlace[i].getLevelICard(k).getColor());
                    checkColor.replace(devPlace[i].getLevelICard(k).getColor(), j, j + 1);
                }
            }
        }
        boolean flag = false;
        for (Map.Entry<Color, Integer> entry : getCost().entrySet()) {
            flag = checkColor.get(entry.getKey()) >= getCost().get(entry.getKey());
        }
        return flag;
    }

    @Override
    public void getRequirements(Game game,Player player) {
        super.getRequirements(game,player);
        game.getViewAdapter().sendMessage(player,"of level" );
    }
}
