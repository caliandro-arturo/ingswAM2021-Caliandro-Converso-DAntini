package it.polimi.ingsw.model;
import java.util.*;

public class ColorCost implements Requirements{
    private final HashMap<Color, Integer> cost = new HashMap<>();

    public ColorCost(Color[] colors, Integer[] quantity){
        for (int i = 0;i<colors.length;i++){
            cost.put(colors[i],quantity[i]);
        }
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
            flag = checkColor.get(entry.getKey()) >= cost.get(entry.getKey());
        }
        return flag;
    }

    @Override
    public void getRequirements() {
        System.out.println("to deploy this card you must have: ");
        for (Map.Entry<Color,Integer> entry: cost.entrySet()){
            System.out.println(entry.getValue() + "" + entry.getKey() + "Card");
        }
    }
}
