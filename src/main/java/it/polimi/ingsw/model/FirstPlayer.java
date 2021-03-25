package it.polimi.ingsw.model;

/**
 * this class is the starting point of the game,
 * after it the first player will decide to start a single player or multiplayer game
 */


import java.util.Scanner;

public class FirstPlayer extends Player{

    public int playersNumber(){
        System.out.println(" \n How many players?\n");
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
}
