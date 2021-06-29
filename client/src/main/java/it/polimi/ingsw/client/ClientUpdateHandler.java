package it.polimi.ingsw.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.polimi.ingsw.client.model.*;
import it.polimi.ingsw.commonFiles.messages.toClient.GameRejoin;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.*;
import it.polimi.ingsw.commonFiles.messages.toServer.*;
import it.polimi.ingsw.commonFiles.model.ProductionPower;
import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * Handles model updates and service communications.
 */
public class ClientUpdateHandler implements ToServerMessageHandler, UpdateHandler {
    private final ClientController controller;
    private final ClientModel model;

    public ClientUpdateHandler(ClientController controller, ClientModel model) {
        this.controller = controller;
        this.model = model;
    }

    private void refresh(String... elements) {
        controller.getView().refresh(elements);
    }

    public void setToDo(String id, String toDo) {
        controller.getView().setToDo(id, toDo);
    }

    public void deleteToDo(String id) {
        controller.getView().deleteToDo(id);
    }

    private void showUpdate(String... update) {
        controller.showUpdate(update);
    }

    /*messages from server*/

    /**
     * Handles the entry of a new player.
     */
    public void visit(NewPlayer msg) {
        showUpdate("newplayer", msg.getName());
    }

    /**
     * Handles the exit of a player from the game.
     */
    public void visit(PlayerLeft msg) {
        showUpdate("playerleft", msg.getName());
    }

    @Override
    public void visit(GameRejoin gameRejoin) {
        if (model.getPlayerUsername() == null) {
            model.setPlayerUsername(gameRejoin.getPlayer());
            showUpdate("resume");
        }
        else
            showUpdate("newplayer", gameRejoin.getPlayer());
    }

    @Override
    public void visit(ResourceUpdate msg) {

    }

    /**
     * Handles the game setting.
     */
    public void visit(SetGame msg) {
        model.setNumOfPlayers(msg.getNumberOfPlayers());
        model.setGameSelected(true);
        if (msg.getNumberOfPlayers() == 1) {
            model.setBoards(model.getPlayerUsername());
            model.getBoard().getFaithTrack().setPositionB(0);
        }
        showUpdate("gameset", Integer.toString(model.getNumOfPlayers()));
    }

    /**
     * Handles the start of the game.
     */
    public void visit(GameStarted msg) {
        refresh("");
        showUpdate("gamestarted");
    }

    /**
     * Handles the last turn message.
     * @param msg
     */
    public void visit(LastTurn msg) {
        model.setLast(true);
        showUpdate("lastturns", msg.getReason());
    }

    @Override
    public void visit(UpdateLeaderCards msg) {
        if (!msg.getPlayer().equals(model.getPlayerUsername())) {
            model.getBoard(msg.getPlayer()).getLeaderCards().add(translator(msg.getID(), msg.getVictoryPoints(),
                    msg.getLeaderPower(), msg.getRequirements()));
        }
    }

