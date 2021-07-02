package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.model.Resource;

/**
 * Leader power that adds an extra Warehouse of 2 spaces for a specific resource.
 */
public class SpecialWarehouse implements LeaderPower {
    private final WarehouseStore extraResource;

    public WarehouseStore getExtraResource() {
        return extraResource;
    }

    public SpecialWarehouse(Resource resource, int quantity) {
        extraResource = new WarehouseStore(quantity) {
            {
                this.setTypeOfResource(resource);
            }

            @Override
            public boolean hasRoomForResource(Resource resource) {
                return this.getTypeOfResource() == resource &&
                        this.getResources().size() < this.getSize();
            }

            @Override
            public Resource takeOutResource() throws IllegalArgumentException {
                if (!getResources().isEmpty()) {
                    Resource resToTake = getResources().get(getResources().size() - 1);
                    getResources().remove(resToTake);
                    return resToTake;
                } else
                    throw new IllegalArgumentException("this shelf is already empty");
            }
        };
    }

    @Override
    public void activatePower(Player player) {
        player.getBoard().getStore().add(extraResource);
    }

    @Override
    public String[] identifier() {
        return new String[]{"specialWarehouse", extraResource.getTypeOfResource().toString()};
    }
}
