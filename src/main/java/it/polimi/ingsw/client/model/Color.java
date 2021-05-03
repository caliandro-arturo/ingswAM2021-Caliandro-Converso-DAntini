package it.polimi.ingsw.client.model;
import it.polimi.ingsw.client.CLI.CLIColor;

public enum Color {
    GREEN(CLIColor.ANSI_GREEN.toString() + "[]" + CLIColor.RESET),
    BLUE(CLIColor.ANSI_BLUE.toString() + "[]" + CLIColor.RESET),
    YELLOW(CLIColor.ANSI_YELLOW.toString() + "[]" + CLIColor.RESET),
    PURPLE(CLIColor.ANSI_PURPLE.toString() + "[]" + CLIColor.RESET),
    RED(CLIColor.ANSI_RED.toString() + "[]" + CLIColor.RESET),
    WHITE( "[]");
    private final String representation;

    Color(String representation){
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}
