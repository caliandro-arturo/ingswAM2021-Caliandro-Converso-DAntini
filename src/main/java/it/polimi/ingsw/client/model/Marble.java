package it.polimi.ingsw.client.model;

import it.polimi.ingsw.server.model.Color;
import it.polimi.ingsw.client.CLI.CLIColor;
import it.polimi.ingsw.server.model.Utility;

public class Marble {
    private Color color;

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
