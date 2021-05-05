package it.polimi.ingsw.client.model;

import it.polimi.ingsw.client.CLI.CLIColor;

public enum Resource {
    SHIELD(CLIColor.ANSI_BLUE + "■" + CLIColor.ANSI_RESET),
    FAITH(CLIColor.ANSI_RED + "■" + CLIColor.ANSI_RESET),
    SERF(CLIColor.ANSI_PURPLE + "■" + CLIColor.ANSI_RESET),
    STONE(CLIColor.ANSI_GREY + "■" + CLIColor.ANSI_RESET),
    COIN(CLIColor.ANSI_YELLOW + "■" + CLIColor.ANSI_RESET);
    private final String representation;

    Resource(String representation){
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}
