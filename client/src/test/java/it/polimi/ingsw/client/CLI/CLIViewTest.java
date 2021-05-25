package it.polimi.ingsw.client.CLI;

import it.polimi.ingsw.client.ClientController;
import it.polimi.ingsw.client.ClientModel;
import it.polimi.ingsw.client.ClientSocketManager;
import it.polimi.ingsw.commonFiles.messages.Message;
import it.polimi.ingsw.commonFiles.utility.StringUtility;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import static org.junit.jupiter.api.Assertions.*;

class CLIViewTest {

    CLIView view;
    String[] names = {"faith path VP", "development card VP", "leader card VP", "pope's favor VP", "resource VP"};
    int[] scores = {3,15,6,2,4};

    @BeforeEach
    void setUp() {
        ClientModel clientModel = new ClientModel();
        clientModel.setPlayerUsername("pippo");
        view = new CLIView(){
            @Override
            public ClientModel getModel() {
                return clientModel;
            }
        };
    }

    @Test
    void displayEndingScore() {
        view.displayEndingScore(names,scores,1);
    }
}