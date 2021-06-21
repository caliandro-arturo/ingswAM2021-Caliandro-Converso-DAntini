package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.client.ClientModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class CLIViewTest {

    CLIView view;
    String[] names = {"faith path VP", "development card VP", "leader card VP", "pope's favor VP", "resource VP"};
    int[] scores = {3,15,6,2,4};

    @BeforeEach
    void setUp() {
        ClientModel clientModel = new ClientModel();
        clientModel.setPlayerUsername("pippo");
        view = new CLIView(){
            @Override
            public ClientModel getModel() {
                return clientModel;
            }
            @Override
            public void activateProduction(String[] commandSlice) {
                String argument;
                try {
                    argument = commandSlice[1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Wrong syntax: use the format \"ACTIVATEPRODUCTION: <id>, <arg1>, ...\".");
                    return;
                }
                String[] arguments = argument.split("\\s*,\\s*");
                String[] elements;
                ArrayList<Integer> cost = new ArrayList<>();
                int ID = Integer.parseInt(arguments[0]);
                try {
                    if (ID < 0)
                        throw new IllegalArgumentException("Invalid ID");
                    if (ID == 0) {
                        elements = arguments[3].split("\\s");
                        for (String element : elements) {
                            cost.add(Integer.parseInt(element));
                        }
                        elements = arguments[1].split("\\s");
                    } else if (ID <= 3) {
                        elements = arguments[1].split("\\s");
                        for (String element : elements) {
                            cost.add(Integer.parseInt(element));
                        }
                    } else if (ID <= 5) {
                        cost.add(Integer.parseInt(arguments[1]));
                    }
                } catch (NumberFormatException e) {
                    System.err.println("You must insert a number.");
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }
        };
    }

    @Test
    void displayEndingScore() {
        view.displayEndingScore(names,scores,1);
    }

    @Test
    void productionCmd(){
        String[] commandSlice = {"activateproduction", "1 ,2 2"};
        String[] commandSlice1 = {"activateproduction", "4, 1 , STONE"};
        String[] commandSlice2 = {"activateproduction", "0 ,STONE SERF, COIN, 1 1" };
        view.activateProduction(commandSlice);
        view.activateProduction(commandSlice1);
        view.activateProduction(commandSlice2);

    }
}