package it.polimi.ingsw.messages.toServer;

public interface ToServerMessage {
    void accept(ToServerMessageHandler v);
}
