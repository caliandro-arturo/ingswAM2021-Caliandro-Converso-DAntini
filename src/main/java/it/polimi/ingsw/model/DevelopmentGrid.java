package it.polimi.ingsw.model;

public class DevelopmentGrid {
    private Deck[][] developmentGrid;

    /*
    TODO make an exception to signal wrong color
     */
    public DevelopmentGrid(DevelopmentCard[] cards){
        developmentGrid = new Deck[3][4];
        Deck greenlv1 = new Deck();
        Deck bluelv1 = new Deck();
        Deck yellowlv1 = new Deck();
        Deck purplelv1 = new Deck();
        Deck greenlv2 = new Deck();
        Deck bluelv2 = new Deck();
        Deck yellowlv2 = new Deck();
        Deck purplelv2 = new Deck();
        Deck greenlv3 = new Deck();
        Deck bluelv3 = new Deck();
        Deck yellowlv3 = new Deck();
        Deck purplelv3 = new Deck();
        for (DevelopmentCard card : cards) {
            if (card.getColor() == Color.GREEN) {
                if (card.getLevel() == 1) {
                    greenlv1.getDeck().push(card);
                } else if (card.getLevel() == 2) {
                    greenlv2.getDeck().push(card);
                } else {
                    greenlv3.getDeck().push(card);
                }
            } else if (card.getColor() == Color.BLUE) {
                if (card.getLevel() == 1) {
                    bluelv1.getDeck().push(card);
                } else if (card.getLevel() == 2) {
                    bluelv2.getDeck().push(card);
                } else {
                    bluelv3.getDeck().push(card);
                }
            } else if (card.getColor() == Color.YELLOW) {
                if (card.getLevel() == 1) {
                    yellowlv1.getDeck().push(card);
                } else if (card.getLevel() == 2) {
                    yellowlv2.getDeck().push(card);
                } else {
                    yellowlv3.getDeck().push(card);
                }
            } else {
                if (card.getLevel() == 1) {
                    purplelv1.getDeck().push(card);
                } else if (card.getLevel() == 2) {
                    purplelv2.getDeck().push(card);
                } else {
                    purplelv3.getDeck().push(card);
                }
            }
        }
        greenlv1.shuffleDeck();
        developmentGrid[0][0]=greenlv1;
        greenlv2.shuffleDeck();
        developmentGrid[1][0]=greenlv2;
        greenlv3.shuffleDeck();
        developmentGrid[2][0]=greenlv3;
        bluelv1.shuffleDeck();
        developmentGrid[0][1]=bluelv1;
        bluelv2.shuffleDeck();
        developmentGrid[1][1]=bluelv2;
        bluelv3.shuffleDeck();
        developmentGrid[2][1]=bluelv3;
        yellowlv1.shuffleDeck();
        developmentGrid[0][2]=yellowlv1;
        yellowlv2.shuffleDeck();
        developmentGrid[1][2]=yellowlv2;
        yellowlv3.shuffleDeck();
        developmentGrid[2][2]=yellowlv3;
        purplelv1.shuffleDeck();
        developmentGrid[0][3]=purplelv1;
        purplelv2.shuffleDeck();
        developmentGrid[1][3]=purplelv2;
        purplelv3.shuffleDeck();
        developmentGrid[2][3]=purplelv3;
    }
    /*
    TODO make an exception to signal not enough resources
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
}
