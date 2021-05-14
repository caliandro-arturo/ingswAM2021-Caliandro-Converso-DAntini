package it.polimi.ingsw.common_files.message.toClient.updates;

import it.polimi.ingsw.client.UpdateHandler;
import it.polimi.ingsw.common_files.message.Message;
import it.polimi.ingsw.common_files.message.toClient.ClientMessageVisitor;
import it.polimi.ingsw.common_files.model.Resource;


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
    public void accept(ClientMessageVisitor v) {
        v.visit(this);
    }

    @Override
    public void accept(UpdateHandler v) {
        v.visit(this);
    }
}
