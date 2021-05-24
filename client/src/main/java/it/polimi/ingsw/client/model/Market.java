package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.utility.StringUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Market {
    private Marble extraMarble;
    private final Marble[][] grid;
    private final int columns = 4;
    private final int rows = 3;

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

    public Marble[] getRowOrColumn(char rowOrColumn, int num) {
        num--;
        Marble[] marbleArray;
        if (rowOrColumn == 'r') {
            marbleArray = new Marble[columns];
            System.arraycopy(grid[num], 0, marbleArray, 0, columns);
        }
        else {
            marbleArray = new Marble[rows];
            for (int i = 0; i < rows; i++)
                marbleArray[i] = grid[i][num];
        }
        return marbleArray;
    }

    public List<Resource> marbleArrayToResourceList(char rowOrColumn, int num) {
        List<Resource> resources = new ArrayList<>();
        Arrays.stream(getRowOrColumn(rowOrColumn, num)).forEach(marble -> resources.add(Utility.colorResourceMap.
                get(marble.getColor())));
        resources.removeIf(r -> r == null || r.equals(Resource.FAITH));
        return resources;
    }

    @Override
    public String toString() {
        return  "┌──────────────┐\n" +
                "│" + StringUtility.center(extraMarble.toString(),14) + "│ \n" +
                "│"+ StringUtility.center(" ▼ ▼ ▼ ▼",14) + "│ \n" +
                "│"+ StringUtility.center("> " +grid[0][0].toString()+ " " +grid[0][1].toString()+ " " +grid[0][2].toString()+ " " + grid[0][3].toString(),14) + "│ \n" +
                "│"+ StringUtility.center("> " +grid[1][0].toString()+" " +grid[1][1].toString()+ " " +grid[1][2].toString()+ " " + grid[1][3].toString(),14) + "│ \n" +
                "│"+ StringUtility.center("> " +grid[2][0].toString()+" " +grid[2][1].toString()+ " " +grid[2][2].toString()+ " " + grid[2][3].toString(),14) + "│ \n" +
                "└──────────────┘\n";
    }
}
