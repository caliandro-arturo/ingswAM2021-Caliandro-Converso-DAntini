package it.polimi.ingsw.commonFiles.messages.toServer;

/**
 * Messages sent by client to the server.
 */
public interface ToServerMessage {
    void setPlayer(String player);
    void accept(ToServerMessageHandler v);
}
