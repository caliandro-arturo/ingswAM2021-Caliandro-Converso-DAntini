package it.polimi.ingsw.server.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * This class simulates the behaviour of the Solo Action stack for single player games.
 */
public class SoloActionsStack {
    protected final ArrayList<SoloAction> soloActions = new ArrayList<>();
    protected int actionPointer = 0;

    public SoloActionsStack() {
        soloActions.add(SoloAction.DELBLUE);
        soloActions.add(SoloAction.DELYELLOW);
        soloActions.add(SoloAction.DELPURPLE);
        soloActions.add(SoloAction.DELGREEN);
        soloActions.add(SoloAction.TWOPOSITIONS);
        soloActions.add(SoloAction.TWOPOSITIONS);
        soloActions.add(SoloAction.ONEPOSITIONRESET);
        Collections.shuffle(soloActions);
    }

    /**
     * Picks a Solo action from the stack, like {@link Stack#pop()}.
     * @param game the game (a single player one) in which the Solo Action has effect
     */
    public void pick(Game game) {
        SoloAction token = soloActions.get(actionPointer++);
        token.act(game, this);
    }

    /**
     * Resets the Solo Action stack.
     */
    public void reset() {
        actionPointer = 0;
        Collections.shuffle(soloActions);
    }
    //------------------------------------------------------------------------------------------------------------------
    /* for debug purposes */
    public ArrayList<SoloAction> getSoloActions() {
        return soloActions;
    }
}
