package it.polimi.ingsw.commonFiles.messages.toClient.updates;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ToClientMessageVisitor;

import java.util.ArrayList;

public class InitBoards extends Message implements GameUpdate {
    private ArrayList<String> usernames = new ArrayList<>();

    public InitBoards(ArrayList<String> usernames) {
        this.usernames = usernames;
    }

    public ArrayList<String> getUsernames() {
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
