package it.polimi.ingsw.client.CLI;

public enum CLIColor {
    ANSI_RED("\u001B[31m"),
    ANSI_GREEN("\u001B[32m"),
    ANSI_YELLOW("\u001B[33m"),
    ANSI_PURPLE("\u001B[35m"),
    ANSI_BLUE("\u001B[36m"),
    ANSI_GREY("\u001B[37m"),
    ANSI_BRIGHT_GREEN("\u001B[92m");
    static final String RESET = "\u001B[0m";
    private final String escape;

    CLIColor(String escape) {
        this.escape = escape;
    }

    public String escape() {
        return escape;
    }
}