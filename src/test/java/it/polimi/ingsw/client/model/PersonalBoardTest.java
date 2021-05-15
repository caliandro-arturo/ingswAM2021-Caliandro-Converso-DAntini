package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.ProductionPower;
import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;
import it.polimi.ingsw.server.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

class PersonalBoardTest {
    private final WarehouseStore warehouseStore = new WarehouseStore();
    private final HashMap<Integer, Boolean> vaticanMap = new HashMap<Integer, Boolean>() {{
        put(1, true);
        put(2, false);
        put(3, true);
    }};
    UtilityProductionAndCost utilityProductionAndCost =
            new UtilityProductionAndCost(1, Resource.COIN);
    UtilityProductionAndCost[] costs = new UtilityProductionAndCost[]{utilityProductionAndCost};
    ProductionPower productionPower = new ProductionPower(costs, costs);
    DevelopmentCard developmentCard = new DevelopmentCard(2, 3, Color.GREEN,costs,productionPower );
    DevelopmentPlace developmentPlace = new DevelopmentPlace();


    private final Strongbox strongbox = new Strongbox();
    private final FaithTrack track = new FaithTrack(1, vaticanMap);
    private final Board personalBoard = new Board(developmentPlace, track, strongbox, warehouseStore);

    LeaderCard card1;
    LeaderCard card2;
    LeaderHand leaderHand ;
    public AdditionalProductionPower production ;

    @Test
    public void printTest(){
        track.setPositionB(3);
        production = new AdditionalProductionPower(Resource.COIN);
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        ArrayList<Color> colors = new ArrayList<>();
        ArrayList<Integer> quantity = new ArrayList<>();
        colors.add(Color.BLUE);
        quantity.add(2);
        Requirements type1 = new ColorCost(colors,quantity);
        LeaderPower power1 = production;
        card1 = new LeaderCard(1, type1,power1);
        card2 = new LeaderCard(1, type1,power1);
        developmentPlace.setDevStack(developmentCard,1);
        developmentPlace.setDevStack(developmentCard,1);
        developmentPlace.setDevStack(developmentCard,1);
        developmentPlace.setDevStack(developmentCard,2);
        developmentPlace.setDevStack(developmentCard,2);
        developmentPlace.setDevStack(developmentCard,2);
        developmentPlace.setDevStack(developmentCard,3);
        developmentPlace.setDevStack(developmentCard,3);
        developmentPlace.setDevStack(developmentCard,3);
        strongbox.addResources(123,Resource.COIN);
        strongbox.addResources(77,Resource.SERF);
        strongbox.addResources(56,Resource.SHIELD);
        strongbox.addResources(34,Resource.STONE);
        warehouseStore.setRes(Resource.SHIELD, 1);
        warehouseStore.setRes(Resource.SERF, 2);
        warehouseStore.setRes(Resource.COIN,3);
        personalBoard.setLeaderCards(card1);
        personalBoard.setLeaderCards(card2);
        warehouseStore.removeRes(Resource.COIN);
        warehouseStore.removeRes(Resource.SERF);
        warehouseStore.removeRes(Resource.SHIELD);

        System.out.println(personalBoard); }
}