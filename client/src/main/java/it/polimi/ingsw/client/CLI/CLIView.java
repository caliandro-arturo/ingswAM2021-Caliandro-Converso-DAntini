package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.model.Board;
import it.polimi.ingsw.client.model.Utility;
import it.polimi.ingsw.commonFiles.messages.toServer.*;
import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.utility.CLIColor;
import it.polimi.ingsw.commonFiles.utility.StringUtility;

import java.io.IOException;
import java.util.*;

/**
 * Prints on {@link System#out} ASCII characters representing the game, updates.
 * It also processes user commands with {@link CLIView#process(String input)}.
 */
public class CLIView extends View {
    private String currentView = "";
    /**
     * Actions that the player must do in a specific moment of the game.
     */
    private final Map<String, String> toDo = new LinkedHashMap<>();

    /**
     * Adds, or replaces, an action that the player must do.
     * @param id the id for the action, to use when the action must be deleted
     * @param toDo the action to do
     */
    @Override
    public void setToDo(String id, String toDo) {
        this.toDo.put(id, toDo);
        if (currentView != null)
            refresh(currentView);
    }

    /**
     * Removes an action from the {@link CLIView#toDo} list.
     *
     * @param id the id of the action to remove
     */
    @Override
    public void deleteToDo(String id) {
        if (id.equals(""))
            toDo.clear();
        else
            toDo.remove(id);
        if (currentView != null)
            refresh(currentView);
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
    @Override
    public void process(String input) {
        String[] commandSlice = input.split("\\s*:\\s*", 2);
        switch (commandSlice[0].toLowerCase()) {
            //each case is an identifier
            case "setnick": {
                setNick(commandSlice);
                break;
            }
            case "joingame": {
                joinGame(commandSlice);
                break;
            }
            case "setgame": {
                setGame(commandSlice);
                break;
            }
            case "back": {
                back();
                break;
            }
            case "choose": {
                choose(commandSlice);
                break;
            }
            case "activateprod": {
                activateProduction(commandSlice);
                break;
            }
            case "usemarket": {
                useMarket(commandSlice);
                break;
            }
            case "choosewhite": {
                chooseWhite(commandSlice);
                break;
            }
            case "buydevcard": {
                buyDevCard(commandSlice);
                break;
            }
            case "useleader": {
                useLeader(commandSlice);
                break;
            }
            case "discardleader": {
                discardLeader(commandSlice);
                break;
            }
            case "deployres": {
                deployRes(commandSlice);
                break;
            }
            case "takeres": {
                takeRes(commandSlice);
                break;
            }
            case "discardres": {
                discardRes(commandSlice);
                break;
            }
            case "getres": {
                getRes(commandSlice);
                break;
            }
            case "show":{
                showHandler(commandSlice);
                break;
            }
            case "next": {
                getController().sendMessage(new Next());
                break;
            }
            //todo insert other commands
            default:
                System.err.println("Invalid command, retry.");
        }
    }

    /**
     * Prints elements on standard output.
     *
     * @param element the element to show
     */
    @Override
    public void show(String element) {
        String[] target = element.split("\\s*,\\s*");
        switch (target[0]) {
            case "asknickname": {
                System.out.println("Insert your nickname by typing SETNICK: <your nickname>:");
                break;
            }
            case "waitgamecreation": {
                System.out.println("Waiting creation of the game...");
                break;
            }
            case "turnphase": {
                System.out.println("Next turn phase: " + getModel().getCurrentTurnPhase());
                break;
            }
            case "board": {
                clear();
                printHead();
                if (target.length == 1) {
                    System.out.println(getModel().getBoard());
                    currentView = element;
                } else {
                    Optional<Board> board = Optional.ofNullable(getModel().getBoard(target[1]));
                    board.ifPresentOrElse(b -> {
                        System.out.println(b);
                        currentView = element;
                    }, () -> {
                        show(currentView);
                        System.err.println("Player not found.");
                    });
                }
                break;
            }
            case "hand": {
                clear();
                printHead();
                currentView = element;
                System.out.println(getModel().getLeaderHand());
                break;
            }
            case "market":{
                clear();
                printHead();
                currentView = element;
                System.out.println(getModel().getMarket());
                break;
            }
            case "devgrid":{
                clear();
                printHead();
                currentView = element;
                System.out.println(getModel().getDevelopmentGrid());
                break;
            }
            case "players": {
                System.out.println();
                getModel().getPlayersUsernames().forEach(p -> {
                    if (p.equals(getModel().getPlayerUsername()))
                        System.out.println(CLIColor.ANSI_BRIGHT_GREEN + p + CLIColor.ANSI_RESET);
                    else System.out.println(p);
                });
                break;
            }
            case "gameslist": {
                if (getModel().isGameSelected())
                    showError("You cannot do this now.");
                else getController().sendMessage(new GamesList());
                break;
            }
            default:
                System.out.println(element);
                break;
        }
    }

    /**
     * Prints out error messages.
     *
     * @param error the error to show
     */
    @Override
    public void showError(String error) {
        System.err.println(error);
    }

    @Override
    public void joinGame(String[] commandSlice) {
        String lobbyName;
        try {
            lobbyName = commandSlice[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Wrong syntax: use the format \"JOINGAME: <game name>\".");
            return;
        }
        if (getController().getModel().isGameSelected()) {
            System.err.println("You have already chosen the game.");
            return;
        }
        getController().sendMessage(new JoinGame(lobbyName));
    }

    @Override
    public void showGamesList(List<String> lobbiesName, List<Integer> currentPlayers, List<Integer> maxPlayersNum) {
        if (lobbiesName.isEmpty()) System.out.println("There are no open games.");
        else {
            int maxSize = lobbiesName.stream().max(Comparator.comparingInt(String::length)).get().length();
            System.out.println(CLIColor.ANSI_BRIGHT_GREEN + "List of open games:" + CLIColor.ANSI_RESET);
            for (int i = 0; i < lobbiesName.size(); i++)
                System.out.println(
                    StringUtility
                            .addPadToTheRight(CLIColor.ANSI_YELLOW + lobbiesName.get(i) + CLIColor.ANSI_RESET, maxSize + 2, ' ')
                            + currentPlayers.get(i)
                            + "/"
                            + maxPlayersNum.get(i));

        }
    }

    /**
     * Prints out updates.
     *
     * @param update the update to print out
     */
    private void showUpdateText(String update) {
        System.out.println(CLIColor.ANSI_BRIGHT_GREEN
                + update
                + CLIColor.ANSI_RESET);
    }

    /**
     * Shows the player position on the table.
     *
     * @param position the position of the player
     */
    @Override
    public void showTablePosition(int position) {
        showUpdate("You are the player #" + position + ".");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignore) {
        }
    }

