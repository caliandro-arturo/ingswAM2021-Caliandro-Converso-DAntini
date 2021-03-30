package it.polimi.ingsw.model;

public class SpecialWarehouse implements LeaderPower{
    private WarehouseStore extraResource;

    public WarehouseStore getExtraResource() {
        return extraResource;
    }

    public SpecialWarehouse (Resource resource, int quantity){
        extraResource = new WarehouseStore();
        extraResource.setResource(resource);
        extraResource.setQuantity(quantity);
    }
}
