package it.polimi.ingsw.server.model;

/**
 * this class represents the faith path,
 * each faith path is connected with a Composition link to the Board
 */
public class FaithTrack {

    private Player player;
    private int position = 1;
    private int scoreCard = 0;

    public FaithTrack(Player player) {
        this.player = player;
    }

    public void setPosition(int position) { this.position = position;    }
    public void setScoreCard(int scoreCard) { this.scoreCard = scoreCard;    }
    public int getPosition() {        return position;    }
    public int getScoreCard() { return scoreCard; }
    private Game game;

    public void setPlayer(Player player) {
        this.player = player;
    }

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
            game.getViewAdapter().incrementFaithTrackPosition(player);
            checkPosition();
        }
    }

    /**
     * checks the behavior of each position
     */
    public void checkPosition(){
        if (this.position == 8 || this.position == 16 || this.position == 24) {
            if (!game.getVaticanMap().get(this.position)) {
                game.vaticanReport(this.position);
            }
            if (this.position == 24){
                game.getViewAdapter().notifyLastTurn("someone has reached the finish line");
                game.setOver(true);
            }
        }
    }


    /**
     * this method check if a player is in a papal space and adds victory points to it
     * @param papalSpace identify the Pope space we are checking for
     */
    public boolean isInVatican(int papalSpace){
        switch(papalSpace){
            case 8:
                if(position>4 && position<9) {
                    scoreCard += 2;
                    return true;
                }else
                    return false;
            case 16:
                if(position>11 && position<17) {
                    scoreCard += 3;
                    return true;
                }else
                    return false;
            case 24:
                if(position>18 && position<25) {
                    scoreCard += 4;
                    return true;
                }

        }
        return false;

    }
}
