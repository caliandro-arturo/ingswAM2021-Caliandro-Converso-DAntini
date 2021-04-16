package it.polimi.ingsw.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MarketTest {
    public Market market = new Market();

    /**
     * This test verifies that all the marbles are placed in the market tray and that there is
     * an extra marble
     */
    @Test
    public void marketIsFulfilled() {
        int[] colorHistogram = new int[7];
        colorHistogram[Color.WHITE.ordinal()] = 4;
        colorHistogram[Color.RED.ordinal()] = 1;
        colorHistogram[Color.GREEN.ordinal()] = 0;
        colorHistogram[Color.YELLOW.ordinal()] = 2;
        colorHistogram[Color.PURPLE.ordinal()] = 2;
        colorHistogram[Color.BLUE.ordinal()] = 2;
        colorHistogram[Color.GREY.ordinal()] = 2;
        for(int i = 0; i < market.getColumns(); i++) {
            Marble[] row = market.getRowOrColumn('c', i + 1);
            for (int j = 0; j < market.getRows(); j++)
                colorHistogram[row[j].getColor().ordinal()]--;
        }
        colorHistogram[market.getExtraMarble().getColor().ordinal()]--;
        assertArrayEquals(new int[] {0, 0, 0, 0, 0, 0, 0}, colorHistogram);
    }

    /**
     * This test verifies that the re-entry of the extra marble in the tray is done correctly
     */
    @Test
    public void marbleReinsertion() {
        Marble oldExtra;
        Marble[] slice;
        Marble[] newSlice;
        char rowOrColumn = 'c';
        for(int num = 1; num < market.getColumns(); num++) {
            oldExtra = market.getExtraMarble();
            slice = market.getRowOrColumn(rowOrColumn, num);
            market.reinsertExtraMarble(rowOrColumn, num);
            newSlice = market.getRowOrColumn(rowOrColumn, num);
            assertEquals(newSlice[0], oldExtra);
            for(int i = 0; i < market.getRows() - 1; i++)
                assertEquals(slice[i], newSlice[i + 1]);
            assertEquals(slice[market.getRows() - 1], market.getExtraMarble());}
        rowOrColumn = 'r';
        for(int num = 1; num < market.getRows(); num++) {
            oldExtra = market.getExtraMarble();
            slice = market.getRowOrColumn(rowOrColumn, num);
            market.reinsertExtraMarble(rowOrColumn, num);
            newSlice = market.getRowOrColumn(rowOrColumn, num);
            assertEquals(newSlice[0], oldExtra);
            for (int i = 0; i < market.getColumns() - 1; i++)
                assertEquals(slice[i], newSlice[i + 1]);
            assertEquals(slice[market.getColumns() - 1], market.getExtraMarble());
        }
    }
}
