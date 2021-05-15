package it.polimi.ingsw.network;

import it.polimi.ingsw.client.CLI.CLI;
import it.polimi.ingsw.client.CLI.CLIView;
import it.polimi.ingsw.common_files.message.toServer.SetNickname;
import it.polimi.ingsw.common_files.network.SocketManager;
import it.polimi.ingsw.server.ServerMain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

class SocketManagerTest {
    ServerMain server = new ServerMain(1234);
    CLI alex = new CLI(), george = new CLI(), jenny = new CLI(), anne = new CLI();
    Thread serverThread, alexThread, georgeThread, jennyThread, anneThread;
    ExecutorService executor = Executors.newCachedThreadPool();

    @BeforeEach
    void setUp() {
        serverThread = new Thread(server::startServer);
        serverThread.start();
        alexThread = new Thread(() -> {
            alex.setView(new CLIView());
            alex.configureConnection("");
            alex.initializeClient();
        });
        georgeThread = new Thread(() -> {
            george.setView(new CLIView());
            george.configureConnection("");
            george.initializeClient();
        });
        jennyThread = new Thread(() -> {
            jenny.setView(new CLIView());
            jenny.configureConnection("");
            jenny.initializeClient();
        });
        anneThread = new Thread(() -> {
            anne.setView(new CLIView());
            anne.configureConnection("");
            anne.initializeClient();
        });
        executor.submit(alexThread);
        executor.submit(georgeThread);
        executor.submit(jennyThread);
        executor.submit(anneThread);
    }

    @AfterEach
    void tearDown() {
        executor.shutdown();
        serverThread.interrupt();
    }

    @Test
    void messageExchanging() throws IOException {
        /*Future<SocketManager> alexSocketManager = alex.getSocketManager();
        alex.getSocketManager().sendMessage(new SetNickname("alex"));*/
    }
}