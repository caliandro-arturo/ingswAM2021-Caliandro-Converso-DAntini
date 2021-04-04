package it.polimi.ingsw.model;

/**
 * class that take place for the development cards
 * each of the 3 places is connected with a Composition link to the PersonalBoard

 */
public class DevelopmentPlace {

    private DevelopmentCard level1Card;
    private DevelopmentCard level2Card;
    private DevelopmentCard level3Card;

    /**
     * this method is used to add a Development card in the Personal board and check if it's possible to do it
     * @param devCard
     */
    public void addCard(DevelopmentCard devCard){

    }

    public DevelopmentCard getLevel1Card() {
        return level1Card;
    }

    public DevelopmentCard getLevel2Card() {
        return level2Card;
    }

    public DevelopmentCard getLevel3Card() {
        return level3Card;
    }

    public DevelopmentCard getLevelCard(int level){
        if (level == 1){
            return getLevel1Card();
        } else if (level == 2){
            return getLevel2Card();
        } else {
            return getLevel3Card();
        }
    }
}

