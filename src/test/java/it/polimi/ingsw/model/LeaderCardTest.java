package it.polimi.ingsw.model;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LeaderCardTest {

    private LeaderCard cardPower1;
    private LeaderCard cardPower2;
    private LeaderCard cardPower3;
    private LeaderCard cardPower4;
    private Player player;
    private Player playerFalse;
    public AdditionalProductionPower production;
    @BeforeEach

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
        PersonalBoard board = new PersonalBoard();
        PersonalBoard boardFalse = new PersonalBoard();
        player.setBoard(board);
        playerFalse.setBoard(boardFalse);
        cardPower1 = new LeaderCard(1,type1,power1);
        cardPower2 = new LeaderCard(1,type2,power2);
        cardPower3 = new LeaderCard(1,type3,power3);
        cardPower4 = new LeaderCard(1,type3,power4);
        DevelopmentCard card1 = new DevelopmentCard(1,1,Color.GREEN,null,null);
        DevelopmentCard card1lv2 = new DevelopmentCard(1,2,Color.GREEN,null,null);
        DevelopmentCard card2 = new DevelopmentCard(1,1,Color.BLUE,null,null);
        player.getBoard().getPersonalDevelopmentSpace()[0].addCard(card1);
        player.getBoard().getPersonalDevelopmentSpace()[0].addCard(card1lv2);
        player.getBoard().getPersonalDevelopmentSpace()[1].addCard(card2);
        player.getBoard().addResource(Resource.COIN);
        player.getBoard().getPersonalBox().addProdResource(Resource.COIN);
        player.getBoard().getPersonalBox().addProdResource(Resource.COIN);
        player.getBoard().getPersonalBox().addProdResource(Resource.COIN);
        player.getBoard().getPersonalBox().addProdResource(Resource.COIN);
        playerFalse.getBoard().getPersonalDevelopmentSpace()[0].addCard(card1);
        playerFalse.getBoard().addResource(Resource.STONE);
    }

    @Test
    public void TestCard1() {
        cardPower1.getLeaderPower().activatePower(player);
        assertEquals(player.getBoard().getProductionList().toArray()[1], production);
    }

    @Test
    public void TestPower2(){
        cardPower2.getLeaderPower().activatePower(player);
        assertEquals(player.getWhiteAlt().toArray()[0],Resource.COIN);
    }

    @Test
    public void TestPower3(){
        cardPower3.getLeaderPower().activatePower(player);
        assertTrue(true);
    }

    @Test
    public void TestPower4(){
        cardPower4.getLeaderPower().activatePower(player);
        assertEquals(player.getSale().toArray()[0],Resource.SHIELD);
    }

    @Test
    public void TestRequirements1(){
        assertTrue(cardPower1.getRequirements().checkRequirements(player));
        assertFalse(cardPower1.getRequirements().checkRequirements(playerFalse));
    }

    @Test
    public void TestRequirements2(){
        assertTrue(cardPower2.getRequirements().checkRequirements(player));
        assertFalse(cardPower2.getRequirements().checkRequirements(playerFalse));
    }

    @Test
    public void TestRequirements3(){
        assertTrue(cardPower3.getRequirements().checkRequirements(player));
        assertFalse(cardPower3.getRequirements().checkRequirements(playerFalse));
    }
}
