package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.utility.CLIColor;
import it.polimi.ingsw.commonFiles.utility.StringUtility;
import javafx.beans.property.*;

import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Faith Track representation for client.
 */
public class FaithTrack {
    private final IntegerProperty position = new SimpleIntegerProperty(0);
    private final IntegerProperty positionB = new SimpleIntegerProperty(-1);
    private final HashMap<Integer, ObjectProperty<Boolean>> vaticanMap = new HashMap<>(){{
        put(1, new SimpleObjectProperty<>(null));
        put(2, new SimpleObjectProperty<>(null));
        put(3, new SimpleObjectProperty<>(null));
    }};

    public HashMap<Integer, ObjectProperty<Boolean>> getVaticanMap() {
        return vaticanMap;
    }

    public int getPosition() {
        return position.get();
    }

    public IntegerProperty positionProperty() {
        return position;
    }

    public int getPositionB() {
        return positionB.get();
    }

    public void setVaticanMap(int num, Boolean passed){
        vaticanMap.get(num).set(passed);
    }

    public void addPosition(){
        position.set(position.get() + 1);
    }

    public void setPosition(int position) {
        this.position.set(position);
    }

    public void setPositionB(int positionB) {
        this.positionB.set(positionB);
    }

    public IntegerProperty positionBProperty() {
        return positionB;
    }

    public void addLorenzoPositionByOne(){
        positionB.set(positionB.get() + 1);
    }

    /**
     * representation method FaithTrack (CLI)
     */
    @Override
    public String toString() {
        StringBuilder faithArt = new StringBuilder();
        Function<Integer, String> cellPadder = i -> StringUtility.center("", i * 4 - 1);
        StringBuilder faithVP = new StringBuilder();
        faithVP.append(CLIColor.ANSI_GREEN)
                .append(cellPadder.apply(4)).append("│VP:1│")
                .append(cellPadder.apply(2)).append("  │VP:2│")
                .append(cellPadder.apply(2)).append("  │VP:4│")
                .append(cellPadder.apply(2)).append("  │VP:6│")
                .append(cellPadder.apply(2)).append("  │VP:9│")
                .append(cellPadder.apply(2)).append("  │VP:12│")
                .append(cellPadder.apply(2)).append(" │VP:16│")
                .append(cellPadder.apply(2)).append("│VP:20│")
                .append(CLIColor.ANSI_RESET);
        String cross = CLIColor.ANSI_RED + "╬" + CLIColor.ANSI_RESET;
        String crossB = "╬";
        Function<Integer, String> crossOrInt = i -> {
            StringBuilder result = new StringBuilder();
            if (i == positionB.get())
                result.append(crossB);
            if (i == position.get())
                result.append(cross);
            else if (result.toString().equals(""))
                result.append(i);
            return result.toString();
        };
        Function<Integer, Boolean> isInVaticanSector = i -> i >= 5 && i <= 8 || i >= 12 && i <= 16 || i >= 19;
        BiFunction<String, CLIColor, String> stringPainter = (str, color) -> color + str + CLIColor.ANSI_RESET;
        StringBuilder[] faithPositions = new StringBuilder[3];
        for (int i = 0; i < 3; i++) {
            faithPositions[i] = new StringBuilder();
        }
        for (int i = 0; i <= 24; i++) {
            faithPositions[0].append(
                    (isInVaticanSector.apply(i) ? stringPainter.apply("╦════", CLIColor.ANSI_YELLOW) : "╦════"));
            faithPositions[1].append(
                    (isInVaticanSector.apply(i) ? stringPainter.apply("║", CLIColor.ANSI_YELLOW) : "║"));
                    String content = crossOrInt.apply(i);
                    faithPositions[1].append(
                            StringUtility.center((content.contains("╬") ?
                                    content : (isInVaticanSector.apply(i) ? stringPainter.apply(content, CLIColor.ANSI_YELLOW) : content)), 4)
                    );
            faithPositions[2]
                    .append((isInVaticanSector.apply(i) ? stringPainter.apply("╩════", CLIColor.ANSI_YELLOW) : "╩════"));
        }
        faithPositions[0].append(stringPainter.apply("╦", CLIColor.ANSI_YELLOW)).append("\n");
        faithPositions[1].append(stringPainter.apply("║", CLIColor.ANSI_YELLOW)).append("\n");
        faithPositions[2].append(stringPainter.apply("╩", CLIColor.ANSI_YELLOW)).append("\n");
        faithVP.append(
                StringUtility.center("", StringUtility.realLength(faithPositions[2].toString()) - StringUtility.realLength(faithVP.toString()))
        ).append("\n");
        StringBuilder faithFT = new StringBuilder();

        for (int i = 1; i <= 3; i++) {
            faithFT.append(cellPadder.apply(7)).append(stringPainter.apply("FT: " + i, CLIColor.ANSI_YELLOW)).append("  ");
            Boolean key = vaticanMap.get(i).get();
            if (key == null) {
                faithFT.append(stringPainter.apply("-", CLIColor.ANSI_GREY));
            } else if (key) {
                faithFT.append(stringPainter.apply("V", CLIColor.ANSI_GREEN));
            } else
                faithFT.append(stringPainter.apply("X", CLIColor.ANSI_RED));
            faithFT.append(stringPainter.apply("   (┼)", CLIColor.ANSI_YELLOW));
        }
        faithFT.append(StringUtility.center("", StringUtility.realLength(faithPositions[2].toString()) - StringUtility.realLength(faithFT.toString()))
        ).append("\n");
        faithArt.append(faithVP).append(faithPositions[0]).append(faithPositions[1]).append(faithPositions[2]).append(faithFT);
        return faithArt.toString();
    }
}
