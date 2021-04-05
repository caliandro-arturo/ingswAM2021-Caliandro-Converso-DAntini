package it.polimi.ingsw.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;


class LorenzoActionsStackTest {
    private SinglePlayerGame game;
    private final Player player = new Player("test", new PersonalBoard(), null);
    private final Deck[] decks = new Deck[4];
    private final HashMap<Color, Integer> colorMap = new HashMap<Color, Integer>() {{
        put(Color.GREEN, 0);
        put(Color.BLUE, 1);
        put(Color.YELLOW, 2);
        put(Color.PURPLE, 3);
    }};
    private final DevelopmentGrid testGrid = new DevelopmentGrid(new DevelopmentCard[]{
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
        game = new SinglePlayerGame(player, null, null, testGrid);
        //Lorenzo Actions ordered for testing purposes
        game.lorenzoActions.lorenzoActions.set(0, LorenzoAction.DELGREEN);
        game.lorenzoActions.lorenzoActions.set(1, LorenzoAction.DELBLUE);
        game.lorenzoActions.lorenzoActions.set(2, LorenzoAction.DELYELLOW);
        game.lorenzoActions.lorenzoActions.set(3, LorenzoAction.DELPURPLE);
        game.lorenzoActions.lorenzoActions.set(4, LorenzoAction.TWOPOSITIONS);
        game.lorenzoActions.lorenzoActions.set(5, LorenzoAction.TWOPOSITIONS);
        game.lorenzoActions.lorenzoActions.set(6, LorenzoAction.ONEPOSITIONRESET);
    }

    /**
     * This method verify that all the Solo Actions are correctly executed
     */
    @Test
    public void actionsTest() {
        game.endTurn(player);                                           //DELGREEN
        assertEquals(0, decks[0].getDeck().size());
        assertEquals(1, game.lorenzoActions.actionPointer);     //stack pointer update check 1
        game.endTurn(player);                                           //DELBLUE
        assertEquals(0, decks[1].getDeck().size());
        game.endTurn(player);                                           //DELYELLOW
        assertEquals(0, decks[2].getDeck().size());
        game.endTurn(player);                                           //DELPURPLE
        assertEquals(0, decks[3].getDeck().size());
        game.endTurn(player);                                           //TWOPOSITIONS
        assertEquals(2, game.getPlayers().get(1).getBoard().getPersonalPath().getPosition());
        game.endTurn(player);                                           //again TWOPOSITIONS
        assertEquals(4, game.getPlayers().get(1).getBoard().getPersonalPath().getPosition());
        assertEquals(6, game.lorenzoActions.actionPointer);     //stack pointer update check 2
        game.endTurn(player);                                           //ONEPOSITIONRESET
        assertEquals(5, game.getPlayers().get(1).getBoard().getPersonalPath().getPosition());
    }
}