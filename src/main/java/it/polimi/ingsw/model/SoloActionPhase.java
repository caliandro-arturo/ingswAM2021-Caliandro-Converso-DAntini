package it.polimi.ingsw.model;

import java.util.ArrayList;

public class SoloActionPhase extends TurnPhase {
    private final SoloActionsStack soloActions = new SoloActionsStack();
    public SoloActionPhase(Game game) {
        super(game, "Solo Action phase", false);
    }

    @Override
    public void start() {
        soloActions.pick(getGame());
        this.nextTurnPhase();
    }

    @Override
    public String getPhaseInfo() {
        return "In this turn phase you have to reveal a Solo Action token from the pile " +
                "and there will be an effect based on the token you have picked.";
    }
    @Override
    public TurnPhase nextTurnPhase() {
        if (!getGame().getDevelopmentGrid().isStillWinnable()){
            getGame().setOver(true);
        }
        return getGame().getTurnPhase("UseLeader");
    }
    //------------------------------------------------------------------------------------------------------------------
    /* for debug purposes */

    public ArrayList<SoloAction> getSoloActions() {
        return soloActions.getSoloActions();
    }
    public int getSoloActionsActionPointer() {
        return soloActions.actionPointer;
    }
}
