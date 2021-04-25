package it.polimi.ingsw.model;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * class that take place for the development cards
 * each of the 3 places is connected with a Composition link to the PersonalBoard

 */
public class DevelopmentPlace {

    private final Stack<DevelopmentCard> developmentCards;


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
    public DevelopmentCard getLevelICard(int i){return developmentCards.get(i);}

    /**
     * return true if this place has space for a specific level card
     * @param level level of the card
     * @return true if has space for the card
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

