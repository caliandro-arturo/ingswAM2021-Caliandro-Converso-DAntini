package it.polimi.ingsw.client.model;


import it.polimi.ingsw.commonFiles.model.Card;
import it.polimi.ingsw.commonFiles.utility.StringUtility;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * DevelopmentGrid class for client
 */
public class DevelopmentGrid{
    private final ArrayList<ObservableList<DevelopmentCard>> grid = new ArrayList<>(){{
        add(FXCollections.observableArrayList(null,null,null,null));
        add(FXCollections.observableArrayList(null,null,null,null));
        add(FXCollections.observableArrayList(null,null,null,null));
    }};

    public ArrayList<ObservableList<DevelopmentCard>> getGrid() {
        return grid;
    }

    public ObservableList<DevelopmentCard> gridProperty(int i) {
        return grid.get(i);
    }

    public DevelopmentGrid(ArrayList<DevelopmentCard> grid){
        setGrid(grid);
    }

    public DevelopmentGrid(DevelopmentCard[][] grid) {
        int level = 0;
        for (DevelopmentCard[] cards : grid){
            this.grid.set(level,FXCollections.observableArrayList(Arrays.asList(cards)));
            level++;
        }
    }

    public void setGrid(ArrayList<DevelopmentCard> grid) {
        for (DevelopmentCard developmentCard : grid) {
            this.grid.get(developmentCard.getLevel()- 1).set(Utility.colorPosition.
                    get(developmentCard.getColor()), developmentCard);
        }
    }

    public DevelopmentCard getCard(int level, Color color){
        return getCard(level-1,Utility.colorPosition.get(color));
    }

    public DevelopmentCard getCard(int row, int column){
        return grid.get(row).get(column);
    }

    public void lorenzoRemoveAction(Color color, Card card, boolean flag){
        for (int i = 0; i<4; i++) {
            if (grid.get(i).get(Utility.colorPosition.get(color)) != null){
                if (flag){
                    grid.get(i).set(Utility.colorPosition.get(color),null);
                    try {
                        grid.get(i + 1).set(Utility.colorPosition.get(color), new DevelopmentCard(card.getID(),
                                i + 2, card.getNewCardVictoryPoints(), color, card.getNewCardCost(),
                                card.getProductions()));
                    } catch (NullPointerException e){
                        grid.get(i + 1).set(Utility.colorPosition.get(color),null);
                    }
                }  else {
                    try {
                        grid.get(i).set(Utility.colorPosition.get(color), new DevelopmentCard(card.getID(),
                                i + 1, card.getNewCardVictoryPoints(), color, card.getNewCardCost(), card.getProductions()));
                    }catch (NullPointerException e){
                        grid.get(i).set(Utility.colorPosition.get(color),null);
                    }
                }
                return;
            }
        }
    }

    public void setCard(int level, String color, DevelopmentCard newCard) {
        grid.get(level-1).set(Utility.colorPosition.get(Utility.mapColor.get(color)), newCard);
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
            if (grid.get(row).get(i) != null)
                costs.append("│").append(StringUtility.center(Arrays.toString(grid.get(row).get(i).getCosts()), 14));
            else
                costs.append("│").append(StringUtility.center("", 14));
        }
        costs.append("│");
        return costs.toString();
    }

    private String assertLengthProductionCost(int row){
        StringBuilder productionCost = new StringBuilder(" ");
        for (int i = 0; i<4; i++) {
            if (grid.get(row).get(i) != null)
                productionCost.append("│").append(StringUtility.center(Arrays.toString(grid.get(row).get(i).
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
            if (grid.get(row).get(i) != null)
                productionValue.append("│").append(StringUtility.center(Arrays.toString(grid.get(row).get(i).
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
            if (grid.get(row).get(i) != null)
                victoryPoints.append("│").append(StringUtility.center(String.format("%2d", grid.get(row).get(i).getVictoryPoints()),14));
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
