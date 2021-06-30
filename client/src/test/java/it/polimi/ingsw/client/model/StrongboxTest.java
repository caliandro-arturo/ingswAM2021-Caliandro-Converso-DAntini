package it.polimi.ingsw.client.model;

import org.junit.jupiter.api.Test;

class StrongboxTest {
    private final Strongbox strongbox = new Strongbox();
    @Test
    public void printTest(){
        strongbox.setResources(123,0);
        strongbox.setResources(77,1);
        strongbox.setResources(56,2);
        strongbox.setResources(34,3);
        System.out.println(strongbox);

    }

}