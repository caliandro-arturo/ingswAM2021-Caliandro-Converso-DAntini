package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.utility.StringUtility;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Market representation for client.
 */
public class Market {
    private final ObjectProperty<Marble> extraMarble = new SimpleObjectProperty<>();
    private final Marble[][] grid;
    private final int columns = 4;
    private final int rows = 3;

    public Market(Marble extraMarble, Marble[][] grid) {
        this.extraMarble.set(extraMarble);
        this.grid = grid;
    }

    public Marble getExtraMarble() {
        return extraMarble.get();
    }

    public Marble[][] getGrid() {
        return grid;
    }

    public ObjectProperty<Marble> marbleProperty() {
        return extraMarble;
    }

    /**
     * Reinserts the extra marble into the market.
     *
     * @param rowOrColumn the row/column decision value
     * @param num the position
     */
    public void reinsertExtraMarble(char rowOrColumn, int num) {
        Marble temp;
        num -= 1;
        if (rowOrColumn == 'r') {
            temp = grid[num][columns - 1];
            System.arraycopy(grid[num], 0, grid[num], 1, columns - 2 + 1);
            grid[num][0] = extraMarble.get();
            extraMarble.set(temp);
        } else if (rowOrColumn == 'c') {
            temp = grid[rows - 1][num];
            for (int i = rows - 2; i >= 0; i--) {
                grid[i + 1][num] = grid[i][num];
            }
            grid[0][num] = extraMarble.get();
            extraMarble.set(temp);
        }
    }

    /**
     * Gets a row or a column.
     * @param rowOrColumn the row/column decision value
     * @param num the number
     * @return the row/column at the position indicated by the number
     */
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

    /**
     * Converts a row/column of the market into a resource list
     * @param rowOrColumn the row/column decision value
     * @param num the position
     * @return a list with the resources represented by the marbles
     */
    public List<Resource> marbleArrayToResourceList(char rowOrColumn, int num) {
        List<Resource> resources = new ArrayList<>();
        Arrays.stream(getRowOrColumn(rowOrColumn, num)).forEach(marble -> resources.add(Utility.colorResourceMap.
                get(marble.getColor())));
        resources.removeIf(r -> r == null || r.equals(Resource.FAITH));
        return resources;
    }

    /**
     * Converts a row/column of the market into a resource list, adding resources instead of null when the marble color is white.
     * @param whiteResource the resource to add instead of null
     * @param rowOrColumn the row/column decision value
     * @param num the position
     * @return a list with the resources represented by the marbles
     */
    public List<Resource> marbleArrayToResourceList(Resource whiteResource, char rowOrColumn, int num) {
        List<Resource> resources = new ArrayList<>();
        Arrays.stream(getRowOrColumn(rowOrColumn, num)).forEach(
                marble -> {
                    if (marble.getColor() == Color.WHITE) {
                        resources.add(whiteResource);
                    }else
                        resources.add(Utility.colorResourceMap.get(marble.getColor()));
                }
        );
        resources.removeIf(r -> r.equals(Resource.FAITH));
        return resources;
    }

    @Override
    public String toString() {
        return  "┌──────────────┐\n" +
                "│" + StringUtility.center(extraMarble.get().toString(),14) + "│ \n" +
                "│"+ StringUtility.center(" v v v v",14) + "│ \n" +
                "│"+ StringUtility.center("> " +grid[0][0].toString()+ " " +grid[0][1].toString()+ " " +grid[0][2].toString()+ " " + grid[0][3].toString(),14) + "│ \n" +
                "│"+ StringUtility.center("> " +grid[1][0].toString()+" " +grid[1][1].toString()+ " " +grid[1][2].toString()+ " " + grid[1][3].toString(),14) + "│ \n" +
                "│"+ StringUtility.center("> " +grid[2][0].toString()+" " +grid[2][1].toString()+ " " +grid[2][2].toString()+ " " + grid[2][3].toString(),14) + "│ \n" +
                "└──────────────┘\n";
    }
}
