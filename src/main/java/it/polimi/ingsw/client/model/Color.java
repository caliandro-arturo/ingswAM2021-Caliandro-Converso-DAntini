package it.polimi.ingsw.client.model;
import it.polimi.ingsw.client.CLI.CLIColor;

public enum Color {
    GREEN(CLIColor.ANSI_GREEN + "█" + CLIColor.ANSI_RESET),
    BLUE(CLIColor.ANSI_BLUE + "█" + CLIColor.ANSI_RESET),
    YELLOW(CLIColor.ANSI_YELLOW + "█" + CLIColor.ANSI_RESET),
    PURPLE(CLIColor.ANSI_PURPLE + "█" + CLIColor.ANSI_RESET),
    RED(CLIColor.ANSI_RED + "█" + CLIColor.ANSI_RESET),
    GREY(CLIColor.ANSI_GREY + "█" + CLIColor.ANSI_RESET),
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