    @Override
    public void showHandler(String[] commandSlice){
        String target;
        try {
            target = commandSlice[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Wrong syntax: use the format \"SHOW: <arg1>, <arg2>, ...\".");
            return;
        }
        String[] boardCmd = target.split("\\s*,\\s*");
        switch (boardCmd[0].toLowerCase()) {
            case "board": {
                if (boardCmd.length==1) {
                    show("board");
                } else {
                    show(target);
                }
                break;
            }
            case "hand": {
                show("hand");
                break;
            }
            case "market": {
                show("market");
                break;
            } case "devgrid":{
                show("devgrid");
                break;
            }
            case "players": {
                show("players");
                break;
            }
            case "gameslist": {
                show("gameslist");
                break;
            }
            default:
                showError("Cannot show \"" + commandSlice[1] + "\".");
        }
    }

    /**
     * this method handle the calling for the player command back
     */
    @Override
    public void back() {
        getController().sendMessage(new Back());
    }

    /**
     * this method handle the calling for the player command discardres
     */
    @Override
    public void discardRes(String[] commandSlice) {
        String resource;
        try {
            resource = commandSlice[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Wrong syntax: use the format \"DISCARDRES: <resource name>\".");
            return;
        }
        if (Utility.mapResource.get(commandSlice[1]) != null){
            if (Utility.isStorable(Utility.mapResource.get(commandSlice[1]))){
                getController().sendMessage(new DiscardRes(Utility.mapResource.get(commandSlice[1])));
            } else
                System.err.println("You can't discard faith");
        } else
            System.err.println("Invalid resource");
    }

