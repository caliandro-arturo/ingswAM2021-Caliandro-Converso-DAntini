package it.polimi.ingsw.commonFiles.utility;

public enum CLIColor {
    ANSI_RED("\u001B[31m"),
    ANSI_GREEN("\u001B[32m"),
    ANSI_YELLOW("\u001B[33m"),
    ANSI_PURPLE("\u001B[35m"),
    ANSI_BLUE("\u001B[94m"),
    ANSI_GREY("\u001B[90m"),
    ANSI_BRIGHT_GREEN("\u001B[92m"),
    ANSI_RESET("\u001B[0m");
    private final String escape;

    CLIColor(String escape) {
        this.escape = escape;
    }

    @Override
    public String toString() {
        return escape;
    }
}