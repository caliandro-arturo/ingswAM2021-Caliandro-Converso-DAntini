package it.polimi.ingsw.model;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DevelopmentCardTest {

    Player player;
    @BeforeEach
    void setUp(){
        UtilityProductionAndCost cost = new UtilityProductionAndCost(1,Resource.COIN);
        UtilityProductionAndCost prod = new UtilityProductionAndCost(2,Resource.STONE);
        UtilityProductionAndCost[] costtest = new UtilityProductionAndCost[]{cost};
        UtilityProductionAndCost[] prodtest = new UtilityProductionAndCost[]{cost,prod};
        ProductionPower production = new ProductionPower(costtest,prodtest);
        DevelopmentCard developmentCard = new DevelopmentCard(1,1,Color.GREEN,costtest,production);
        PersonalBoard board = new PersonalBoard();
        player = new Player("Test");
        player.setBoard(board);
        player.getBoard().getPersonalDevelopmentSpace()[0].addCard(developmentCard);
        player.getBoard().getPersonalBox().addProdResource(Resource.COIN);
        player.getBoard().getPersonalBox().addProdResource(Resource.COIN);
    }

    @Test
    public void TestDevProduction(){
        player.getBoard().getPersonalDevelopmentSpace()[0].getLevel1Card().getProduction().startProduction(player.getBoard());
        assertEquals(1,player.getBoard().getPersonalBox().getResourceMap().get(Resource.COIN));
    }
}
