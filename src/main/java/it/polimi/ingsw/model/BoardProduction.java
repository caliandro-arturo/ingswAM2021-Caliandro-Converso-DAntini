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

    /**
     * ask the player what type of resource he want to use for a special production
     * @return type of resource chosen
     */
    private Resource askPlayerResourceType(){
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Chose a Resource");
            String whatResource = scanner.nextLine();
            switch (whatResource) {
                case "coin":
                    return Resource.COIN;
                case "stone":
                    return Resource.STONE;
                case "serf":
                    return Resource.SERF;
                case "shield":
                    return Resource.SHIELD;
                default:
                    System.out.println("Error, please write again");
                    break;
            }
        }
    }
}
