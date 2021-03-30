package it.polimi.ingsw.model;
import java.util.*;

public class ColorCost implements Requirements{
    private HashMap<Color ,Integer> cost;

    public ColorCost(Color color,Integer num){
        cost.put(color, num);
    }
    /*
    public HashMap<Color ,Integer> getCost()
     */
    @Override
    public void checkRequirements(Player player) throws NotMetRequirementsException{

    }
}
