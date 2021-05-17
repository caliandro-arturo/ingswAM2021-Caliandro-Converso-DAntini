package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.utility.CLIColor;

public enum Color {
    BLUE(CLIColor.ANSI_BLUE + "█" + CLIColor.ANSI_RESET),
    GREY(CLIColor.ANSI_GREY + "█" + CLIColor.ANSI_RESET),
    PURPLE(CLIColor.ANSI_PURPLE + "█" + CLIColor.ANSI_RESET),
    YELLOW(CLIColor.ANSI_YELLOW + "█" + CLIColor.ANSI_RESET),
    RED(CLIColor.ANSI_RED + "█" + CLIColor.ANSI_RESET),
    WHITE("█"),
    GREEN(CLIColor.ANSI_GREEN + "█" + CLIColor.ANSI_RESET);
    private final String representation;

    Color(String representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }

}
