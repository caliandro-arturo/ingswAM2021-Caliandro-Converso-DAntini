package it.polimi.ingsw.client.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Development place representation for client
 */
public class DevelopmentPlace {

    private final ArrayList<ObservableList<DevelopmentCard>> devStack;

    public DevelopmentPlace() {
        devStack = new ArrayList<>();
        devStack.add(FXCollections.observableArrayList());
        devStack.add(FXCollections.observableArrayList());
        devStack.add(FXCollections.observableArrayList());
    }

    /**
     * Setter for the DevelopmentCard
     *
     * @param developmentCard card to insert
     * @param pos             position of the spot to insert in
     */
    public void setDevStack(DevelopmentCard developmentCard, int pos) {
        this.devStack.get(--pos).add(developmentCard);
    }

    public ArrayList<ObservableList<DevelopmentCard>> getDevStack() {
        return devStack;
    }

    /**
     * Gets the top card of a specified development space.
     *
     * @param pos the development space
     * @return the top card of the specified development space.
     */
    public DevelopmentCard getTopCard(int pos) {
        int size = devStack.get(--pos).size();
        return this.devStack.get(pos).get(--size);
    }

    /**
     * Collects the top cards of the development spaces. If there is no card in a development space, inserts a null.
     *
     * @return the top cards
     */
    public ArrayList<DevelopmentCard> getTopCards() {
        ArrayList<DevelopmentCard> devCards = new ArrayList<>();
        for (ObservableList<DevelopmentCard> devPlace : devStack) {
            if (devPlace.isEmpty()) {
                devCards.add(null);
            } else
                devCards.add(devPlace.get(devPlace.size() - 1));
        }
        return devCards;
    }

    /**
     * Representation method for Development Place (CLI)
     *
     * @return String with the representation
     */
    @Override
    public String toString() {
        StringBuilder devPlaceArt = new StringBuilder();
        StringBuilder[] devString = new StringBuilder[3];
        devString[0] = new StringBuilder();
        devString[1] = new StringBuilder();
        devString[2] = new StringBuilder();
        int i = 0;
        String space = "       ";
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
        String emptySpace = """
                                  \s
                                  \s
                                  \s
                """;
        String[] temp1;
        String[] temp2;
        String[] temp3;

        for (ObservableList<DevelopmentCard> stacks : devStack) {
            if (stacks.isEmpty()) {
                devString[i].append(emptyCard);
                devString[i].append(emptySpace).append(emptySpace);
            } else {
                devString[i].append(stacks.get(stacks.size() - 1).toString());
                switch (stacks.size()) {
                    case 1 -> devString[i].append(emptySpace).append(emptySpace);
                    case 2 -> {
                        int l = stacks.get(0).toString().length();
                        devString[i].append(stacks.get(0).toString(), l - 58, l);
                        devString[i].append(emptySpace);
                    }
                    case 3 -> {
                        int len = stacks.get(0).toString().length();
                        devString[i].append(stacks.get(0).toString(), len - 58, len);
                        devString[i].append(stacks.get(1).toString(), len - 58, len);
                    }
                }

            }
            i++;
        }
        temp1 = devString[0].toString().split("\n");
        temp2 = devString[1].toString().split("\n");
        temp3 = devString[2].toString().split("\n");

        for (int j = 0; j < 14; j++) {
            devPlaceArt.append(temp1[j]).append(space).append(temp2[j]).append(space).append(temp3[j]);
            devPlaceArt.append("\n");
        }
        return devPlaceArt.toString();
    }
}
