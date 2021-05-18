package it.polimi.ingsw.client.model;


import it.polimi.ingsw.commonFiles.utility.CLIColor;
import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.utility.StringUtility;

import java.util.ArrayList;

/**
 * light version of Board class for representation purposes
 */

public class Board {
    private DevelopmentPlace developmentPlace;
    private FaithTrack faithTrack;
    private Strongbox strongbox ;
    private WarehouseStore warehouseStore;
    private ArrayList<LeaderCard> leaderCards;
    private ArrayList<Resource> resHand;

    public Board() {
        this.developmentPlace = new DevelopmentPlace();
        this.faithTrack = new FaithTrack(0);
        this.strongbox = new Strongbox();
        this.warehouseStore = new WarehouseStore();
        this.leaderCards = new ArrayList<>();
        this.resHand = new ArrayList<>();
    }

    public ArrayList<LeaderCard> getLeaderCards() {
        return leaderCards;
    }

    public void setLeaderCards(ArrayList<LeaderCard> leaderCards) {
        this.leaderCards = leaderCards;
    }

    public void setDevelopmentPlace(DevelopmentPlace developmentPlace) {
        this.developmentPlace = developmentPlace;
    }

    public void setResHand(ArrayList<Resource> resHand) {
        this.resHand = resHand;
    }

    public DevelopmentPlace getDevelopmentPlace() {
        return developmentPlace;
    }

    public FaithTrack getFaithTrack() {
        return faithTrack;
    }

    public Strongbox getStrongbox() {
        return strongbox;
    }

    public void setLeaderCards(LeaderCard leaderCard) {
        this.leaderCards.add(leaderCard);
    }

    public WarehouseStore getWarehouseStore() {
        return warehouseStore;
    }

    public void removeResource(int[] store, ArrayList<Resource> resources){
        for (int i=0; i<store.length; i++){
            if (store[i]==0){
                strongbox.removeResources(resources.get(i));
            } else if (store[i] <=3){
                warehouseStore.removeRes(resources.get(i));
            } else {
                //TODO leader warehouse not accessible
            }
        }
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
        boardArt.append("┌─────────────────────────────────────────────────────────────────────────────────────" +
                "──────────────────────────────────────────────────────────────┐\n");
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
        StringBuilder tempArt = new StringBuilder();
        switch(leaderCards.size()){
            case(0):
                tempArt.append(emptyCard).append(emptyCard);
                break;
            case(1):
                tempArt.append(leaderCards.get(0).toString()).append(emptyCard);
                break;
            case(2):
                tempArt.append(leaderCards.get(0).toString()).append(leaderCards.get(1).toString());
                break;
        }
        String[] leaderArt = tempArt.toString().split("\n");
        for(int k=0; k<4; k++){
            boardArt.append("│");
            if(k==2)
                boardArt.append("  "+faithArt[k]+"   "+leaderArt[k]);
            else
            boardArt.append(StringUtility.center(faithArt[k],127)).append(leaderArt[k]);
            boardArt.append(" │\n");
        }
        boardArt.append("│  "+faithArt[4]+"     "+leaderArt[4]+" │\n");

        for(int i = 0; i<9;i++){
            boardArt.append(wareArt[i].concat("                        "+devPlaceArt[i])).append("      ").append(leaderArt[i+5]);
            boardArt.append(" │\n");
        }
        boardArt.append(boxArt[0]).append("     ╦══════════╦       "+devPlaceArt[9]).append("      ").append(leaderArt[14]).append(" │\n");
        boardArt.append(boxArt[1]).append("     ║ 1"+b+"--┐    ║       "+devPlaceArt[10]+"      "+leaderArt[15]+" │\n");
        boardArt.append(boxArt[2]).append("     ║     ├>1"+b+" ║       "+devPlaceArt[11]+"      "+leaderArt[16]+" │\n");
        boardArt.append(boxArt[3]).append("     ║ 1"+b+"--┘    ║       "+devPlaceArt[12]+"      "+leaderArt[17]+" │\n");
        boardArt.append(boxArt[4]).append("     ╩══════════╩       "+devPlaceArt[13]+"      "+leaderArt[18]+" │\n");
        boardArt.append(boxArt[5]).append("                                        " +
                "                                                             "+leaderArt[19]+" │\n");
        boardArt.append("└───────────────────────────────────────────────────────────────────────────────────────" +
                "────────────────────────────────────────────────────────────┘\n");

        return boardArt.toString();
    }
}
