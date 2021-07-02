package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.messages.toClient.updates.LorenzoPick;
import it.polimi.ingsw.commonFiles.model.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Simulates the behaviour of the Solo Action stack for single player games.
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
     *
     * @param game the game (a single player one) in which the Solo Action has effect
     */
    public void pick(Game game) {
        SoloAction token = soloActions.get(actionPointer++);
        if (token.name().startsWith("DEL")) {
            Color color = Utility.mapColor.get(token.name().substring(3));
            boolean flag = checkIfLorenzoDiscardCardOfDifferentLevel(game.getDevelopmentGrid(), color);
            Card card = game.getDevelopmentGrid().lorenzoCardsUpdate(color, flag);
            token.act(game, this);
            game.getViewAdapter().sendMessage(new LorenzoPick(token.name(), flag, card));
        } else {
            token.act(game, this);
            game.getViewAdapter().sendMessage(new LorenzoPick(token.name(), false));
        }
    }

    private boolean checkIfLorenzoDiscardCardOfDifferentLevel(DevelopmentGrid grid, Color color) {
        return grid.lorenzoUpdate(color);
    }

    /**
     * Resets the Solo Action stack.
     */
    public void reset() {
        actionPointer = 0;
        Collections.shuffle(soloActions);
    }

    public ArrayList<SoloAction> getSoloActions() {
        return soloActions;
    }

    public void setActionPointer(int actionPointer) {
        this.actionPointer = actionPointer;
    }
}
