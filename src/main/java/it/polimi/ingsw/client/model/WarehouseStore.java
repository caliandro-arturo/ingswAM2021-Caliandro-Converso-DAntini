package it.polimi.ingsw.client.model;

import it.polimi.ingsw.client.CLI.CLIColor;

import java.util.ArrayList;

/**
 * light version of WarehouseStore class for representation purposes
 */
public class WarehouseStore {
    private Resource res1;
    private ArrayList<Resource> res2 = new ArrayList<>();
    private ArrayList<Resource> res3 = new ArrayList<>();

    /**
     * setter for the single resource in the first shelf of the store
     * @param res1 : single resource in the first shelf
     */
    public void setRes1(Resource res1) {
        this.res1 = res1;
    }

    /**
     * setter for the two resources in the second shelf of the store
     * @param resource : resource to add in the second shelf
     */
    public void setRes2(Resource resource) {
        this.res2.add(resource);
    }

    /**
     * setter for the three resources in the third shelf of the store
     * @param resource : resource to add in the third shelf
     */
    public void setRes3(Resource resource) {
        this.res3.add(resource);
    }
    /**
     * representation method for Warehouse Store (CLI)
     * @return String with the representation
     */
    @Override
    public String toString(){
        StringBuilder storeArt = new StringBuilder("");
        String resString1 = "";
        String resString2 = "";
        String resString3 = "";


        if(res1==null){
            resString1= "│         /     _    \\      \n";
        }
        else
            resString1 = "│         /    "+res1+"    \\      \n";


        if(res2.isEmpty()){
            resString2 = "│       /   _     _   \\    \n";
        }
        else
            if(res2.size()==1)
                resString2 = "│       /   "+res2.get(0)+"     _   \\    \n";
            else if(res2.size()==2)
                resString2 = "│       /   "+res2.get(0)+"     "+res2.get(1)+"   \\    \n";

        if(res3.isEmpty())
            resString3 ="│     /   _    _    _   \\  \n";
        else
            if(res3.size()==1)
                resString3 = "│     /   "+res3.get(0)+"    _    _   \\  \n";
            else if(res3.size()==2)
                resString3 = "│     /   "+res3.get(0)+"    "+res3.get(1)+"    _   \\  \n";
            else if(res3.size()==3)
                resString3 = "│     /   "+res3.get(0)+"    "+res3.get(1)+"    "+res3.get(2)+"   \\  \n";

        storeArt.append("│       "+"\u001B[100m"+"Warehouse Store" + CLIColor.ANSI_RESET+ "    \n"+
                        "│          /───────\\       \n" + resString1 +
                        "│        /───────────\\     \n" + resString2 +
                        "│      /───────────────\\   \n" + resString3 +
                        "│    /───────────────────\\ \n"+
                        "│                          \n"
        );
        return storeArt.toString();
    }
}
