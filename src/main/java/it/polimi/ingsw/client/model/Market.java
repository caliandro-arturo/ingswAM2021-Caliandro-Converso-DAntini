package it.polimi.ingsw.client.model;

public class Market {
    private Marble extraMarble;
    private Marble[][] grid;

    public Market(Marble extraMarble, Marble[][] grid) {
        this.extraMarble = extraMarble;
        this.grid = grid;
    }

    @Override
    public String toString() {
        return "" +extraMarble.toString()+ "\n" +
                "" +grid[0][0].toString()+grid[0][1].toString()+grid[0][2].toString()+grid[0][3].toString()+ "\n" +
                "" +grid[1][0].toString()+grid[1][1].toString()+grid[1][2].toString()+grid[1][3].toString()+ "\n" +
                "" +grid[2][0].toString()+grid[2][1].toString()+grid[2][2].toString()+grid[2][3].toString()+ "\n";
    }
}
