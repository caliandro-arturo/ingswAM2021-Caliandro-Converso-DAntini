package it.polimi.ingsw.common_files.message.toServer;

import it.polimi.ingsw.common_files.message.Message;

/**
 * Message sent to set the game with a certain number of players.
 */
public class SetGame extends Message implements ToServerMessage {
    private final int numberOfPlayers;

    public SetGame(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    @Override
    public void accept(ToServerMessageHandler v) {
        v.visit(this);
    }
}