    /**
     * translator from primitive format to object for the leader card
     *
     * @param ID identifier
     * @param victoryPoints int that display the victoryPoints
     * @param leaderPowerString String that display the leader power to translate in object
     * @param requirementString String that display the requirements to translate in object
     * @return a new leader card made by the parameters
     */
    private LeaderCard translator(int ID, int victoryPoints, String[] leaderPowerString, String[] requirementString) {
        LeaderPower leaderPower;
        Requirements requirement;
        switch (leaderPowerString[0]) {
            case "saleOnDevelopment":
                leaderPower = new SaleOnDevelopment(Utility.mapRepresentationResource.
                        get(leaderPowerString[1]));
                break;
            case "whiteMarbleConversion":
                leaderPower = new WhiteMarbleConversion(Utility.mapRepresentationResource.
                        get(leaderPowerString[1]));
                break;
            case "specialWarehouse":
                leaderPower = new SpecialWarehouse(Utility.mapRepresentationResource.
                        get(leaderPowerString[1]));
                break;
            default:
                leaderPower = new AdditionalProductionPower(Utility.mapRepresentationResource.
                        get(leaderPowerString[1]));
                break;
        }
        if (requirementString[0].equals("resourceCost")) {
            requirement = new ResourceCost(new UtilityProductionAndCost(Integer.parseInt(requirementString[2]),
                    Utility.mapRepresentationResource.get(requirementString[1])));
        } else {
            String[] colors = requirementString[1].split("\\s");
            String[] quantity = requirementString[2].split("\\s");
            ArrayList<Color> colorArrayList = new ArrayList<>();
            ArrayList<Integer> quantityArrayList = new ArrayList<>();
            for (int k = 0; k < colors.length; k++) {
                colorArrayList.add(Utility.mapColor.get(colors[k]));
                quantityArrayList.add(Integer.parseInt(quantity[k]));
            }
            if (requirementString[0].equals("colorCost")) {
                requirement = new ColorCost(colorArrayList, quantityArrayList);
            } else {
                requirement = new LevelCost(colorArrayList, quantityArrayList,
                        Integer.parseInt(requirementString[3]));
            }
        }
        return new LeaderCard(ID, victoryPoints, requirement, leaderPower);
    }

    /**
     * initialize leaderHand in model
     * @param msg message from server with attributes
     */
    @Override
    public void visit(InitLeaderHand msg) {
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        for (int i = 0; i < msg.getVictoryPoints().size(); i++) {
            leaderCards.add(translator(msg.getIDs().get(i), msg.getVictoryPoints().get(i),msg.getLeaderPower().get(i),
                    msg.getRequirements().get(i)));
        }
        model.setLeaderHand(leaderCards);
        if (!model.isGameStarted()) {
            model.setGameStarted(true);
            setToDo("discardleader", "Discard two leader cards.");
            controller.show("hand");
        }
    }

    /**
     * initialize Market in model
     * @param msg message from server with attributes
     */
    @Override
    public void visit(InitMarket msg) {
        Marble[][] marbles = new Marble[3][4];
        Marble marble = new Marble(Utility.mapColor.get(msg.getExtraMarble()));
        for (int i = 0; i < 3; i++){
            for (int k = 0; k < 4; k++){
                marbles[i][k] = new Marble(Utility.mapColor.get(msg.getTray()[i][k]));
            }
        }
        model.setMarket(new Market(marble,marbles));
    }

    /**
     * initialize DevelopmentGrid in model
     * @param msg message from server with attributes
     */
    @Override
    public void visit(InitDevGrid msg) {
        DevelopmentCard[][] grid = new DevelopmentCard[3][4];
        for(int i=0; i<12; i++){
            int ID = msg.getIDs().get(i);
            int level = msg.getLevels().get(i);
            Color color = Utility.mapColor.get(msg.getColors().get(i));
            int victoryPoints = msg.getVictoryPoints().get(i);
            grid[level-1][Utility.colorPosition.get(color)] = new DevelopmentCard(ID, level,victoryPoints,color,
                    msg.getCosts().get(i),msg.getProductions().get(i));
        }
        model.setDevelopmentGrid(new DevelopmentGrid(grid));
    }

    /**
     * initialize board in client
     * @param msg contains users username
     */
    @Override
    public void visit(InitBoards msg) {
        model.setBoards(msg.getUsernames());
    }

    /**
     * Set the position of the player and shows it.
     *
     * @param msg contains the position
     */
    @Override
    public void visit(TablePosition msg) {
        model.setPosition(msg.getPosition());
        controller.getView().showTablePosition(msg.getPosition());
    }

    /**
     * Announces the initial resource amount.
     */
    @Override
    public void visit(InitialResourcesAmount msg) {
        if (msg.getResourcesAmount() > 0) {
            model.setResourcesToGet(msg.getResourcesAmount());
            setToDo("getresource", "You have " + msg.getResourcesAmount() + " initial resource" +
                    (msg.getResourcesAmount() > 1 ? "s" : "") + ".");
            showUpdate("getinitialresource");
        }
    }

