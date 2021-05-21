package it.polimi.ingsw.client;

import it.polimi.ingsw.client.model.*;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.*;
import it.polimi.ingsw.commonFiles.messages.toServer.SetGame;
import it.polimi.ingsw.commonFiles.messages.toServer.SetNickname;
import it.polimi.ingsw.commonFiles.messages.toServer.ToServerMessageHandler;
import it.polimi.ingsw.commonFiles.messages.toServer.actions.*;
import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;

import java.util.ArrayList;
import java.util.List;

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

    /*messages from server*/

    /**
     * Handles the entry of a new player.
     */
    public void visit(NewPlayer msg) {
        model.setBoards(msg.getName());
        controller.showUpdate(msg.getName() + " has entered the game.");
    }

    /**
     * Handles the exit of a player from the game.
     */
    public void visit(PlayerLeft msg) {
        controller.showUpdate(msg.getName() + " has left the game.");
    }

    @Override
    public void visit(ResourceUpdate msg) {

    }

    /**
     * Handles the game setting.
     */
    public void visit(GameSet msg) {
        model.setNumOfPlayers(msg.getPlayersNum());
        controller.showUpdate("The game has been set: " + model.getNumOfPlayers() + " allowed players.");
    }

    /**
     * Handles the start of the game.
     */
    public void visit(GameStarted msg) {
        controller.showUpdate("The game is starting now...");
    }

    public void visit(LastTurn msg) {
        if (!model.isLast())
            controller.showUpdate("Last turns: " + msg.getReason());
    }

    /**
     * initialize leaderHand in model
     * @param msg message from server with attributes
     */
    @Override
    public void visit(InitLeaderHand msg) {
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        Requirements requirement;
        LeaderPower leaderPower;
        for (int i = 0; i < msg.getVictoryPoints().size(); i++) {
            int victoryPoints = msg.getVictoryPoints().get(i);
            switch (msg.getLeaderPower().get(i)[0]) {
                case "saleOnDevelopment":
                    leaderPower = new SaleOnDevelopment(Utility.mapRepresentationResource.
                            get(msg.getLeaderPower().get(i)[1]));
                    break;
                case "whiteMarbleConversion":
                    leaderPower = new WhiteMarbleConversion(Utility.mapRepresentationResource.
                            get(msg.getLeaderPower().get(i)[1]));
                    break;
                case "specialWarehouse":
                    leaderPower = new SpecialWarehouse(Utility.mapRepresentationResource.
                            get(msg.getLeaderPower().get(i)[1]));
                    break;
                default:
                    leaderPower = new AdditionalProductionPower(Utility.mapRepresentationResource.
                            get(msg.getLeaderPower().get(i)[1]));
                    break;
            }
            if (msg.getRequirements().get(i)[0].equals("resourceCost")) {
                requirement = new ResourceCost(new UtilityProductionAndCost(Integer.parseInt(msg.getRequirements().
                        get(i)[2]), Utility.mapRepresentationResource.get(msg.getRequirements().get(i)[1])));
            } else {
                String[] colors = msg.getRequirements().get(i)[1].split("\\s");
                String[] quantity = msg.getRequirements().get(i)[2].split("\\s");
                ArrayList<Color> colorArrayList = new ArrayList<>();
                ArrayList<Integer> quantityArrayList = new ArrayList<>();
                for (int k = 0; k < colors.length; k++) {
                    colorArrayList.add(Utility.mapColor.get(colors[k]));
                    quantityArrayList.add(Integer.parseInt(quantity[k]));
                }
                if (msg.getRequirements().get(i)[0].equals("colorCost")) {
                    requirement = new ColorCost(colorArrayList, quantityArrayList);
                } else {
                    requirement = new LevelCost(colorArrayList,quantityArrayList,
                            Integer.parseInt(msg.getRequirements().get(i)[3]));
                }
            }
            leaderCards.add(new LeaderCard(victoryPoints,requirement,leaderPower));
        }
        model.setLeaderHand(new LeaderHand(leaderCards));
        model.setGameStarted(true);
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
            int level = msg.getLevels().get(i);
            Color color = Utility.mapColor.get(msg.getColors().get(i));
            int victoryPoints = msg.getVictoryPoints().get(i);
            grid[level-1][Utility.colorPosition.get(color)] = new DevelopmentCard(level,victoryPoints,color,
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
     * set the position of the player
     * @param msg contains the position
     */
    @Override
    public void visit(TablePosition msg) {
        model.setPosition(msg.getPosition());
        controller.showUpdate("You are the player n." + msg.getPosition() + ".");
    }

    /**
     * Increases the position of the cross in the player's faith track
     */
    @Override
    public void visit(IncrementFaithTrackPosition msg) {
        String player = msg.getPlayer();
        model.getBoard(player).getFaithTrack().addPosition();
        refresh("board, " + player);
    }

    @Override
    public void visit(NewTurn msg) {
        if (msg.getPlayer().equals(model.getPlayerUsername()))
            controller.showUpdate("It's your turn.");
        else controller.showUpdate("It's " + msg.getPlayer() + "'s turn.");
    }

    //------------------------------------------------------------------------------------------------------------------
    /*messages from client*/

    /**
     * Sets the nickname of the player.
     */
    @Override
    public void visit(SetNickname msg) {
        model.setPlayerUsername(msg.getNickname());
        controller.showUpdate("Your nickname has been set.");
    }

    /**
     * Updates the market when the player uses it.
     */
    @Override
    public void visit(UseMarket msg) {
        model.getMarket().reinsertExtraMarble(msg.getRowOrColumn(),msg.getNum());
        refresh("market");
    }

    /**
     * Sets the game inserting the number of players in the model.
     */
    @Override
    public void visit(SetGame msg) {
        model.setNumOfPlayers(msg.getNumberOfPlayers());
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
        refresh("board, " + msg.getPlayer());
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
        refresh("developmentgrid", "board, " + msg.getPlayer());
    }

    @Override
    public void visit(DeployRes msg) {
        model.getBoard(msg.getPlayer()).removeResourceFromHand(msg.getResource());
        model.getBoard(msg.getPlayer()).getWarehouseStore().setRes(msg.getResource(), msg.getDepot());
        refresh("board, " + msg.getPlayer());
    }

    @Override
    public void visit(UseLeader msg) {
        LeaderHand leaderHand = model.getLeaderHand();
        model.getBoard().getLeaderCards().add(leaderHand.getHand().get(msg.getIDCard()-1));
        leaderHand.removeCardFromHand(msg.getIDCard());
        refresh("hand", "board");
    }

    @Override
    public void visit(DiscardLeader msg) {
        if (!msg.getPlayer().equals(model.getPlayerUsername()))
            return;
        model.getLeaderHand().removeCardFromHand(msg.getPos());
        refresh("hand");
    }

    @Override
    public void visit(TakeRes msg) {
        //model.getBoard(msg.getPlayer()).getWarehouseStore().removeRes(msg.getResource(), msg.getDepot());
    }

    @Override
    public void visit(GetResource msg) {
        model.getBoard(msg.getPlayer()).addResourceToHand(msg.getResource());
        refresh("board, " + msg.getPlayer());
    }

    @Override
    public void visit(Next msg) {
    }

    /**
     * Sets the current turn phase to the one selected by the player.
     */
    @Override
    public void visit(ChooseTurnPhase msg) {
        model.setCurrentTurnPhase(msg.getTurnPhaseName());
    }
}
