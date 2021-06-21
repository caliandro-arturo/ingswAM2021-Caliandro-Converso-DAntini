package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LeaderCardTest {

    private LeaderCard cardPower1;
    private LeaderCard cardPower2;
    private LeaderCard cardPower3;
    private LeaderCard cardPower4;
    private Player player;
    private Player playerFalse;
    public AdditionalProductionPower production;
    Requirements type1;
    ColorCost cost;
    Requirements type2;
    Requirements type3;
    LeaderPower power1;
    LeaderPower power2;
    LeaderPower power3;
    LeaderPower power4;

    @BeforeAll
    void setUp(){
        UtilityProductionAndCost cost1 = new UtilityProductionAndCost(5, Resource.COIN);
        Color[] colors = new Color[]{Color.GREEN,Color.BLUE};
        Color[] colors1 = new Color[]{Color.GREEN};
        Integer[] quantity = new Integer[]{2,1};
        Integer[] quantity1 = new Integer[]{1};
        cost = new ColorCost(colors,quantity);
        type1 = cost;
        type2 = new ResourceCost(cost1);
        type3 = new LevelCost(colors1,quantity1,2);
        production = new AdditionalProductionPower(Resource.SERF);
        power1 = production;
        power2 = new WhiteMarbleConversion(Resource.COIN);
        power3 = new SpecialWarehouse(Resource.COIN,2);
        power4 = new SaleOnDevelopment(Resource.SHIELD);
        player = new Player("Test");
        playerFalse = new Player("TestFalse");
        Board board = new Board(player);
        Board boardFalse = new Board(playerFalse);
        player.setBoard(board);
        playerFalse.setBoard(boardFalse);
        cardPower1 = new LeaderCard(0,1,type1,power1);
        cardPower2 = new LeaderCard(0,1,type2,power2);
        cardPower3 = new LeaderCard(0,1,type3,power3);
        cardPower4 = new LeaderCard(0,1,type3,power4);
        DevelopmentCard card1 = new DevelopmentCard(1,0,1,Color.GREEN,null,null);
        DevelopmentCard card1lv2 = new DevelopmentCard(1,0,2,Color.GREEN,null,null);
        DevelopmentCard card2 = new DevelopmentCard(1,0,1,Color.BLUE,null,null);
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
    public void testCardsPower() {
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
    public void testRequirements(){
        assertTrue(cardPower1.getRequirements().checkRequirements(player));
        assertFalse(cardPower1.getRequirements().checkRequirements(playerFalse));
        assertTrue(cardPower2.getRequirements().checkRequirements(player));
        assertFalse(cardPower2.getRequirements().checkRequirements(playerFalse));
        assertTrue(cardPower3.getRequirements().checkRequirements(player));
        assertFalse(cardPower3.getRequirements().checkRequirements(playerFalse));
    }

    @Test
    public void testIdentifier(){
        assertEquals(2, cost.getCost().get(Color.GREEN));
        assertEquals(1, cost.getCost().get(Color.BLUE));
        String[] arguments = type1.identifier();
        String[] quantity = arguments[2].split("\\s");
        assertEquals(2,quantity.length);
        String[] colors = arguments[1].split("\\s");
        if(colors[0].equals("GREEN")) {
            assertEquals("BLUE", colors[1]);
            assertEquals("1",quantity[1]);
            assertEquals("2",quantity[0]);
        } else {
            assertEquals("GREEN", colors[1]);
            assertEquals("1",quantity[0]);
            assertEquals("2",quantity[1]);
        }
    }
}
