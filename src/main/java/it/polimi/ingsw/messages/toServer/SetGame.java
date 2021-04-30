package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.messages.Message;

public class SetGame extends Message implements ToServerMessage {
    @Override
    public void accept(ServerMessageVisitor v) {
        v.visit(this);
    }
    private final int numberOfPlayers;

    public SetGame(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
}
