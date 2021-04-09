package it.polimi.ingsw.model;

import java.util.Scanner;

/**
 * Basic type of production power
 */
public class ProductionPower implements Production{
    private final UtilityProductionAndCost[] cost;
    private final UtilityProductionAndCost[] production;


    public ProductionPower(UtilityProductionAndCost[] cost, UtilityProductionAndCost[] production){
        this.cost = cost;
        this.production = production;
    }

    /**
     * standard operation for the production in the game
     */
    @Override
    public UtilityProductionAndCost[] startProduction(PersonalBoard board){
        manageCost(board);
        return production;
    }

    private void manageCost(PersonalBoard board){
        for (UtilityProductionAndCost utilityProductionAndCost : cost) {
            System.out.println("Cost" + utilityProductionAndCost.getResource());
            int j = utilityProductionAndCost.getQuantity();
            while (j != 0) {
                if (askPlayerWhereTakeResource()) {
                    board.getPersonalBox().removeResource(utilityProductionAndCost.getResource());
                } else {
                    board.removeResource(utilityProductionAndCost.getResource());
                }
                j--;
            }
        }
    }
    private boolean askPlayerWhereTakeResource(){
        while (true) {
            System.out.println("Where you want to take the resource ?");
            Scanner scanner = new Scanner(System.in);
            String whatStore = scanner.nextLine();
            if (whatStore.equals("strongbox")) {
                return true;
            } else if (whatStore.equals("warehouse")) {
                return false;
            } else {
                System.out.println("Error, please write again");
            }
        }
    }
}
