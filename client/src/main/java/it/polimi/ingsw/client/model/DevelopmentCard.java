package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.Production;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;
import it.polimi.ingsw.commonFiles.utility.StringUtility;

import java.util.Arrays;

/**
 * Client representation of development cards.
 */
public class DevelopmentCard {
    private final int level;
    private final int ID;
    private final int victoryPoints;
    private Color color;
    private final UtilityProductionAndCost[] costs;
    private final Production production;

    public DevelopmentCard(int ID, int level, int victoryPoints, Color color, UtilityProductionAndCost[] costs, Production production) {
        this.ID = ID;
        this.level = level;
        this.victoryPoints = victoryPoints;
        this.color = color;
        this.costs = costs;
        this.production = production;
    }

    public int getID() {
        return ID;
    }

    public int getLevel() {
        return level;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public UtilityProductionAndCost[] getCosts() {
        return costs;
    }

    public Production getProduction() {
        return production;
    }

    @Override
    public String toString() {

        return  "┌─────────────────┐\n" +
                "│     " + color + "  " + level + "  " + color + "     │\n" +
                "│" + StringUtility.center(Arrays.toString(costs), 17)+ "│\n" +
                "│                 │\n" +
                "│                 │\n" +
                production+
                "│                 │\n" +
                "│     " + color + " " + String.format("%2d", victoryPoints) + "  " + color + "     │\n" +
                "└─────────────────┘\n";
    }
}
