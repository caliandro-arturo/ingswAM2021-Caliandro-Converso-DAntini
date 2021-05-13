package it.polimi.ingsw.common_files.message.toClient;

public interface ToClientMessage {
    void accept(ClientMessageVisitor v);
}