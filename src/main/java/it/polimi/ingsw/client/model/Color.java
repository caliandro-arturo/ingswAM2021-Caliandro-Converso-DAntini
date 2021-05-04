package it.polimi.ingsw.client.model;
import it.polimi.ingsw.client.CLI.CLIColor;

public enum Color {
    GREEN(CLIColor.ANSI_GREEN + "█" + CLIColor.RESET),
    BLUE(CLIColor.ANSI_BLUE + "█" + CLIColor.RESET),
    YELLOW(CLIColor.ANSI_YELLOW + "█" + CLIColor.RESET),
    PURPLE(CLIColor.ANSI_PURPLE + "█" + CLIColor.RESET),
    RED(CLIColor.ANSI_RED + "█" + CLIColor.RESET),
    WHITE( "█");
    private final String representation;

    Color(String representation){
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}
