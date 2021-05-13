package it.polimi.ingsw.client.model;

import it.polimi.ingsw.common_files.model.Utility;

public class Market {
    private Marble extraMarble;
    private Marble[][] grid;
    private int columns = 4;
    private int rows = 3;

    public Market(Marble extraMarble, Marble[][] grid) {
        this.extraMarble = extraMarble;
        this.grid = grid;
    }
    public void reinsertExtraMarble(char rowOrColumn, int num) {
        Marble temp;
        num -= 1;
        if (rowOrColumn == 'r') {
            temp = grid[num][columns - 1];
            System.arraycopy(grid[num], 0, grid[num], 1, columns - 2 + 1);
            grid[num][0] = extraMarble;
            extraMarble = temp;
        } else if (rowOrColumn == 'c') {
            temp = grid[rows - 1][num];
            for (int i = rows - 2; i >= 0; i--) {
                grid[i + 1][num] = grid[i][num];
            }
            grid[0][num] = extraMarble;
            extraMarble = temp;
        }
    }
    @Override
    public String toString() {
        return  "┌──────────────┐\n" +
                "│" + Utility.center(extraMarble.toString(),14) + "│ \n" +
                "│"+ Utility.center(" ▼ ▼ ▼ ▼",14) + "│ \n" +
                "│"+ Utility.center("> " +grid[0][0].toString()+ " " +grid[0][1].toString()+ " " +grid[0][2].toString()+ " " + grid[0][3].toString(),14) + "│ \n" +
                "│"+ Utility.center("> " +grid[1][0].toString()+" " +grid[1][1].toString()+ " " +grid[1][2].toString()+ " " + grid[1][3].toString(),14) + "│ \n" +
                "│"+ Utility.center("> " +grid[2][0].toString()+" " +grid[2][1].toString()+ " " +grid[2][2].toString()+ " " + grid[2][3].toString(),14) + "│ \n" +
                "└──────────────┘\n";
    }
}
