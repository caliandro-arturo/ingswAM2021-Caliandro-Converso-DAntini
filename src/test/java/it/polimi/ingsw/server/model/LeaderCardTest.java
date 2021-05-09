package it.polimi.ingsw.server.model;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LeaderCardTest {

    private LeaderCard cardPower1;
    private LeaderCard cardPower2;
    private LeaderCard cardPower3;
    private LeaderCard cardPower4;
    private Player player;
    private Player playerFalse;
    public AdditionalProductionPower production;

    @BeforeAll
    void setUp(){
        UtilityProductionAndCost cost1 = new UtilityProductionAndCost(5,Resource.COIN);
        Color[] colors = new Color[]{Color.GREEN,Color.BLUE};
        Color[] colors1 = new Color[]{Color.GREEN};
        Integer[] quantity = new Integer[]{2,1};
        Integer[] quantity1 = new Integer[]{1};
        Requirements type1 = new ColorCost(colors,quantity);
        Requirements type2 = new ResourceCost(cost1);
        Requirements type3 = new LevelCost(colors1,quantity1,2);
        production = new AdditionalProductionPower(Resource.SERF);
        LeaderPower power1 = production;
        LeaderPower power2 = new WhiteMarbleConversion(Resource.COIN);
        LeaderPower power3 = new SpecialWarehouse(Resource.COIN,2);
        LeaderPower power4 = new SaleOnDevelopment(Resource.SHIELD);
        player = new Player("Test");
        playerFalse = new Player("TestFalse");
        Board board = new Board();
        Board boardFalse = new Board();
        player.setBoard(board);
        playerFalse.setBoard(boardFalse);
        cardPower1 = new LeaderCard(1,type1,power1);
        cardPower2 = new LeaderCard(1,type2,power2);
        cardPower3 = new LeaderCard(1,type3,power3);
        cardPower4 = new LeaderCard(1,type3,power4);
        DevelopmentCard card1 = new DevelopmentCard(1,1,Color.GREEN,null,null);
        DevelopmentCard card1lv2 = new DevelopmentCard(1,2,Color.GREEN,null,null);
        DevelopmentCard card2 = new DevelopmentCard(1,1,Color.BLUE,null,null);
        player.getBoard().getDevelopmentSpace()[0].getDevelopmentCards().push(card1);
        player.getBoard().getDevelopmentSpace()[0].getDevelopmentCards().push(card1lv2);
        player.getBoard().getDevelopmentSpace()[1].getDevelopmentCards().push(card2);
        player.getBoard().getStore().get(0).addResource(Resource.COIN);
        player.getBoard().getStrongbox().addProdResource(Resource.COIN);
        player.getBoard().getStrongbox().addProdResource(Resource.COIN);
        player.getBoard().getStrongbox().addProdResource(Resource.COIN);
        player.getBoard().getStrongbox().addProdResource(Resource.COIN);
        playerFalse.getBoard().getDevelopmentSpace()[0].getDevelopmentCards().push(card1);
        playerFalse.getBoard().addResource(Resource.STONE);
    }

    @Test
    public void TestCardsPower() {
        cardPower1.getLeaderPower().activatePower(player);
        assertEquals(player.getBoard().getProductionList().toArray()[1], production);
        cardPower2.getLeaderPower().activatePower(player);
        assertEquals(player.getWhiteAlt().toArray()[0],Resource.COIN);
        cardPower3.getLeaderPower().activatePower(player);
        assertTrue(player.getBoard().getStore().size() > 3);
        assertEquals(player.getBoard().getStore().get(3).getTypeOfResource(),Resource.COIN);
        cardPower4.getLeaderPower().activatePower(player);
        assertEquals(player.getSale().toArray()[0],Resource.SHIELD);
    }

    @Test
    public void TestRequirements(){
        assertTrue(cardPower1.getRequirements().checkRequirements(player));
        assertFalse(cardPower1.getRequirements().checkRequirements(playerFalse));
        assertTrue(cardPower2.getRequirements().checkRequirements(player));
        assertFalse(cardPower2.getRequirements().checkRequirements(playerFalse));
        assertTrue(cardPower3.getRequirements().checkRequirements(player));
        assertFalse(cardPower3.getRequirements().checkRequirements(playerFalse));
    }

}
