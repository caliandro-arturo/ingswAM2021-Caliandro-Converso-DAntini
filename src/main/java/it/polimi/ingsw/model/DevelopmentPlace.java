package it.polimi.ingsw.model;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * class that take place for the development cards
 * each of the 3 places is connected with a Composition link to the PersonalBoard

 */
public class DevelopmentPlace {

    private Stack<DevelopmentCard> developmentCards;
    /*private DevelopmentCard level1Card;
    private DevelopmentCard level2Card;
    private DevelopmentCard level3Card;*/

    public Stack<DevelopmentCard> getDevelopmentCards() {
        return developmentCards;
    }

    public DevelopmentPlace() {
        this.developmentCards = new Stack<>();
    }
    public DevelopmentCard getLevel1Card() {
        return developmentCards.get(0);
    }
    public DevelopmentCard getLevel2Card() {
        return developmentCards.get(1);
    }
    public DevelopmentCard getLevel3Card() {
        return developmentCards.get(2);
    }

    /**
     * return true if this place has space for a specific level card
     * @param level
     * @return
     */
    public boolean hasRoomForCard(int level) {
        try {
            if(level>1 && developmentCards.isEmpty())
                return false;
            else
                return level == developmentCards.peek().getLevel() + 1;
        }catch(EmptyStackException e){
            return true;
        }
    }

}

