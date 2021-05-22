package it.polimi.ingsw.commonFiles.messages.toServer;

import it.polimi.ingsw.commonFiles.messages.Message;

public class ChooseWhiteMarble extends Message implements ToServerMessage {
    private final int leaderPosition;

    public ChooseWhiteMarble(int leaderPosition) {
        this.leaderPosition = leaderPosition;
    }

    public int getLeaderPosition() {
        return leaderPosition;
    }

    @Override
    public void accept(ToServerMessageHandler v) {
        v.visit(this);
    }
}
