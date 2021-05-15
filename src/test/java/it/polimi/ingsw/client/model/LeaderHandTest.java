package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.Resource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class LeaderHandTest {
    LeaderCard card1;
    LeaderCard card2;
    LeaderHand leaderHand ;
    public AdditionalProductionPower production ;

    @Test
    public void printTest(){
        production = new AdditionalProductionPower(Resource.COIN);
        ArrayList<LeaderCard> leaderCards = new ArrayList<>();
        ArrayList<Color> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        ArrayList<Integer> quantity = new ArrayList<>();
        quantity.add(2);
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