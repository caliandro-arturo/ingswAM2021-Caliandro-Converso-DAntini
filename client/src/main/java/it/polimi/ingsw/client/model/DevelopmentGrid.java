package it.polimi.ingsw.client.model;


import it.polimi.ingsw.commonFiles.model.Card;
import it.polimi.ingsw.commonFiles.utility.StringUtility;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * DevelopmentGrid class for client
 */
public class DevelopmentGrid{
    private DevelopmentCard[][] grid;

    public DevelopmentGrid(ArrayList<DevelopmentCard> grid){
        this.grid = new DevelopmentCard[3][4];
        setGrid(grid);
    }

    public DevelopmentGrid(DevelopmentCard[][] grid) {
        this.grid = grid;
    }

    public void setGrid(ArrayList<DevelopmentCard> grid) {
        for (DevelopmentCard developmentCard : grid) {
            this.grid[developmentCard.getLevel()- 1][Utility.colorPosition.
                    get(developmentCard.getColor())] = developmentCard;
        }
    }

    public DevelopmentCard getCard(int level, Color color){
        return grid[level-1][Utility.colorPosition.get(color)];
    }

    public void lorenzoRemoveAction(Color color, Card card, boolean flag){
        for (int i = 0; i<4; i++) {
            if (grid[i][Utility.colorPosition.get(color)] != null){
                if (flag){
                    grid[i][Utility.colorPosition.get(color)] = null;
                    try {
                        grid[i + 1][Utility.colorPosition.get(color)] = new DevelopmentCard(card.getID(),
                                i + 2, card.getNewCardVictoryPoints(), color, card.getNewCardCost(), card.getProductions());
                    } catch (NullPointerException e){
                        grid[i + 1][Utility.colorPosition.get(color)] = null;
                    }
                }  else {
                    try {
                        grid[i][Utility.colorPosition.get(color)] = new DevelopmentCard(card.getID(),
                                i + 1, card.getNewCardVictoryPoints(), color, card.getNewCardCost(), card.getProductions());
                    }catch (NullPointerException e){
                        grid[i][Utility.colorPosition.get(color)] = null;
                    }
                }
                return;
            }
        }
    }

    public void setCard(int level, String color, DevelopmentCard newCard) {
        grid[level-1][Utility.colorPosition.get(Utility.mapColor.get(color))] = newCard;
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
        StringBuilder costs = new StringBuilder(" ");
        for (int i = 0; i < 4; i++) {
            if (grid[row][i] != null)
                costs.append("│").append(StringUtility.center(Arrays.toString(grid[row][i].getCosts()), 14));
            else
                costs.append("│").append(StringUtility.center("", 14));
        }
        costs.append("│");
        return costs.toString();
    }

    private String assertLengthProductionCost(int row){
        StringBuilder productionCost = new StringBuilder(" ");
        for (int i = 0; i<4; i++) {
            if (grid[row][i] != null)
                productionCost.append("│").append(StringUtility.center(Arrays.toString(grid[row][i].
                        getProduction().getCost()), 14));
            else
                productionCost.append("│").append(StringUtility.center("", 14));
        }
        productionCost.append("│");
        return productionCost.toString();
    }

    private String assertLengthProductionValue(int row){
        StringBuilder productionValue = new StringBuilder(" ");
        for (int i = 0; i<4; i++) {
            if (grid[row][i] != null)
                productionValue.append("│").append(StringUtility.center(Arrays.toString(grid[row][i].
                    getProduction().getProd()),14));
            else
                productionValue.append("│").append(StringUtility.center("", 14));
        }
        productionValue.append("│");
        return productionValue.toString();
    }

    private String assertVictoryPoints(int row){
        StringBuilder victoryPoints = new StringBuilder(" ");
        for (int i = 0; i<4; i++){
            if (grid[row][i] != null)
                victoryPoints.append("│").append(StringUtility.center(String.format("%2d", grid[row][i].getVictoryPoints()),14));
            else
                victoryPoints.append("│").append(StringUtility.center("", 14));
        }
        victoryPoints.append("│");
        return victoryPoints.toString();
    }

    private String assertInfoColor(){
        return  "        " + Color.GREEN + Color.GREEN  + "             " + Color.BLUE +
                Color.BLUE + "             " + Color.YELLOW + Color.YELLOW + "             " +
                Color.PURPLE + Color.PURPLE + "        ";
    }
}
