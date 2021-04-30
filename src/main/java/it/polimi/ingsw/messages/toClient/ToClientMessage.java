package it.polimi.ingsw.messages.toClient;

public interface ToClientMessage {
    void accept(ClientMessageVisitor v);
}