package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.ProductionPower;
import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;
import org.junit.jupiter.api.Test;

class DevelopmentCardTest {

    UtilityProductionAndCost utilityProductionAndCost =
            new UtilityProductionAndCost(1, Resource.SHIELD);
    UtilityProductionAndCost[] costs = new UtilityProductionAndCost[]{utilityProductionAndCost};
    DevelopmentCard developmentCard = new DevelopmentCard(1,1,2, Color.GREEN, costs, new ProductionPower(costs, costs));

    @Test
    public void printTest(){
        System.out.println(developmentCard);

    }
}