package it.polimi.ingsw.commonFiles.messages.toClient.updates;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ToClientMessageVisitor;
import it.polimi.ingsw.commonFiles.model.Resource;

import java.util.ArrayList;

public class ResourceUpdate extends Message implements GameUpdate {
    private ArrayList<Resource> resHandUpdate;

    public ResourceUpdate(ArrayList<Resource> resHandUpdate) {
        this.resHandUpdate = resHandUpdate;
    }

    public ArrayList<Resource> getResHandUpdate() {
        return resHandUpdate;
    }

    public void setResHandUpdate(ArrayList<Resource> resHandUpdate) {
        this.resHandUpdate = resHandUpdate;
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
