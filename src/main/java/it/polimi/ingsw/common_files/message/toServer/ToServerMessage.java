package it.polimi.ingsw.common_files.message.toServer;

/**
 * Messages sent by client to the server.
 */
public interface ToServerMessage {
    void accept(ToServerMessageHandler v);
}
