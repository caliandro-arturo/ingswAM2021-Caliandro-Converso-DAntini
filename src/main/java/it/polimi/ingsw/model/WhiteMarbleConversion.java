package it.polimi.ingsw.model;

public class WhiteMarbleConversion implements LeaderPower{
    private Color color;

    public Color getColor() {
        return color;
    }

    @Override
    public void getPower() {
        System.out.println("Convert the white marble in" +getColor());
    }

    @Override
    public void activatePower(Player player) {
        player.addWhiteAlt(getColor());
    }
}
