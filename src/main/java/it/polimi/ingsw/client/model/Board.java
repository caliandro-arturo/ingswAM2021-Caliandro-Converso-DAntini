package it.polimi.ingsw.client.model;

import it.polimi.ingsw.client.CLI.CLIColor;

/**
 * light version of Board class for representation purposes
 */

public class Board {
    private DevelopmentPlace developmentPlace;
    private FaithTrack faithTrack;
    private LeaderCard leaderCard;
    private Strongbox strongbox ;
    private WarehouseStore warehouseStore;

    public Board(DevelopmentPlace developmentPlace, FaithTrack faithTrack/*, LeaderCard leaderCard,*/
                         ,Strongbox strongbox, WarehouseStore warehouseStore) {
        this.developmentPlace = developmentPlace;
        this.faithTrack = faithTrack;
        //this.leaderCard = leaderCard;
        this.strongbox = strongbox;
        this.warehouseStore = warehouseStore;
    }

    public DevelopmentPlace getDevelopmentPlace() {
        return developmentPlace;
    }

    public FaithTrack getFaithTrack() {
        return faithTrack;
    }

    public LeaderCard getLeaderCard() {
        return leaderCard;
    }

    public Strongbox getStrongbox() {
        return strongbox;
    }

    public WarehouseStore getWarehouseStore() {
        return warehouseStore;
    }

    /**
     * representation method for Board (CLI)
     * @return String with the representation
     */
    @Override
    public String toString(){
        StringBuilder boardArt = new StringBuilder("");
        String[] wareArt = warehouseStore.toString().split("\n");
        String[] boxArt = strongbox.toString().split("\n");
        String[] faithArt = faithTrack.toString().split("\n");
        String[] devPlaceArt = developmentPlace.toString().split("\n");
        String b = "\u001B[40m"+"?"+ CLIColor.ANSI_RESET;
        boardArt.append("┌──────────────────────────────────────────────────────────────────────────────" +
                "─────────────────────────────────────────────────┐\n");
        for(int k=0; k<4; k++){
            boardArt.append("│");
            boardArt.append(Utility.center(faithArt[k],127));
            boardArt.append("│\n");
        }
        boardArt.append("│  "+faithArt[4]+"    │\n");

        for(int i = 0; i<9;i++){
            boardArt.append(wareArt[i].concat("                     "+devPlaceArt[i]));
            boardArt.append("\n");
        }
        boardArt.append(boxArt[0]).append("     ╦══════════╦    "+devPlaceArt[9]).append("\n");
        boardArt.append(boxArt[1]).append("     ║ 1"+b+"--┐    ║    "+devPlaceArt[10]+"\n");
        boardArt.append(boxArt[2]).append("     ║     ├>1"+b+" ║    "+devPlaceArt[11]+"\n");
        boardArt.append(boxArt[3]).append("     ║ 1"+b+"--┘    ║    "+devPlaceArt[12]+"\n");
        boardArt.append(boxArt[4]).append("     ╩══════════╩    "+devPlaceArt[13]+"\n");
        boardArt.append(boxArt[5]).append("                                    " +
                "                                                                 │\n");
        boardArt.append("└───────────────────────────────────────────────────────────────────────────────────────" +
                "────────────────────────────────────────┘\n");

        return boardArt.toString();
    }
}
