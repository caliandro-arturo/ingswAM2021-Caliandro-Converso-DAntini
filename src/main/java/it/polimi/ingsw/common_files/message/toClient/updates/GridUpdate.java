package it.polimi.ingsw.common_files.message.toClient.updates;

import it.polimi.ingsw.common_files.model.DevelopmentCard;
import it.polimi.ingsw.client.UpdateHandler;
import it.polimi.ingsw.common_files.message.Message;
import it.polimi.ingsw.common_files.message.toClient.ClientMessageVisitor;
import java.util.ArrayList;

public class GridUpdate extends Message implements  GameUpdate {
    private ArrayList<DevelopmentCard> grid;


    public GridUpdate(ArrayList<DevelopmentCard> grid) {
        this.grid = grid;
    }

    public ArrayList<DevelopmentCard> getGrid() {
        return grid;
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
