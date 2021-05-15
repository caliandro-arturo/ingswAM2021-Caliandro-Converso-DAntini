package it.polimi.ingsw.commonFiles.messages.toClient;

/**
 * Messages that are sent to clients.
 */
public interface ToClientMessage {
    void accept(ClientMessageVisitor v);
}