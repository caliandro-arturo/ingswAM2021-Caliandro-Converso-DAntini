package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.Production;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;

import java.util.Arrays;

public class DevelopmentCard {
    private int level;
    private int victoryPoints;
    private Color color;
    private UtilityProductionAndCost[] costs;
    private Production production;

    public DevelopmentCard(int level, int victoryPoints, Color color, UtilityProductionAndCost[] costs, Production production) {
        this.level = level;
        this.victoryPoints = victoryPoints;
        this.color = color;
        this.costs = costs;
        this.production = production;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
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

    public void setCosts(UtilityProductionAndCost[] costs) {
        this.costs = costs;
    }

    public Production getProduction() {
        return production;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    @Override
    public String toString() {

        return  "┌─────────────────┐\n" +
                "│     " + color + "  " + level + "  " + color + "     │\n" +
                "│" + Utility.center(Arrays.toString(costs), 17)+ "│\n" +
                "│                 │\n" +
                "│                 │\n" +
                production+
                "│                 │\n" +
                "│     " + color + " " + String.format("%2d", victoryPoints) + "  " + color + "     │\n" +
                "└─────────────────┘\n";
    }
}