    /**
     * Increases the position of the cross in the player's faith track
     */
    @Override
    public void visit(IncrementFaithTrackPosition msg) {
        String player = msg.getPlayer();
        model.getBoard(player).getFaithTrack().addPosition();
        refresh(msg.getPlayer().equals(model.getPlayerUsername()) ? "board" : "board, " + msg.getPlayer());
    }

    /**
     * Announces a new turn.
     */
    @Override
    public void visit(NewTurn msg) {
        model.setCurrentPlayerInTheGame(msg.getPlayer());
        deleteToDo("");
        if (!msg.getPlayer().equals(model.getPlayerUsername()))
            setToDo("wait", "Wait your turn...");
    }

    /**
     * Announces a vatican report.
     */
    @Override
    public void visit(VaticanReport msg) {
        if (msg.getPlayer().equals(model.getPlayerUsername())) {
            model.getBoard().getFaithTrack().setVaticanMap(msg.getNum(), msg.isPassed());
            showUpdate("vatican", Integer.toString(msg.getNum()), Boolean.toString(msg.isPassed()));
        } else
            model.getBoard(msg.getPlayer()).getFaithTrack().setVaticanMap(msg.getNum(), msg.isPassed());
    }

    /**
     * {@link FaithTrack#addLorenzoPositionByOne()}
     */
    @Override
    public void visit(LorenzoPosition msg) {
        model.getBoard().getFaithTrack().addLorenzoPositionByOne();
    }

