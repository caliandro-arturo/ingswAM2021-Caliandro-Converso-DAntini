package it.polimi.ingsw.commonFiles.messages.toServer;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.model.Resource;

public class DiscardRes extends Message implements ToServerMessage {
    private final Resource resource;

    public DiscardRes(Resource resource) {
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }

    @Override
    public void accept(ToServerMessageHandler v) {
        v.visit(this);
    }
}
