package it.polimi.ingsw.commonFiles.messages.toServer.actions;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toServer.ToServerMessage;
import it.polimi.ingsw.commonFiles.messages.toServer.ToServerMessageHandler;
import it.polimi.ingsw.commonFiles.model.Resource;

public class GetResource extends Message implements ToServerMessage {
    private final Resource resource;

    public GetResource(Resource resource) {
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