package it.polimi.ingsw.client.GUI;

import it.polimi.ingsw.client.View;
import it.polimi.ingsw.client.model.Utility;
import it.polimi.ingsw.commonFiles.messages.toServer.*;
import it.polimi.ingsw.commonFiles.model.Resource;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Duration;

import java.util.*;

public class GUIView extends View {

    @Override
    public void show(String element) {
        Platform.runLater(() -> {
            switch (element) {
                case "hand", "board", "market" -> {
                }
                default -> {
                    App.out.setStyle("-fx-background-color: null");
                    App.out.setText(element);
                }
            }
        });
    }

    @Override
    public void showError(String error) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                show("");
            }
        };
        Timer timer = new Timer();
        Platform.runLater(() -> {
            App.out.setStyle("-fx-background-color: #d03c3c");
            App.out.setText(error);
        });
        timer.schedule(task, 4000);
    }

    @Override
    public void refresh(String... elements) {
        /*if (elements[0].equals("")) return;
        GamePanel gamePanel = (GamePanel) App.controller;
        switch (elements[0]) {
            case "hand", "board", "market" -> {}
        }*/
    }

    @Override
    public void setToDo(String id, String toDo) {
        Platform.runLater(()-> {
            try {
                GamePanel gamePanel = (GamePanel) App.controller;
                gamePanel.getToolTipHelp().setText(toDo);
            } catch (ClassCastException ignore) {
            }
        });
    }

    @Override
    public void showHandler(String[] commandSlice) {

    }

    @Override
    public void setNick(String[] commandSlice) {
        String nickname;
        nickname = commandSlice[1].trim();
        if (nickname.equals("")){
            App.out.setText("Empty nickname");
            return;
        }
        getController().sendMessage(new SetNickname(nickname));
    }

    @Override
    public void joinGame(String[] commandSlice) {

    }

    @Override
    public void setGame(String[] commandSlice) {

    }

    @Override
    public void choose(String[] commandSlice) {
        String turnPhase;
        try {
            turnPhase = commandSlice[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }
        getController().sendMessage(new ChooseTurnPhase(turnPhase.trim().toLowerCase()));
    }

    @Override
    public void activateProduction(String[] commandSlice) {
        String argument;
        try {
            argument = commandSlice[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }
        String[] arguments = argument.split("\\s*,\\s*");
        String[] elements;
        ArrayList<Integer> cost = new ArrayList<>();
        int ID = Integer.parseInt(arguments[0]);
            if (ID < 0)
                throw new IllegalArgumentException("Invalid ID");
            if (ID == 0) {
                elements = arguments[3].split("\\s");
                for (String element : elements) {
                    cost.add(Integer.parseInt(element));
                }
                elements = arguments[1].split("\\s");
                getController().sendMessage(new StartProduction(ID, cost, arguments[2], elements));
            } else if (ID <= 3) {
                elements = arguments[1].split("\\s");
                for (String element : elements) {
                    cost.add(Integer.parseInt(element));
                }
                getController().sendMessage(new StartProduction(ID, cost));
            } else if (ID <= 5) {
                cost.add(Integer.parseInt(arguments[1]));
                getController().sendMessage(new StartProduction(ID, cost, arguments[2]));
            }

    }

    @Override
    public void buyDevCard(String[] commandSlice) {
        String arg = commandSlice[1];
        String[] arguments = arg.split("\\s*,\\s*");
        int level, space;
        String color;
        ArrayList<Integer> stores = new ArrayList<>();
            level = Integer.parseInt(arguments[0]);
            color = arguments[1];
            space = Integer.parseInt(arguments[2]);
            arguments = arguments[3].split("\\s");
            for (String argument : arguments) {
                stores.add(Integer.parseInt(argument));
            }
            getController().sendMessage(new BuyCard(level, color, space, stores));
    }

    @Override
    public void useMarket(String[] commandSlice) {
        String argument = commandSlice[1];
        String[] args = argument.split("\\s*,\\s*");

        if (args[0].toLowerCase().matches("[rc]") && args.length == 2) {
                getController().sendMessage(new UseMarket(args[0].charAt(0), Integer.parseInt(args[1])));
        } else
            showError("Wrong parameter");
    }

    @Override
    public void chooseWhite(String[] commandSlice) {
        getModel().setWhiteMarbleQuantity(getModel().getWhiteMarbleQuantity() - 1);
        getController().sendMessage(new ChooseWhiteMarble(Integer.parseInt(commandSlice[1].trim())));
    }

    @Override
    public void useLeader(String[] commandSlice) {
        getController().sendMessage(new UseLeader(Integer.parseInt(commandSlice[1])));
    }

    @Override
    public void deployRes(String[] commandSlice) {
        String argument = commandSlice[1];
        String[] args = argument.split("\\s*,\\s*");
        getController().sendMessage(new DeployRes(Utility.mapResource.get(args[0].trim().toLowerCase()), Integer.parseInt(args[1])));
    }

    @Override
    public void discardLeader(String[] commandSlice) {
        getController().sendMessage(new DiscardLeader(Integer.parseInt(commandSlice[1])));
    }

    @Override
    public void takeRes(String[] commandSlice) {
        int depot;
        depot=Integer.parseInt(commandSlice[1]);
        getController().sendMessage(new TakeRes(depot));
    }

    @Override
    public void getRes(String[] commandSlice) {
        Resource res = Utility.mapResource.get(commandSlice[1].toLowerCase());
        getController().sendMessage(new GetResource(res));
    }

    @Override
    public void printHead() {

    }

    @Override
    public void deleteToDo(String id) {

    }

    @Override
    public void back() {
        getController().sendMessage(new Back());
    }

    @Override
    public void discardRes(String[] commandSlice) {
        String resource;
        resource = commandSlice[1].toLowerCase();
        if (Utility.mapResource.get(resource) != null){
            if (Utility.isStorable(Utility.mapResource.get(resource))){
                getController().sendMessage(new DiscardRes(Utility.mapResource.get(resource)));
                show("You have discarded a resource, each of your opponent earn a faith resource");
                }
            }
    }

    /**
     * Shows the end game pane with the final result, the ranking and specific victory points.
     * @param scores scores of
     * @param ranking
     */
    @Override
    public void displayEndingScore(int[] scores, LinkedHashMap<String, Integer> ranking) {
        Platform.runLater(() -> {
            GamePanel gamePanel = (GamePanel) App.controller;
            gamePanel.closePopup(null);
            gamePanel.setOver(true);
            int playerScore = ranking.get(getModel().getPlayerUsername());
            int position = ranking.keySet().stream().toList().indexOf(getModel().getPlayerUsername());
            boolean hasWon;
            if (playerScore < 0) {
                hasWon = playerScore == -2;
                gamePanel.setSpecificResultLabel("You " + (hasWon ? "win" : "lose") + ".");
                gamePanel.getSpecificResultLabel().setTranslateY(80);
            } else {
                hasWon = position == 0;
                List<String> ordinals = new ArrayList<>(Arrays.asList("first", "second", "third", "fourth"));
                gamePanel.setSpecificResultLabel("You got the " + ordinals.get(position) + " place.");
                ObservableList<PlayerScorePair> rankingForTableView = FXCollections.observableArrayList();
                ranking.forEach((player, score) -> rankingForTableView.add(new PlayerScorePair(player, score)));
                gamePanel.getRankingTabView().setDisable(false);
                gamePanel.getRankingTabView().setOpacity(1);
                gamePanel.getRankingTabView().setItems(rankingForTableView);
            }
            gamePanel.setMainResultLabel(hasWon ? "Victory" : "Defeat");
            gamePanel.getMainResultLabel().setStyle("-fx-text-fill: " + (hasWon ? "#2fff00" : "#c70303"));
            for (int i = 0; i < scores.length; i++) {
                gamePanel.getScores().get(i).setText(Integer.toString(scores[i]));
            }
            gamePanel.goFront(gamePanel.getEndGamePane());
        });
    }

    @Override
    public void showGamesList(List<String> lobbiesName, List<Integer> lobbiesCurrentConnectedClientsNumber, List<Integer> lobbiesMaxPlayersNum) {
        Launcher launcher = (Launcher) App.controller;
        for (int i = 0; i < lobbiesName.size(); i++) {
            launcher.lobbies.add(new Lobby(lobbiesName.get(i), lobbiesCurrentConnectedClientsNumber.get(i), lobbiesMaxPlayersNum.get(i)));
        }

    }

    @Override
    public void showWhiteMarble() {
    }

    @Override
    public void showLorenzoAction(String announcement) {
        Platform.runLater(() -> ((GamePanel) App.controller).showSoloAction(announcement));
    }

    @Override
    public void showTablePosition(int position) {

    }

    @Override
    public void showNicknameSet() {
        Platform.runLater(() -> App.out.setText("Your nickname has been set."));
        Launcher launcher = (Launcher) App.controller;
        launcher.setCurrentPane(launcher.createOrJoinPane);
    }

    @Override
    public void showGameJoined() {
        show("You joined the game.");
        Launcher launcher = (Launcher) App.controller;
        launcher.setCurrentPane(launcher.waitPane);
    }

    @Override
    public void showResume() {
        Launcher launcher = (Launcher) App.controller;
        Platform.runLater(() -> {
            launcher.getNicknameLabel().setText(getModel().getPlayerUsername());
            launcher.setCurrentPane(launcher.resumePane);
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4),
                    event -> App.setScene("board", "Masters of Renaissance")));
            timeline.play();
        });
    }

    @Override
    public void showCreateGame() {
        Launcher launcher = (Launcher) App.controller;
        launcher.setCurrentPane(launcher.createGamePane);
    }

    @Override
    public void showGameSet(int playersNum) {
        show("Your game has been set.");
        Launcher launcher = (Launcher) App.controller;
        launcher.setCurrentPane(launcher.waitPane);
    }

    @Override
    public void showWaitGameStart() {
        Launcher launcher = (Launcher) App.controller;
        Platform.runLater(() -> launcher.waitLabel.setText("Waiting the start of the game..."));
        launcher.setCurrentPane(launcher.waitPane);
    }

    @Override
    public void showGameStarted() {
        Platform.runLater(() -> App.setScene("board", "Masters of Renaissance"));
    }

    @Override
    public void showNewPlayer(String playerNick) {
        show(playerNick + " joined the game.");
    }

    @Override
    public void showPlayerLeft(String playerNick) {
        show(playerNick + " has disconnected.");
    }

    @Override
    public void showGetInitialResource() {
        show("Choose one resource to get");
        Platform.runLater(() -> {
            GamePanel controller = (GamePanel) App.controller;
            controller.getInitialResourcesButton().setDisable(false);
            controller.getInitialResourcesButton().setOpacity(1);
            controller.getTotalInitialResourcesAmount().setText(Integer.toString(getModel().getResourcesToGet()));
            controller.getInitialResourceIndex().setText("1");
            controller.goFront(controller.getGetInitialResourcesPane());
        });
    }

    @Override
    public void showGotResource(String resource) {
        show("A " + resource + " has been added to your hand. Remember to deploy it!");
    }

    @Override
    public void showResourceTaken() {
        show("Resource taken.");
    }

    @Override
    public void showCardBought() {
        show("Card bought successfully.");
        Platform.runLater(() -> {
            GamePanel controller = (GamePanel) App.controller;
            controller.closePopup(null);
            controller.getPaymentButton().setDisable(true);
            controller.getPaymentPositionQuest().setDisable(true);
            controller.getPaymentBox().setDisable(true);
            controller.getNextButton().fire();
        });
    }

    @Override
    public void showMarketUsed() {
        show("You have used the market: deploy or discard the resources in your hand.");
    }

    @Override
    public void showVaticanReport(int reportNum, boolean isPassed) {
        show("You "
        + (isPassed ? "passed" : "didn't pass")
        + " the vatican report number " + reportNum + ".");
    }

    @Override
    public void showLastTurns(String reason) {

    }

    @Override
    public void showConnectionLost() {
        Platform.runLater(() -> App.setScene("connectionLost", "Masters of Renaissance"));
    }

    @Override
    public void showTimeUp(boolean timeIsUp) {
        if (timeIsUp) {
            Platform.runLater(() -> ((GamePanel) App.controller).startTimeUp());
        } else {
            Platform.runLater(() -> ((GamePanel) App.controller).removeTimeUp());
        }
    }

}
