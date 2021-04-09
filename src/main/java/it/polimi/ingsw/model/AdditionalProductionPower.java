package it.polimi.ingsw.model;

import java.util.Scanner;

/**
 * Power of the leader that add a possible production
 */
public class AdditionalProductionPower  implements LeaderPower,Production{

    private final Resource cost;
    private final Resource production1 = Resource.FAITH;

    public AdditionalProductionPower(Resource cost){
        this.cost = cost;
    }

    public Resource getCost() {
        return cost;
    }

    @Override
    public UtilityProductionAndCost[] startProduction(PersonalBoard board) {
        manageCost(board);
        UtilityProductionAndCost[] production = new UtilityProductionAndCost[2];
        production[0] = new UtilityProductionAndCost(1,production1);
        production[1] = new UtilityProductionAndCost(1,askPlayerResourceType());

        return production;
    }

    @Override
    public void getPower() {
        System.out.println("add an additional production of a FAITH point and a chose resource for the cost of" +getCost());
    }

    @Override
    public void activatePower(Player player) {
        player.getBoard().getProductionList().add(this);
    }

    /**
     * remove the cost of a Production
     * @param board player's board
     */
    private void manageCost(PersonalBoard board){
        for (int i = 0; i<2; i++){
            System.out.println("Cost" + cost);
            if (askPlayerWhereTakeResource()) {
                board.getPersonalBox().removeResource(cost);
            } else {
                board.removeResource(cost);
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
