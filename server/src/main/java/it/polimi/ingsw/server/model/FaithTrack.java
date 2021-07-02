package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.messages.toClient.updates.LorenzoPosition;
import it.polimi.ingsw.commonFiles.messages.toClient.updates.VaticanReport;

import java.util.HashMap;

/**
 * Represents the faith path.
 */
public class FaithTrack {

    private Player player;
    private int position = 0;
    private int scoreCard = 0;
    private final HashMap<Integer, Boolean> vaticanReportsMap = new HashMap<Integer, Boolean>() {{
        put(8, null);
        put(16, null);
        put(24, null);
    }};

    public FaithTrack(Player player) {
        this.player = player;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setScoreCard(int scoreCard) {
        this.scoreCard = scoreCard;
    }

    public HashMap<Integer, Boolean> getVaticanReportsMap() {
        return vaticanReportsMap;
    }

    public int getPosition() {
        return position;
    }

    public int getScoreCard() {
        return scoreCard;
    }

    private Game game;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    /**
     * Increases the position of the Faith Marker on the faith track, if it's in 8,16 or 24 triggers a vatican report.
     */
    public void increasePosition() {
        if (this.position < 24) {
            this.position++;
            notifyPositionChange();
            checkPosition();
        }
    }

    /**
     * Notifies the player about the update of his position.
     */
    public void notifyPositionChange() {
        game.getViewAdapter().incrementFaithTrackPosition(player);
    }

    /**
     * Checks the behavior of each position.
     */
    public void checkPosition() {
        if (this.position == 8 || this.position == 16 || this.position == 24) {
            if (!game.getVaticanMap().get(this.position)) {
                game.vaticanReport(this.position);
            }
            if (this.position == 24) {
                game.getViewAdapter().notifyLastTurn("someone has reached the finish line");
                game.setOver(true);
            }
        }
    }


    /**
     * Checks if a player is in a papal space and adds victory points to it.
     *
     * @param papalSpace identify the Pope space we are checking for
     */
    public void isInVatican(int papalSpace) {
        VaticanReport vaticanReport = new VaticanReport();
        vaticanReport.setPlayer(player.getUsername());
        boolean isPassed = false;
        switch (papalSpace) {
            case 8 -> {
                vaticanReport.setNum(1);
                if (position > 4 && position < 9) {
                    scoreCard += 2;
                    isPassed = true;
                }
            }
            case 16 -> {
                vaticanReport.setNum(2);
                if (position > 11 && position < 17) {
                    scoreCard += 3;
                    isPassed = true;
                }
            }
            case 24 -> {
                vaticanReport.setNum(3);
                if (position > 18 && position < 25) {
                    scoreCard += 4;
                    isPassed = true;
                }
            }
        }
        vaticanReport.setPassed(isPassed);
        vaticanReportsMap.replace(papalSpace, isPassed);
        game.getViewAdapter().sendMessage(vaticanReport);
    }

    @Override
    public String toString() {
        return """
                {
                    "position": %d,
                    "vaticanReport": {
                        "1": %s,
                        "2": %s,
                        "3": %s
                    }
                }""".formatted(
                position,
                vaticanReportsMap.get(8),
                vaticanReportsMap.get(16),
                vaticanReportsMap.get(24));
    }
}
