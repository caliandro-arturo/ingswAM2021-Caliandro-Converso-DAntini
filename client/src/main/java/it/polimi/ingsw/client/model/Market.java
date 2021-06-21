package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.utility.StringUtility;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Market {
    private Marble extraMarble;
    private final ObjectProperty<Marble[][]> grid = new SimpleObjectProperty<>();
    private final int columns = 4;
    private final int rows = 3;

    public Market(Marble extraMarble, Marble[][] grid) {
        this.extraMarble = extraMarble;
        this.grid.set(grid);
    }

    public Marble getExtraMarble() {
        return extraMarble;
    }

    public Marble[][] getGrid() {
        return grid.get();
    }

    public ObjectProperty<Marble[][]> gridProperty() {
        return grid;
    }

    public void reinsertExtraMarble(char rowOrColumn, int num) {
        Marble temp;
        num -= 1;
        if (rowOrColumn == 'r') {
            temp = grid.get()[num][columns - 1];
            System.arraycopy(grid.get()[num], 0, grid.get()[num], 1, columns - 2 + 1);
            grid.get()[num][0] = extraMarble;
            extraMarble = temp;
        } else if (rowOrColumn == 'c') {
            temp = grid.get()[rows - 1][num];
            for (int i = rows - 2; i >= 0; i--) {
                grid.get()[i + 1][num] = grid.get()[i][num];
            }
            grid.get()[0][num] = extraMarble;
            extraMarble = temp;
        }
    }

    public Marble[] getRowOrColumn(char rowOrColumn, int num) {
        num--;
        Marble[] marbleArray;
        if (rowOrColumn == 'r') {
            marbleArray = new Marble[columns];
            System.arraycopy(grid.get()[num], 0, marbleArray, 0, columns);
        }
        else {
            marbleArray = new Marble[rows];
            for (int i = 0; i < rows; i++)
                marbleArray[i] = grid.get()[i][num];
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
                "│" + StringUtility.center(extraMarble.toString(),14) + "│ \n" +
                "│"+ StringUtility.center(" v v v v",14) + "│ \n" +
                "│"+ StringUtility.center("> " +grid.get()[0][0].toString()+ " " +grid.get()[0][1].toString()+ " " +grid.get()[0][2].toString()+ " " + grid.get()[0][3].toString(),14) + "│ \n" +
                "│"+ StringUtility.center("> " +grid.get()[1][0].toString()+" " +grid.get()[1][1].toString()+ " " +grid.get()[1][2].toString()+ " " + grid.get()[1][3].toString(),14) + "│ \n" +
                "│"+ StringUtility.center("> " +grid.get()[2][0].toString()+" " +grid.get()[2][1].toString()+ " " +grid.get()[2][2].toString()+ " " + grid.get()[2][3].toString(),14) + "│ \n" +
                "└──────────────┘\n";
    }
}
