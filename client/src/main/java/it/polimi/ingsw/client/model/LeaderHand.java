package it.polimi.ingsw.client.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Represents the leader hand of the player.
 */
public class LeaderHand {
    private final ObservableList<LeaderCard> hand = FXCollections.observableArrayList();

    public LeaderHand() {
    }

    public LeaderHand(ArrayList<LeaderCard> hand) {
        this.hand.setAll(hand);
    }

    public void removeCardFromHand(int pos) {
        hand.remove(pos - 1);
    }

    public ArrayList<LeaderCard> getHand() {
        return new ArrayList<>(hand);
    }

    public ObservableList<LeaderCard> handProperty() {
        return hand;
    }

    /**
     * representation method for the hand of the player (CLI)
     *
     * @return String with the representation
     */
    @Override
    public String toString() {
        StringBuilder tempArt = new StringBuilder();
        StringBuilder handArt = new StringBuilder();
        switch (hand.size()) {
            case (0):
                return "No leader cards to show";
            case (1):
                tempArt.append(hand.get(0));
                return tempArt.toString();
            case (2):
                tempArt.append(hand.get(0).toString()).append(hand.get(1).toString());
                String[] handArt2 = tempArt.toString().split("\n");
                for (int i = 0; i < 10; i++) {
                    handArt.append(handArt2[i]).append(" ").append(handArt2[i + 10]).append("\n");
                }
                return handArt.toString();
            case (3):
                tempArt.append(hand.get(0).toString()).append(hand.get(1).toString()).append(hand.get(2).toString());
                String[] handArt3 = tempArt.toString().split("\n");
                for (int i = 0; i < 10; i++) {
                    handArt.append(handArt3[i]).append(" ").append(handArt3[i + 10]).append(" ").append(handArt3[i + 20]).append("\n");
                }
                return handArt.toString();
            case (4):
                tempArt.append(hand.get(0).toString()).append(hand.get(1).toString()).append(hand.get(2).toString()).
                        append(hand.get(3).toString());
                String[] handArt4 = tempArt.toString().split("\n");
                for (int i = 0; i < 10; i++) {
                    handArt.append(handArt4[i]).append(" ").append(handArt4[i + 10]).append(" ").append(handArt4[i + 20]).append(" ").append(handArt4[i + 30]).append("\n");

                }
                return handArt.toString();
        }
        return "";
    }
}
