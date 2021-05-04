package it.polimi.ingsw.client.model;


public class ColorCost implements Requirements{
    Color[] colors;
    int[] quantity;

    public ColorCost(Color[] colors,int[] quantity){
        this.colors = colors;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        if(colors.length == 1){
            return "│        "+ colors[0] + quantity[0] +"       │\n";
        }else {
            return "│       "+ colors[0] + quantity[0] + colors[1] + quantity[1] +"      │\n";
        }
    }
}
