package it.polimi.ingsw.client;

import it.polimi.ingsw.commonFiles.messages.toServer.Next;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * View element of the MVC architectural pattern applied to the client.
 */
public abstract class View {
    private ClientController controller;
    private ClientModel model;

    public void setController(ClientController controller) {
        this.controller = controller;
        model = controller.getModel();
    }

    public ClientController getController() {
        return controller;
    }

    public ClientModel getModel() {
        return model;
    }

    /**
     * Reads and processes user commands.
     * <p>
     * <b>Commands</b>
     * <p>
     * A command is structured as follows:<br> {@code identifier<: arg1, arg2, ..., argn>}.<br> Each command has a
     * specific number of arguments (0 to n), and this verifies the correctness of both identifier and number of
     * arguments.
     */
    public void process(String input) {
        String[] commandSlice = input.split("\\s*:\\s*", 2);
        switch (commandSlice[0].toLowerCase()) {
            //each case is an identifier
            case "setnick" -> setNick(commandSlice);
            case "joingame" -> joinGame(commandSlice);
            case "setgame" -> setGame(commandSlice);
            case "back" -> back();
            case "choose" -> choose(commandSlice);
            case "activateprod" -> activateProduction(commandSlice);
            case "usemarket" -> useMarket(commandSlice);
            case "choosewhite" -> chooseWhite(commandSlice);
            case "buydevcard" -> buyDevCard(commandSlice);
            case "useleader" -> useLeader(commandSlice);
            case "discardleader" -> discardLeader(commandSlice);
            case "deployres" -> deployRes(commandSlice);
            case "takeres" -> takeRes(commandSlice);
            case "discardres" -> discardRes(commandSlice);
            case "getres" -> getRes(commandSlice);
            case "show" -> showHandler(commandSlice);
            case "next" -> getController().sendMessage(new Next());
            default -> System.err.println("Invalid command, retry.");
        }
    }

    /**
     * Shows elements to the user.
     *
     * @param element the element to show
     */
    public abstract void show(String element);

    /**
     * Shows an error message
     *
     * @param error the error to show
     */
    public abstract void showError(String error);

    /**
     * Shows an update.
     *
     * @param update the update to show
     */
    public void showUpdate(String... update) {
        switch (update[0]) {
            case "nicknameset" -> showNicknameSet();
            case "gamejoined" -> showGameJoined();
            case "resume" -> showResume();
            case "gameset" -> showGameSet(Integer.parseInt(update[1]));
            case "waitgamestart" -> showWaitGameStart();
            case "gamestarted" -> showGameStarted();
            case "newplayer" -> showNewPlayer(update[1]);
            case "playerleft" -> showPlayerLeft(update[1]);
            case "getinitialresource" -> showGetInitialResource();
            case "gotresource" -> showGotResource(update[1]);
            case "resourcetaken" -> showResourceTaken();
            case "cardbought" -> showCardBought();
            case "marketused" -> showMarketUsed();
            case "vatican" -> showVaticanReport(Integer.parseInt(update[1]), Boolean.parseBoolean(update[2]));
            case "lastturns" -> showLastTurns(update[1]);
            case "lorenzoaction" -> showLorenzoAction(update[1]);
            case "connectionlost" -> showConnectionLost();
            case "timeup" -> showTimeUp(Boolean.parseBoolean(update[1]));
            case "whitemarble" -> showWhiteMarble();
        }
    }

    /**
     * Shows the white marble choice request.
     */
    public abstract void showWhiteMarble();

    /**
     * Shows the Lorenzo action
     *
     * @param announcement the Lorenzo action
     */
    public abstract void showLorenzoAction(String announcement);

    /**
     * Shows the update of the nickname set.
     */
    public abstract void showNicknameSet();

    /**
     * Shows the update of the game joined.
     */
    public abstract void showGameJoined();

    /**
     * Shows the update of the resume game.
     */
    public abstract void showResume();

    /**
     * Shows the update of the game set.
     *
     * @param playersNum the number of players
     */
    public abstract void showGameSet(int playersNum);

    /**
     * Shows a waiting message.
     */
    public abstract void showWaitGameStart();

    /**
     * Shows the start of the game.
     */
    public abstract void showGameStarted();

