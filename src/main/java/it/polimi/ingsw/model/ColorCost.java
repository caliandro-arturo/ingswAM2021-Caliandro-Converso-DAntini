package it.polimi.ingsw.model;
import java.util.*;

public class ColorCost implements Requirements{
    private HashMap<Color, Integer> cost;

    public ColorCost(){
        cost = new HashMap<>();
    }
    public void addCost(Color color,Integer num){
        this.cost.put(color,num);
    }

    public HashMap<Color, Integer> getCost() {
        return cost;
    }

    /*
        possible null exception
         */
    @Override
    public boolean checkRequirements(Player player) {
        HashMap<Color,Integer> checkColor = new HashMap<>();
        checkColor.put(Color.GREEN,0);
        checkColor.put(Color.BLUE,0);
        checkColor.put(Color.YELLOW,0);
        checkColor.put(Color.PURPLE,0);
        int j;
        for (int i = 0;i<player.getBoard().getPersonalDevelopmentSpace().length;i++){
            for (int k=1; k<4;k++){
                if (player.getBoard().getPersonalDevelopmentSpace()[i].getLevelCard(k)!=null){
                    j= checkColor.get(player.getBoard().getPersonalDevelopmentSpace()[i].getLevelCard(k).getColor());
                    checkColor.replace(player.getBoard().getPersonalDevelopmentSpace()[i].getLevelCard(k).getColor(),j,j+1);
                }
            }
        }
        boolean flag = false;
        for (Map.Entry<Color, Integer> entry: cost.entrySet()){
            if (checkColor.get(entry.getKey()) >= cost.get(entry.getKey())){
                flag = true;
            } else flag = false;
        }
        return flag;
    }
}
