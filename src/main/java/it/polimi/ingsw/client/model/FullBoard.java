package it.polimi.ingsw.client.model;

import java.util.ArrayList;

/**
 * class used to build and represent the personal board next to the card grid, market and leader cards for a player
 */
public class FullBoard {
    private PersonalBoard board;
    private DevelopmentGrid grid;
    private Market market;
    private ArrayList<LeaderCard> leaderCards;
    private String space = "                                                        ";
    private String bigSpace = "                                                                             \n";
    String emptyCard =
                    "┌─────────────────┐\n"+
                    "│                 │\n"+
                    "│                 │\n"+
                    "│                 │\n"+
                    "│                 │\n"+
                    "│                 │\n"+
                    "│                 │\n"+
                    "│                 │\n"+
                    "│                 │\n"+
                    "└─────────────────┘\n";

    public FullBoard(PersonalBoard board, DevelopmentGrid grid, Market market, ArrayList<LeaderCard> leaderCards) {
        this.board = board;
        this.grid = grid;
        this.market = market;
        this.leaderCards = leaderCards;
    }

    public void setLeaderCards(LeaderCard leaderCard) {
        this.leaderCards.add(leaderCard);
    }

    public DevelopmentGrid getGrid() {
        return grid;
    }

    public PersonalBoard getBoard() {
        return board;
    }

    public Market getMarket() {
        return market;
    }

    public ArrayList<LeaderCard> getLeaderCards() {
        return leaderCards;
    }

    @Override
    public String toString(){
        StringBuilder fullBoardArt = new StringBuilder();
        String[] boardArt = board.toString().split("\n");
        String[] gridArt = grid.toString().split("\n");
        String[] marketArt = market.toString().split("\n");
        StringBuilder tempCardArt = new StringBuilder();
        int l = boardArt.length;
        int m = gridArt.length;
        int s = marketArt.length;

        switch(leaderCards.size()){
            case 0:
                tempCardArt.append(emptyCard).append(emptyCard).append(bigSpace).append(bigSpace);
                break;
            case 1:
                tempCardArt.append(leaderCards.get(0).toString()).append(emptyCard).append(bigSpace).append(bigSpace);
                break;
            case 2:
                tempCardArt.append(leaderCards.get(0).toString()).append(leaderCards.get(1).toString()).append(bigSpace).append(bigSpace);
                break;
        }
        String[] leaderCardArt = tempCardArt.toString().split("\n");
        int po = leaderCardArt.length;
        for(int i=0; i<16; i++ ){
            fullBoardArt.append(boardArt[i]).append(gridArt[i]).append(leaderCardArt[i]).append("\n");
        }
        for(int j=16; j<21;j++) {
            fullBoardArt.append(boardArt[j]).append(marketArt[j-16]).append(space).append(leaderCardArt[j]).append("\n");
        }
        fullBoardArt.append(boardArt[21]);
        return fullBoardArt.toString();

    }
}
