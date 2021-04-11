package it.polimi.ingsw.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;


class SoloActionsStackTest {
    SinglePlayerGame game;
    Player player = new Player("test");
    Deck[] decks = new Deck[4];
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
                if (!decks[colorMap.get(color)].getDeck().empty())
                    decks[colorMap.get(color)].getDeck().pop();
            }
        }
    };
    SoloActionPhase soloActionPhase;

    @BeforeEach
    void setUp() {
        decks[0] = new Deck();
        decks[0].getDeck().push(new DevelopmentCard(1,1, Color.GREEN,  null,null));
        decks[0].getDeck().push(new DevelopmentCard(1,1, Color.GREEN,  null,null));
        decks[1] = new Deck();
        decks[1].getDeck().push(new DevelopmentCard(1,1, Color.BLUE,   null,null));
        decks[1].getDeck().push(new DevelopmentCard(1,1, Color.BLUE,   null,null));
        decks[2] = new Deck();
        decks[2].getDeck().push(new DevelopmentCard(1,1, Color.YELLOW, null,null));
        decks[2].getDeck().push(new DevelopmentCard(1,1, Color.YELLOW, null,null));
        decks[3] = new Deck();
        decks[3].getDeck().push(new DevelopmentCard(1,1, Color.PURPLE, null,null));
        decks[3].getDeck().push(new DevelopmentCard(1,1, Color.PURPLE, null,null));
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
        assertEquals(0, decks[0].getDeck().size());
        assertEquals(1, soloActionPhase.getSoloActionsActionPointer());         //stack pointer update check 1
        soloActionPhase.start();                                                        //DELBLUE
        assertEquals(0, decks[1].getDeck().size());
        soloActionPhase.start();                                                        //DELYELLOW
        assertEquals(0, decks[2].getDeck().size());
        soloActionPhase.start();                                                        //DELPURPLE
        assertEquals(0, decks[3].getDeck().size());
        soloActionPhase.start();                                                        //TWOPOSITIONS
        assertEquals(2, game.getPlayers().get(1).getBoard().getPersonalPath().getPosition());
        soloActionPhase.start();                                                        //again TWOPOSITIONS
        assertEquals(4, game.getPlayers().get(1).getBoard().getPersonalPath().getPosition());
        assertEquals(6, soloActionPhase.getSoloActionsActionPointer());         //stack pointer update check 2
        soloActionPhase.start();                                                        //ONEPOSITIONRESET
        assertEquals(5, game.getPlayers().get(1).getBoard().getPersonalPath().getPosition());
    }
}