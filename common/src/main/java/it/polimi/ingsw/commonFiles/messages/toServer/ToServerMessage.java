package it.polimi.ingsw.commonFiles.messages.toServer;

/**
 * Messages sent by client to the server.
 */
public interface ToServerMessage {
    void accept(ToServerMessageHandler v);
}
