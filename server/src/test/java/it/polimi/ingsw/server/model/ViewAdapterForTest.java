package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.messages.Message;
import org.junit.jupiter.api.BeforeAll;

import java.util.LinkedHashMap;

/**
 * An empty view adapter used to bypass network in tests.
 */
public class ViewAdapterForTest {
    public static ViewAdapter testView;

    @BeforeAll
    static void setUp() {
        testView = new ViewAdapter() {
            @Override
            public void sendMessage(Player player, Message message) {
            }

            @Override
            public void sendMessage(Message message) {
            }

            @Override
            public void notifyInitialResourcesAmount(Player player, int resourceQuantity) {
            }

            @Override
            public void sendLeaderUpdate(LeaderCard leaderCard) {
            }

            @Override
            public void askLeaderAction() {
            }

            @Override
            public void giveTurnPhaseInfo(String information) {
            }

            @Override
            public void notifyGameEnded(String player, int[] points, LinkedHashMap<String, Integer> ranking) {
            }

            @Override
            public void announceTurnPhase(Player player, String turnPhaseName, String turnPhaseInfo) {
            }

            @Override
            public void notifyLastTurn(String reason) {
            }

            @Override
            public void sendTable() {
            }

            @Override
            public void sendTable(Player player) {
            }

            @Override
            public void sendLeaderHand(Player player) {
            }

            @Override
            public void incrementFaithTrackPosition(Player player) {
            }
        };
    }
}
