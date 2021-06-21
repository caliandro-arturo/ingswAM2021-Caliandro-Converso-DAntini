package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.Card;
import it.polimi.ingsw.commonFiles.model.ProductionPower;
import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DevelopmentGridTest {
    DevelopmentGrid grid;
    ArrayList<DevelopmentCard> cards = new ArrayList<>();

    @BeforeEach
    void setUp(){
        UtilityProductionAndCost utilitytest = new UtilityProductionAndCost(1, Resource.COIN);
        UtilityProductionAndCost[] utilityarray = new UtilityProductionAndCost[]{utilitytest};
        ProductionPower production = new ProductionPower(utilityarray, utilityarray);
        DevelopmentCard cglv1 = new DevelopmentCard(1, 1,1, Color.GREEN, utilityarray, production);
        DevelopmentCard cglv2 = new DevelopmentCard(1, 2,2, Color.GREEN, utilityarray, production);
        DevelopmentCard cglv3 = new DevelopmentCard(1, 3,3, Color.GREEN, utilityarray, production);
        DevelopmentCard cblv1 = new DevelopmentCard(1, 1,1, Color.BLUE, utilityarray, production);
        DevelopmentCard cblv2 = new DevelopmentCard(1, 2,2, Color.BLUE, utilityarray, production);
        DevelopmentCard cblv3 = new DevelopmentCard(1, 3,3, Color.BLUE, utilityarray, production);
        DevelopmentCard cylv1 = new DevelopmentCard(1, 1,1, Color.YELLOW, utilityarray, production);
        DevelopmentCard cylv2 = new DevelopmentCard(1, 2,2, Color.YELLOW, utilityarray, production);
        DevelopmentCard cylv3 = new DevelopmentCard(1, 3,3, Color.YELLOW, utilityarray, production);
        DevelopmentCard cplv1 = new DevelopmentCard(1, 1,1, Color.PURPLE, utilityarray, production);
        DevelopmentCard cplv2 = new DevelopmentCard(1, 2,2, Color.PURPLE, utilityarray, production);
        DevelopmentCard cplv3 = new DevelopmentCard(1, 3,3, Color.PURPLE, utilityarray, production);
        cards.addAll(Arrays.asList(cglv1, cglv2, cglv3, cblv1,cblv2,cblv3, cylv1,cylv2,cylv3,cplv1,cplv2,cplv3));
        grid = new DevelopmentGrid(cards);
    }

    @Test
    void lorenzoActions(){
        System.out.println(grid);
        UtilityProductionAndCost testcost = new UtilityProductionAndCost(2, Resource.SERF);
        UtilityProductionAndCost testcost1 = new UtilityProductionAndCost(2,Resource.STONE);
        UtilityProductionAndCost[] arrayCost= new UtilityProductionAndCost[]{testcost,testcost1};
        ProductionPower productionPower = new ProductionPower(arrayCost, arrayCost);
        Card card = new Card(1, arrayCost, 1,productionPower );
        grid.lorenzoRemoveAction(Color.BLUE, card, false);
        System.out.println(grid);
        assertEquals(1, grid.getCard(1,Color.BLUE).getID());
        grid.lorenzoRemoveAction(Color.BLUE, card, true);
        assertNull( grid.getCard(1,Color.BLUE));
        assertEquals(1,  grid.getCard(2,Color.BLUE).getID());
        System.out.println(grid);
    }
}