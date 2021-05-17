package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.utility.CLIColor;

public class Marble {
    private final Color color;

    public Marble(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        if (Utility.colorMap.get(color)!=null) {
            return Utility.colorMap.get(color) + "@" + CLIColor.ANSI_RESET;
        } else
            return "@";
    }
}
