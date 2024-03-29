package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.model.ProductionPower;
import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DevelopmentGridTest {

    DevelopmentCard cglv1;
    DevelopmentGrid d1;
    @BeforeEach
    void setUp() {
        UtilityProductionAndCost utilitytest = new UtilityProductionAndCost(1, Resource.COIN);
        UtilityProductionAndCost[] utilityarray = new UtilityProductionAndCost[]{utilitytest};
        ProductionPower production = new ProductionPower(utilityarray, utilityarray);
        cglv1 = new DevelopmentCard(1, 0,1, Color.GREEN, utilityarray, production);
        DevelopmentCard cglv1type2 = new DevelopmentCard(1, 1,1, Color.GREEN, utilityarray, production);
        DevelopmentCard cglv2 = new DevelopmentCard(1, 0,2, Color.GREEN, utilityarray, production);
        DevelopmentCard cglv3 = new DevelopmentCard(1, 0,3, Color.GREEN, utilityarray, production);
        DevelopmentCard cblv1 = new DevelopmentCard(1, 0,1, Color.BLUE, utilityarray, production);
        DevelopmentCard cblv2 = new DevelopmentCard(1, 0,2, Color.BLUE, utilityarray, production);
        DevelopmentCard cblv2type2 =  new DevelopmentCard(1, 1,2, Color.BLUE, utilityarray, production);
        DevelopmentCard cblv3 = new DevelopmentCard(1, 0,3, Color.BLUE, utilityarray, production);
        DevelopmentCard cylv1 = new DevelopmentCard(1, 0,1, Color.YELLOW, utilityarray, production);
        DevelopmentCard cylv2 = new DevelopmentCard(1, 0,2, Color.YELLOW, utilityarray, production);
        DevelopmentCard cylv3 = new DevelopmentCard(1, 0,3, Color.YELLOW, utilityarray, production);
        DevelopmentCard cplv1 = new DevelopmentCard(1, 0,1, Color.PURPLE, utilityarray, production);
        DevelopmentCard cplv2 = new DevelopmentCard(1, 0,2, Color.PURPLE, utilityarray, production);
        DevelopmentCard cplv3 = new DevelopmentCard(1, 0,3, Color.PURPLE, utilityarray, production);
        DevelopmentCard[] totcard = new DevelopmentCard[]{cglv1,cglv1type2, cglv2, cglv3, cblv1, cblv2,cblv2type2, cblv3, cylv1, cylv2, cylv3, cplv1, cplv2, cplv3};
        d1 = new DevelopmentGrid(totcard);
    }

    @Test
    public void testBuy(){
        if (d1.getDeck(1,Color.GREEN).getTopCard().equals(cglv1))
            assertEquals(cglv1,d1.buyCard(Color.GREEN,1));
    }

    @Test
    public void testRemove(){
        assertTrue(d1.lorenzoUpdate(Color.BLUE));
        d1.removeCard(Color.BLUE);
        assertTrue(d1.getDeck(1,Color.BLUE).getDeck().empty());
    }

    @Test
    public void testLorenzoUpdate(){
        assertFalse(d1.lorenzoUpdate(Color.GREEN));
        d1.removeCard(Color.GREEN);
        assertNull(d1.lorenzoCardsUpdate(Color.GREEN,false));
    }
}
