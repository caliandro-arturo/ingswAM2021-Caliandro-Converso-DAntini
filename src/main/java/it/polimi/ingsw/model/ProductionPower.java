package it.polimi.ingsw.model;

import java.util.Scanner;

/**
 * Basic type of production power
 */
public class ProductionPower implements Production{
    private UtilityProductionAndCost[] cost;
    private UtilityProductionAndCost[] production;


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
        for (int i = 0; i<cost.length; i++){
            System.out.println("Cost" +cost[i].getResource());
            int j = cost[i].getQuantity();
            while (j!=0) {
                if (askPlayerWhereTakeResource()) {
                    board.getPersonalBox().removeResource(cost[i].getResource());
                } else {
                    board.removeResource(cost[i].getResource());
                }
                j--;
            }
        }
    }
    private boolean askPlayerWhereTakeResource(){
        System.out.println("Where you want to take the resource ?");
        Scanner scanner = new Scanner(System.in);
        String whatStore = scanner.nextLine();
        if (whatStore.equals("strongbox")){
            return true;
        } else if (whatStore.equals("warehouse")){
            return false;
        } else {
            System.out.println("Error, please write again");
            return askPlayerWhereTakeResource();
        }
    }
}
