package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.utility.StringUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SpecialWarehouse implements LeaderPower{
    ObservableList<Resource> resources = FXCollections.observableArrayList();
    Resource resourceType;

    public SpecialWarehouse(Resource resource) {
        resourceType = resource;
    }

    @Override
    public void activatePower(Board board) {
        board.getWarehouseStore().addWarehouse(resources);
    }

    @Override
    public String toString() {
        if (resources.size()==0) {
            return "│" + StringUtility.center(resourceType + ": × ×",17) + "│\n";
        } else if (resources.size()==1){
            return "│" + StringUtility.center(resourceType + ": " + resourceType + " ×",17) +"│\n";
        } else{
            return "│" + StringUtility.center(resourceType + ": " + resourceType + " " + resourceType,17) + "│\n";
        }
    }
}
