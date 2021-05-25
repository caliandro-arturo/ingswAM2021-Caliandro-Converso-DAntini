module common {
    requires com.google.gson;
    exports it.polimi.ingsw.commonFiles.model;
    exports it.polimi.ingsw.commonFiles.messages;
    exports it.polimi.ingsw.commonFiles.messages.toClient.updates;
    exports it.polimi.ingsw.commonFiles.messages.toClient;
    exports it.polimi.ingsw.commonFiles.messages.toServer;
    exports it.polimi.ingsw.commonFiles.utility;
    exports it.polimi.ingsw.commonFiles.network;
}