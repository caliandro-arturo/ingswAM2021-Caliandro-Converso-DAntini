package it.polimi.ingsw.server.model;

import java.util.ArrayList;

/**
 * Lorenzo's action phase.
 */
public class SoloActionPhase extends TurnPhase {
    private final SoloActionsStack soloActions = new SoloActionsStack();

    public SoloActionPhase(Game game) {
        super(game, "Solo Action", true);
    }

    @Override
    public void start() {
        getGame().getViewAdapter().announceTurnPhase(getGame().getCurrentPlayer(), getName(), getPhaseInfo());
        soloActions.pick(getGame());
    }

    @Override
    public String getPhaseInfo() {
        return "In this turn phase a Solo Action token will be revealed from the pile " +
                "and there will be an effect.";
    }

    @Override
    public TurnPhase nextTurnPhase() {
        if (!getGame().getDevelopmentGrid().isStillWinnable()) {
            getGame().setOver(true);
        }
        return getGame().getTurnPhase("UseLeader");
    }

    public ArrayList<SoloAction> getSoloActions() {
        return soloActions.getSoloActions();
    }

    public int getSoloActionsActionPointer() {
        return soloActions.actionPointer;
    }

    public void setActionPointer(int actionPointer) {
        soloActions.setActionPointer(actionPointer);
    }
}
