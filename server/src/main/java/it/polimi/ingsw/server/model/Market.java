package it.polimi.ingsw.server.model;

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
  
    public Market() {
        this(3, 4, new ArrayList<Marble>() {{
            add(new Marble(Color.WHITE));
            add(new Marble(Color.WHITE));
            add(new Marble(Color.WHITE));
            add(new Marble(Color.WHITE));
            add(new Marble(Color.BLUE));
            add(new Marble(Color.BLUE));
            add(new Marble(Color.GREY));
            add(new Marble(Color.GREY));
            add(new Marble(Color.YELLOW));
            add(new Marble(Color.YELLOW));
            add(new Marble(Color.PURPLE));
            add(new Marble(Color.PURPLE));
            add(new Marble(Color.RED));
        }});
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
     * Returns a selected row or column of marbles from the tray
     *
     * @param rowOrColumn selection between row or column (it must be 'r' or 'c')
     * @param num position of the selected row/column
     */
    public Marble[] getRowOrColumn(char rowOrColumn, int num) {
        if(!(rowOrColumn == 'r' || rowOrColumn == 'c'))
            throw new IllegalArgumentException("You must choose between 'r' or 'c'.");
        else if(num < 1)
            throw new IllegalArgumentException("Number cannot be less than 1.");
        else if(rowOrColumn == 'r' && num > rows)
            throw new IllegalArgumentException("Maximum number accepted for rows is " + rows + ".");
        else if(rowOrColumn == 'c' && num > columns)
            throw new IllegalArgumentException("Maximum number accepted for columns is " + columns + ".");
        num--;
        Marble[] marbleArray;
        if (rowOrColumn == 'r') {
            marbleArray = new Marble[columns];
            System.arraycopy(tray[num], 0, marbleArray, 0, columns);
        }
        else {
            marbleArray = new Marble[rows];
            for (int i = 0; i < rows; i++)
                marbleArray[i] = tray[i][num];
        }
        return marbleArray;
    }

    /**
     * Updates the market after the selection of a row or a column, inserting the extra marble at the first
     * position and shifting the others down/right
     * @param rowOrColumn the choice between row or column
     * @param num position of the selected row/column
     */
    public void reinsertExtraMarble(char rowOrColumn, int num) {
        Marble temp;
        num -= 1;
        if (rowOrColumn == 'r') {
            temp = tray[num][columns - 1];
            System.arraycopy(tray[num], 0, tray[num], 1, columns - 2 + 1);
            tray[num][0] = extraMarble;
            extraMarble = temp;
        } else if (rowOrColumn == 'c') {
            temp = tray[rows - 1][num];
            for (int i = rows - 2; i >= 0; i--) {
                tray[i + 1][num] = tray[i][num];
            }
            tray[0][num] = extraMarble;
            extraMarble = temp;
        }
    }

    /**
     * Gives the current player resources or faith point based on the row or column selected.
     * @param game the game in which the current player uses the market
     * @param rowOrColumn selection between row or column
     * @param num position of the selected row/column
     * @throws IllegalArgumentException if parameters are invalids
     */
    public void getMarblesResources(Game game, char rowOrColumn, int num) {
        Marble[] chosenMarketSlice = getRowOrColumn(rowOrColumn, num);
        for (Marble marble : chosenMarketSlice)
            marble.pick(game);
        reinsertExtraMarble(rowOrColumn, num);
    }
}