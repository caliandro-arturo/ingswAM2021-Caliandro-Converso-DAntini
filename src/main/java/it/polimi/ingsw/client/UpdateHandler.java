package it.polimi.ingsw.client;

import it.polimi.ingsw.messages.toClient.updates.*;

public class UpdateHandler {
    private final ClientController controller;
    private final ClientModel model;
    public UpdateHandler(ClientController controller, ClientModel model) {
        this.controller = controller;
        this.model = model;
    }
    public void visit(NewPlayer msg) {
        controller.showUpdate(msg.getName() + " has entered the game.");
    }
    public void visit(GameHasBeenSet msg) {
    }
    public void visit(PlayerLeft msg) {
        controller.showUpdate(msg.getName() + " has left the game.");
    }


}
