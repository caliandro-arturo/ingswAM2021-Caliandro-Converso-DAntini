package it.polimi.ingsw.model;

public class ChooseActionPhase extends TurnPhase {
    public ChooseActionPhase(Game game) {
        super(game);
    }

    @Override
    public void start() {
        getGame().getViewMethodsCaller().askTurnPhaseAction();
    }

    @Override
    public String getPhaseInfo() {
        return "In this turn phase you have to choose an action between:\n" +
                "-Use the Market: choose a row or a column from the Market and earn resources;\n" +
                "-Buy a Development Card: use your resources to buy a new Development Card to insert in your board;\n" +
                "-Activate productions: use your resources to produce other resources.";
    }
}
