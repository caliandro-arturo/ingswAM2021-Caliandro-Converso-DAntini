package it.polimi.ingsw.server.model;

import com.google.gson.Gson;
import it.polimi.ingsw.commonFiles.model.Production;
import it.polimi.ingsw.commonFiles.model.ProductionPower;
import it.polimi.ingsw.commonFiles.model.UtilityProductionAndCost;

/**
 * this class represent a special type of card (Development type)
 *
 * level -> level of the card
 * color -> color of the card
 * cost -> cost in resource of the card
 * productionPower -> the power of production of the card
 * ID -> identifier of the card
 *
 */
public class DevelopmentCard {
    private final int victoryPoints;
    private final int level;
    private final Color color;
    private final UtilityProductionAndCost[] cost;
    private final Production productionPower;
    private final int ID;

    public DevelopmentCard(int victoryPoints,int ID, int level, Color color, UtilityProductionAndCost[] cost, ProductionPower productionPower){
        this.ID = ID;
        this.victoryPoints = victoryPoints;
        this.level = level;
        this.color = color;
        this.cost = cost;
        this.productionPower = productionPower;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }
    public int getID(){return ID;}
    public Color getColor(){
        return color;
    }
    public int getLevel(){
        return level;
    }
    public UtilityProductionAndCost[] getCost(){
        return cost;
    }
    public Production getProduction(){
        return productionPower;
    }
    public String getColorString(){
        return color.name();
    }

    @Override
    public String toString() {
        StringBuilder devCardJson = new StringBuilder();
        devCardJson.append("""
                {
                    "ID": %d,
                    "color": "%s",
                    "cost": [""".formatted(ID, color.name()));
        for (UtilityProductionAndCost upc : cost) {
            devCardJson.append("""
                    {
                        "resource": "%s",
                        "quantity": %d
                    },
                    """.formatted(
                    upc.getResource().name(),
                    upc.getQuantity()));
        }
        devCardJson.deleteCharAt(devCardJson.lastIndexOf(","));
        devCardJson.append("""
                ],
                    "production": {
                """);
        devCardJson.append("""
                    "cost": [""");
        for (UtilityProductionAndCost upc : productionPower.getCost()) {
            devCardJson.append("""
                        {
                            "resource": "%s",
                            "quantity": %d
                        },
                        """.formatted(
                    upc.getResource().name(),
                    upc.getQuantity()));
        }
        devCardJson.deleteCharAt(devCardJson.lastIndexOf(","));
        devCardJson.append("""
                    ],
                    "products": [""");
        for (UtilityProductionAndCost upc : productionPower.getProd()) {
            devCardJson.append("""
                        {
                            "resource": "%s",
                            "quantity": %d
                        },
                        """.formatted(
                    upc.getResource().name(),
                    upc.getQuantity()));
        }
        devCardJson.deleteCharAt(devCardJson.lastIndexOf(","));
        devCardJson.append("""
                    ]
                },
                    "victoryPoints": %d,
                    "level": %d
                }""".formatted(
                victoryPoints,
                level
        ));
        return devCardJson.toString();
    }
}
