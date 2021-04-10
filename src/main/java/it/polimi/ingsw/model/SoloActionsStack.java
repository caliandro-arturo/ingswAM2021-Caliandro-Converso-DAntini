package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class simulates the behaviour of the Solo Action stack for single player games
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

    public void pick(Game game) {
        SoloAction token = soloActions.get(actionPointer++);
        token.act(game, this);
    }

    public void reset() {
        actionPointer = 0;
        Collections.shuffle(soloActions);
    }
    //------------------------------------------------------------------------------------------------------------------

    public ArrayList<SoloAction> getSoloActions() {
        return soloActions;
    }
}
