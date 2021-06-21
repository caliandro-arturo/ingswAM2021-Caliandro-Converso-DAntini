package it.polimi.ingsw.commonFiles.messages.toServer;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.model.Resource;

/**
 * input command to deploy the resources that the players have in the hand
 * into the warehouse store specifying the depot
 */
public class DeployRes extends Message implements ToServerMessage {
    private Resource resource;
    private int depot;

    public DeployRes(Resource resource, int depot) {
        this.resource = resource;
        this.depot = depot;
    }

    public Resource getResource() {
        return resource;
    }

    public int getDepot() {
        return depot;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public void setDepot(int depot) {
        this.depot = depot;
    }

    @Override
    public void accept(ToServerMessageHandler v){v.visit(this);}
}
