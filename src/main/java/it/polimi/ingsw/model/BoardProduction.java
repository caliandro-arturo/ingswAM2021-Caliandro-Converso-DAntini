package it.polimi.ingsw.model;

import java.util.Scanner;

/**
 * special Board Production
 */
public class BoardProduction implements Production{

    @Override
    public UtilityProductionAndCost[] startProduction(PersonalBoard board) {
        UtilityProductionAndCost[] costs = new UtilityProductionAndCost[2];
        costs[0] = new UtilityProductionAndCost(1,askPlayerResourceType());
        manageCost(costs[0],board);
        costs[1] = new UtilityProductionAndCost(1,askPlayerResourceType());
        UtilityProductionAndCost[] production = new UtilityProductionAndCost[1];
        manageCost(costs[1],board);
        production[0] = new UtilityProductionAndCost(1,askPlayerResourceType());
        return production;
    }

    /**
     * Remove the cost from player's resources
     * @param cost chosen by the player
     * @param board player's board
     */
    private void manageCost(UtilityProductionAndCost cost, PersonalBoard board){
        for (int i = 0; i<2; i++){
            System.out.println("Cost" + cost.getResource());
            if (askPlayerWhereTakeResource()) {
                board.getPersonalBox().removeResource(cost.getResource());
            } else {
                board.removeResource(cost.getResource());
            }
        }
    }

    /**
     * ask the player from where he want to take the resource
     * @return true if from the strongbox and false if is from the warehouse
     */
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

    /**
     * ask the player what type of resource he want to use for a special production
     * @return type of resource chosen
     */
    private Resource askPlayerResourceType(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chose a Resource");
        String whatResource = scanner.nextLine();
        if (whatResource.equals("coin")){
            return Resource.COIN;
        } else if (whatResource.equals("stone")){
            return Resource.STONE;
        } else if (whatResource.equals("serf")){
            return Resource.SERF;
        } else if (whatResource.equals("shield")){
            return Resource.SHIELD;
        } else {
            System.out.println("Error, please write again");
            return askPlayerResourceType();
        }
    }
}
