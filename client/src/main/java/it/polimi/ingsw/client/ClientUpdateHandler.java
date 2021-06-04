package it.polimi.ingsw.client;

import it.polimi.ingsw.client.model.*;
import it.polimi.ingsw.commonFiles.messages.toClient.GameRejoin;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.InitialResourcesAmount;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.*;
import it.polimi.ingsw.commonFiles.messages.toServer.*;
import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;

import java.util.ArrayList;

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
    public void visit(ResourceUpdate msg) {

    }

    /**
     * Handles the game setting.
     */
    public void visit(SetGame msg) {
        model.setNumOfPlayers(msg.getNumberOfPlayers());
        if (msg.getNumberOfPlayers() == 1) {
            model.setBoards(model.getPlayerUsername());
            model.getBoard().getFaithTrack().setPositionB(1);
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
        model.setLeaderHand(new LeaderHand(leaderCards));
        model.setGameStarted(true);
        setToDo("discardleader", "Discard two leader card by using the command discardleader: <pos>");
        controller.show("hand");
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

    @Override
    public void visit(InitialResourcesAmount msg) {
        if (msg.getResourcesAmount() > 0) {
            model.setResourcesToGet(msg.getResourcesAmount());
            setToDo("getresource", "You have " + msg.getResourcesAmount() + " initial resource" +
                    (msg.getResourcesAmount() > 1 ? "s" : "") + ".");
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

    @Override
    public void visit(NewTurn msg) {
        model.setCurrentPlayerInTheGame(msg.getPlayer());
        deleteToDo("");
        if (!msg.getPlayer().equals(model.getPlayerUsername()))
            setToDo("wait", "Wait your turn...");
    }

    @Override
    public void visit(VaticanReport msg) {
        if (msg.getPlayer().equals(model.getPlayerUsername())) {
            model.getBoard().getFaithTrack().setVaticanMap(msg.getNum(), msg.isPassed());
            showUpdate("vatican", Integer.toString(msg.getNum()), Boolean.toString(msg.isPassed()));
        } else
            model.getBoard(msg.getPlayer()).getFaithTrack().setVaticanMap(msg.getNum(), msg.isPassed());
    }

    @Override
    public void visit(GameRejoin gameRejoin) {
        if (gameRejoin.getPlayer().equals(model.getPlayerUsername()))
            showUpdate("resume");
        else
            showUpdate("newplayer", gameRejoin.getPlayer());
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
        model.getBoard(msg.getPlayer()).addResourcesToHand(model.getMarket().
                marbleArrayToResourceList(msg.getRowOrColumn(),msg.getNum()));
        if (msg.getPlayer().equals(model.getPlayerUsername())) {
            deleteToDo("turnaction");
            showUpdate("marketused");
        }
        model.getMarket().reinsertExtraMarble(msg.getRowOrColumn(),msg.getNum());
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
        for (String s : msg.getCostResource()) {
            resources.add(Utility.mapResource.get(s));
        }
        model.updateResource(msg.getCost().stream().mapToInt(i->i).toArray(),resources);
        if (msg.getID() == 0){
            model.getBoard(msg.getPlayer()).getStrongbox().addResources(1,Utility.mapResource.
                    get(msg.getProduction()));
        } else if (msg.getID()<=3){
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
            model.getBoard(msg.getPlayer()).getStrongbox().addResources(1,Utility.mapResource.
                    get(msg.getProduction()));
            model.getBoard(msg.getPlayer()).getFaithTrack().addPosition();
        }
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
            resources.add(card.getCosts()[i].getResource());
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
        refresh("developmentgrid", msg.getPlayer().equals(model.getPlayerUsername()) ? "board" : "board, " + msg.getPlayer());
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
