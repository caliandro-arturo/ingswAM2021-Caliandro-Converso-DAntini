package it.polimi.ingsw.client.model;

/**
 * ColorCost requirements class for client
 */
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
            return "│"+ Utility.center(colors[0] + ":" + quantity[0],17) +"│\n";
        }else {
            return "│"+ Utility.center(colors[0] + ":" + quantity[0] +" "+ colors[1] + ":" + quantity[1],17) +"│\n";
        }
    }
}
