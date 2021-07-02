package it.polimi.ingsw.server.model;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Slots for the development cards.
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

    public DevelopmentCard getLevelICard(int i) {
        return developmentCards.get(i);
    }

    /**
     * Returns true if this place has space for a specific level card.
     *
     * @param level level of the card
     * @return true if has space for the card
     */
    public boolean hasRoomForCard(int level) {
        try {
            if (level > 1 && developmentCards.isEmpty())
                return false;
            else
                return level == developmentCards.peek().getLevel() + 1;
        } catch (EmptyStackException e) {
            return true;
        }
    }

    @Override
    public String toString() {
        StringBuilder devPlaceJson = new StringBuilder();
        devPlaceJson.append("[");
        for (DevelopmentCard c : developmentCards) {
            devPlaceJson.append(c.toString());
            devPlaceJson.append(",\n");
        }
        if (devPlaceJson.lastIndexOf(",") != -1)
            devPlaceJson.deleteCharAt(devPlaceJson.lastIndexOf(","));
        devPlaceJson.append("]");
        return devPlaceJson.toString();
    }
}

