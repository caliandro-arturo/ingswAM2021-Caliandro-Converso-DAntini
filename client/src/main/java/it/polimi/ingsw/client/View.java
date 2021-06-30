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
     * A command is structured as follows:<br>
     * {@code identifier<: arg1, arg2, ..., argn>}.<br>
     * Each command has a specific number of arguments (0 to n), and this
     * verifies the correctness of both identifier and number of arguments.
     */
    public void process(String input){
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
            case "creategame" -> showCreateGame();
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

    public abstract void showWhiteMarble();

    public abstract void showLorenzoAction(String announcement);

    public abstract void showNicknameSet();

    public abstract void showGameJoined();

    public abstract void showResume();

    public abstract void showCreateGame();

    public abstract void showGameSet(int playersNum);

    public abstract void showWaitGameStart();

    public abstract void showGameStarted();

    public abstract void showNewPlayer(String playerNick);

    public abstract void showPlayerLeft(String playerNick);

    public abstract void showGetInitialResource();

    public abstract void showGotResource(String resource);

    public abstract void showResourceTaken();

    public abstract void showCardBought();

    public abstract void showMarketUsed();

    public abstract void showVaticanReport(int reportNum, boolean isPassed);

    public abstract void showLastTurns(String reason);

    public abstract void showConnectionLost();

    public abstract void showTimeUp(boolean timeIsUp);

    /**
     * Shows the player position on the table.
     *
     * @param position the position of the player
     */
    public abstract void showTablePosition(int position);

    /**
     * Refresh the visual representation of an element if it's currently shown.
     *
     * @param elements the element to refresh if shown
     */
    public abstract void refresh(String... elements);

    /**
     * Reminds the player what he must do in a certain moment.
     * @param toDo the action to do
     */
    public abstract void setToDo(String id, String toDo);

    public abstract void showHandler(String[] commandSlice);

    public abstract void setNick(String[] commandSlice);

    public abstract void joinGame(String[] commandSlice);

    public abstract void setGame(String[] commandSlice);

    public abstract void choose(String[] commandSlice);

    public abstract void activateProduction(String[] commandSlice);

    public abstract void buyDevCard(String[] commandSlice);

    public abstract void useMarket(String[] commandSlice);

    public abstract void chooseWhite(String[] commandSlice);

    public abstract void useLeader(String[] commandSlice);

    public abstract void deployRes(String[] commandSlice);

    public abstract void discardLeader(String[] commandSlice);

    public abstract void takeRes(String[] commandSlice);

    public abstract void getRes(String[] commandSlice);

    public abstract void printHead();

    public abstract void deleteToDo(String id);

    public abstract void back();

    public abstract void discardRes(String[] commandSlice);

    public abstract void displayEndingScore(int[] scores, LinkedHashMap<String, Integer> ranking);

    public abstract void showGamesList(List<String> lobbiesName, List<Integer> lobbiesCurrentConnectedClientsNumber, List<Integer> lobbiesMaxPlayersNum);
}
