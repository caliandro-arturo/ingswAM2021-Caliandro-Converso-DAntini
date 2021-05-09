package it.polimi.ingsw.server.model;

/**
 * This enum contains all the Solo Action effects.
 */
public enum SoloAction {
    DELYELLOW           ((game, soloActions) -> game.getDevelopmentGrid().removeCard(Color.YELLOW)),
    DELGREEN            ((game, soloActions) -> game.getDevelopmentGrid().removeCard(Color.GREEN)),
    DELPURPLE           ((game, soloActions) -> game.getDevelopmentGrid().removeCard(Color.PURPLE)),
    DELBLUE             ((game, soloActions) -> game.getDevelopmentGrid().removeCard(Color.BLUE)),
    TWOPOSITIONS        ((game, soloActions) -> {
                            game.getPlayer(1).getBoard().getFaithTrack().increasePosition();
                            game.getPlayer(1).getBoard().getFaithTrack().increasePosition();
                        }),
    ONEPOSITIONRESET    ((game, soloActions) -> {
                            game.getPlayer(1).getBoard().getFaithTrack().increasePosition();
                            soloActions.reset();
                        });

    private final GameAndSoloAction action;

    @FunctionalInterface
    private interface GameAndSoloAction {
        void act(Game game, SoloActionsStack soloActions);
    }

    /**
     * This method is called when a Solo Action is picked from the Solo Action stack
     * @param game the single player game in which the Solo Action is to be performed
     * @param soloActions the Solo Actions stack
     */
    public void act(Game game, SoloActionsStack soloActions) {
        action.act(game, soloActions);
    }
    SoloAction(final GameAndSoloAction action) {
        this.action = action;
    }
}