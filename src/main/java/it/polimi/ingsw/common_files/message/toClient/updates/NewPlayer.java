package it.polimi.ingsw.common_files.message.toClient.updates;

import it.polimi.ingsw.client.UpdateHandler;
import it.polimi.ingsw.common_files.message.Message;
import it.polimi.ingsw.common_files.message.toClient.ClientMessageVisitor;

public class NewPlayer extends Message implements GameUpdate{
    private final String name;

    public NewPlayer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(ClientMessageVisitor v) {
        v.visit(this);
    }

    @Override
    public void accept(UpdateHandler v) {
        v.visit(this);
    }
}
