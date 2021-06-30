package it.polimi.ingsw.commonFiles.messages.toClient.updates;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ToClientMessageVisitor;
import it.polimi.ingsw.commonFiles.model.Card;

public class LorenzoPick extends Message implements GameUpdate{
    private final String action;
    private final boolean takenCardsOfDifferentLevel;
    private Card card;

    public LorenzoPick(String action, boolean takenCardsOfDifferentLevel) {
        this.action = action;
        this.takenCardsOfDifferentLevel = takenCardsOfDifferentLevel;
    }

    public LorenzoPick(String action, boolean takenCardsOfDifferentLevel, Card cards){
        this.action = action;
        this.takenCardsOfDifferentLevel = takenCardsOfDifferentLevel;
        this.card = cards;
    }

    public boolean isTakenCardsOfDifferentLevel() {
        return takenCardsOfDifferentLevel;
    }

    public Card getCard() {
        return card;
    }

    public String getAction() {
        return action;
    }

    @Override
    public void accept(ToClientMessageVisitor v) {
        v.visit(this);
    }

    @Override
    public void accept(UpdateHandler v) {
        v.visit(this);
    }
}
