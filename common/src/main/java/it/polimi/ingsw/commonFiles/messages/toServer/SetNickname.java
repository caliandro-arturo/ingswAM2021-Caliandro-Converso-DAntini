package it.polimi.ingsw.commonFiles.messages.toServer;

import it.polimi.ingsw.commonFiles.messages.Message;

/**
 * Message sent by the client to set his nickname.
 */
public class SetNickname extends Message implements ToServerMessage {
    private final String nickname;
    private Object client;

    public SetNickname(String nickname) {
        this.nickname = nickname;
    }

    public Object getClient() {
        return client;
    }

    public String getNickname() {
        return nickname;
    }

    public void setClient(Object client) {
        this.client = client;
    }

    @Override
    public void accept(ToServerMessageHandler v) {
        v.visit(this);
    }
}
