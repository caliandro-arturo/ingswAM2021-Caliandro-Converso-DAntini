package it.polimi.ingsw.client.model;

import java.util.ArrayList;
import java.util.Stack;


/**
 * light version of DevelopmentPlace class for representation purposes
 */
public class DevelopmentPlace {

    private final ArrayList<Stack<DevelopmentCard>> devStack;

    public DevelopmentPlace() {
        devStack = new ArrayList<>();
        devStack.add(new Stack<>());
        devStack.add(new Stack<>());
        devStack.add(new Stack<>());
    }

    /**
     * setter for the DevelopmentCard
     * @param developmentCard : card to insert
     * @param pos : position of the spot to insert in
     */
    public void setDevStack(DevelopmentCard developmentCard, int pos){
        this.devStack.get(--pos).push(developmentCard);
    }


    public DevelopmentCard getTopCard(int pos){
        return this.devStack.get(--pos).peek();
    }

    /**
     * representation method for Development Place (CLI)
     * @return String with the representation
     */
    @Override
    public String toString() {
        StringBuilder devPlaceArt = new StringBuilder();
        StringBuilder[] devString = new StringBuilder[3];
        devString[0]= new StringBuilder();
        devString[1]= new StringBuilder();
        devString[2]= new StringBuilder();
        int i=0;
        String space = "       ";
        String emptyCard =
                "┌─────────────────┐\n"+
                "│                 │\n"+
                "│                 │\n"+
                "│                 │\n"+
                "│                 │\n"+
                "│                 │\n"+
                "│                 │\n"+
                "│                 │\n"+
                "│                 │\n"+
                "└─────────────────┘\n";
        String emptySpace = "                   \n"+"                   \n"+"                   \n";
        String[] temp1;
        String[] temp2;
        String[] temp3;

        for(Stack<DevelopmentCard> stacks: devStack){
            if(stacks.isEmpty()) {
                devString[i].append(emptyCard);
                devString[i].append(emptySpace).append(emptySpace);
            }
            else{
                devString[i].append(stacks.peek().toString());
                switch (stacks.size()){
                    case 1:
                        devString[i].append(emptySpace).append(emptySpace);
                        break;
                    case 2:
                        int l = stacks.get(0).toString().length();
                        devString[i].append(stacks.get(0).toString(), l - 58, l);
                        devString[i].append(emptySpace);
                        break;
                    case 3:
                        int len = stacks.get(0).toString().length();
                        devString[i].append(stacks.get(0).toString(), len - 58, len);
                        devString[i].append(stacks.get(1).toString(), len - 58, len);
                        break;
                    }

                }
            i++;}
        temp1=devString[0].toString().split("\n");
        temp2=devString[1].toString().split("\n");
        temp3=devString[2].toString().split("\n");

        for (int j = 0; j < 14; j++) {
            devPlaceArt.append(temp1[j]).append(space).append(temp2[j]).append(space).append(temp3[j]);
            devPlaceArt.append("\n");
        }
        return devPlaceArt.toString();
    }
}
