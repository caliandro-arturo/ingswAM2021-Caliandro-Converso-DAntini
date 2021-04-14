package it.polimi.ingsw.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * This class is the creator of the right instance of the game; its purpose is also to read JSON files with the cards
 * of the game.
 */
public class GameCreator {
    private final Market market = new Market();
    private final Stack<LeaderCard> leaderDeck;
    private final DevelopmentGrid developmentGrid;
    private HashMap<String, Color> mapColor = new HashMap<String ,Color>(){{
        put("GREEN" , Color.GREEN);
        put("BLUE" , Color.BLUE);
        put("PURPLE" , Color.PURPLE);
        put("YELLOW" , Color.YELLOW);
    }};
    private HashMap<String, Resource> mapResource = new HashMap<String, Resource>(){{
        put("faith", Resource.FAITH);
        put("shield", Resource.SHIELD);
        put("serf", Resource.SERF);
        put("coin", Resource.COIN);
        put("stone", Resource.STONE);
    }};


    public GameCreator() {
        Gson gson = new Gson();
        DevelopmentCard[] developmentGrid = new DevelopmentCard[48];
        LeaderCard[] leaderDeck= new LeaderCard[16];
        try {
            Reader reader = Files.newBufferedReader(Paths.get("devCard.json"));
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            JsonArray jsonArray = jsonObject.get("DevelopmentCard").getAsJsonArray();
            for (int i = 0; i<48;i++){
                UtilityProductionAndCost[] costs = utilityCreator(jsonArray.get(i).getAsJsonObject(), "cost");
                ProductionPower production = new ProductionPower(utilityCreator(jsonArray.get(i).getAsJsonObject().
                        get("productionPower").getAsJsonObject(), "cost"), utilityCreator(jsonArray.
                        get(i).getAsJsonObject().get("productionPower").getAsJsonObject(), "production"));
                developmentGrid[i] = new DevelopmentCard(jsonArray.get(i).getAsJsonObject().get("victoryPoints").getAsInt(),
                        jsonArray.get(i).getAsJsonObject().get("level").getAsInt(),mapColor.get(jsonArray.get(i).
                        getAsJsonObject().get("color").getAsString()),costs,production);
            }
            reader = Files.newBufferedReader(Paths.get("ledCard.json"));
            jsonObject = gson.fromJson(reader, JsonObject.class);
            jsonArray = jsonObject.get("LeaderCard").getAsJsonArray();
            for (int i=0; i<4;i++){
                leaderDeck[i] = new LeaderCard(jsonArray.get(i).getAsJsonObject().get("victoryPoints").getAsInt(),
                        colorCostJSON(jsonArray.get(i).getAsJsonObject()), new SaleOnDevelopment(powerCreator(jsonArray.
                        get(i).getAsJsonObject(),"saleOnDev")));
            }
            for (int i=4; i<8; i++){
                leaderDeck[i] = new LeaderCard(jsonArray.get(i).getAsJsonObject().get("victoryPoints").getAsInt(),
                        resourceCostJSON(jsonArray.get(i).getAsJsonObject()), new SpecialWarehouse(powerCreator(jsonArray.
                        get(i).getAsJsonObject(),"specialW"),2));
            }
            for (int i=8; i<12;i++){
                leaderDeck[i] = new LeaderCard(jsonArray.get(i).getAsJsonObject().get("victoryPoints").getAsInt(),
                        colorCostJSON(jsonArray.get(i).getAsJsonObject()), new WhiteMarbleConversion(powerCreator(jsonArray.
                        get(i).getAsJsonObject(),"whiteMarble")));
            }
            for (int i=12; i<16;i++) {
                leaderDeck[i] = new LeaderCard(jsonArray.get(i).getAsJsonObject().get("victoryPoints").getAsInt(),
                        levelCostJSON(jsonArray.get(i).getAsJsonObject()), new AdditionalProductionPower(powerCreator(
                                jsonArray.get(i).getAsJsonObject(),"specialProduction")));
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }

        this.leaderDeck = new Stack<LeaderCard>() {{
            addAll(Arrays.asList(leaderDeck));
        }};
        this.developmentGrid = new DevelopmentGrid(developmentGrid);
    }

    private UtilityProductionAndCost[] utilityCreator(JsonObject jsonObject, String jsonstring){
        String resource;
        UtilityProductionAndCost[] value = new UtilityProductionAndCost[jsonObject.get(jsonstring)
                .getAsJsonArray().size()];
        for (int i =0; i<jsonObject.get(jsonstring).getAsJsonArray().size(); i++){
            resource = jsonObject.get(jsonstring).getAsJsonArray().get(i)
                    .getAsJsonObject().get("resource").getAsString();
            value[i] = new UtilityProductionAndCost(jsonObject.get(jsonstring).getAsJsonArray().
                    get(i).getAsJsonObject().get("quantity").getAsInt(),mapResource.get(resource));
        }
        return value;
    }

    private Resource powerCreator(JsonObject jsonObject, String jsonstring){
        return mapResource.get(jsonObject.get(jsonstring).getAsJsonObject().get("resource").getAsString());
    }

    private ColorCost colorCostJSON(JsonObject jsonObject){
        Color[] colors = {Color.PURPLE,Color.BLUE,Color.GREEN,Color.YELLOW};
        Integer[] integers = new Integer[4];
        integers[0] = jsonObject.get("colorCost").getAsJsonObject().get("purple").getAsInt();
        integers[1] = jsonObject.get("colorCost").getAsJsonObject().get("blue").getAsInt();
        integers[2] = jsonObject.get("colorCost").getAsJsonObject().get("green").getAsInt();
        integers[3] = jsonObject.get("colorCost").getAsJsonObject().get("yellow").getAsInt();
        return new ColorCost(colors,integers);
    }

    private ResourceCost resourceCostJSON(JsonObject jsonObject){
        UtilityProductionAndCost value = new UtilityProductionAndCost(jsonObject.get("resourceCost").
                getAsJsonObject().get("quantity").getAsInt(),mapResource.get(jsonObject.get("resourceCost").
                getAsJsonObject().get("resource").getAsString()));
        return new ResourceCost(value);
    }


    private LevelCost levelCostJSON(JsonObject jsonObject){
        Color[] colors = {mapColor.get(jsonObject.get("levelCost").getAsJsonObject().get("color").getAsString())};
        Integer[] integers = {jsonObject.get("levelCost").getAsJsonObject().get("quantity").getAsInt()};
        int level = jsonObject.get("levelCost").getAsJsonObject().get("level").getAsInt();
        return new LevelCost(colors,integers,level);
    }

    public Game create(Player player, int playersNum) {
        if(playersNum == 1)
            return new SinglePlayerGame(player, playersNum, market, leaderDeck, developmentGrid);
        else if(playersNum >= 2 && playersNum <= 4)
            return new MultiplayerGame(player, playersNum, market, leaderDeck, developmentGrid);
        return null; //TODO error handling
    }
}
