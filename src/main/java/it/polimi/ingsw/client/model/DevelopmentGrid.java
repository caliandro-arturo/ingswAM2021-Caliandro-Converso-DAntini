package it.polimi.ingsw.client.model;

import it.polimi.ingsw.common_files.model.Utility;
import it.polimi.ingsw.common_files.model.DevelopmentCard;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * DevelopmentGrid class for client
 */
public class DevelopmentGrid{
    private DevelopmentCard[][] grid;

    public DevelopmentGrid(DevelopmentCard[][] grid) {
        this.grid = grid;
    }

    public void setGrid(ArrayList<DevelopmentCard> grid) {
        for (DevelopmentCard developmentCard : grid) {
            this.grid[developmentCard.getLevel()][Utility.colorPosition.
                    get(developmentCard.getColor())] = developmentCard;
        }
    }

    @Override
    public String toString() {
        return " ┌──────────────┬──────────────┬──────────────┬──────────────┐\n" +
                assertLengthCost(2) + "\n" +
                assertLengthProductionCost(2) + "\n" +
                assertLengthProductionValue(2) + "\n" +
                assertVictoryPoints(2) + "\n" +
                " ├──────────────┼──────────────┼──────────────┼──────────────┤\n" +
                assertLengthCost(1) + "\n" +
                assertLengthProductionCost(1) + "\n" +
                assertLengthProductionValue(1) + "\n" +
                assertVictoryPoints(1) + "\n" +
                " ├──────────────┼──────────────┼──────────────┼──────────────┤\n" +
                assertLengthCost(0) + "\n" +
                assertLengthProductionCost(0) + "\n" +
                assertLengthProductionValue(0) + "\n" +
                assertVictoryPoints(0) + "\n" +
                " └──────────────┴──────────────┴──────────────┴──────────────┘\n" +
                "        " + grid[0][0].getColor() + grid[0][0].getColor() + "             " + grid[0][1].getColor() +
                grid[0][1].getColor() + "             " + grid[0][2].getColor() + grid[0][2].getColor() + "             " +
                 grid[0][3].getColor() + grid[0][3].getColor() + "        ";

    }

    private String assertLengthCost(int row) {
        StringBuilder costs = new StringBuilder(new String(" "));
        for (int i = 0; i < 4; i++) {
            costs.append("│").append(Utility.center(Arrays.toString(grid[row][i].getCost()), 14));
        }
        costs.append("│");
        return costs.toString();
    }

    private String assertLengthProductionCost(int row){
        StringBuilder productionCost = new StringBuilder(new String(" "));
        for (int i = 0; i<4; i++) {
            productionCost.append("│").append(Utility.center(Arrays.toString(grid[row][i].
                    getProduction().getCost()),14));
        }
        productionCost.append("│");
        return productionCost.toString();
    }

    private String assertLengthProductionValue(int row){
        StringBuilder productionValue = new StringBuilder(new String(" "));
        for (int i = 0; i<4; i++) {
            productionValue.append("│").append(Utility.center(Arrays.toString(grid[row][i].
                    getProduction().getProd()),14));
        }
        productionValue.append("│");
        return productionValue.toString();
    }

    private String assertVictoryPoints(int row){
        StringBuilder victoryPoints = new StringBuilder(new String(" "));
        for (int i = 0; i<4; i++){
            victoryPoints.append("│").append(Utility.center(String.format(String.format("%2d", grid[row][i].getVictoryPoints())),14));
        }
        victoryPoints.append("│");
        return victoryPoints.toString();
    }
}
