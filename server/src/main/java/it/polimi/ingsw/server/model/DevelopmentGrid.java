package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.model.Card;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class DevelopmentGrid {
    private final Deck[][] developmentGrid;

    public Deck getDeck(int level, Color color){
        return developmentGrid[level-1][Utility.colorPosition.get(color)];
    }

    public boolean isStillWinnable(){
        int i = 0;
        while (i<4){
            if (developmentGrid[2][i].getDeck().empty()){
                if (developmentGrid[1][i].getDeck().empty()){
                    if (developmentGrid[0][i].getDeck().empty())
                        return false;
                }
            }
            i++;
        }
        return true;
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
     * take the card from the grid and return to the player
     * @param color color ask by the player
     * @param level level ask by the player
     * @return the card chosen
     */
    public DevelopmentCard buyCard(Color color,int level){
        for (Map.Entry<Color,Integer> entry : Utility.colorPosition.entrySet()){
            if (entry.getKey() == color){
                return developmentGrid[level-1][entry.getValue()].takeCard();
            }
        }
        return null;
    }

    public boolean lorenzoUpdate(Color color){
        for (int i=0; i<3; i++) {
            if (!developmentGrid[i][Utility.colorPosition.get(color)].getDeck().isEmpty()){
                return developmentGrid[i][Utility.colorPosition.get(color)].getDeck().size() == 1;
            }
        }
        return false;
    }

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
        //a solution may be add level in Card
    }

    /**
     * SinglePlayer action of lorenzo
     * @param color action's color
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
     *
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
