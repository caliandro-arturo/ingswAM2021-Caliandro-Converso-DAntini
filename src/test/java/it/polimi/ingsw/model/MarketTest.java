package it.polimi.ingsw.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class MarketTest {
    public static Market market;
    private static ArrayList<Marble> marbles = new ArrayList<>();
    /**
     * this method initializes the market
     */
    @BeforeAll
    public static void setUp(){
        marbles.add(new Marble(Color.WHITE));
        marbles.add(new Marble(Color.WHITE));
        marbles.add(new Marble(Color.WHITE));
        marbles.add(new Marble(Color.WHITE));
        marbles.add(new Marble(Color.BLUE));
        marbles.add(new Marble(Color.BLUE));
        marbles.add(new Marble(Color.GREY));
        marbles.add(new Marble(Color.GREY));
        marbles.add(new Marble(Color.YELLOW));
        marbles.add(new Marble(Color.YELLOW));
        marbles.add(new Marble(Color.PURPLE));
        marbles.add(new Marble(Color.PURPLE));
        marbles.add(new Marble(Color.RED));
        market = new Market(3, 4, marbles);
    }

    /**
     * This test verify that all the marbles are placed in the market tray and that there is
     * an extra marble
     */
    @Test
    public void fulfillMarket() {
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
        assertTrue(Arrays.equals(colorHistogram, new int[] {0, 0, 0, 0, 0, 0, 0}));
    }

    /**
     * This test verify that the re-entry of the extra marble in the tray is done correctly
     */
    @Test
    public void marble() {
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
