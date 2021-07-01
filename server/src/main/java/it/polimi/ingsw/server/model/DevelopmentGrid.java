package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.model.Card;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Map;

public class DevelopmentGrid {
    private final Deck[][] developmentGrid;

    public Deck getDeck(int level, Color color){
        return developmentGrid[level-1][Utility.colorPosition.get(color)];
    }

    public DevelopmentGrid(DevelopmentCard[] cards){
        developmentGrid = new Deck[3][4];
        for (int i =0; i < 3; i++){
            for (int j=0; j<4; j++){
                developmentGrid[i][j] = new Deck();
            }
        }
        for (DevelopmentCard card : cards) {
            for (Map.Entry<Color, Integer> entry : Utility.colorPosition.entrySet()) {
                if (card.getColor() == entry.getKey()) {
                    if (card.getLevel() == 1) {
                        developmentGrid[0][entry.getValue()].getDeck().push(card);
                    } else if (card.getLevel() == 2) {
                        developmentGrid[1][entry.getValue()].getDeck().push(card);
                    } else {
                        developmentGrid[2][entry.getValue()].getDeck().push(card);
                    }
                }
            }
        }
        for (int i =0; i < 3; i++){
            for (int j=0; j<4; j++){
                developmentGrid[i][j].shuffleDeck();
            }
        }
    }

    /**
     * Takes the first card of the deck specified by color and level.
     *
     * @param color the color of the card
     * @param level the level of the card
     * @return the card, or null if there are no more cards of the required color and/or level
     */
    public DevelopmentCard buyCard(Color color,int level){
        for (Map.Entry<Color,Integer> entry : Utility.colorPosition.entrySet()){
            if (entry.getKey() == color){
                return developmentGrid[level-1][entry.getValue()].takeCard();
            }
        }
        return null;
    }

    /**
     * Checks if the first non-empty deck of the deck column of the selected color has only one card.
     *
     * @param color the color of the deck to check
     * @return true if the first non-empty deck of the selected color has only one card, false otherwise.
     */
    public boolean lorenzoUpdate(Color color){
        for (int i=0; i<3; i++) {
            if (!developmentGrid[i][Utility.colorPosition.get(color)].getDeck().isEmpty()){
                return developmentGrid[i][Utility.colorPosition.get(color)].getDeck().size() == 1;
            }
        }
        return false;
    }

    /**
     * Solo Action method.
     * <p>
     * Takes what was under the last removed card before the Solo Action.
     *
     * @param color the color of the
     * @param flag  true if the solo action removed two cards of different level
     * @return the card that was under the last removed card, or {@code null} if the removed card was the last card of
     * the deck
     */
    public Card lorenzoCardsUpdate(Color color, boolean flag){
        DevelopmentCard card;
        int index;
        for (int i=0; i<3; i++) {
            if (!developmentGrid[i][Utility.colorPosition.get(color)].getDeck().isEmpty() &&
                    developmentGrid[i][Utility.colorPosition.get(color)].getDeck().size() >= 2){
                index = developmentGrid[i][Utility.colorPosition.get(color)].getDeck().size() - 3;
                try {
                    card = getDeck(i+1,color).getDeck().get(index);
                } catch (ArrayIndexOutOfBoundsException e) {
                    return null;
                }
                return new Card(card.getID(),card.getCost(),card.getVictoryPoints(), card.getProduction());
            } else if (flag && !developmentGrid[i][Utility.colorPosition.get(color)].getDeck().isEmpty()){
                index = developmentGrid[i][Utility.colorPosition.get(color)].getDeck().size() - 2;
                try {
                    card = getDeck(i+2,color).getDeck().get(index);
                } catch (ArrayIndexOutOfBoundsException | EmptyStackException e) {
                    return null;
                }
                return new Card(card.getID(),card.getCost(),card.getVictoryPoints(), card.getProduction());
            }
        }
        return null;
    }

    /**
     * Solo Action method.
     * <p>
     * Checks if the development grid has not any empty decks column.
     *
     * @return true if there is no empty deck column, false otherwise
     */
    public boolean isStillWinnable() {
        int i = 0;
        while (i < 4) {
            if (developmentGrid[2][i].getDeck().empty()) {
                if (developmentGrid[1][i].getDeck().empty()) {
                    if (developmentGrid[0][i].getDeck().empty())
                        return false;
                }
            }
            i++;
        }
        return true;
    }

    /**
     * Solo Action method.
     * <p>
     * Removes two cards from a deck column of the specified color.
     *
     * @param color the deck column color
     */
    public void removeCard(Color color){
        int i = 0;
        int j = 2;
        while (j>0){
            for (Map.Entry<Color,Integer> entry : Utility.colorPosition.entrySet()){
                if (entry.getKey() == color){
                    while (developmentGrid[i][entry.getValue()].getDeck().empty() && i<3){
                        i++;
                    }
                     developmentGrid[i][entry.getValue()].takeCard();
                }
            }
            j--;
        }
    }

    /**
     * Collects the top card of each deck in the grid.
     *
     * @return a collection of development cards containing the top cards
     */
    public ArrayList<DevelopmentCard> getTopDevelopmentCards(){
        ArrayList<DevelopmentCard> cards = new ArrayList<>();
        for(Deck[] d : developmentGrid) {
            for(Deck deck : d) {
                cards.add(deck.getTopCard());
            }
        }
        return cards;
    }
}
