package it.polimi.ingsw.client;

import it.polimi.ingsw.client.model.Color;
import it.polimi.ingsw.client.model.DevelopmentCard;
import it.polimi.ingsw.client.model.DevelopmentGrid;
import it.polimi.ingsw.client.model.Utility;
import it.polimi.ingsw.commonFiles.messages.toServer.BuyCard;
import it.polimi.ingsw.commonFiles.model.Card;
import it.polimi.ingsw.commonFiles.model.ProductionPower;
import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ClientUpdateHandlerTest {

    ClientModel model = new ClientModel();
    ClientController controller;
    ClientUpdateHandler updateHandler;

    @BeforeEach
    void setUp() {
        updateHandler = new ClientUpdateHandler(controller,model){
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
            public void setToDo(String id, String toDo) {
            }

            @Override
            public void deleteToDo(String id) {
            }

            public void showUpdate(String... update) {
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
}