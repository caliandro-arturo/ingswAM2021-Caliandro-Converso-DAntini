package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.client.View;
import it.polimi.ingsw.messages.toServer.SetGame;
import it.polimi.ingsw.messages.toServer.SetNickname;

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
                if (!commandSlice[1].isEmpty() && !commandSlice[1].matches("\\s*")) {
                    System.out.println("creating nickname...");
                    getController().sendMessage(new SetNickname(commandSlice[1]));
                } else System.out.println("nickname not valid.");
                break;
            }
            case "setgame": {
                try {
                    getController().sendMessage(new SetGame(Integer.parseInt(commandSlice[1])));
                } catch (NumberFormatException e) {
                    System.err.println("You must insert a number.");
                    break;
                }
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
            }
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
        System.out.println(CLIColor.ANSI_BRIGHT_GREEN.toString()
                + update
                + CLIColor.ANSI_RESET);
    }

}
