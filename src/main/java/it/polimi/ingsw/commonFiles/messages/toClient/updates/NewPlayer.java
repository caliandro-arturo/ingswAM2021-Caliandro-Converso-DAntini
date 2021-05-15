package it.polimi.ingsw.commonFiles.messages.toClient.updates;

import it.polimi.ingsw.client.UpdateHandler;
import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ClientMessageVisitor;

/**
 * Update sent when a player joins the virtualView.
 */
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
