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
        HashMap<Color,Integer> checkColor = new HashMap<>();
        checkColor.put(Color.GREEN,0);
        checkColor.put(Color.BLUE,0);
        checkColor.put(Color.YELLOW,0);
        checkColor.put(Color.PURPLE,0);
        int j;
        for (int i = 0;i<player.getBoard().getPersonalDevelopmentSpace().length;i++){
            if (player.getBoard().getPersonalDevelopmentSpace()[i].getLevelCard(level)!=null){
                j= checkColor.get(player.getBoard().getPersonalDevelopmentSpace()[i].getLevelCard(level).getColor());
                checkColor.replace(player.getBoard().getPersonalDevelopmentSpace()[i].getLevelCard(level).getColor(),j,j+1);
            }
        }
        boolean flag = false;
        for (Map.Entry<Color, Integer> entry: getCost().entrySet()){
            flag = checkColor.get(entry.getKey()) >= getCost().get(entry.getKey());
        }
        return flag;
    }

    @Override
    public void getRequirements() {
        super.getRequirements();
        System.out.println("of level" + level);
    }
}
