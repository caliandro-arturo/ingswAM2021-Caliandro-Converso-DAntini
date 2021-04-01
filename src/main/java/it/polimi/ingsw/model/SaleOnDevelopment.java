package it.polimi.ingsw.model;

public class SaleOnDevelopment implements LeaderPower{
    private Resource resource;

    public Resource getResource() {
        return resource;
    }

    public SaleOnDevelopment(Resource resource){
        this.resource = resource;
    }

    @Override
    public void getPower() {
        System.out.println("Sale of an amount of one" +getResource());
    }

    @Override
    public void activatePower(Player player) {
        player.addSale(getResource());
    }
}
