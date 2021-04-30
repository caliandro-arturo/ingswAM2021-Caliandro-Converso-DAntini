package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.messages.Message;

/**
 * Message with a nickname. {@link SetNickname#nickname} cannot be empty.
 */
public class SetNickname extends Message implements ToServerMessage {
    @Override
    public void accept(ServerMessageVisitor v) {
        v.visit(this);
    }

    private String nickname;

    public SetNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
