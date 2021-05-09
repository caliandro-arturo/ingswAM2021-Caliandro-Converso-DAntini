package it.polimi.ingsw.messages.toServer;

import it.polimi.ingsw.messages.Message;
import it.polimi.ingsw.server.ClientHandler;

/**
 * Message with a nickname. {@link SetNickname#nickname} cannot be empty.
 */
public class SetNickname extends Message implements ToServerMessage {
    @Override
    public void accept(ToServerMessageHandler v) {
        v.visit(this);
    }

    private String nickname;
    private ClientHandler client;

    public SetNickname(String nickname) {
        this.nickname = nickname;
    }

    public ClientHandler getClient() {
        return client;
    }

    public String getNickname() {
        return nickname;
    }

    public void setClient(ClientHandler client) {
        this.client = client;
    }
}
