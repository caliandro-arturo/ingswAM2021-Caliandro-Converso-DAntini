package it.polimi.ingsw.client.model;

import it.polimi.ingsw.commonFiles.utility.CLIColor;
import it.polimi.ingsw.commonFiles.model.Resource;

import java.util.ArrayList;

/**
 * light version of WarehouseStore class for representation purposes
 */
public class WarehouseStore {

    private ArrayList<ArrayList<Resource>> res = new ArrayList<ArrayList<Resource>>(){{
        add(new ArrayList<>());
        add(new ArrayList<>());
        add(new ArrayList<>());
    }};

    /**
     * setter for the resources in the warehouse store
     * @param resource the resource to add
     * @param position the number of the shelf to add in
     */
    public void setRes( Resource resource, int position){
        --position;
        this.res.get(position).add(resource);
    }

    /**
     * removes the first instance of the searched resource
     * @param pos store selected
     * @param resource the resource you want to remove
     */
    public void removeRes( int pos, Resource resource){
        res.get(--pos).remove(resource);
    }

    public Resource removeRes(int pos){
        Resource resource = res.get(--pos).get(0);
        res.get(pos).remove(0);
        return resource;
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


        if(res.get(0).isEmpty()){
            resString1= "│         /    _    \\      \n";
        }
        else
            resString1 = "│         /    "+res.get(0).get(0)+"    \\      \n";


        if(res.get(1).isEmpty()){
            resString2 = "│       /   _     _   \\    \n";
        }
        else
            if(res.get(1).size()==1)
                resString2 = "│       /   "+res.get(1).get(0)+"     _   \\    \n";
            else if(res.get(1).size()==2)
                resString2 = "│       /   "+res.get(1).get(0)+"     "+res.get(1).get(1)+"   \\    \n";

        if(res.get(2).isEmpty())
            resString3 ="│     /   _    _    _   \\  \n";
        else
            if(res.get(2).size()==1)
                resString3 = "│     /   "+res.get(2).get(0)+"    _    _   \\  \n";
            else if(res.get(2).size()==2)
                resString3 = "│     /   "+res.get(2).get(0)+"    "+res.get(2).get(1)+"    _   \\  \n";
            else if(res.get(2).size()==3)
                resString3 = "│     /   "+res.get(2).get(0)+"    "+res.get(2).get(1)+"    "+res.get(2).get(2)+"   \\  \n";

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
