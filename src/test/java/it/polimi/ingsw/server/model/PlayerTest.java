package it.polimi.ingsw.server.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PlayerTest {
    private Player pippo= new Player("pippo");
    DevelopmentCard card1;
    DevelopmentGrid d1;
    UtilityProductionAndCost[] utilityarray;
    Game testGame;
    @BeforeAll
    void setUp(){
        pippo.getBoard().getStore().get(0).addResource(Resource.COIN);
        pippo.getBoard().getPersonalBox().addProdResource(Resource.COIN);
        pippo.getBoard().getPersonalBox().addProdResource(Resource.COIN);
        pippo.getBoard().getPersonalBox().addProdResource(Resource.STONE);
        pippo.getBoard().getPersonalBox().addProdResource(Resource.SHIELD);
        Production production = new AdditionalProductionPower(Resource.STONE);
        pippo.getBoard().getProductionList().add(production);
        UtilityProductionAndCost cost = new UtilityProductionAndCost(1,Resource.SHIELD);
        UtilityProductionAndCost prod = new UtilityProductionAndCost(1,Resource.SERF);
        UtilityProductionAndCost[] costs = new UtilityProductionAndCost[]{cost};
        UtilityProductionAndCost[] prods = new UtilityProductionAndCost[]{prod};
        ProductionPower production1 = new ProductionPower(costs,prods);
        DevelopmentCard card = new DevelopmentCard(1,1,Color.BLUE,costs,production1);
        pippo.getBoard().addCard(card,1);
        card1 = new DevelopmentCard(2,2,Color.BLUE,costs,production1);
        UtilityProductionAndCost utilitytest = new UtilityProductionAndCost(1, Resource.COIN);
        utilityarray = new UtilityProductionAndCost[]{utilitytest};
        ProductionPower production2 = new ProductionPower(utilityarray, utilityarray);
        DevelopmentCard cglv1 = new DevelopmentCard(1, 1, Color.GREEN, utilityarray, production2);
        DevelopmentCard cglv2 = new DevelopmentCard(1, 2, Color.GREEN, utilityarray, production2);
        DevelopmentCard cglv3 = new DevelopmentCard(1, 3, Color.GREEN, utilityarray, production2);
        DevelopmentCard cblv1 = new DevelopmentCard(1, 1, Color.BLUE, utilityarray, production2);
        DevelopmentCard cblv3 = new DevelopmentCard(1, 3, Color.BLUE, utilityarray, production2);
        DevelopmentCard cylv1 = new DevelopmentCard(1, 1, Color.YELLOW, utilityarray, production2);
        DevelopmentCard cylv2 = new DevelopmentCard(1, 2, Color.YELLOW, utilityarray, production2);
        DevelopmentCard cylv3 = new DevelopmentCard(1, 3, Color.YELLOW, utilityarray, production2);
        DevelopmentCard cplv1 = new DevelopmentCard(1, 1, Color.PURPLE, utilityarray, production2);
        DevelopmentCard cplv2 = new DevelopmentCard(1, 2, Color.PURPLE, utilityarray, production2);
        DevelopmentCard cplv3 = new DevelopmentCard(1, 3, Color.PURPLE, utilityarray, production2);
        DevelopmentCard[] totcard = new DevelopmentCard[]{cglv1, cglv2, cglv3, cblv1, card1, cblv3, cylv1, cylv2, cylv3, cplv1, cplv2, cplv3};
        d1 = new DevelopmentGrid(totcard);
        testGame = new MultiplayerGame(pippo,4,null,null,d1);
        pippo.getBoard().addActiveLeader(new LeaderCard(3,
                new ColorCost(new Color[]{Color.BLUE},new Integer[]{2}),
                new AdditionalProductionPower(Resource.COIN)));
    }

    @Test
    void productionTest(){
        testGame.setCurrentPlayer(pippo);
        TurnPhase prod = new ActivateProdPhase(testGame);
        testGame.setCurrentTurnPhase(prod);
        prod.start();
        String[] cmdstringcost = new String[]{"coin","coin"};
        Resource production = Resource.STONE;
        int[] cmdbox = new int[]{1,0};
        pippo.startBoardProduction(cmdbox,cmdstringcost,production);
        assertEquals(1,pippo.getBoard().getPersonalBox().getProductionBox().size());
        cmdbox = new int[]{0};
        pippo.getBoard().getPersonalBox().addProdResource(Resource.SHIELD);
        pippo.startDevProduction(1,cmdbox);
        assertEquals(2,pippo.getBoard().getPersonalBox().getProductionBox().size());
        pippo.startLeaderProduction(0,Resource.SHIELD,1);
        assertEquals(3,pippo.getBoard().getPersonalBox().getProductionBox().size());
        prod.nextTurnPhase();
    }

    @Test
    void buyCardTest(){
        testGame.setCurrentPlayer(pippo);
        TurnPhase buyCard = new BuyDevCardPhase(testGame);
        testGame.setCurrentTurnPhase(buyCard);
        buyCard.start();
        pippo.buyDevelopmentCard(card1,new int[]{0},1);
        assertEquals(card1,pippo.getBoard().getPersonalDevelopmentSpace()[0].getDevelopmentCards().peek());
    }

    @Test
    void victoryPointsTest() {
        pippo.getBoard().getPersonalPath().increasePosition();
        pippo.getBoard().getPersonalPath().increasePosition();
        pippo.getBoard().getPersonalPath().increasePosition();
        ProductionPower productionPower = new ProductionPower(utilityarray,utilityarray);
        pippo.getBoard().addCard(new DevelopmentCard(2,1,Color.BLUE,null, productionPower), 2);
        //2 VP in [1]
        //pippo.getBoard().addActiveLeader(new LeaderCard(3,
        //        new ColorCost(new Color[]{Color.BLUE},new Integer[]{2}),
        //        new AdditionalProductionPower(Resource.COIN)))
        //3 VP in [2]
        pippo.getBoard().getPersonalPath().increasePosition();
        pippo.getBoard().getPersonalPath().increasePosition();
        pippo.getBoard().getPersonalPath().increasePosition();
        pippo.getBoard().getPersonalPath().increasePosition();
        pippo.getBoard().getPersonalPath().increasePosition();
        //2 VP in [3]
        pippo.getBoard().getPersonalBox().addProdResource(Resource.COIN);
        pippo.getBoard().getPersonalBox().addProdResource(Resource.COIN);
        pippo.getBoard().getPersonalBox().addProdResource(Resource.COIN);
        pippo.getBoard().getPersonalBox().addProdResource(Resource.COIN);
        pippo.getBoard().getPersonalBox().addProdResource(Resource.COIN);
        pippo.getBoard().getPersonalBox().addProdResource(Resource.COIN);
        pippo.getBoard().getPersonalBox().addProdResource(Resource.STONE);
        pippo.getBoard().getPersonalBox().addProdResource(Resource.STONE);
        pippo.getBoard().getPersonalBox().addProdResource(Resource.STONE);
        pippo.getBoard().getPersonalBox().addProdResource(Resource.STONE);
        // 1 VP in [4]
        int[] vic = pippo.getVictoryPoints();
        assertEquals(4, vic[0]);
        assertEquals(5, vic[1]);
        assertEquals(3, vic[2]);
        assertEquals(2, vic[3]);
        assertEquals(2, vic[4]);
    }

    @Test
    public void RefundTest(){
        Player pluto = new Player("pluto");
        try {
            testGame.addPlayer(pluto);
        }catch (Exception e){}
        pluto.getBoard().getPersonalBox().addProdResource(Resource.COIN);
        testGame.setCurrentPlayer(pluto);
        UtilityProductionAndCost coin = new UtilityProductionAndCost(2,Resource.COIN);
        TurnPhase prod = new ActivateProdPhase(testGame);
        testGame.setCurrentTurnPhase(prod);
        UtilityProductionAndCost[] costs = new UtilityProductionAndCost[]{coin};
        int[] box = {0,0};
        pluto.getBoard().addCard(new DevelopmentCard(1,
                1,Color.BLUE, null ,new ProductionPower(costs,costs)),1);
        try {
            pluto.startDevProduction(1, box);
        } catch (IllegalArgumentException e) {
            pluto.refundCost(pluto.getProcessedResources(), box, costs);
        }
        assertEquals(1,pluto.getBoard().getPersonalBox().getResourceMap().get(Resource.COIN));
    }

    @Test
    void LeaderTest(){
        Player minnie = new Player("minnie");
        try {
            testGame.addPlayer(minnie);
        }catch (Exception e){}
        testGame.setCurrentPlayer(minnie);
        LeaderCard leaderCard1 = new LeaderCard(1,
                new ResourceCost(new UtilityProductionAndCost(1,Resource.COIN)),
                new WhiteMarbleConversion(Resource.COIN));
        LeaderCard leaderCard2 = new LeaderCard(1,
                new ResourceCost(new UtilityProductionAndCost(1,Resource.COIN)),
                new WhiteMarbleConversion(Resource.SHIELD));
        minnie.addLeaderCards(leaderCard1);
        minnie.addLeaderCards(leaderCard2);
        minnie.getBoard().getPersonalBox().addProdResource(Resource.COIN);
        testGame.setCurrentTurnPhase(new UseLeaderPhase(testGame,true));
        minnie.useLeader(leaderCard1);
        minnie.discardLeaderCard(leaderCard2);
        assertEquals(Resource.COIN,minnie.getWhiteAlt().get(0));
        assertEquals(2,minnie.getBoard().getPersonalPath().getPosition());
    }

    @Test
    public void invalidCmdTest(){
        Player topolino = new Player("topolino");
        try {
            testGame.addPlayer(topolino);
        }catch (Exception e){}
        testGame.setCurrentPlayer(topolino);
        TurnPhase prod = new ActivateProdPhase(testGame);
        testGame.setCurrentTurnPhase(prod);
        topolino.getBoard().getPersonalBox().addProdResource(Resource.COIN);
        topolino.getBoard().getStore().get(0).addResource(Resource.COIN);
        topolino.getBoard().getStore().get(1).addResource(Resource.STONE);
        assertThrows(IllegalArgumentException.class,() -> {
            topolino.startDevProduction(1,new int[]{0});
            topolino.startLeaderProduction(0,Resource.SHIELD,1);
            topolino.startBoardProduction(new int[]{0,1},new String[]{"shield","faith"},Resource.SHIELD);
        });
        topolino.addLeaderCards(new LeaderCard(1,
                new ResourceCost(new UtilityProductionAndCost(0,Resource.COIN)),
                new AdditionalProductionPower(Resource.STONE)));
        topolino.getBoard().getPersonalDevelopmentSpace()[0].getDevelopmentCards().push(new DevelopmentCard(
                1,1,Color.BLUE,null,new ProductionPower(
                        new UtilityProductionAndCost[]{new UtilityProductionAndCost(1,Resource.COIN)},
                new UtilityProductionAndCost[]{new UtilityProductionAndCost(1,Resource.COIN)})));
        assertThrows(IllegalArgumentException.class,()->{
            topolino.startBoardProduction(new int[]{0,0}, new String[]{"coin","coin"},Resource.SHIELD);
            topolino.startLeaderProduction(0,Resource.SHIELD,1);
            topolino.startDevProduction(1,new int[]{0});
        });
    }
}