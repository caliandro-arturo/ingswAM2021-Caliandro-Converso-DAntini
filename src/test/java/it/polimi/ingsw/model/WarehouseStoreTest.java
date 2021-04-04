package it.polimi.ingsw.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseStoreTest {

    private WarehouseStore warehouseStore = new WarehouseStore();


    @Test
    void quantityTest(){
        warehouseStore.setQuantity(3);
        warehouseStore.setResource(Resource.SHIELD);
        warehouseStore.setSize(1);

        assertEquals(3, warehouseStore.getQuantity());
        assertEquals(Resource.SHIELD, warehouseStore.getResource());
        assertEquals(1,warehouseStore.getSize());
    }
}