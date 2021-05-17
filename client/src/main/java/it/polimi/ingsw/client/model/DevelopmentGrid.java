package it.polimi.ingsw.client.model;


import it.polimi.ingsw.commonFiles.utility.StringUtility;

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
                    get(Utility.mapColor.get(developmentCard.getColor()))] = developmentCard;
        }
    }

    public DevelopmentCard getCard(int level, Color color){
        return grid[level-1][Utility.colorPosition.get(color) -1 ];
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
                assertInfoColor();

    }

    private String assertLengthCost(int row) {
        StringBuilder costs = new StringBuilder(new String(" "));
        for (int i = 0; i < 4; i++) {
            costs.append("│").append(StringUtility.center(Arrays.toString(grid[row][i].getCosts()), 14));
        }
        costs.append("│");
        return costs.toString();
    }

    private String assertLengthProductionCost(int row){
        StringBuilder productionCost = new StringBuilder(new String(" "));
        for (int i = 0; i<4; i++) {
            productionCost.append("│").append(StringUtility.center(Arrays.toString(grid[row][i].
                    getProduction().getCost()),14));
        }
        productionCost.append("│");
        return productionCost.toString();
    }

    private String assertLengthProductionValue(int row){
        StringBuilder productionValue = new StringBuilder(new String(" "));
        for (int i = 0; i<4; i++) {
            productionValue.append("│").append(StringUtility.center(Arrays.toString(grid[row][i].
                    getProduction().getProd()),14));
        }
        productionValue.append("│");
        return productionValue.toString();
    }

    private String assertVictoryPoints(int row){
        StringBuilder victoryPoints = new StringBuilder(new String(" "));
        for (int i = 0; i<4; i++){
            victoryPoints.append("│").append(StringUtility.center(String.format(String.format("%2d", grid[row][i].getVictoryPoints())),14));
        }
        victoryPoints.append("│");
        return victoryPoints.toString();
    }

    private String assertInfoColor(){
        return  "        " + grid[0][0].getColor() + grid[0][0].getColor() + "             " + grid[0][1].getColor() +
                grid[0][1].getColor() + "             " + grid[0][2].getColor() + grid[0][2].getColor() + "             " +
                grid[0][3].getColor() + grid[0][3].getColor() + "        ";
    }
}
