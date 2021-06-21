package it.polimi.ingsw.client;

import it.polimi.ingsw.client.model.*;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.LorenzoPick;
import it.polimi.ingsw.commonFiles.messages.toServer.BuyCard;
import it.polimi.ingsw.commonFiles.messages.toServer.StartProduction;
import it.polimi.ingsw.commonFiles.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ClientUpdateHandlerTest {

    ClientModel model = new ClientModel();
    ClientController controller;
    ClientUpdateHandler updateHandler;

    @BeforeEach
    void setUp() {
        updateHandler = new ClientUpdateHandler(controller,model){
            @Override
            public void visit(StartProduction msg) {
                ArrayList<Resource> resources = new ArrayList<>();
                if (msg.getID() == 0){
                    for (String s : msg.getCostResource()) {
                        resources.add(Utility.mapResource.get(s));
                    }
                    model.updateResource(msg.getCost().stream().mapToInt(i->i).toArray(),resources);
                    model.getBoard(msg.getPlayer()).getStrongbox().addResources(1,Utility.mapResource.
                            get(msg.getProduction()));
                } else if (msg.getID()<=3){
                    for (UtilityProductionAndCost cost: model.getBoard(msg.getPlayer()).
                            getDevelopmentPlace().getTopCard(msg.getID()).getProduction().getCost()){
                        for (int i=0; i < cost.getQuantity(); i++){
                            resources.add(cost.getResource());
                        }
                    }
                    model.updateResource(msg.getCost().stream().mapToInt(i->i).toArray(),resources);
                    UtilityProductionAndCost[] prod = model.getBoard(msg.getPlayer()).
                            getDevelopmentPlace().getTopCard(msg.getID()).getProduction().getProd();
                    for (int i = 0; i<prod.length; i++) {
                        if (Utility.isStorable(prod[i].getResource())) {
                            model.getBoard(msg.getPlayer()).getStrongbox().addResources(prod[i].getQuantity(),
                                    prod[i].getResource());
                        }else {
                            for (int k=0; k<prod[i].getQuantity(); i++){
                                model.getBoard(msg.getPlayer()).getFaithTrack().addPosition();
                            }
                        }
                    }
                } else {
                    model.updateResource(new int[]{msg.getCost().get(0)}, new ArrayList<>(Arrays.asList(model.getBoard().getPowerProd().get(msg.getID()-4))));
                    model.getBoard(msg.getPlayer()).getStrongbox().addResources(1,Utility.mapResource.
                            get(msg.getProduction()));
                    model.getBoard(msg.getPlayer()).getFaithTrack().addPosition();
                }
            }

            @Override
            public void visit(BuyCard msg) {
                DevelopmentCard card = model.getDevelopmentGrid().
                        getCard(msg.getLevel(), Utility.mapColor.get(msg.getColor()));
                ArrayList<Resource> resources = new ArrayList<>();
                for (int i=0; i<card.getCosts().length; i++){
                    for (int k=0; k<card.getCosts()[i].getQuantity(); k++) {
                        resources.add(card.getCosts()[i].getResource());
                    }
                }
                model.updateResource(msg.getStores(),resources);
                model.getBoard(msg.getPlayer()).getDevelopmentPlace().setDevStack(card,msg.getSpace());
                DevelopmentCard newCard;
                try {
                    newCard = new DevelopmentCard(
                            msg.getID(),
                            msg.getLevel(),
                            msg.getNewCardVictoryPoints(),
                            Utility.mapColor.get(msg.getColor()),
                            msg.getNewCardCost(),
                            msg.getProductions()
                    );
                } catch (NullPointerException e) {
                    newCard = null;
                }
                model.getDevelopmentGrid().setCard(msg.getLevel(), msg.getColor(), newCard);
            }

            @Override
            public void visit(LorenzoPick msg) {
                switch (msg.getAction()) {
                    case "TWOPOSITIONS" -> {

                    }
                    case "ONEPOSITIONRESET" -> {

                    }
                    default -> {
                        Color color = Utility.mapColor.get(msg.getAction().substring(3));
                        model.getDevelopmentGrid().lorenzoRemoveAction(color, msg.getCard(), msg.isTakenCardsOfDifferentLevel());
                    }
                }
            }


            @Override
            public void setToDo(String id, String toDo) {
            }

            @Override
            public void deleteToDo(String id) {
            }

        };
        model.setPlayerUsername("test");
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
        ArrayList<DevelopmentCard> cards = new ArrayList<>(Arrays.asList(cglv1, cglv2, cglv3, cblv1, cblv2, cblv3, cylv1, cylv2, cylv3, cplv1, cplv2, cplv3));
        model.setDevelopmentGrid(new DevelopmentGrid(cards));
        model.setBoards("test");
    }

    @Test
    void testVisitBuyCard() {
        model.getBoard().getWarehouseStore().setRes(Resource.COIN,1);
        BuyCard msg = new BuyCard(1,"GREEN", 1, new ArrayList<>(1));
        msg.setPlayer("test");
        UtilityProductionAndCost unit = new UtilityProductionAndCost(1,Resource.SERF);
        UtilityProductionAndCost[] cost = new UtilityProductionAndCost[]{unit};
        ProductionPower productionPower = new ProductionPower(cost,cost);
        Card card = new Card(2,cost, 4,productionPower);
        msg.setNewCard(card);
        updateHandler.visit(msg);
        assertEquals(2,model.getDevelopmentGrid().getCard(1,Color.GREEN).getID());
    }

    @Test
    void testVisitProduction(){
        model.getBoard().getWarehouseStore().setRes(Resource.COIN,1);
        UtilityProductionAndCost unit = new UtilityProductionAndCost(1,Resource.COIN);
        UtilityProductionAndCost unit1 = new UtilityProductionAndCost(1,Resource.SERF);
        UtilityProductionAndCost[] cost = new UtilityProductionAndCost[]{unit};
        UtilityProductionAndCost[] prod = new UtilityProductionAndCost[]{unit1};
        ProductionPower productionPower = new ProductionPower(cost,prod);
        model.getBoard().getDevelopmentPlace().setDevStack(new DevelopmentCard(1,1,1,Color.BLUE,cost,productionPower),1);
        StartProduction msg = new StartProduction(1,new ArrayList<>(Arrays.asList(1)));
        msg.setPlayer("test");
        updateHandler.visit(msg);
        System.out.println(model.getBoard().getWarehouseStore());
        assertEquals(1,model.getBoard().getStrongbox().getResources()[Utility.mapStrongbox.get(Resource.SERF)]);
        msg = new StartProduction(0,new ArrayList<Integer>(Arrays.asList(1,0)),"coin", new String[]{"stone", "serf"});
        msg.setPlayer("test");
        model.getBoard().getWarehouseStore().setRes(Resource.STONE,1);
        updateHandler.visit(msg);
        assertEquals(0, model.getBoard().getStrongbox().getResources()[Utility.mapStrongbox.get(Resource.SERF)]);
        assertEquals(1, model.getBoard().getStrongbox().getResources()[Utility.mapStrongbox.get(Resource.COIN)]);
        msg = new StartProduction(4,new ArrayList<Integer>(Arrays.asList(0)),"shield");
        msg.setPlayer("test");
        model.getBoard().setLeaderCards(new LeaderCard(1,1,null,new AdditionalProductionPower(Resource.COIN)));
        model.getBoard().getLeaderCards().get(0).getPower().activatePower(model.getBoard());
        updateHandler.visit(msg);
        assertEquals(0, model.getBoard().getStrongbox().getResources()[Utility.mapStrongbox.get(Resource.COIN)]);
    }

    @Test
    void testVisitLorenzo(){
        UtilityProductionAndCost unit = new UtilityProductionAndCost(1,Resource.SERF);
        UtilityProductionAndCost[] array = new UtilityProductionAndCost[]{unit};
        Production productionPower = new ProductionPower(array,array);
        Card card = new Card(3,array,1,productionPower);
        LorenzoPick msg = new LorenzoPick("DELBLUE",true,card);
        updateHandler.visit(msg);
        assertNull(model.getDevelopmentGrid().getCard(1,Color.BLUE));
        System.out.println(model.getDevelopmentGrid());
    }
}