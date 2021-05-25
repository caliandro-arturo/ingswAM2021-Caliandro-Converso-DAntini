package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.model.Resource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class LeaderHandTest {
    LeaderCard card1;
    LeaderCard card2;
    LeaderCard card3;
    LeaderCard card4;
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


        card1 = new LeaderCard(1, type1,power1);
        card2 = new LeaderCard(2, type1, power1);
        card3 = new LeaderCard(3, type1, power1);
        card4 = new LeaderCard(4, type1, power1);
        leaderCards.add(card1);
        //leaderCards.add(card2);
        //leaderCards.add(card3);
        leaderCards.add(card4);
        leaderHand = new LeaderHand(leaderCards);


        System.out.println(leaderHand);
    }
}