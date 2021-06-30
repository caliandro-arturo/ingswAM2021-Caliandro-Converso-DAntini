package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.client.ClientModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;

class CLIViewTest {

    CLIView view;
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
        LinkedHashMap<String, Integer> playersMap = new LinkedHashMap<>() {{
            put("michele", 25);
            put("giovanni", 12);
            put("pippo", 34);
            put("mirella", 2);
        }};
        view.displayEndingScore(scores, playersMap);
        LinkedHashMap<String, Integer> playersSoloMap = new LinkedHashMap<>() {{
            put("pippo", -1);
        }};
        view.displayEndingScore(scores, playersSoloMap);
        LinkedHashMap<String, Integer> playersWinningSoloMap = new LinkedHashMap<>() {{
            put("pippo", -2);
        }};
        view.displayEndingScore(scores, playersWinningSoloMap);
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