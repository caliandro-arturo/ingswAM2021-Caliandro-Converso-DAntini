package it.polimi.ingsw.server.model;

import java.util.Map;

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
}
