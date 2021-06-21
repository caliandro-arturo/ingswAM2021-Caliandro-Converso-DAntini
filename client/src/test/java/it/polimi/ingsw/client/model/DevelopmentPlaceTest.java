package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.ProductionPower;
import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;
import org.junit.jupiter.api.Test;

class DevelopmentPlaceTest {
    UtilityProductionAndCost utilityProductionAndCost =
            new UtilityProductionAndCost(1, Resource.SHIELD);
    UtilityProductionAndCost[] costs = new UtilityProductionAndCost[]{utilityProductionAndCost};
    DevelopmentCard developmentCard = new DevelopmentCard(2, 1, 1, Color.GREEN, costs, new ProductionPower(costs,costs));
    DevelopmentPlace developmentPlace = new DevelopmentPlace();

    @Test
    public void printTest(){

        developmentPlace.setDevStack(developmentCard,1);
        developmentPlace.setDevStack(developmentCard,1);
        developmentPlace.setDevStack(developmentCard,1);
        developmentPlace.setDevStack(developmentCard,2);
        developmentPlace.setDevStack(developmentCard,2);
        developmentPlace.setDevStack(developmentCard,2);
        developmentPlace.setDevStack(developmentCard,3);
        developmentPlace.setDevStack(developmentCard,3);
        developmentPlace.setDevStack(developmentCard,3);


        System.out.println(developmentPlace);
    }
}