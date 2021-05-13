package it.polimi.ingsw.common_files.message.toServer;

public interface ToServerMessage {
    void accept(ToServerMessageHandler v);
}
