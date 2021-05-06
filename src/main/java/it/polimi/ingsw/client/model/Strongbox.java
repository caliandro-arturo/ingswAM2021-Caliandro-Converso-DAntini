package it.polimi.ingsw.client.model;
import it.polimi.ingsw.client.CLI.CLIColor;

/**
 * light version of Strongbox class for representation purposes
 */
public class Strongbox {
    int[] resources = new int[]{0,0,0,0};

    /**
     * set the strongbox (CLI) as an array of int, where the value is the number of these resources,
     * according to the index of the array
     * <p> index 0 for COINS
     * <p> index 1 for SERFS
     * <p> index 2 for STONES
     * <p> index 3 for SHIELDS
     * @param num : number of the resources to set
     * @param pos : index for match the type of resource
     */
    public void setResources(int num, int pos) {
        this.resources[pos] = num;
    }

    /**
     * representation method for Strongbox (CLI)
     * @return String with the representation
     */
    @Override
    public String toString(){
        String boxArt = "" +
                "│          " + "\u001b[43m" + "StrongBox" + CLIColor.ANSI_RESET + "       \n" +
                "│  ┌──────────────────────┐\n" +
                "│  │   " + Resource.COIN + " :" + String.format("%3d", resources[0]) +
                "     " + Resource.SERF + " :" + String.format("%3d", resources[1]) +
                "  │\n" +
                "│  │                      │\n" +
                "│  │   " + Resource.STONE + " :" + String.format("%3d", resources[2]) +
                "     " + Resource.SHIELD + " :" + String.format("%3d", resources[3]) +
                "  │\n" +
                "│  └──────────────────────┘";
        return boxArt;
    }

}
