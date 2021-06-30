package it.polimi.ingsw.commonFiles.messages.toClient;

import it.polimi.ingsw.commonFiles.messages.Message;

import java.util.LinkedHashMap;

public class EndingScores extends Message implements ToClientMessage {
    private LinkedHashMap<String, Integer> ranking;
    private int[] points;

    public EndingScores(int[] points, LinkedHashMap<String, Integer> ranking) {
        this.points = points;
        this.ranking = ranking;
    }

    public int[] getPoints() {
        return points;
    }

    public LinkedHashMap<String, Integer> getRanking() {
        return ranking;
    }

    @Override
    public void accept(ToClientMessageVisitor v) {
        v.visit(this);
    }
}
