package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This enumeration contains all the colors used by marbles and cards.
 * For marbles, each color has an action to be performed when a marble is in a selected row or column in the market.
 */
public enum Color {
    BLUE(player -> player.getBoard().addResource(Resource.SHIELD)),
    GREY(player -> player.getBoard().addResource(Resource.STONE)),
    PURPLE(player -> player.getBoard().addResource(Resource.SERF)),
    YELLOW(player -> player.getBoard().addResource(Resource.COIN)),
    RED(player -> {
            FaithTrack path = player.getBoard().getPersonalPath();
            path.setPosition(path.getPosition() + 1);
    }),
    WHITE(player -> {
        ArrayList<Resource> whiteAlt = player.getWhiteAlt();
        if(whiteAlt.isEmpty())
            return;
        Resource resource = null;
        if(whiteAlt.size() == 1)
            resource = whiteAlt.get(0);
        else if(whiteAlt.size() == 2) {
            System.out.println("Choose a power:");
            AtomicInteger i = new AtomicInteger();
            whiteAlt.forEach(res -> System.out.println(i.incrementAndGet() + ": "
                    + res.name().toLowerCase()));
            Scanner in = new Scanner(System.in);
            resource = whiteAlt.get(in.nextInt() - 1);
        }
        player.getBoard().addResource(resource);
    }),
    GREEN(player -> {/*only used by DevelopmentCard*/});
    PlayerAction action;
    @FunctionalInterface
    interface PlayerAction {
        void act(Player player);
    }
    Color(PlayerAction action) {
        this.action = action;
    }
}
