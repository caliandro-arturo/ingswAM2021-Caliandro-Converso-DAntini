package it.polimi.ingsw.server.model;

import it.polimi.ingsw.commonFiles.messages.Message;
import org.junit.jupiter.api.BeforeAll;

public class ViewAdapterForTest {
    public static ViewAdapter testView;

    @BeforeAll
    static void setUp() {
        testView = new ViewAdapter() {
            @Override
            public void sendMessage(Player player, Message message) {
            }

            @Override
            public void sendMessage(Player player, String message) {
            }

            @Override
            public void sendMessage(Message message) {
            }

            @Override
            public void notifyInitialResourcesAmount(Player player, int resourceQuantity) {
            }

            @Override
            public void askToUseTheMarket() {
            }

            @Override
            public void askLeaderAction() {
            }

            @Override
            public void giveTurnPhaseInfo(String information) {
            }

            @Override
            public void askWhichCardToBuyFromTheDevelopmentGrid() {
            }

            @Override
            public void notifyGameEnded(Player player, int ranking, int[] points) {
            }

            @Override
            public void notifyPlayerTurnNumber(Player player, int number) {
            }

            @Override
            public void announceTurnPhase(Player player, String turnPhaseName, String turnPhaseInfo) {
            }

            @Override
            public void notifyTurnPass() {
            }

            @Override
            public void sendErrorMessage(Message message, String error) {
            }

            @Override
            public void notifyLastTurn(String reason) {
            }

            @Override
            public void sendTable() {
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
