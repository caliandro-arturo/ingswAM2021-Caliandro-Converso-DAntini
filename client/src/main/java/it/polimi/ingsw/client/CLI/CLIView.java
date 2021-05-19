package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.client.View;
import it.polimi.ingsw.commonFiles.messages.toServer.SetGame;
import it.polimi.ingsw.commonFiles.messages.toServer.SetNickname;
import it.polimi.ingsw.commonFiles.messages.toServer.actions.*;
import it.polimi.ingsw.commonFiles.model.Resource;
import it.polimi.ingsw.commonFiles.utility.CLIColor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Prints on {@link System#out} ASCII characters representing the game, updates.
 * It also processes user commands with {@link CLIView#process(String input)}.
 */
public class CLIView extends View {

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
                chose(commandSlice);
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
        switch (element) {
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
                System.out.println(getModel().getBoard());
                break;
            }
            case "hand": {
                System.out.println(getModel().getLeaderHand());
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
        if (getModel().isGameStarted()){
            refresh();
        }
    }

    private void showHandler(String[] commandSlice){
        String target = commandSlice[1].toLowerCase();
        switch (target) {
            case "myboard": {
                show("board");
                break;
            }
            case "hand": {
                show(target);
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
     * this methods handles the calling for the player command chose
     * @param commandSlice command by the player
     */
    private void chose(String[] commandSlice){
        if (!commandSlice[1].isEmpty() || commandSlice[1].matches("\\s*"))
            getController().sendMessage(new ChooseTurnPhase(commandSlice[1]));
        else System.err.println("You must insert a turn phase.");
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
        String argument = args[0].toLowerCase();
        try {
            switch (argument) {
                case "serf":
                    getController().sendMessage(new DeployRes(Resource.SERF, Integer.parseInt(args[1])));
                    break;
                case "shield":
                    getController().sendMessage(new DeployRes(Resource.SHIELD, Integer.parseInt(args[1])));
                    break;
                case "coin":
                    getController().sendMessage(new DeployRes(Resource.COIN, Integer.parseInt(args[1])));
                    break;
                case "stone":
                    getController().sendMessage(new DeployRes(Resource.STONE, Integer.parseInt(args[1])));
                    break;
            }
        } catch (NumberFormatException e) {
            System.err.println("Wrong parameter");
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

    /**
     * refresh the cli after a change
     */
    private void refresh(){
        //TODO save current state
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                System.out.print("\u001B[H\u001B[2J");
        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
        }
        show("board");
    }

    private void clear() {
    }
}
