package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.model.Board;
import it.polimi.ingsw.client.model.Utility;
import it.polimi.ingsw.commonFiles.messages.toServer.*;
import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.utility.CLIColor;
import it.polimi.ingsw.commonFiles.utility.StringUtility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Prints on {@link System#out} ASCII characters representing the game, updates.
 * It also processes user commands with {@link CLIView#process(String input)}.
 */
public class CLIView extends View {
    private String currentView;
    private String toDo = "";

    @Override
    public void setToDo(String toDo) {
        this.toDo += toDo + '\n';
        if (currentView != null)
            refresh(currentView);
    }

    @Override
    public void resetToDo() {
        toDo = "";
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
        String[] commandSlice = input.split("\\s*:\\s*");
        switch (commandSlice[0].toLowerCase()) {
            //each case is an identifier
            case "setnick": {
                setNick(commandSlice);
                break;
            }
            case "setgame": {
                setGame(commandSlice);
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
            case "deployres":{
                deployRes(commandSlice);
                break;
            }
            case "takeres":{
                takeRes(commandSlice);
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
            case "creategame": {
                System.out.println("You are the first player: create a game by typing SETGAME: <number of players>:");
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

    /**
     * Prints out updates.
     *
     * @param update the update to print out
     */
    @Override
    public void showUpdate(String update) {
        System.out.println(CLIColor.ANSI_BRIGHT_GREEN
                + update
                + CLIColor.ANSI_RESET);
    }

    private void showHandler(String[] commandSlice){
        String target = commandSlice[1].toLowerCase();
        String[] boardCmd = target.split("\\s*,\\s*");
        switch (boardCmd[0]) {
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
        }
    }

    /**
     * this methods handles the calling for the player command setnick
     * @param commandSlice command by the player
     */
    private void setNick(String[] commandSlice){
        if (!commandSlice[1].isEmpty() && !commandSlice[1].matches("\\s*")) {
            System.out.println("Creating nickname...");
            getController().sendMessage(new SetNickname(commandSlice[1]));
        } else System.err.println("Nickname not valid.");
    }

    /**
     * this methods handles the calling for the player command setgame
     * @param commandSlice command by the player
     */
    private void setGame(String[] commandSlice){
        try {
            getController().sendMessage(new SetGame(Integer.parseInt(commandSlice[1])));
        } catch (NumberFormatException e) {
            System.err.println("You must insert a number.");
        }
    }

    /**
     * this methods handles the calling for the player command choose
     * @param commandSlice command by the player
     */
    private void choose(String[] commandSlice){
        if (commandSlice[1].isEmpty()) {
            System.err.println("You must insert a turn phase.");
            return;
        }
        getController().sendMessage(new ChooseTurnPhase(commandSlice[1].trim().toLowerCase()));
    }

    /**
     * this methods control if the command is write in a good way than
     * handles the calling for the player command activeprod
     * @param commandSlice command by the player
     */
    private void activateProduction(String[] commandSlice){
        String[] arguments = commandSlice[1].split("\\s*,\\s*");
        String[] elements;
        ArrayList<Integer> cost = new ArrayList<>();
        int ID = Integer.parseInt(arguments[0]);
        try {
            if (ID < 0)
                throw new IllegalArgumentException("Invalid ID");
            if (ID == 0) {
                elements = arguments[3].split("\\s*");
                for (String element : elements) {
                    cost.add(Integer.parseInt(element));
                }
                elements = arguments[1].split("\\s*");
                getController().sendMessage(new StartProduction(ID, cost, arguments[2], elements));
            } else if (ID <= 3) {
                elements = arguments[1].split("\\s*");
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
    private void buyDevCard(String[] commandSlice){
        String[] arguments = commandSlice[1].split("\\s*,\\s*");
        int level, space;
        String color;
        ArrayList<Integer> stores = new ArrayList<>();
        try {
            level = Integer.parseInt(arguments[0]);
            color = arguments[1];
            space = Integer.parseInt(arguments[2]);
            arguments = arguments[3].split("\\s*");
            for (String argument : arguments) {
                stores.add(Integer.parseInt(argument));
            }
            getController().sendMessage(new BuyCard(level, color, space, stores));
        } catch (NumberFormatException e) {
            System.err.println("You must insert a number.");
        }
    }

    /**
     * this methods handles the calling for the player command usemarket
     * @param commandSlice command by the player
     */
    private void useMarket(String[] commandSlice){
        String[] args = commandSlice[1].split("\\s*,\\s*");
        if (args[0].matches("[rc]")) {
            try {
                getController().sendMessage(new UseMarket(args[0].charAt(0), Integer.parseInt(args[1])));
            } catch (NumberFormatException e) {
                System.err.println("Wrong parameter");
            }
        } else
            System.err.println("Wrong parameter");
    }

    private void chooseWhite(String[] commandSlice) {
        //todo handle white marble choice
    }

    /**
     * this methods handles the calling for the player command useleader
     * @param commandSlice command by the player
     */
    private void useLeader(String[] commandSlice){
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
    private void deployRes(String[] commandSlice){
        String[] args = commandSlice[1].split("\\s*,\\s*");
        try {
            getController().sendMessage(new DeployRes(Utility.mapResource.get(args[0].toLowerCase()), Integer.parseInt(args[1])));
        } catch (NumberFormatException e) {
            System.err.println("Wrong parameter: you must insert a resource name.");
        }
    }

    /**
     * this methods handles the calling for the player command discardleader
     * @param commandSlice command by the player
     */
    private void discardLeader(String[] commandSlice){
        int pos;
        try {
            pos = Integer.parseInt(commandSlice[1]);
            getController().sendMessage(new DiscardLeader(pos));
        } catch (NumberFormatException e) {
            System.err.println("You must insert a number.");
        }
    }

    /**
     * this methods handles the calling for the player command takeRes
     * @param commandSlice command by the player
     */
    private void takeRes(String[] commandSlice){
        int depot;
        try{
            depot=Integer.parseInt(commandSlice[1]);
            getController().sendMessage(new TakeRes(depot));
        }catch(NumberFormatException e){
            System.err.println("you must insert a number");
        }
    }


    private void getRes(String[] commandSlice) {
        if (commandSlice[1].trim().isEmpty()) {
            System.err.println("Missing parameter: you must insert a resource name.");
            return;
        }
        Resource res = Utility.mapResource.get(commandSlice[1].toLowerCase());
        if (res == null)
            System.err.println("Wrong parameter: you must insert a resource name.");
        else getController().sendMessage(new GetResource(res));
    }

    /**
     * refresh the cli after a change
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

    @Override
    public void refresh(String... viewsToRefresh) {
        for (String s : viewsToRefresh)
            if (s.equals("") || currentView.equals(s)) {
                clear();
                break;
            }
        if (currentView != null) show(currentView);
    }
    
    private void printHead() {
        if (getModel() == null) return;
        if (getModel().getCurrentPlayerInTheGame() != null)
            System.out.println(StringUtility.center(getModel().getCurrentPlayerInTheGame() + "'s turn", 160, '-'));
        if (getModel().getCurrentTurnPhase() != null)
            System.out.println(StringUtility.center(getModel().getCurrentTurnPhase(), 160, '-'));
        if (toDo != null)
            System.out.println(toDo);
    }
}
