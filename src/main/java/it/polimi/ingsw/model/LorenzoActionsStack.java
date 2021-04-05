package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class simulates the behaviour of the Solo Action stack for single player games
 */
public class LorenzoActionsStack {
    protected final ArrayList<LorenzoAction> lorenzoActions = new ArrayList<>();
    protected int actionPointer = 0;

    public LorenzoActionsStack() {
        lorenzoActions.add(LorenzoAction.DELBLUE);
        lorenzoActions.add(LorenzoAction.DELYELLOW);
        lorenzoActions.add(LorenzoAction.DELPURPLE);
        lorenzoActions.add(LorenzoAction.DELGREEN);
        lorenzoActions.add(LorenzoAction.TWOPOSITIONS);
        lorenzoActions.add(LorenzoAction.TWOPOSITIONS);
        lorenzoActions.add(LorenzoAction.ONEPOSITIONRESET);
        Collections.shuffle(lorenzoActions);
    }

    public void pick(Game game) {
        LorenzoAction token = lorenzoActions.get(actionPointer++);
        token.action.act(game, this);
    }

    public void reset() {
        actionPointer = 0;
        Collections.shuffle(lorenzoActions);
    }

}
