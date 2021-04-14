package it.polimi.ingsw.model;

/**
 * this class represents the faith path,
 * each faith path is connected with a Composition link to the PersonalBoard
 */
public class FaithTrack {

    private int position = 1;
    private int scoreCard = 0;

    public void setPosition(int position) { this.position = position;    }
    public void setScoreCard(int scoreCard) { this.scoreCard = scoreCard;    }
    public int getPosition() {        return position;    }
    public int getScoreCard() { return scoreCard; }
    private Game game;


    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    /**
     * this method increase the position of the Faith Marker in the faith track,
     * if it's in 8,16 or 24 invokes the vaticanReport method
     */
    public void increasePosition(){
        if(this.position<24) {
            this.position++;
            checkPosition();

        }
    }

    public void checkPosition(){
        if (this.position == 8 || this.position == 16 || this.position == 24) {
            if (!game.getVaticanMap().get(this.position)) {
                game.vaticanReport(this.position);
            }
        }
    }


    /**
     * this method check if a player is in a papal space and adds victory points to it
     * @param papalSpace
     */
    public void isInVatican(int papalSpace){
        switch(papalSpace){
            case 8:
                if(this.position>4) {
                    this.scoreCard = this.scoreCard +2;
                }
            case 16:
                if(this.position>11) {
                    this.scoreCard = this.scoreCard +3;
                }
            case 24:
                if(this.position>18) {
                    this.scoreCard = this.scoreCard +4;
                }

        }

    }
}
