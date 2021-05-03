package it.polimi.ingsw.client.model;

import it.polimi.ingsw.client.CLI.CLIColor;

public enum Resource {
    SHIELD(CLIColor.ANSI_BLUE.toString() + "0" + CLIColor.RESET),
    FAITH(CLIColor.ANSI_RED.toString() + "+" + CLIColor.RESET),
    SERF(CLIColor.ANSI_PURPLE.toString() + "S" + CLIColor.RESET),
    STONE(CLIColor.ANSI_GREY.toString() + "D" + CLIColor.RESET),
    COIN(CLIColor.ANSI_YELLOW.toString() + "o" + CLIColor.RESET);
    private final String representation;

    Resource(String representation){
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}