    /**
     * Shows the update of a new player.
     *
     * @param playerNick the nickname of the new player
     */
    public abstract void showNewPlayer(String playerNick);

    /**
     * Shows the update of a player that has lost the connection.
     *
     * @param playerNick the nickname of the player that has lost the connection
     */
    public abstract void showPlayerLeft(String playerNick);

    /**
     * Shows initial resources request.
     */
    public abstract void showGetInitialResource();

    /**
     * Shows the update of received resource.
     *
     * @param resource the received resource
     */
    public abstract void showGotResource(String resource);

    /**
     * Shows the update of a taken resource.
     */
    public abstract void showResourceTaken();

    /**
     * Shows the update of a bought card.
     */
    public abstract void showCardBought();

    /**
     * Shows the update of a used market.
     */
    public abstract void showMarketUsed();

    /**
     * Shows the result of a vatican report.
     *
     * @param reportNum the number of the vatican report
     * @param isPassed  the result of the vatican report
     */
    public abstract void showVaticanReport(int reportNum, boolean isPassed);

    /**
     * Shows the update of last turn, providing a reason.
     *
     * @param reason the reason why the game is in the final rounds
     */
    public abstract void showLastTurns(String reason);

    /**
     * Shows the lost of the connection.
     */
    public abstract void showConnectionLost();

    /**
     * Shows the pause message.
     *
     * @param timeIsUp if true, the pause is set; the pause is removed otherwise
     */
    public abstract void showTimeUp(boolean timeIsUp);

    /**
     * Refresh the visual representation of an element if it's currently shown.
     *
     * @param elements the element to refresh if shown
     */
    public abstract void refresh(String... elements);

    /**
     * Reminds the player what he must do in a certain moment.
     *
     * @param toDo the action to do
     */
    public abstract void setToDo(String id, String toDo);

    /**
     * Handles elements showing.
     */
    public abstract void showHandler(String[] commandSlice);

    /**
     * Processes the SetNick command.
     */
    public abstract void setNick(String[] commandSlice);

    /**
     * Processes the JoinGame command.
     */
    public abstract void joinGame(String[] commandSlice);

    /**
     * Processes the SetGame command.
     */
    public abstract void setGame(String[] commandSlice);

    /**
     * Processes the Choose command.
     */
    public abstract void choose(String[] commandSlice);

    /**
     * Processes the ActivateProduction command.
     */
    public abstract void activateProduction(String[] commandSlice);

    /**
     * Processes the BuyCard command.
     */
    public abstract void buyDevCard(String[] commandSlice);

    /**
     * Processes the UseMarket command.
     */
    public abstract void useMarket(String[] commandSlice);

    /**
     * Processes the ChooseWhite command.
     */
    public abstract void chooseWhite(String[] commandSlice);

    /**
     * Processes the UseLeader command.
     */
    public abstract void useLeader(String[] commandSlice);

    /**
     * Processes the DeployRes command.
     */
    public abstract void deployRes(String[] commandSlice);

    /**
     * Processes the DiscardLeader command.
     */
    public abstract void discardLeader(String[] commandSlice);

    /**
     * Processes the TakeRes command.
     */
    public abstract void takeRes(String[] commandSlice);

    /**
     * Processes the GetRes command.
     */
    public abstract void getRes(String[] commandSlice);

    /**
     * Prints head containing information about the current state of the game.
     */
    public abstract void printHead();

    /**
     * Deletes a toDo entry.
     */
    public abstract void deleteToDo(String id);

    /**
     * Processes the Back command.
     */
    public abstract void back();

    /**
     * Processes the DiscardRes command.
     */
    public abstract void discardRes(String[] commandSlice);

    /**
     * Displays final scores and ranking.
     *
     * @param scores  the player scores
     * @param ranking ranking with points of other players
     */
    public abstract void displayEndingScore(int[] scores, LinkedHashMap<String, Integer> ranking);

    /**
     * Shows the available games list.
     *
     * @param lobbiesName                          list containing names of the games
     * @param lobbiesCurrentConnectedClientsNumber number of clients currently connected to games
     * @param lobbiesMaxPlayersNum                 max number of players allowed in the games
     */
    public abstract void showGamesList(List<String> lobbiesName, List<Integer> lobbiesCurrentConnectedClientsNumber, List<Integer> lobbiesMaxPlayersNum);
}
