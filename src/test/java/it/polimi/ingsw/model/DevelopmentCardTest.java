package it.polimi.ingsw.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DevelopmentCardTest {

    UtilityProductionAndCost cost = new UtilityProductionAndCost(1,Resource.COIN);
    UtilityProductionAndCost[] costtest = new UtilityProductionAndCost[]{cost};
    ProductionPower production = new ProductionPower(costtest,costtest);
    DevelopmentCard developmentCard = new DevelopmentCard(1,1,Color.GREEN,costtest,production);
    PersonalBoard board = new PersonalBoard();
    LeaderCard[] leaderCards = new LeaderCard[2];
    Player player = new Player("Test",board,leaderCards);

}
