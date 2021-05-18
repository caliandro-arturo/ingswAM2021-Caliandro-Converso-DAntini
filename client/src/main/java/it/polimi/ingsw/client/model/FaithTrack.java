package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.utility.CLIColor;

import java.util.HashMap;

/**
 * light version of FaithTrack class for representation purposes
 */
public class FaithTrack {
    private int position;
    private int positionB = 0;
    private HashMap<Integer, Boolean> vaticanMap = new HashMap<Integer, Boolean>(){{
        put(1, false);
        put(2, false);
        put(3, false);
    }};


    public FaithTrack(int position){
        this.position = position;
    }

    public void addPosition(){
        this.position++;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setPositionB(int positionB) {
        this.positionB = positionB;
    }

    /**
     * representation method FaithTrack (CLI)
     * @return String with the representation
     */
    @Override
    public String toString() {
        StringBuilder faithArt = new StringBuilder("");
        String f = "     ";
        int ft=1;

        String cross = CLIColor.ANSI_RED +"╬" + CLIColor.ANSI_RESET;
        String crossB = CLIColor.ANSI_RESET+"\u001B[40m" +"╬" + CLIColor.ANSI_RESET;

        faithArt.append(CLIColor.ANSI_GREEN+"           │VP:1│         │VP:2│         │VP:4│").
                append("         │VP:6│         │VP:9│        │VP:12│").
                append("         │VP:16│        │VP:20│"+CLIColor.ANSI_RESET+"\n");
        faithArt.append(" ");
        for (int i = 1; i < 25; i++) {
            if (i > 4 && i < 9 || i > 11 && i < 17 || i > 18) {
                faithArt.append(CLIColor.ANSI_YELLOW + "╦════" + CLIColor.ANSI_RESET);
            } else
                faithArt.append("╦════");

        }
        faithArt.append(CLIColor.ANSI_YELLOW + "╦" + CLIColor.ANSI_RESET);
        faithArt.append("\n");

        for (int j = 1; j < 25; j++) {
            final boolean b = j > 4 && j < 9 || j > 11 && j < 17 || j > 18;
            if(j == position && j==positionB){
                if(b){
                    faithArt.append(CLIColor.ANSI_YELLOW + " ║ "+cross+crossB + CLIColor.ANSI_RESET);
                }
                else
                    faithArt.append(" ║ "+cross+crossB);
            }
            else if (j == position) {
                if(b) {
                    faithArt.append(CLIColor.ANSI_YELLOW + " ║ "+cross+" " + CLIColor.ANSI_RESET);
                }
                else
                    faithArt.append(" ║ "+cross+" ");

            }
            else if (j == positionB){
                if(b){
                    faithArt.append(CLIColor.ANSI_YELLOW + " ║ "+crossB+" " + CLIColor.ANSI_RESET);
                }
                else
                    faithArt.append(" ║ "+crossB+" ");
            }
            else if (b) {
                if (j < 10)
                    faithArt.append(CLIColor.ANSI_YELLOW + " ║ "+j+" " + CLIColor.ANSI_RESET);
                else
                    faithArt.append(CLIColor.ANSI_YELLOW + " ║ "+ j + CLIColor.ANSI_RESET);
            } else if (j < 10)
                faithArt.append(" ║ "+j+" ");
            else
                faithArt.append(" ║ "+j);
        }
        faithArt.append(CLIColor.ANSI_YELLOW + " ║" + CLIColor.ANSI_RESET);
        faithArt.append("\n ");

        for (int k = 1; k < 25; k++) {
            if (k > 4 && k < 9 || k > 11 && k < 17 || k > 18) {
                faithArt.append(CLIColor.ANSI_YELLOW + "╩════" + CLIColor.ANSI_RESET);
            } else
                faithArt.append("╩════");
        }
        faithArt.append(CLIColor.ANSI_YELLOW + "╩" + CLIColor.ANSI_RESET+"\n");


        for (Boolean key: vaticanMap.values()){
            if(key){
                faithArt.append(f+f+f+f+f+CLIColor.ANSI_YELLOW+"  FT:"+ft+"  "+CLIColor.ANSI_GREEN+"V"+
                        CLIColor.ANSI_YELLOW+"   (┼)"+CLIColor.ANSI_RESET);
            }else
                faithArt.append(f+f+f+f+f+CLIColor.ANSI_YELLOW+"  FT:"+ft+"  "+CLIColor.ANSI_RED+"X"+
                        CLIColor.ANSI_YELLOW+"   (┼)"+CLIColor.ANSI_RESET);
            ft++;
        }

        return faithArt.toString();
    }
}
