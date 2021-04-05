package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents the market, which consists of a tray with marbles and an extra marble that
 * must be inserted in a row (or a column) any time a player chooses to use the market in his/her turn
 */
public class Market {
    private final int rows;
    private final int columns;
    /**
     * This attribute is the tray of the market
     */
    private final Marble[][] tray;
    /**
     * this attribute represents the extra marble
     */
    private Marble extraMarble;

    /**
     * This constructor initializes the market inserting the marbles into the market tray
     * @param marbles list of marbles that goes into the market
     */
    public Market(int rows, int columns, ArrayList<Marble> marbles) {
        /*if(rows * columns != marbles.size())
            System.out.println("error");*/
        this.rows = rows;
        this.columns = columns;
        tray = new Marble[rows][columns];
        Collections.shuffle(marbles);
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < columns; j++)
                tray[i][j] = marbles.get(i * columns + j);
        extraMarble = marbles.get(rows * columns);
    }

    public Marble[][] getTray() {
        return tray;
    }

    public Marble getExtraMarble() {
        return extraMarble;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    /**
     * This method returns a selected row or column of marbles from the tray
     * @param rowOrColumn selection between row or column (it must be 'r' or 'c')
     * @param num position of the selected row/column
     */
    public Marble[] getRowOrColumn(char rowOrColumn, int num) {
        num -= 1;
        Marble[] marbleArray = null;
        if(rowOrColumn == 'r') {
            marbleArray = new Marble[columns];
            if (columns >= 0) System.arraycopy(tray[num], 0, marbleArray, 0, columns);
        }
        else if(rowOrColumn == 'c') {
            marbleArray = new Marble[rows];
            for(int i = 0; i < rows; i++)
                marbleArray[i] = tray[i][num];
        }
        return marbleArray;
    }

    /**
     * This method updates the market after the selection of a row or a column, inserting the extra marble at the first
     * position and shifting the others down/right
     * @param rowOrColumn the choice between row or column
     * @param num position of the selected row/column
     */
    public void reinsertExtraMarble(char rowOrColumn, int num) {
        Marble temp;
        num -= 1;
        if(rowOrColumn == 'r') {
            temp = tray[num][columns - 1];
            System.arraycopy(tray[num], 0, tray[num], 1, columns - 2 + 1);
            tray[num][0] = extraMarble;
            extraMarble = temp;
        }
        else if(rowOrColumn == 'c') {
            temp = tray[rows - 1][num];
            for (int i = rows - 2; i >= 0; i--) {
                tray[i + 1][num] = tray[i][num];
            }
            tray[0][num] = extraMarble;
            extraMarble = temp;
        }
    }

    /**
     * This method is the one directly called when chooses to use the market
     * @param player the player who uses the market
     * @param rowOrColumn selection between row or column
     * @param num position of the selected row/column
     */
    public void getMarblesResources(Player player, char rowOrColumn, int num) {
        Marble[] chosenMarketSlice = getRowOrColumn(rowOrColumn, num);
        for(Marble marble: chosenMarketSlice)
            marble.pick(player);
        reinsertExtraMarble(rowOrColumn, num);
    }
}