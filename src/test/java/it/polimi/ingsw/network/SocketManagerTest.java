package it.polimi.ingsw.network;

import it.polimi.ingsw.client.CLI.CLI;
import it.polimi.ingsw.common_files.message.toServer.SetNickname;
import it.polimi.ingsw.server.ServerMain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SocketManagerTest {
    ServerMain server = new ServerMain(1234);
    CLI alex = new CLI(), george = new CLI(), jenny = new CLI(), anne = new CLI();
    Thread serverThread, alexThread, georgeThread, jennyThread, anneThread;

    @BeforeEach
    void setUp() {
        serverThread = new Thread(server::startServer);
        serverThread.start();
        alexThread = new Thread(() -> {
            try {
                alex.connectToServer("127.0.0.1", 1234);
            } catch (IOException ignore) {
            }
            alex.initializeCli();
        });
        georgeThread = new Thread(() -> {
            try {
                george.connectToServer("127.0.0.1", 1234);
            } catch (IOException ignore) {
            }
            george.initializeCli();
        });
        jennyThread = new Thread(() -> {
            try {
                jenny.connectToServer("127.0.0.1", 1234);
            } catch (IOException ignore) {
            }
            jenny.initializeCli();
        });
        anneThread = new Thread(() -> {
            try {
                anne.connectToServer("127.0.0.1", 1234);
            } catch (IOException ignore) {
            }
            anne.initializeCli();
        });
        alexThread.start();
        georgeThread.start();
        jennyThread.start();
        anneThread.start();
    }

    @AfterEach
    void tearDown() {
        alexThread.interrupt();
        georgeThread.interrupt();
        jennyThread.interrupt();
        anneThread.interrupt();
        serverThread.interrupt();
    }

    @Test
    void messageExchanging() throws IOException {
        /*alex.getSocketManager().sendMessage(new SetNickname("alex"));*/
    }
}