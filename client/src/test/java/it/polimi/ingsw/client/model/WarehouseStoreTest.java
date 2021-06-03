package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.Resource;
import org.junit.jupiter.api.Test;

class WarehouseStoreTest {
    private final WarehouseStore warehouseStore = new WarehouseStore();


    @Test
    public void printTest(){

        warehouseStore.setRes(Resource.SHIELD, 1);
        warehouseStore.setRes(Resource.SERF, 2);
        warehouseStore.setRes(Resource.COIN,3);
        System.out.println(warehouseStore);
    }

}