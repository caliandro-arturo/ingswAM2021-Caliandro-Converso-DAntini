package it.polimi.ingsw.client.model;

import it.polimi.ingsw.client.CLI.CLI;
import it.polimi.ingsw.client.CLI.CLIColor;

import java.util.HashMap;

public class FaithTrack {
    private final int position;
    private HashMap<Integer, Boolean> vaticanMap = new HashMap<Integer, Boolean>();


    public FaithTrack(int position, HashMap<Integer, Boolean> vaticanMap){
        this.position = position;
        this.vaticanMap = vaticanMap;
    };

    @Override
    public String toString() {
        StringBuilder faithArt = new StringBuilder("");
        String f = "     ";
        int ft=1;

        String cross = CLIColor.ANSI_RED +"╬" + CLIColor.RESET;
        faithArt.append(CLIColor.ANSI_GREEN+"           │VP:1│         │VP:2│         │VP:4│").
                append("         │VP:6│         │VP:9│        │VP:12│").
                append("         │VP:16│        │VP:20│\n"+CLIColor.RESET);
        faithArt.append(" ");
        for (int i = 1; i < 25; i++) {
            if (i > 4 && i < 9 || i > 11 && i < 17 || i > 18) {
                faithArt.append(CLIColor.ANSI_YELLOW + "╦════" + CLIColor.RESET);
            } else
                faithArt.append("╦════");

        }
        faithArt.append(CLIColor.ANSI_YELLOW + "╦" + CLIColor.RESET);
        faithArt.append("\n");

        for (int j = 1; j < 25; j++) {
            final boolean b = j > 4 && j < 9 || j > 11 && j < 17 || j > 18;
            if (j == position) {
                if(b) {
                    faithArt.append(CLIColor.ANSI_YELLOW + " ║ "+cross+" " + CLIColor.RESET);
                }
                else
                    faithArt.append(" ║ "+cross+" ");

            } else if (b) {
                if (j < 10)
                    faithArt.append(CLIColor.ANSI_YELLOW + " ║ "+j+" " + CLIColor.RESET);
                else
                    faithArt.append(CLIColor.ANSI_YELLOW + " ║ "+ j + CLIColor.RESET);
            } else if (j < 10)
                faithArt.append(" ║ "+j+" ");
            else
                faithArt.append(" ║ "+j);
        }
        faithArt.append(CLIColor.ANSI_YELLOW + " ║" + CLIColor.RESET);
        faithArt.append("\n ");

        for (int k = 1; k < 25; k++) {
            if (k > 4 && k < 9 || k > 11 && k < 17 || k > 18) {
                faithArt.append(CLIColor.ANSI_YELLOW + "╩════" + CLIColor.RESET);
            } else
                faithArt.append("╩════");
        }
        faithArt.append(CLIColor.ANSI_YELLOW + "╩\n" + CLIColor.RESET);


        for (Boolean key: vaticanMap.values()){

            if(key){
                faithArt.append(f+f+f+f+f+CLIColor.ANSI_YELLOW+"  FT:"+ft+"  "+CLIColor.ANSI_GREEN+"✔"+
                        CLIColor.ANSI_YELLOW+"   (┼)");
            }else
                faithArt.append(f+f+f+f+f+CLIColor.ANSI_YELLOW+"  FT:"+ft+"  "+CLIColor.ANSI_RED+"❌"+
                        CLIColor.ANSI_YELLOW+"   (┼)");
            ft++;
        }

        return faithArt.toString();
    }
}