    /**
     * Initializes the local model to represent the actual game state. This update is received by players reconnected to
     * a game after losing the connection.
     */
    @Override
    public void visit(GameStamp gameStamp) {
        model.setGameSelected(true);
        model.setGameStarted(true);
        String status = gameStamp.getGameStatus();
        JsonElement json = JsonParser.parseString(status);
        JsonObject gameStatus = json.getAsJsonObject();
        if (!gameStatus.get("currentPlayer").isJsonNull()) {
            model.setCurrentPlayerInTheGame(gameStatus.get("currentPlayer").getAsString());
            model.setCurrentTurnPhase(gameStatus.get("currentTurnPhase").getAsString());
            setToDo("wait", "Wait your turn...");
        }
        JsonArray players = gameStatus.getAsJsonArray("players");
        model.setNumOfPlayers(players.size());
        //parsing players
        players.forEach(p -> {
            JsonObject player = p.getAsJsonObject();
            //parsing the nickname
            String playerNick = player.get("nickname").getAsString();
            model.setBoards(playerNick);
            Board playerBoard = model.getBoard(playerNick);
            JsonObject board = player.getAsJsonObject("board");
            //parsing the faith track
            FaithTrack playerTrack = playerBoard.getFaithTrack();
            JsonObject faithTrack = board.getAsJsonObject("faithTrack");
            playerTrack.setPosition(faithTrack.get("position").getAsInt());
            JsonObject vaticanReport = faithTrack.getAsJsonObject("vaticanReport");
            for (Map.Entry<String, JsonElement> j : vaticanReport.entrySet()) {
                playerTrack.getVaticanMap().replace(
                        Integer.parseInt(j.getKey()), j.getValue().isJsonNull() ? null : j.getValue().getAsBoolean());
            }
            //parsing leader cards
            JsonArray leaderCards = board.getAsJsonArray("leaderCards");
            for (JsonElement cardElement : leaderCards) {
                JsonObject card = cardElement.getAsJsonObject();
                int cardId = card.get("ID").getAsInt();
                int victoryPoints = card.get("victoryPoints").getAsInt();
                JsonArray requirementsArray = card.getAsJsonArray("requirements");
                ArrayList<String> requirements = new ArrayList<>();
                for (JsonElement j : requirementsArray) {
                    requirements.add(j.getAsString());
                }
                JsonArray powerArray = card.getAsJsonArray("leaderPower");
                ArrayList<String> power = new ArrayList<>();
                for (JsonElement j : powerArray) {
                    power.add(j.getAsString());
                }
                LeaderCard toAdd = translator(cardId, victoryPoints, power.toArray(new String[0]), requirements.toArray(new String[0]));
                playerBoard.getLeaderCards().add(toAdd);
                toAdd.getPower().activatePower(playerBoard);
            }
            //parsing warehouse stores (including the leader power one)
            JsonArray warehouseStores = board.getAsJsonArray("warehouseStores");
            int warehousePosition = 0;
            for (JsonElement j : warehouseStores) {
                JsonObject jsonStore = j.getAsJsonObject();
                ObservableList<Resource> store;
                try {
                    store = playerBoard.getWarehouseStore().getSpecificStore(warehousePosition);
                } catch (IndexOutOfBoundsException e) {
                    store = FXCollections.observableArrayList();
                    playerBoard.getWarehouseStore().getRes().set(warehousePosition, store);
                }
                if (!jsonStore.get("resourceType").isJsonNull()) {
                    String resource = jsonStore.get("resourceType").getAsString();
                    Resource trueResource = Utility.mapResource.get(resource.toLowerCase());
                    for (int i = 0; i < jsonStore.get("quantity").getAsInt(); i++)
                        store.add(trueResource);
                }
                warehousePosition++;
            }
            //parsing the strongbox
            JsonObject strongBox = board.getAsJsonObject("strongBox");
            Strongbox box = playerBoard.getStrongbox();
            for (Map.Entry<String, JsonElement> entry :
                    strongBox.entrySet()) {
                Resource trueResource = Utility.mapResource.get(entry.getKey().toLowerCase());
                box.addResources(entry.getValue().getAsInt(), trueResource);
            }
            //parsing development cards
            JsonArray devSpace = board.getAsJsonArray("developmentPlaces");
            DevelopmentPlace developmentPlace = playerBoard.getDevelopmentPlace();
            int devPlaceIndex = 0;
            for (JsonElement j :
                    devSpace) {
                JsonArray devPlace = j.getAsJsonArray();
                devPlaceIndex++;
                for (JsonElement k :
                        devPlace) {
                    JsonObject devCard = k.getAsJsonObject();
                    int ID = devCard.get("ID").getAsInt();
                    Color color = Color.valueOf(devCard.get("color").getAsString());
                    UtilityProductionAndCost[] cost = utilityProductionAndCostsParser(devCard.getAsJsonArray("cost"));
                    JsonObject production = devCard.getAsJsonObject("production");
                    ProductionPower productionPower = new ProductionPower(
                            utilityProductionAndCostsParser(production.getAsJsonArray("cost")),
                            utilityProductionAndCostsParser(production.getAsJsonArray("products"))
                    );
                    int victoryPoints = devCard.get("victoryPoints").getAsInt();
                    int level = devCard.get("level").getAsInt();
                    DevelopmentCard card = new DevelopmentCard(ID, level, victoryPoints, color, cost, productionPower);
                    developmentPlace.setDevStack(card, devPlaceIndex);
                }
            }
            //parsing the resource hand
            JsonArray resHand = board.getAsJsonArray("resourceHand");
            for (JsonElement j :
                    resHand) {
                Resource res = Utility.mapResource.get(j.getAsString().toLowerCase());
                playerBoard.addResourceToHand(res);
            }
            //parsing white conversion resources
            JsonArray whiteAlt = player.getAsJsonArray("whiteAlt");
            for (JsonElement j :
                    whiteAlt) {
                playerBoard.setPowerWhiteMarble(Utility.mapResource.get(j.getAsString().toLowerCase()));
            }
            //parsing sale resources
            JsonArray sale = player.getAsJsonArray("sale");
            for (JsonElement j :
                    sale) {
                playerBoard.setPowerSale(Utility.mapResource.get(j.getAsString().toLowerCase()));
            }
        });
    }

