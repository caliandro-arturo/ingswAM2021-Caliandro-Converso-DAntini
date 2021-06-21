package it.polimi.ingsw.server.model;

import java.util.Collections;
import java.util.Stack;

/**
 * this class identifies all the group of dev cards in the project
 */
public class Deck {
    private final Stack<DevelopmentCard> deck;


    public Deck(){
        this.deck = new Stack<>();
    }
    public Stack<DevelopmentCard> getDeck(){
        return deck;
    }
    public void shuffleDeck(){
        Collections.shuffle(deck);
    }
    public DevelopmentCard takeCard(){
        return this.deck.pop();
    }
    public DevelopmentCard getTopCard(){
        return this.deck.peek();
    }
}
