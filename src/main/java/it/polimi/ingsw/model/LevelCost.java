package it.polimi.ingsw.model;

public class LevelCost extends ColorCost{
    private int level;

    public LevelCost(Color color, Integer num, int level){
        super(color,num);
        this.level=level;
    }

    @Override
    public void checkRequirements(Player player) throws NotMetRequirementsException {

    }
}
