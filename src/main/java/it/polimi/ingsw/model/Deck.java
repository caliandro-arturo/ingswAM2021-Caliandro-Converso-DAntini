package it.polimi.ingsw.model;
import java.util.*;

/**
 * this class identifies all the group of cards in the project
 */
public class Deck {
    private Stack<Card> deck;


    public Deck (){
        deck= new Stack<Card>();
    }
    public Deck(Card[] cards){
        deck.addAll( Arrays.asList(cards));
    }
    public Stack<Card> getDeck(){
        return deck;
    }
    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

}
