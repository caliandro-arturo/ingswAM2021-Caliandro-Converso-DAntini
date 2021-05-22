package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.model.Resource;

import java.util.ArrayList;

/**
 * This enumeration contains all the colors used by marbles and cards. For marbles, each color is associated with an
 * action to do when a player uses the market.
 */
public enum Color {
    BLUE(game -> game.getCurrentPlayer().getBoard().addResource(Resource.SHIELD)),
    GREY(game -> game.getCurrentPlayer().getBoard().addResource(Resource.STONE)),
    PURPLE(game -> game.getCurrentPlayer().getBoard().addResource(Resource.SERF)),
    YELLOW(game -> game.getCurrentPlayer().getBoard().addResource(Resource.COIN)),
    RED(game -> game.getCurrentPlayer().getBoard().getFaithTrack().increasePosition()),
    WHITE(game -> {
        ArrayList<Resource> whiteAlt = game.getCurrentPlayer().getWhiteAlt();
        if (whiteAlt.isEmpty())
            return;
        Resource resource;
        if (whiteAlt.size() == 1) {
            resource = whiteAlt.get(0);
            game.getCurrentPlayer().getBoard().addResource(resource);
        } else if (whiteAlt.size() == 2) {
            game.getCurrentPlayer().changeWhiteMarbleChoicesNumber(1);
        }
    }),
    GREEN(game -> {/*only used by DevelopmentCard*/});
    private final GameAction action;

    @FunctionalInterface
    private interface GameAction {
        void act(Game game);
    }

    /**
     * This method is called when a Marble is picked from the Market.
     *
     * @param game the game where the player picks the Marble
     */
    public void act(Game game) {
        action.act(game);
    }

    Color(GameAction action) {
        this.action = action;
    }


}
