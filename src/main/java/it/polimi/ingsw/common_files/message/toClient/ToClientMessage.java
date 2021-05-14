package it.polimi.ingsw.common_files.message.toClient;

/**
 * Messages that are sent to clients.
 */
public interface ToClientMessage {
    void accept(ClientMessageVisitor v);
}