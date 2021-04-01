package it.polimi.ingsw.model;

public class AdditionalProductionPower  implements LeaderPower,Production{

    private Resource cost;
    private Resource production1 = Resource.FAITH;

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

    /*
    TODO create an exception for resource not found
     */

    private void manageCost(PersonalBoard board) {
        if(askPlayerWhatDeposit(board)){
            board.getPersonalBox().getResourceMap().remove(cost,1);
        } else {
            //board.getStore()
        }
    }

    private boolean askPlayerWhatDeposit(PersonalBoard board){
        return true;
    }

    private Resource askPlayerResourceType(){
        return null;
    }
}