    @Override
    public void showLorenzoAction(String announcement) {
        System.out.println(CLIColor.ANSI_GREEN + announcement + CLIColor.ANSI_RESET);
    }

    /**
     * this methods handles the calling for the player command setnick
     * @param commandSlice command by the player
     */
    @Override
    public void setNick(String[] commandSlice){
        String nickname;
        try {
            nickname = commandSlice[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Wrong syntax: use the format \"SETNICK: <nickname>\".");
            return;
        }
        if (getController().getModel().getPlayerUsername() != null) {
            System.err.println("You have already chosen your nickname.");
            return;
        }
        getController().sendMessage(new SetNickname(nickname));
    }

    /**
     * this methods handles the calling for the player command setgame
     * @param commandSlice command by the player
     */
    @Override
    public void setGame(String[] commandSlice){
        try {
            getController().sendMessage(new SetGame(Integer.parseInt(commandSlice[1])));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.err.println("You must insert a number.");
        }
    }

    /**
     * this methods handles the calling for the player command choose
     * @param commandSlice command by the player
     */
    @Override
    public void choose(String[] commandSlice){
        String turnPhase;
        try {
            turnPhase = commandSlice[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Wrong syntax: use the format \"CHOOSE: <turnphasename>\".");
            return;
        }
        getController().sendMessage(new ChooseTurnPhase(turnPhase.trim().toLowerCase()));
    }

