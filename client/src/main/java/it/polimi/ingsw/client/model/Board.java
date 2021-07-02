package it.polimi.ingsw.client.model;


import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.utility.CLIColor;
import it.polimi.ingsw.commonFiles.utility.StringUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * light version of Board class for representation purposes
 */

public class Board {
    private final DevelopmentPlace developmentPlace;
    private final FaithTrack faithTrack;
    private final Strongbox strongbox;
    private final WarehouseStore warehouseStore;
    private final ObservableList<LeaderCard> leaderCards = FXCollections.observableArrayList();
    private final ResourceHand resHand = new ResourceHand();
    private final ArrayList<ArrayList<Resource>> powers;
    private final ObservableList<Boolean> isProductionAlreadyUsed = FXCollections.observableArrayList(Arrays.asList(null, null, null, null, null, null));


    public Board() {
        this.developmentPlace = new DevelopmentPlace();
        this.faithTrack = new FaithTrack();
        this.strongbox = new Strongbox();
        this.warehouseStore = new WarehouseStore();
        this.powers = new ArrayList<>() {{
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

    public void setPowerSale(Resource sale) {
        this.powers.get(1).add(sale);
    }

    public void setPowerProd(Resource prod) {
        this.powers.get(2).add(prod);
    }

    public ObservableList<LeaderCard> getLeaderCards() {
        return leaderCards;
    }

    public ResourceHand getResHand() {
        return resHand;
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

    public ObservableList<Boolean> getIsProductionAlreadyUsed() {
        return isProductionAlreadyUsed;
    }

    public void setIsProductionAlreadyUsed(boolean flag, int ID) {
        this.isProductionAlreadyUsed.set(ID, flag);
    }

    public Strongbox getStrongbox() {
        return strongbox;
    }

    public ObservableList<Integer> getStrongboxObject() {
        return strongbox.getStrongboxList();
    }

    public void setLeaderCards(LeaderCard leaderCard) {
        this.leaderCards.add(leaderCard);
    }

    public WarehouseStore getWarehouseStore() {
        return warehouseStore;
    }

    /**
     * Enables or disables productions
     *
     * @param flag the control boolean: if true, productions are disabled
     */
    public void setProductionInterface(boolean flag) {
        for (int i = 0; i < isProductionAlreadyUsed.size(); i++) {
            if (isProductionAlreadyUsed.get(i) != null) {
                isProductionAlreadyUsed.set(i, flag);
            }
        }
    }

    /**
     * Removes resource from the indicated stores. Each cell in the store array is linked to a resource in the list.
     *
     * @param store     the depot from which to remove a resource
     * @param resources the list of resource to remove
     */
    public void removeResource(int[] store, ArrayList<Resource> resources) {
        for (int i = 0; i < store.length; i++) {
            if (store[i] == 0) {
                strongbox.removeResources(resources.get(i));
            } else {
                warehouseStore.removeRes(store[i], resources.get(i));
            }
        }
    }

    /**
     * Representation method for Board (CLI).
     *
     * @return String with the representation
     */
    @Override
    public String toString() {
        StringBuilder boardArt = new StringBuilder();
        String[] wareArt = warehouseStore.toString().split("\n");
        String[] boxArt = strongbox.toString().split("\n");
        String[] faithArt = faithTrack.toString().split("\n");
        String[] devPlaceArt = developmentPlace.toString().split("\n");
        String b = "\u001B[40m" + "?" + CLIColor.ANSI_RESET;
        boardArt.append("┌─────────────────────────────────────────────────────────────────────────────────────" +
                "──────────────────────────────────────────────────────────────┐\n");
        String emptyCard =
                """
                        ┌─────────────────┐
                        │                 │
                        │                 │
                        │                 │
                        │                 │
                        │                 │
                        │                 │
                        │                 │
                        │                 │
                        └─────────────────┘
                        """;
        StringBuilder tempArt = new StringBuilder();
        switch (leaderCards.size()) {
            case (0) -> tempArt.append(emptyCard).append(emptyCard);
            case (1) -> tempArt.append(leaderCards.get(0).toString()).append(emptyCard);
            case (2) -> tempArt.append(leaderCards.get(0).toString()).append(leaderCards.get(1).toString());
        }
        String[] leaderArt = tempArt.toString().split("\n");
        for (int k = 0; k < 4; k++) {
            boardArt.append("│");
            boardArt.append(StringUtility.center(faithArt[k], 127)).append(leaderArt[k]);
            boardArt.append(" │\n");
        }
        boardArt.append("│").append(faithArt[4]).append(leaderArt[4]).append(" │\n");

        for (int i = 0; i < 9; i++) {
            boardArt.append(wareArt[i].concat("                        " + devPlaceArt[i])).append("      ").append(leaderArt[i + 5]);
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
