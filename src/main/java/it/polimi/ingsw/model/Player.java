package it.polimi.ingsw.model;

/**
 * this class implements all the methods used by a player
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private String username;
    private PersonalBoard board;
    private LeaderCard[] leaderCard;
    private ArrayList<Resource> whiteAlt = new ArrayList<>();
    private ArrayList<Resource> sale = new ArrayList<>();
    private Game game;

    public Player(String username){
        this.username = username;
        this.board = new PersonalBoard();
    }

    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
        board.setGame(game);
        board.getPersonalPath().setGame(game);
    }
    public void setBoard(PersonalBoard board) {
        this.board = board;
    }
    public void setLeaderCard(LeaderCard[] leaderCard) { this.leaderCard = leaderCard;    }
    public void setUsername(String username) {        this.username = username;    }
    public void setSale(ArrayList<Resource> sale) { this.sale = sale;    }
    public void setWhiteAlt(ArrayList<Resource> whiteAlt) { this.whiteAlt = whiteAlt;    }

    public LeaderCard[] getLeaderCard() { return leaderCard;    }
    public PersonalBoard getBoard() {        return board;    }
    public String getUsername() {        return username;    }
    public ArrayList<Resource> getWhiteAlt() { return whiteAlt; }
    public ArrayList<Resource> getSale() { return sale; }
    public void addWhiteAlt(Resource resource) {
        whiteAlt.add(resource);
    }

    public void addSale(Resource resource) {
        sale.add(resource);
    }

    /**
     * this method is used in the beginning of the game to discard 2 leaderCards of your choice
     * or during a player turn
     * @param leader
     *
     */
    public void discardLeaderCard(LeaderCard leader){

    }

    /**
     * buying method of Development Cards
     * @param devCard
     * @return
     */
    public void buyDevelopmentCard (DevelopmentCard devCard){

    }


    /**
     * this method is used to activate a leader power
     * @param leader
     */
    public void useLeader(LeaderCard leader){

    }


    /**
     * this method is used to activate the base production or the development card production
     * @param productionPower
     * @return
     */
    public Resource activateProduction(ProductionPower productionPower){

        Resource resource = null;
        return null;
    }




}

