package it.polimi.ingsw.common_files.model;

import it.polimi.ingsw.client.CLI.CLIColor;
import it.polimi.ingsw.server.model.Game;

import java.util.ArrayList;

/**
 * This enumeration contains all the colors used by marbles and cards.
 * For marbles, each color is associated with an action to do when a
 * player uses the market.
 */
public enum Color {
    BLUE        (CLIColor.ANSI_BLUE + "█" + CLIColor.ANSI_RESET,
            game -> game.getCurrentPlayer().getBoard().addResource(Resource.SHIELD)),
    GREY        (CLIColor.ANSI_GREY + "█" + CLIColor.ANSI_RESET,
            game -> game.getCurrentPlayer().getBoard().addResource(Resource.STONE)),
    PURPLE      (CLIColor.ANSI_PURPLE + "█" + CLIColor.ANSI_RESET,
            game -> game.getCurrentPlayer().getBoard().addResource(Resource.SERF)),
    YELLOW      (CLIColor.ANSI_YELLOW + "█" + CLIColor.ANSI_RESET,
            game -> game.getCurrentPlayer().getBoard().addResource(Resource.COIN)),
    RED         (CLIColor.ANSI_RED + "█" + CLIColor.ANSI_RESET,
            game ->  game.getCurrentPlayer().getBoard().getFaithTrack().increasePosition()),
    WHITE       ("█",game -> {
                    ArrayList<Resource> whiteAlt = game.getCurrentPlayer().getWhiteAlt();
                    if(whiteAlt.isEmpty())
                        return;
                    Resource resource;
                    if(whiteAlt.size() == 1) {
                        resource = whiteAlt.get(0);
                        game.getCurrentPlayer().getBoard().addResource(resource);
                    } else if (whiteAlt.size() == 2) {
                        game.getCurrentPlayer().changeWhiteMarbleChoicesNumber(1);
                        game.getViewAdapter().askWhiteMarbleResource();
                    }
                }),
    GREEN       (CLIColor.ANSI_GREEN + "█" + CLIColor.ANSI_RESET, game -> {/*only used by DevelopmentCard*/});
    private final String representation;
    private final GameAction action;
    @FunctionalInterface
    private interface GameAction {
        void act(Game game);
    }

    @Override
    public String toString() {
        return representation;
    }

    /**
     * This method is called when a Marble is picked from the Market.
     * @param game the game where the player picks the Marble
     */
    public void act(Game game) {
        action.act(game);
    }
    Color(String representation, GameAction action) {
        this.representation = representation;
        this.action = action;
    }
}
