package it.polimi.ingsw.model;

/**
 * this class represents the faith path,
 * each faith path is connected with a Composition link to the PersonalBoard
 */
public class FaithTrack {

    private int position =0;
    private int scoreCard =0;

    public void setPosition(int position) {
        this.position = position;
    }

    public void setScoreCard(int scoreCard) {
        this.scoreCard = scoreCard;
    }

    public int getPosition() {
        return position;
    }

    public int getScoreCard() {
        return scoreCard;
    }


}
