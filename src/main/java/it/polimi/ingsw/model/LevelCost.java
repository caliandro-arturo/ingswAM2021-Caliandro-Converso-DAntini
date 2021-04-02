package it.polimi.ingsw.model;

import java.util.HashMap;
import java.util.Map;

public class LevelCost extends ColorCost{
    private int level;

    public LevelCost(Color color,Integer num,int level){
        super();
        addLevelCost(color,num,level);
    }

    public void addLevelCost(Color color, Integer num, int level) {
        super.addCost(color, num);
        this.level =level;
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
            if (checkColor.get(entry.getKey()) >= getCost().get(entry.getKey())){
                flag = true;
            } else flag = false;
        }
        return flag;
    }
}
