package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.utility.StringUtility;

import java.util.ArrayList;

public class ColorCost implements Requirements{
    private final ArrayList<Color> colors;
    private final ArrayList <Integer> quantity;

    public ColorCost(ArrayList<Color> colors, ArrayList<Integer> quantity) {
        this.colors = colors;
        this.quantity = quantity;
    }

    public ArrayList<Color> getColors() {
        return colors;
    }

    public ArrayList<Integer> getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        if(colors.size() == 1){
            return "│"+ StringUtility.center(colors.get(0) + ":" + quantity.get(0),17) +"│\n";
        }else {
            return "│"+  StringUtility.center(colors.get(0) + ":" + quantity.get(0) +
                    " "+ colors.get(1) + ":" + quantity.get(1),17) +"│\n";
        }
    }
}
