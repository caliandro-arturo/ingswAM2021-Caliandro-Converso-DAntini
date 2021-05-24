package it.polimi.ingsw.commonFiles.messages.toClient;

import it.polimi.ingsw.commonFiles.messages.Message;


public class DisplayEndingScores extends Message implements ToClientMessage {
    private int[] points;
    private int ranking;
    private String[] names = {"faith path VP",
            "development card VP", "leader card VP", "pope's favor VP", "resource VP"};

    public DisplayEndingScores(int[] points, int ranking) {
        this.points = points;
        this.ranking = ranking;
    }

    public int[] getPoints() {
        return points;
    }

    public int getRanking() {
        return ranking;
    }

    public String[] getNames() {
        return names;
    }

    @Override
    public void accept(ToClientMessageVisitor v) {
        v.visit(this);
    }
}
