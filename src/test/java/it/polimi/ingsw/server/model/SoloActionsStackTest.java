package it.polimi.ingsw.server.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;


class SoloActionsStackTest {
    SinglePlayerGame game;
    Player player = new Player("test");
    ArrayList<Stack<DevelopmentCard>> decks = new ArrayList<>();
    HashMap<Color, Integer> colorMap = new HashMap<Color, Integer>() {{
        put(Color.GREEN, 0);
        put(Color.BLUE, 1);
        put(Color.YELLOW, 2);
        put(Color.PURPLE, 3);
    }};
    DevelopmentGrid testGrid = new DevelopmentGrid(new DevelopmentCard[]{
            new DevelopmentCard(1, 1, Color.GREEN, null, null)
    }) {
        @Override
        public void removeCard(Color color) {
            for (int i = 0; i < 2; i++) {
                if (!decks.get(colorMap.get(color)).empty())
                    decks.get(colorMap.get(color)).pop();
            }
        }
    };
    SoloActionPhase soloActionPhase;

    @BeforeEach
    void setUp() {
        decks.add(new Stack<>());
        decks.get(0).push(new DevelopmentCard(1,1, Color.GREEN,  null,null));
        decks.get(0).push(new DevelopmentCard(1,1, Color.GREEN,  null,null));
        decks.add(new Stack<>());
        decks.get(1).push(new DevelopmentCard(1,1, Color.BLUE,   null,null));
        decks.get(1).push(new DevelopmentCard(1,1, Color.BLUE,   null,null));
        decks.add(new Stack<>());
        decks.get(2).push(new DevelopmentCard(1,1, Color.YELLOW, null,null));
        decks.get(2).push(new DevelopmentCard(1,1, Color.YELLOW, null,null));
        decks.add(new Stack<>());
        decks.get(3).push(new DevelopmentCard(1,1, Color.PURPLE, null,null));
        decks.get(3).push(new DevelopmentCard(1,1, Color.PURPLE, null,null));
        game = new SinglePlayerGame(player, 1,null, null, testGrid);
        soloActionPhase = new SoloActionPhase(game);
        //Lorenzo Actions ordered for testing purposes
        soloActionPhase.getSoloActions().set(0, SoloAction.DELGREEN);
        soloActionPhase.getSoloActions().set(1, SoloAction.DELBLUE);
        soloActionPhase.getSoloActions().set(2, SoloAction.DELYELLOW);
        soloActionPhase.getSoloActions().set(3, SoloAction.DELPURPLE);
        soloActionPhase.getSoloActions().set(4, SoloAction.TWOPOSITIONS);
        soloActionPhase.getSoloActions().set(5, SoloAction.TWOPOSITIONS);
        soloActionPhase.getSoloActions().set(6, SoloAction.ONEPOSITIONRESET);
    }

    /**
     * This method verify that all the Solo Actions are correctly executed
     */
    @Test
    public void actionsTest() {
        soloActionPhase.start();                                                        //DELGREEN
        assertEquals(0, decks.get(0).size());
        assertEquals(1, soloActionPhase.getSoloActionsActionPointer());         //stack pointer update check 1
        soloActionPhase.start();                                                        //DELBLUE
        assertEquals(0, decks.get(1).size());
        soloActionPhase.start();                                                        //DELYELLOW
        assertEquals(0, decks.get(2).size());
        soloActionPhase.start();                                                        //DELPURPLE
        assertEquals(0, decks.get(3).size());
        soloActionPhase.start();                                                        //TWOPOSITIONS
        assertEquals(3, game.getPlayers().get(1).getBoard().getPersonalPath().getPosition());
        soloActionPhase.start();                                                        //again TWOPOSITIONS
        assertEquals(5, game.getPlayers().get(1).getBoard().getPersonalPath().getPosition());
        assertEquals(6, soloActionPhase.getSoloActionsActionPointer());         //stack pointer update check 2
        soloActionPhase.start();                                                        //ONEPOSITIONRESET
        assertEquals(6, game.getPlayers().get(1).getBoard().getPersonalPath().getPosition());
    }
}