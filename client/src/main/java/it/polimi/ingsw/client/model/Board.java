package it.polimi.ingsw.client.model;


import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.utility.CLIColor;
import it.polimi.ingsw.commonFiles.utility.StringUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * light version of Board class for representation purposes
 */

public class Board {
    private DevelopmentPlace developmentPlace;
    private final FaithTrack faithTrack;
    private final Strongbox strongbox ;
    private final WarehouseStore warehouseStore;
    private ArrayList<LeaderCard> leaderCards;
    private final ResourceHand resHand  = new ResourceHand();
    private ArrayList<ArrayList<Resource>> powers;

    public Board() {
        this.developmentPlace = new DevelopmentPlace();
        this.faithTrack = new FaithTrack();
        this.strongbox = new Strongbox();
        this.warehouseStore = new WarehouseStore();
        this.leaderCards = new ArrayList<>();
        this.powers = new ArrayList<>(){{
            add(new ArrayList<>());
            add(new ArrayList<>());
            add(new ArrayList<>());
        }};
    }

    public ArrayList<Resource> getPowerWhite() {
        return powers.get(0);
    }

    public ArrayList<Resource> getPowerSale() {
        return powers.get(1);
    }

    public ArrayList<Resource> getPowerProd() {
        return powers.get(2);
    }

    public void setPowerWhiteMarble(Resource whiteMarble) {
        this.powers.get(0).add(whiteMarble);
    }

    public void setPowerSale(Resource sale){
        this.powers.get(1).add(sale);
    }

    public void setPowerProd(Resource prod){
        this.powers.get(2).add(prod);
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

    public void addResourcesToHand(List<Resource> resources) {
        resHand.addResources(resources);
    }
    
    public void addResourceToHand(Resource resource) {
        resHand.addResource(resource);
    }

    public void removeResourcesFromHand(List<Resource> resources) {
        resHand.removeResources(resources);
    }
    
    public void removeResourceFromHand(Resource resource) {
        resHand.removeResource(resource);
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
            } else {
                warehouseStore.removeRes(store[i], resources.get(i));
            }
        }
    }

    /**
     * representation method for Board (CLI)
     * @return String with the representation
     */
    @Override
    public String toString(){
        StringBuilder boardArt = new StringBuilder();
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
                boardArt.append("  ").append(faithArt[k]).append("   ").append(leaderArt[k]);
            else
            boardArt.append(StringUtility.center(faithArt[k],127)).append(leaderArt[k]);
            boardArt.append(" │\n");
        }
        boardArt.append("│  ").append(faithArt[4]).append("     ").append(leaderArt[4]).append(" │\n");

        for(int i = 0; i<9;i++){
            boardArt.append(wareArt[i].concat("                        "+devPlaceArt[i])).append("      ").append(leaderArt[i+5]);
            boardArt.append(" │\n");
        }
        boardArt.append(boxArt[0]).append("     ╦══════════╦       ").append(devPlaceArt[9]).append("      ").append(leaderArt[14]).append(" │\n");
        boardArt.append(boxArt[1]).append("     ║ 1").append(b).append("--┐    ║       ").append(devPlaceArt[10]).append("      ").append(leaderArt[15]).append(" │\n");
        boardArt.append(boxArt[2]).append("     ║     ├>1").append(b).append(" ║       ").append(devPlaceArt[11]).append("      ").append(leaderArt[16]).append(" │\n");
        boardArt.append(boxArt[3]).append("     ║ 1").append(b).append("--┘    ║       ").append(devPlaceArt[12]).append("      ").append(leaderArt[17]).append(" │\n");
        boardArt.append(boxArt[4]).append("     ╩══════════╩       ").append(devPlaceArt[13]).append("      ").append(leaderArt[18]).append(" │\n");
        boardArt.append(boxArt[5]).append("                                        " + "                                                             ").append(leaderArt[19]).append(" │\n");
        if (!resHand.isEmpty()) {
            boardArt.append("│  Resources hand: ").append(resHand)
                    .append(StringUtility.center("", 145 - 16 - StringUtility.realLength(resHand.toString())))
                    .append("│\n");
        }
        boardArt.append("└───────────────────────────────────────────────────────────────────────────────────────" +
                "────────────────────────────────────────────────────────────┘\n");

        return boardArt.toString();
    }
}
