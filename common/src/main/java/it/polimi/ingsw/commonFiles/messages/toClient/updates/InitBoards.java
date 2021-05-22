package it.polimi.ingsw.commonFiles.messages.toClient.updates;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ToClientMessageVisitor;

import java.util.ArrayList;
import java.util.List;

public class InitBoards extends Message implements GameUpdate {
    private final List<String> usernames;

    public InitBoards(List<String> usernames) {
        this.usernames = usernames;
    }

    public List<String> getUsernames() {
        return usernames;
    }

    @Override
    public void accept(ToClientMessageVisitor v) {
        v.visit(this);
    }

    @Override
    public void accept(UpdateHandler v) {
        v.visit(this);
    }
}
