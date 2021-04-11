package it.polimi.ingsw.model;

import java.util.Stack;

/**
 * class that take place for the development cards
 * each of the 3 places is connected with a Composition link to the PersonalBoard

 */
public class DevelopmentPlace {

    private Stack<DevelopmentCard> developmentCards;

    public Stack<DevelopmentCard> getDevelopmentCards() {
        return developmentCards;
    }
    /*public DevelopmentCard getLevel1Card() { return level1Card;    }
    public DevelopmentCard getLevel2Card() { return level2Card;    }
    public DevelopmentCard getLevel3Card() { return level3Card;    }

    public void setLevel1Card(DevelopmentCard level1Card) { this.level1Card = level1Card; }
    public void setLevel2Card(DevelopmentCard level2Card) { this.level2Card = level2Card; }
    public void setLevel3Card(DevelopmentCard level3Card) { this.level3Card = level3Card; }*/

    /*public DevelopmentCard getLevelCard(int level){
        if (level == 1){
            return getLevel1Card();
        } else if (level == 2){
            return getLevel2Card();
        } else {
            return getLevel3Card();
        }
    }*/

    public boolean hasRoomForCard(int level){
        if (level ==1 && developmentCards.isEmpty()) {
            return true;
        } else    
            return level == developmentCards.peek().getLevel()+1;
    }

}

