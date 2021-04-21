package it.polimi.ingsw.model;

public class ActivateProdPhase extends TurnPhase {
    public ActivateProdPhase(Game game) {
        super(game, "Activate productions phase", false);
    }

    @Override
    public void start() {

    }

    @Override
    public String getPhaseInfo() {
        return null;
    }

    @Override
    public TurnPhase nextTurnPhase() {
        PersonalBoard board = getGame().getCurrentPlayer().getBoard();
        board.getPersonalBox().emptyProdBox();
        for (int i = 0; i < board.getProductionList().size(); i++){
            board.getProductionList().get(i).setProductionCanBeActivate(true);
        }
        for (int i = 0; i < board.getPersonalDevelopmentSpace().length; i++){
            if (!board.getPersonalDevelopmentSpace()[i].getDevelopmentCards().empty()){
                board.getPersonalDevelopmentSpace()[i].getDevelopmentCards().peek().getProduction()
                        .setProductionCanBeActivate(true);
            }
        }
        return getGame().getTurnPhase("UseAgainLeader");
    }
}
