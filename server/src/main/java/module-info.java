module it.polimi.ingsw.server {
    requires it.polimi.ingsw.common;
    requires com.google.gson;
    opens it.polimi.ingsw.server to com.google.gson;
    opens it.polimi.ingsw.server.model to com.google.gson;
}