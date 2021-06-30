package it.polimi.ingsw.client.GUI;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlayerScorePair {
    private final StringProperty player = new SimpleStringProperty();
    private final IntegerProperty score = new SimpleIntegerProperty();

    public PlayerScorePair(String player, int score) {
        this.player.set(player);
        this.score.set(score);
    }

    public String getPlayer() {
        return player.get();
    }

    public StringProperty playerProperty() {
        return player;
    }

    public int getScore() {
        return score.get();
    }

    public IntegerProperty scoreProperty() {
        return score;
    }
}