    private UtilityProductionAndCost[] utilityProductionAndCostsParser(JsonArray upsRawArray) {
        ArrayList<UtilityProductionAndCost> upc = new ArrayList<>();
        for (JsonElement j :
                upsRawArray) {
            JsonObject upcRawUnit = j.getAsJsonObject();
            UtilityProductionAndCost upcUnit = new UtilityProductionAndCost(
                    upcRawUnit.get("quantity").getAsInt(),
                    Utility.mapResource.get(upcRawUnit.get("resource").getAsString().toLowerCase())
            );
            upc.add(upcUnit);
        }
        return upc.toArray(new UtilityProductionAndCost[0]);
    }

    @Override
    public void visit(TimeUp timeUp) {
        showUpdate("timeup", Boolean.toString(timeUp.isTimeEffectivelyUp()));
    }

    @Override
    public void visit(AskWhiteMarble msg) {
        model.setWhiteMarbleQuantity(msg.getWhiteMarblesToChoose());
        controller.getView().show("whitemarble");
    }

    @Override
    public void visit(LorenzoPick msg) {
        switch (msg.getAction()) {
            case "TWOPOSITIONS" -> {
                showUpdate("lorenzoaction", "twopositions");
            }
            case "ONEPOSITIONRESET" -> {
                showUpdate("lorenzoaction", "onepositionreset");
            }
            default -> {
                Color color = Utility.mapColor.get(msg.getAction().substring(3));
                model.getDevelopmentGrid().lorenzoRemoveAction(color, msg.getCard(), msg.isTakenCardsOfDifferentLevel());
                showUpdate("lorenzoaction", msg.getAction().toLowerCase());
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    /*messages from client*/

    /**
     * Sets the nickname of the player.
     */
    @Override
    public void visit(SetNickname msg) {
        model.setPlayerUsername(msg.getNickname());
        showUpdate("nicknameset");
    }

    @Override
    public void visit(GamesList gamesList) {
        controller.getView().showGamesList(gamesList.getLobbiesName(), gamesList.getLobbiesCurrentConnectedClientsNumber(), gamesList.getLobbiesMaxPlayersNum());
    }

    @Override
    public void visit(JoinGame joinGame) {
        model.setGameSelected(true);
        showUpdate("gamejoined");
    }

    /**
     * Updates the market when the player uses it.
     */
    @Override
    public void visit(UseMarket msg) {
        if (model.getBoard(msg.getPlayer()).getPowerWhite().size() == 1) {
            model.getBoard(msg.getPlayer()).addResourcesToHand(model.getMarket().
                    marbleArrayToResourceList(model.getBoard(msg.getPlayer()).getPowerWhite().get(0),
                            msg.getRowOrColumn(), msg.getNum()));
        } else
            model.getBoard(msg.getPlayer()).addResourcesToHand(model.getMarket().
                    marbleArrayToResourceList(msg.getRowOrColumn(), msg.getNum()));
        if (msg.getPlayer().equals(model.getPlayerUsername())) {
            deleteToDo("turnaction");
            showUpdate("marketused");
        }
        model.getMarket().reinsertExtraMarble(msg.getRowOrColumn(),msg.getNum());
        model.setIsFinished(true);
        refresh("market");
    }

    @Override
    public void visit(ChooseWhiteMarble msg) {
        //not used: instead of this message, the server sends a GetResource one.
    }

    @Override
    public void visit(Back back) {

    }

    @Override
    public void visit(StartProduction msg) {
        ArrayList<Resource> resources = new ArrayList<>();
        if (msg.getID() == 0){
            for (String s : msg.getCostResource()) {
                resources.add(Utility.mapResource.get(s.toLowerCase()));
            }
            model.updateResource(msg.getCost().stream().mapToInt(i->i).toArray(),resources);
            model.getBoard(msg.getPlayer()).getStrongbox().addResources(1,Utility.mapResource.
                    get(msg.getProduction().toLowerCase()));
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
                    get(msg.getProduction().toLowerCase()));
            model.getBoard(msg.getPlayer()).getFaithTrack().addPosition();
        }
        model.setIsFinished(true);
        model.getBoard().setIsProductionAlreadyUsed(true,msg.getID());
        refresh(msg.getPlayer().equals(model.getPlayerUsername()) ? "board" : "board, " + msg.getPlayer());
    }

    @Override
    public void visit(DiscardRes discardRes) {
        model.getBoard(discardRes.getPlayer()).removeResourceFromHand(discardRes.getResource());
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
        if (msg.getPlayer().equals(model.getCurrentPlayerInTheGame())) {
            model.getBoard().setIsProductionAlreadyUsed(true, msg.getSpace());
        }
        model.setIsFinished(true);
        refresh("developmentgrid", msg.getPlayer().equals(model.getPlayerUsername()) ? "board" : "board, " + msg.getPlayer());
        if (msg.getPlayer().equals(model.getPlayerUsername()))
            showUpdate("cardbought");
    }

    @Override
    public void visit(DeployRes msg) {
        model.getBoard(msg.getPlayer()).removeResourceFromHand(msg.getResource());
        model.getBoard(msg.getPlayer()).getWarehouseStore().setRes(msg.getResource(), msg.getDepot());
        refresh(msg.getPlayer().equals(model.getPlayerUsername()) ? "board" : "board, " + msg.getPlayer());
    }

    @Override
    public void visit(UseLeader msg) {
        if(msg.getPlayer().equals(model.getPlayerUsername())) {
            LeaderHand leaderHand = model.getLeaderHand();
            leaderHand.getHand().get(msg.getIDCard() - 1).getPower().activatePower(model.getBoard());
            model.getBoard().getLeaderCards().add(leaderHand.getHand().get(msg.getIDCard() - 1));
            leaderHand.removeCardFromHand(msg.getIDCard());
        }
        refresh("hand", "board");
    }

    @Override
    public void visit(DiscardLeader msg) {
        if (!msg.getPlayer().equals(model.getPlayerUsername()))
            return;
        model.getLeaderHand().removeCardFromHand(msg.getPos());
        if (model.getLeaderHand().getHand().size() == 2)
            deleteToDo("discardleader");
        refresh("hand");
    }

    @Override
    public void visit(TakeRes msg) {
        Resource res = model.getBoard(msg.getPlayer()).getWarehouseStore().removeRes(msg.getDepot());
        model.getBoard(msg.getPlayer()).addResourceToHand(res);
        refresh(msg.getPlayer().equals(model.getPlayerUsername()) ? "board" : "board, " + msg.getPlayer());
        if (model.getPlayerUsername().equals(msg.getPlayer()))
            showUpdate("resourcetaken");
    }

    @Override
    public void visit(GetResource msg) {
        model.getBoard(msg.getPlayer()).addResourceToHand(msg.getResource());
        if (msg.getPlayer().equals(model.getPlayerUsername())) {
            model.decrementResourcesToGet();
            if (model.getResourcesToGet() == 0) deleteToDo("getresource");
        }
        refresh(msg.getPlayer().equals(model.getPlayerUsername()) ? "board" : "board, " + msg.getPlayer());
        if (model.getPlayerUsername().equals(msg.getPlayer()))
            showUpdate("gotresource", msg.getResource().name().toLowerCase());
    }

    @Override
    public void visit(Next msg) {
    }

    @Override
    public void visit(ChooseTurnPhase chooseTurnPhase) {
        model.setIsFinished(false);
    }

    /**
     * Sets the current turn phase to the next.
     */
    @Override
    public void visit(TurnPhaseAnnouncement msg) {
        model.setCurrentTurnPhase(msg.getTurnPhaseName());
        if (model.getPlayerUsername().equals(model.getCurrentPlayerInTheGame()))
            setToDo("turnaction", msg.getTurnPhaseToDo());
    }
}
