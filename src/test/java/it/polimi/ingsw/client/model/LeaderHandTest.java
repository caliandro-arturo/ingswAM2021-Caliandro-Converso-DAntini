package it.polimi.ingsw.client.model;

import it.polimi.ingsw.common_files.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LeaderHandTest {
    LeaderCard card1;
    LeaderCard card2;
    LeaderHand leaderHand ;
    public AdditionalProductionPower production ;

    @Test
    public void printTest(){
        production = new AdditionalProductionPower(Resource.COIN);
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        Color[] colors = new Color[]{Color.GREEN,Color.BLUE};
        Integer[] quantity = new Integer[]{2,1};
        Requirements type1 = new ColorCost(colors,quantity);
        LeaderPower power1 = production;

        leaderHand = new LeaderHand(leaderCards);
        card1 = new LeaderCard(1, type1,power1);
        leaderCards.add(card1);
        leaderCards.add(card1);
        leaderCards.add(card1);
        leaderCards.add(card1);
        leaderHand.discardLeaderCard(card1);
        leaderHand.discardLeaderCard(card1);

        System.out.println(leaderHand);
    }
}