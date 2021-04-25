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


    @Override
    public boolean checkRequirements(Player player) {
        HashMap<Color,Integer> checkColor = new HashMap<>();
        checkColor.put(Color.GREEN,0);
        checkColor.put(Color.BLUE,0);
        checkColor.put(Color.YELLOW,0);
        checkColor.put(Color.PURPLE,0);
        int j;
        DevelopmentPlace[] devPlace = player.getBoard().getPersonalDevelopmentSpace();
        for (DevelopmentPlace developmentPlace : devPlace) {
            if (!developmentPlace.getDevelopmentCards().empty()) {
                for (int k = 0; k < developmentPlace.getDevelopmentCards().size(); k++) {
                    j = checkColor.get(developmentPlace.getLevelICard(k).getColor());
                    checkColor.replace(developmentPlace.getLevelICard(k).getColor(), j, j + 1);
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
    public void getRequirements(Game game, Player player) {
        game.getViewAdapter().sendMessage(player,"to deploy this card you must have: ");
        for (Map.Entry<Color,Integer> entry: cost.entrySet()){
            if (entry.getValue()>0) {
                game.getViewAdapter().sendMessage(player,entry.getValue() + "" + entry.getKey() + "Card");
            }
        }
    }
}
