package it.polimi.ingsw.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class MarketTest {
    private Market market;
    private ArrayList<Marble> marbleArrayList;


    public MarketTest() {
        this.marbleArrayList = marbleListMaker();
        this.market = new Market(3, 4, marbleArrayList);
    }

    private ArrayList<Marble> marbleListMaker(){
        ArrayList<Marble> marbleArrayList = new ArrayList();
        Marble marble0 = new Marble(Color.WHITE);
        marbleArrayList.add(marble0);
        Marble marble1 = new Marble(Color.WHITE);
        marbleArrayList.add(marble1);
        Marble marble2 = new Marble(Color.WHITE);
        marbleArrayList.add(marble2);
        Marble marble3 = new Marble(Color.WHITE);
        marbleArrayList.add(marble3);
        Marble marble4 = new Marble(Color.BLUE);
        marbleArrayList.add(marble4);
        Marble marble5 = new Marble(Color.BLUE);
        marbleArrayList.add(marble5);
        Marble marble6 = new Marble(Color.GREY);
        marbleArrayList.add(marble6);
        Marble marble7 = new Marble(Color.GREY);
        marbleArrayList.add(marble7);
        Marble marble8 = new Marble(Color.YELLOW);
        marbleArrayList.add(marble8);
        Marble marble9 = new Marble(Color.YELLOW);
        marbleArrayList.add(marble9);
        Marble marble10 = new Marble(Color.PURPLE);
        marbleArrayList.add(marble10);
        Marble marble11 = new Marble(Color.PURPLE);
        marbleArrayList.add(marble11);
        Marble marble12 = new Marble(Color.RED);
        marbleArrayList.add(marble12);
        return marbleArrayList;
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
    public void rightExtraction(char rowOrColumn, int num) {
        rowOrColumn = 'c';
        num = 1;
        Marble oldExtra = market.getExtraMarble();
        Marble[] slice = market.getRowOrColumn(rowOrColumn, num);
        market.reinsertExtraMarble(rowOrColumn, num);
        Marble[] newSlice = market.getRowOrColumn(rowOrColumn, num);
        if(!newSlice[0].equals(oldExtra))
            assertTrue(false);
        for(int i = 0; i < market.getRows() - 1; i++)
            if(slice[i] != newSlice[i + 1])
                assertTrue(false);
        if(!slice[market.getRows() - 1].equals(market.getExtraMarble()))
            assertTrue(false);
        assertTrue(true);
    }
}
