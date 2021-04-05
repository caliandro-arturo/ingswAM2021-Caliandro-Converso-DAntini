package it.polimi.ingsw.model;

import java.util.Collections;

public enum LorenzoAction {
    DELYELLOW((game, lorenzoActions) -> game.getDevelopmentGrid().removeCard(Color.YELLOW)),
    DELGREEN((game, lorenzoActions) -> game.getDevelopmentGrid().removeCard(Color.GREEN)),
    DELPURPLE((game, lorenzoActions) -> game.getDevelopmentGrid().removeCard(Color.PURPLE)),
    DELBLUE((game, lorenzoActions) -> game.getDevelopmentGrid().removeCard(Color.BLUE)),
    TWOPOSITIONS((game, lorenzoActions) -> game.getPlayers().get(1).getBoard().getPersonalPath()
            .setPosition(game.getPlayers().get(1).getBoard().getPersonalPath().getPosition() + 2)),
    ONEPOSITIONRESET((game, lorenzoActions) -> {
        game.getPlayers().get(1).getBoard().getPersonalPath()
                .setPosition(game.getPlayers().get(1).getBoard().getPersonalPath().getPosition() + 1);
        lorenzoActions.reset();
    });
    GameAction action;
    @FunctionalInterface
    interface GameAction {
        void act(Game game, LorenzoActionsStack lorenzoActions);
    }
    LorenzoAction(final GameAction action) {
        this.action = action;
    }
}