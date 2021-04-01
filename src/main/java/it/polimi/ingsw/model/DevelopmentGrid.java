package it.polimi.ingsw.model;

public class DevelopmentGrid {
    private Deck[][] developmentGrid;

    public DevelopmentGrid(DevelopmentCard[] cards){
        developmentGrid = new Deck[3][4];
        for (int i =0; i < 3; i++){
            for (int j=0; j<4; j++){
                developmentGrid[i][j] = new Deck();
            }
        }
        for (DevelopmentCard card : cards) {
            if (card.getColor() == Color.GREEN) {
                if (card.getLevel() == 1) {
                    developmentGrid[0][0].getDeck().push(card);
                } else if (card.getLevel() == 2) {
                    developmentGrid[1][0].getDeck().push(card);
                } else {
                    developmentGrid[2][0].getDeck().push(card);
                }
            } else if (card.getColor() == Color.BLUE) {
                if (card.getLevel() == 1) {
                    developmentGrid[0][1].getDeck().push(card);
                } else if (card.getLevel() == 2) {
                    developmentGrid[1][1].getDeck().push(card);
                } else {
                    developmentGrid[2][1].getDeck().push(card);
                }
            } else if (card.getColor() == Color.YELLOW) {
                if (card.getLevel() == 1) {
                    developmentGrid[0][2].getDeck().push(card);
                } else if (card.getLevel() == 2) {
                    developmentGrid[1][2].getDeck().push(card);
                } else {
                    developmentGrid[2][2].getDeck().push(card);
                }
            } else {
                if (card.getLevel() == 1) {
                    developmentGrid[0][3].getDeck().push(card);
                } else if (card.getLevel() == 2) {
                    developmentGrid[1][3].getDeck().push(card);
                } else {
                    developmentGrid[2][3].getDeck().push(card);
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
     * @return the card choosen
     */
    public Card buyCard(Color color,int level){
        if(color == Color.GREEN) {
            return developmentGrid[level - 1][0].getDeck().pop();
        } else if (color == Color.BLUE){
            return developmentGrid[level - 1][1].getDeck().pop();
        } else if (color == Color.YELLOW){
            return developmentGrid[level - 1][2].getDeck().pop();
        } else {
            return developmentGrid[level - 1][3].getDeck().pop();
        }
    }

    /**
     * singleplayer action of lorenzo
     * @param color action's color
     */
    public void removeCard(Color color){
        int i = 0;
        int j = 2;
        while (j>0){
            if(color == Color.GREEN) {
                while (developmentGrid[i][0].getDeck().empty()){
                    i++;
                }
                developmentGrid[i][0].getDeck().pop();
            } else if (color == Color.BLUE){
                while (developmentGrid[i][1].getDeck().empty()){
                    i++;
                }
                developmentGrid[i][1].getDeck().pop();
            } else if (color == Color.YELLOW){
                while (developmentGrid[i][2].getDeck().empty()){
                    i++;
                }
                developmentGrid[i][2].getDeck().pop();
            } else {
                while (developmentGrid[i][3].getDeck().empty()){
                    i++;
                }
                developmentGrid[i][3].getDeck().pop();
            }
            j--;
        }
    }
}