    /**
     * this methods control if the command is write in a good way than
     * handles the calling for the player command activeprod
     *
     * @param commandSlice command by the player
     */
    @Override
    public void activateProduction(String[] commandSlice) {
        String argument;
        try {
            argument = commandSlice[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Wrong syntax: use the format \"ACTIVATEPRODUCTION: <id>, <arg1>, ...\".");
            return;
        }
        String[] arguments = argument.split("\\s*,\\s*");
        String[] elements;
        ArrayList<Integer> cost = new ArrayList<>();
        int ID = Integer.parseInt(arguments[0]);
        try {
            if (ID < 0)
                throw new IllegalArgumentException("Invalid ID");
            if (ID == 0) {
                elements = arguments[3].split("\\s");
                for (String element : elements) {
                    cost.add(Integer.parseInt(element));
                }
                elements = arguments[1].split("\\s");
                getController().sendMessage(new StartProduction(ID, cost, arguments[2], elements));
            } else if (ID <= 3) {
                elements = arguments[1].split("\\s");
                for (String element : elements) {
                    cost.add(Integer.parseInt(element));
                }
                getController().sendMessage(new StartProduction(ID, cost));
            } else if (ID <= 5) {
                cost.add(Integer.parseInt(arguments[1]));
                getController().sendMessage(new StartProduction(ID, cost, arguments[2]));
            }
        } catch (NumberFormatException e) {
            System.err.println("You must insert a number.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }


    /**
     * this methods control if the command is write in a good way than
     * handles the calling for the player command buydevcard
     * @param commandSlice command by the player
     */
    public void buyDevCard(String[] commandSlice){
        String arg;
        try {
            arg = commandSlice[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Wrong syntax: use the format \"BUYDEVCARD: <arg1>, <arg2>, ...\".");
            return;
        }
        String[] arguments = arg.split("\\s*,\\s*");
        int level, space;
        String color;
        ArrayList<Integer> stores = new ArrayList<>();
        try {
            level = Integer.parseInt(arguments[0]);
            color = arguments[1];
            space = Integer.parseInt(arguments[2]);
            arguments = arguments[3].split("\\s");
            for (String argument : arguments) {
                stores.add(Integer.parseInt(argument));
            }
            getController().sendMessage(new BuyCard(level, color, space, stores));
        } catch (NumberFormatException e) {
            System.err.println("You must insert a number.");
        }
    }

    /**
     * this method handles the calling for the player command usemarket
     * @param commandSlice command by the player
     */
    @Override
    public void useMarket(String[] commandSlice){
        String argument;
        try {
            argument = commandSlice[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Wrong syntax: use the format \"USEMARKET: <rowOrColumn>, <number>\".");
        }
        String[] args = commandSlice[1].split("\\s*,\\s*");
        if (args[0].toLowerCase().matches("[rc]") && args.length == 2) {
            try {
                getController().sendMessage(new UseMarket(args[0].charAt(0), Integer.parseInt(args[1])));
            } catch (NumberFormatException e) {
                System.err.println("Wrong parameter");
            }
        } else
            System.err.println("Wrong parameter");
    }

    /**
     * Handles the {@code choosewhite} command.
     */
    @Override
    public void chooseWhite(String[] commandSlice) {
        try {
            getController().sendMessage(new ChooseWhiteMarble(Integer.parseInt(commandSlice[1].trim())));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.err.println("You must insert a leader position (1 or 2).");
        }
    }

    /**
     * this methods handles the calling for the player command useleader
     * @param commandSlice command by the player
     */
    @Override
    public void useLeader(String[] commandSlice){
        int pos;
        try {
            pos = Integer.parseInt(commandSlice[1]);
            getController().sendMessage(new UseLeader(pos));
        } catch (NumberFormatException e) {
            System.err.println("You must insert a number.");
        }
    }

    /**
     * this methods handles the calling for the player command deployRes
     * @param commandSlice command by the player
     */
    @Override
    public void deployRes(String[] commandSlice){
        String[] args = commandSlice[1].split("\\s*,\\s*");
        if (args.length != 2) {
            System.err.println("Wrong syntax: use the format \"DEPLOYRES: <resource name, depot position number>\".");
            return;
        }
        Optional<Resource> toGet = Optional.ofNullable(Utility.mapResource.get(args[0].trim().toLowerCase()));
        toGet.ifPresentOrElse(res -> {
            try {
                getController().sendMessage(new DeployRes(res, Integer.parseInt(args[1])));
            } catch (NumberFormatException e) {
                System.err.println("Wrong parameter: you must insert the position in which you want to deploy the resource.");
            }
                }, () -> System.err.println("Wrong parameter: you must insert a resource name."));
    }

    /**
     * this methods handles the calling for the player command discardleader
     * @param commandSlice command by the player
     */
    @Override
    public void discardLeader(String[] commandSlice){
        int pos;
        try {
            pos = Integer.parseInt(commandSlice[1]);
            getController().sendMessage(new DiscardLeader(pos));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.err.println("You must insert a number.");
        }
    }

    /**
     * this methods handles the calling for the player command takeRes
     * @param commandSlice command by the player
     */
    @Override
    public void takeRes(String[] commandSlice){
        int depot;
        try{
            depot=Integer.parseInt(commandSlice[1]);
            getController().sendMessage(new TakeRes(depot));
        }catch(ArrayIndexOutOfBoundsException | NumberFormatException e){
            System.err.println("you must insert a number");
        }
    }


    /**
     * Handles the {@code getres} command.
     */
    @Override
    public void getRes(String[] commandSlice) {
        String resource;
        try {
            resource = commandSlice[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Wrong syntax: use the format \"GETRES: <resource>\".");
            return;
        }
        Resource res = Utility.mapResource.get(resource.toLowerCase());
        if (res == null)
            System.err.println("Wrong parameter: you must insert a resource name.");
        else getController().sendMessage(new GetResource(res));
    }

    /**
     * Clears the screen.
     */
    private void clear(){
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                System.out.print("\u001B[H\u001B[2J");
        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * If the current shown element appears in {@param viewsToRefresh}, clears the screen and reprints the element.
     *
     * @param viewsToRefresh elements that need too be refreshed
     */
    @Override
    public void refresh(String... viewsToRefresh) {
        for (String s : viewsToRefresh) {
            if (s.equals("") || currentView.equals(s)) {
                clear();
                break;
            }
        }
        if (!currentView.equals("")) show(currentView);
    }

    /**
     * Prints the "head" of the CLI, containing:
     * <p>
     * -the current player;
     * <p>
     * -the current turn phase;
     * <p>
     * -actions that the player must do at the time this method is called.
     */
    @Override
    public void printHead() {
        if (getModel() == null) return;
        if (getModel().getCurrentPlayerInTheGame() != null)
            System.out.println(StringUtility.center(
                    (getModel().getCurrentPlayerInTheGame().equals(getModel().getPlayerUsername()) ?
                            (CLIColor.ANSI_BRIGHT_GREEN + "Your turn" + CLIColor.ANSI_RESET) :
                            (getModel().getCurrentPlayerInTheGame() + "'s turn")), 149, '-'));
        if (getModel().getCurrentTurnPhase() != null)
            System.out.println(StringUtility.center(getModel().getCurrentTurnPhase(), 149, '-'));
        System.out.println();
        toDo.forEach((id, action) -> System.out.println(action));
        System.out.println();
    }

    @Override
    public void displayEndingScore(String[] categories, int[] scores, int ranking) {
        int tot = 0;
        StringBuilder endingTable = new StringBuilder("╔════════════════════════╗ \n");
        endingTable.append("║").append(StringUtility.center(getModel().getPlayerUsername() + "'s score", 24)).
                append("║ \n");
        endingTable.append("╠════════════════════════╣ \n");
        endingTable.append("║");
        switch (ranking) {
            case (1) :{
                endingTable.append(StringUtility.center("Your ranking is: " + ranking + "st", 24));
                break;
            }
            case (2) :{
                endingTable.append(StringUtility.center("Your ranking is: " + ranking + "nd", 24));
                break;
            }
            case (3) :{
                endingTable.append(StringUtility.center("Your ranking is: " + ranking + "rd", 24));
                break;
            }
            default: {
                endingTable.append(StringUtility.center("Your ranking is: " + ranking + "th", 24));
                break;
            }
        }
        endingTable.append("║ \n");
        endingTable.append("╠════════════════════════╣ \n");
        for (int i = 0; i < categories.length; i++){
            endingTable.append("║").append(StringUtility.center(categories[i] + ": " + scores[i], 24)).
                    append("║ \n");
            endingTable.append("╠════════════════════════╣ \n");
            tot += scores[i];
        }
        endingTable.append("║").append(StringUtility.center("tot VP: " + tot, 24)).
                append("║ \n");
        endingTable.append("╚════════════════════════╝ \n");
        System.out.println(endingTable);
    }

    @Override
    public void showNicknameSet() {
        showUpdateText("Your nickname has been set. Now create a game or search for open games.");
    }

    @Override
    public void showGameJoined() {
        showUpdateText("You entered the game.");
    }

    @Override
    public void showResume() {
        showUpdateText("Welcome back, " + getModel().getPlayerUsername() + ".");
    }

    @Override
    public void showCreateGame() {
        showUpdateText("You are the first player: create a game by typing SETGAME: <number of players>.");
    }

    @Override
    public void showGameSet(int playersNum) {
        showUpdateText("The game has been set: " + playersNum + " allowed players.");
        showWaitGameStart();
    }

    @Override
    public void showWaitGameCreation() {
        showUpdateText("Waiting creation of the game...");
    }

    @Override
    public void showWaitGameStart() {
        showUpdateText("Waiting other players...");
    }

    @Override
    public void showGameStarted() {
        showUpdateText("The game is starting now...");
    }

    @Override
    public void showNewPlayer(String playerNick) {
        showUpdateText(playerNick + " has entered the game.");
    }

    @Override
    public void showPlayerLeft(String playerNick) {
        showUpdateText(playerNick + " has lost the connection.");
    }

    @Override
    public void showGotResource(String resource) {
        showUpdateText("A " + resource + " has been added to your hand. Remember to deploy it!");
    }

    @Override
    public void showResourceTaken() {
        showUpdateText("Resource taken.");
    }

    @Override
    public void showMarketUsed() {
        showUpdateText("You have used the market: deploy or discard the resources in your hand.");
    }

    @Override
    public void showVaticanReport(int reportNum, boolean isPassed) {
        showUpdateText("You "
                + (isPassed ? "passed" : "didn't pass")
                + " the vatican report number " + reportNum + ".");
    }

    @Override
    public void showLastTurns(String reason) {
        showUpdateText("Last turns: " + reason);
    }
}
