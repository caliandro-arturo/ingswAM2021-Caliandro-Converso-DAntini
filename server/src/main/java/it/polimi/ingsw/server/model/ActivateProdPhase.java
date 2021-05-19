package it.polimi.ingsw.server.model;

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
        Board board = getGame().getCurrentPlayer().getBoard();
        board.getStrongbox().emptyProdBox();
        for (int i = 0; i < board.getProductionList().size(); i++){
            board.getProductionList().get(i).setProductionCanBeActivate(true);
        }
        for (int i = 0; i < board.getDevelopmentSpace().length; i++){
            if (!board.getDevelopmentSpace()[i].getDevelopmentCards().empty()){
                board.getDevelopmentSpace()[i].getDevelopmentCards().peek().getProduction()
                        .setProductionCanBeActivate(true);
            }
        }
        return getGame().getTurnPhase("UseAgainLeader");
    }
}