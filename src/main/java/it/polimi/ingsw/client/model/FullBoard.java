package it.polimi.ingsw.client.model;

import it.polimi.ingsw.client.CLI.CLIColor;

import java.util.ArrayList;

/**
 * class used to build and represent the personal board next to the card grid, market and leader cards for a player
 */
public class FullBoard {
    private Board board;
    private DevelopmentGrid grid;
    private Market market;
    private ArrayList<LeaderCard> leaderCards;
    private String space = "                               ";
    private String bigSpace = "                                                                             \n";
    private String marketName = "\u001B[101m" + "Market:" + CLIColor.ANSI_RESET+" ";
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

    public FullBoard(Board board, DevelopmentGrid grid, Market market, ArrayList<LeaderCard> leaderCards) {
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

    public Board getBoard() {
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
                tempCardArt.append(leaderCards.get(0).toString()).append(leaderCards.get(1).toString()).
                        append(bigSpace).append(bigSpace);
                break;
        }
        String[] leaderCardArt = tempCardArt.toString().split("\n");
        int po = leaderCardArt.length;
        fullBoardArt.append(boardArt[0]+"\n");
        fullBoardArt.append(boardArt[1]+"   \u001B[42m" + "Leader Cards" + CLIColor.ANSI_RESET+" \n");

        for(int i=2; i<23; i++ ){
            //fullBoardArt.append(boardArt[i]).append(gridArt[i]).append(leaderCardArt[i]).append("\n");
            fullBoardArt.append(boardArt[i]+"  ").append(leaderCardArt[i-2]+"\n");
        }
        /*fullBoardArt.append(boardArt[17]).append("              ").append(marketArt[0]).append(space+" ").append(leaderCardArt[16]).append("\n");
        fullBoardArt.append(boardArt[18]).append("      "+marketName).append(marketArt[1]).append(space).append(leaderCardArt[17]).append("\n");
        for(int j=19; j<22;j++) {
            fullBoardArt.append(boardArt[j]).append("              ").append(marketArt[j-17]).append(space).append(leaderCardArt[j]).append("\n");
        }
        fullBoardArt.append("                                                                                  " +
                "                                                             ").append(marketArt[5]).append("\n");
        fullBoardArt.append("                                                                                  "+
                "                                                             ").append(marketArt[6]).append("\n");*/

        return fullBoardArt.toString();

    }
}
