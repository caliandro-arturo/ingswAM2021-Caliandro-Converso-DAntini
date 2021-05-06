package it.polimi.ingsw.client.model;

import java.util.ArrayList;
import java.util.Collections;

public class Market {
    private Marble extraMarble;
    private Marble[][] grid;

    public Market(ArrayList<Marble> marbles) {
        grid = new Marble[3][4];
        Collections.shuffle(marbles);
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 4; j++)
                grid[i][j] = marbles.get(i * 4 + j);
        extraMarble = marbles.get(3 * 4);
    }


    @Override
    public String toString() {
        return  "┌──────────────┐\n" +
                "│" +Utility.center(extraMarble.toString(),14) + "│ \n" +
                "│"+ Utility.center(" ▼ ▼ ▼ ▼",14) + "│ \n" +
                "│"+ Utility.center("> " +grid[0][0].toString()+ " " +grid[0][1].toString()+ " " +grid[0][2].toString()+ " " + grid[0][3].toString(),14) + "│ \n" +
                "│"+ Utility.center("> " +grid[1][0].toString()+" " +grid[1][1].toString()+ " " +grid[1][2].toString()+ " " + grid[1][3].toString(),14) + "│ \n" +
                "│"+ Utility.center("> " +grid[2][0].toString()+" " +grid[2][1].toString()+ " " +grid[2][2].toString()+ " " + grid[2][3].toString(),14) + "│ \n" +
                "└──────────────┘\n";
    }
}
