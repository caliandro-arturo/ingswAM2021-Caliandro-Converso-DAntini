package it.polimi.ingsw.common_files.message.toServer.actions;

import it.polimi.ingsw.common_files.message.Message;
import it.polimi.ingsw.common_files.message.toServer.ToServerMessage;
import it.polimi.ingsw.common_files.message.toServer.ToServerMessageHandler;

import java.util.ArrayList;

public class StartProduction extends Message implements ToServerMessage {
    private int ID;
    private ArrayList<Integer> cost;
    private String production;
    private String[] costResource;

    public int getID() {
        return ID;
    }

    public ArrayList<Integer> getCost() {
        return cost;
    }

    public String getProduction() {
        return production;
    }

    public String[] getCostResource() {
        return costResource;
    }

    public StartProduction(int ID, ArrayList<Integer> cost, String production, String[] costResource) {
        this.ID = ID;
        this.cost = cost;
        this.production = production;
        this.costResource = costResource;
    }

    public StartProduction(int ID, ArrayList<Integer> cost, String production) {
        this.ID = ID;
        this.cost = cost;
        this.production = production;
    }

    public StartProduction(int ID, ArrayList<Integer> cost) {
        this.ID = ID;
        this.cost = cost;
    }

    @Override
    public void accept(ToServerMessageHandler v) {
        v.visit(this);
    }
}
