package it.polimi.ingsw.commonFiles.messages.toServer;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.server.ClientHandler;

/**
 * Message sent by the client to set his nickname.
 */
public class SetNickname extends Message implements ToServerMessage {
    private final String nickname;
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

    @Override
    public void accept(ToServerMessageHandler v) {
        v.visit(this);
    }
}
