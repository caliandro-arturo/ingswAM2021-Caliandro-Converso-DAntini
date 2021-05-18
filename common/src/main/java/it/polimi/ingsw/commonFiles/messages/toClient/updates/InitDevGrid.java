package it.polimi.ingsw.commonFiles.messages.toClient.updates;

import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.messages.toClient.ToClientMessageVisitor;
import it.polimi.ingsw.commonFiles.model.Production;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;

import java.util.ArrayList;

public class InitDevGrid extends Message implements GameUpdate {
    private ArrayList<String> colors;
    private ArrayList<UtilityProductionAndCost[]> costs;
    private ArrayList<Integer> levels;
    private ArrayList<Integer> victoryPoints;
    private ArrayList<Production> productions;

    public InitDevGrid(ArrayList<String> colors, ArrayList<UtilityProductionAndCost[]> costs, ArrayList<Integer> levels,
                       ArrayList<Integer> victoryPoints, ArrayList<Production> productions) {
        this.colors = colors;
        this.costs = costs;
        this.levels = levels;
        this.victoryPoints = victoryPoints;
        this.productions = productions;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public ArrayList<UtilityProductionAndCost[]> getCosts() {
        return costs;
    }

    public ArrayList<Integer> getLevels() {
        return levels;
    }

    public ArrayList<Integer> getVictoryPoints() {
        return victoryPoints;
    }

    public ArrayList<Production> getProductions() {
        return productions;
    }

    @Override
    public void accept(ToClientMessageVisitor v) {
        v.visit(this);
    }

    @Override
    public void accept(UpdateHandler v) {
        v.visit(this);
    }
}
