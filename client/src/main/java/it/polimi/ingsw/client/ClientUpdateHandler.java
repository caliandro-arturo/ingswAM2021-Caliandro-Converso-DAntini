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
     * updates the hand of the player with the recently bought resources from the market
     * @param msg
     */
    public void visit(ResourceUpdate msg){
        model.getBoard().setResHand(msg.getResHandUpdate());
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
                    leaderPower = new SpecialWarehouse(null, Utility.mapRepresentationResource.
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
        ArrayList<Resource> resources = new ArrayList<Resource>();
        for (String s : msg.getCostResource()) {
            resources.add(Utility.mapResource.get(s));
        }
        model.updateResource(msg.getCost().stream().mapToInt(i->i).toArray(),resources);
        if (msg.getID() == 0){
            model.getBoard().getStrongbox().addResources(1,Utility.mapResource.
                    get(msg.getProduction()));
        } else if (msg.getID()<=3){
            UtilityProductionAndCost[] prod = model.getBoard().
                    getDevelopmentPlace().getTopCard(msg.getID()).getProduction().getProd();
            for (int i = 0; i<prod.length; i++) {
                if (Utility.isStorable(prod[i].getResource())) {
                    model.getBoard().getStrongbox().addResources(prod[i].getQuantity(),
                            prod[i].getResource());
                }else {
                    for (int k=0; k<prod[i].getQuantity(); i++){
                        model.getBoard().getFaithTrack().addPosition();
                    }
                }
            }
        } else {
            model.getBoard().getStrongbox().addResources(1,Utility.mapResource.
                    get(msg.getProduction()));
            model.getBoard().getFaithTrack().addPosition();
        }
    }

    @Override
    public void visit(BuyCard msg) {
        DevelopmentCard card = model.getDevelopmentGrid().
                getCard(msg.getLevel(), Utility.mapColor.get(msg.getColor()));
        ArrayList<Resource> resources = new ArrayList<Resource>();
        for (int i=0; i<card.getCosts().length; i++){
            resources.add(card.getCosts()[i].getResource());
        }
        model.updateResource(msg.getStores(),resources);
        model.getBoard().getDevelopmentPlace().setDevStack(card,msg.getSpace());
    }

    @Override
    public void visit(DeployRes deployRes) {

    }

    @Override
    public void visit(UseLeader msg) {
        LeaderHand leaderHand = model.getLeaderHand();
        model.getBoard().getLeaderCards().add(leaderHand.getHand().get(msg.getIDCard()-1));
        leaderHand.removeCardFromHand(msg.getIDCard());
    }

    @Override
    public void visit(DiscardLeader msg){
        model.getLeaderHand().removeCardFromHand(msg.getPos());
        model.getBoard().getFaithTrack().addPosition();
    }

    @Override
    public void visit(TakeRes takeRes) {

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
