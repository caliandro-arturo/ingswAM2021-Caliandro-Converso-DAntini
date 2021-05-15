package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.model.Resource;

import java.util.ArrayList;

/**
 * power tha add a extra Warehouse of a resource large 2 spaces
 */
public class SpecialWarehouse implements LeaderPower {
    private final WarehouseStore extraResource;

    public WarehouseStore getExtraResource() {
        return extraResource;
    }

    public SpecialWarehouse (Resource resource, int quantity){
        extraResource = new WarehouseStore(quantity) {{
            this.setTypeOfResource(resource);
        }
            @Override
            public boolean hasRoomForResource(Resource resource) {
                return this.getTypeOfResource() == resource &&
                        this.getResources().size() < this.getSize();
            }

            @Override
            public Resource takeOutResource() throws IllegalArgumentException {
                if(!getResources().isEmpty()) {
                    Resource resToTake = getResources().get(getResources().size()-1);
                    getResources().remove(resToTake);
                    return resToTake;
                }
                else
                    throw new IllegalArgumentException("this shelf is already empty");
            }
        };
    }

    @Override
    public void getPower(Game game, Player player) {
        game.getViewAdapter().sendMessage(player, "Build extra Warehouse of" );
    }

    @Override
    public void activatePower(Player player) {
        player.getBoard().getStore().add(extraResource);
    }

}
