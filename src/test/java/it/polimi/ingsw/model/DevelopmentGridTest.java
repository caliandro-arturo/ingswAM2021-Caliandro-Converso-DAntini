package it.polimi.ingsw.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DevelopmentGridTest {

    UtilityProductionAndCost utest = new UtilityProductionAndCost(1,Resource.COIN);
    UtilityProductionAndCost[] uarray = new UtilityProductionAndCost[]{utest};
    ProductionPower production= new ProductionPower(uarray,uarray);
    DevelopmentCard cglv1 = new DevelopmentCard(1,1,Color.GREEN,uarray,production);
    DevelopmentCard cglv2 = new DevelopmentCard(1,2,Color.GREEN,uarray,production);
    DevelopmentCard cglv3 = new DevelopmentCard(1,3,Color.GREEN,uarray,production);
    DevelopmentCard cblv1 = new DevelopmentCard(1,1,Color.BLUE,uarray,production);
    DevelopmentCard cblv2 = new DevelopmentCard(1,2,Color.BLUE,uarray,production);
    DevelopmentCard cblv3 = new DevelopmentCard(1,3,Color.BLUE,uarray,production);
    DevelopmentCard cylv1 = new DevelopmentCard(1,1,Color.YELLOW,uarray,production);
    DevelopmentCard cylv2 = new DevelopmentCard(1,2,Color.YELLOW,uarray,production);
    DevelopmentCard cylv3 = new DevelopmentCard(1,3,Color.YELLOW,uarray,production);
    DevelopmentCard cplv1 = new DevelopmentCard(1,1,Color.PURPLE,uarray,production);
    DevelopmentCard cplv2 = new DevelopmentCard(1,2,Color.PURPLE,uarray,production);
    DevelopmentCard cplv3 = new DevelopmentCard(1,3,Color.PURPLE,uarray,production);
    DevelopmentCard[] totcard = new DevelopmentCard[]{cglv1,cglv2,cglv3,cblv1,cblv2,cblv3,cylv1,cylv2,cylv3,cplv1,cplv2,cplv3};

    DevelopmentGrid d1 = new DevelopmentGrid(totcard);

    @Test
    public void TestBuy(){
        assertEquals(cglv1,d1.buyCard(Color.GREEN,1));
    }
}
